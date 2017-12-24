package com.agile.framework.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileParser {

	private List<String> importLines;
	private String classContent;
	private int braceCount;
	private BufferedReader bufferedReader;
	private boolean isEatingComment;
	
	public FileParser() {
		importLines = new ArrayList<String>();
		classContent = "";
		braceCount = 0;
		isEatingComment = false;
	}

	public List<String> getImportLines() {
		return importLines;
	}

	public String getClassContent() {
		return classContent;
	}
	
	/*
	 * 扫描代码文件
	 *    获取class或者interface的import语句及其{}里的代码
	 * @return 
	 */	
	public void parseFilie(String filePath) {
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				bufferedReader = new BufferedReader(read);
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {					
					parseImportLine(line);
					parseClassContent(line);
				}
				read.close();
			} else {
				System.out.println("Not found:" + filePath);
			}
		} 
		catch (Exception e) {
			System.out.println("Read file error!");
			e.printStackTrace();
		}
	}
	
	/*
	 * 获取class或者interface的import语句
	 * @return 
	 */		
	public void parseImportLine(String strline) {
		String line = eraseComment(strline);
		int start = line.indexOf("import");
		if (start != -1) {
			int end = line.lastIndexOf(";");
			if (end == -1)
				end = line.length() - 1;
			importLines.add("import " + line.substring(start+6,end).trim() + ";");
		}
	}
	
	/*
	 * 获取class或者interface代码块
	 * @return 
	 */	
	public void parseClassContent(String strline) throws IOException {		
		String line = eraseComment(strline);
		if (line.indexOf("class") != -1 || 
			line.indexOf("interface") != -1) 
		{
			findClassLeftBrace(strline);
		}
	}
	
	/*
	 * 找到class或者interface左括号
	 * @return 返回左括号所在的位置
	 */
	public int findClassLeftBrace(String strline) throws IOException{
		int position = -1;
		String line = "";
		
		if (strline != null)
		{
			do{
				// 查找类开始的{
				line = eraseComment(strline);
				position = line.indexOf("{");
				if (position != -1) {
					braceCount = 1; 
					
					// 取类的{后面的内容
					line = strline.substring(position + 1);
					if (isBlankLine(line)) {
						line = bufferedReader.readLine();
					}
					
					// 继续查找类的 }
					position = findClassRightBrace(line);
					
					// 跳出类括号查找
					break;
				}
			}while ((strline = bufferedReader.readLine()) != null);
		}
		return position;
	}

	/*
	 * 找到class或者interface右括号
	 * @return 返回右括号所在的位置
	 */	
	public int findClassRightBrace(String strline) throws IOException{
		int position = -1;
		String line = "";
		
		if (strline != null) 
		{
			do {
				line = eraseComment(strline);
				position = getClassEndBraceAt(line);
				if (position == -1) {
					classContent += strline;
					classContent += "\r\n";
				}else{
					// 找到class右括号}
					if (position > 0) {
						classContent += strline.substring(0, position);
					}					
					// 去掉最后的换行符
					if (classContent.endsWith("\r\n")) {
						classContent = classContent.substring(0, classContent.length()-2);
					}
					break;
				}			
			}while ((strline = bufferedReader.readLine()) != null);
		}
		return position;
	}
	
	/*
	 * 对左右括号计数,如果归零则遇到class结束括号
	 * @return -1 行内没有class结束括号
	 * 		  >=0 class结束括号所在位置
	 */
	public int getClassEndBraceAt(String line) {
		for(int i=0; i<line.length() ;i++) {
			char c = line.charAt(i);
			if (c == '{') {
				braceCount++;
			}
			else if (c == '}') {
				braceCount--;
				if (braceCount == 0) {
					return i;
				}
			}
		}
		return -1;
	}
	
	/*
	 * 是不是空行,不包含换行回车符
	 * @return 
	 */
	public boolean isBlankLine(String line) {
		for(int i=0; i<line.length() ;i++) {
			if (line.charAt(i) != ' ' && line.charAt(i) != '\t' )
				return false;
		}
		return true;
	}

	/*
	 * 将行内的注释块变成一个空格
	 * @return 
	 */	
	public String eatComment(String line) {
		String str = "";		
		for(int i=0; i<line.length() ;i++) {
			char c = line.charAt(i);
			
			if (isEatingComment) {
				if (c == '*') {
					if (i+1 < line.length()) {
						if (line.charAt(i+1) == '/') {
							isEatingComment = false;
							i++;
							continue;
						}
					}
				}
			}
			else if (c == '/') {
				if (i+1 < line.length()) {
					// 单行注释
					if (line.charAt(i+1) == '/')
						break;
					// 多行注释
					if (line.charAt(i+1) == '*') {
						str += " ";
						isEatingComment = true;
						i++;
					}
				}
			}
			
			if (!isEatingComment)
			str += c;
		}
		return str;
	}
	
	/*
	 * 将行内的注释的每个字符变成空格
	 * @return 
	 */	
	public String eraseComment(String line) {
		String str = "";		
		boolean doubleSlash = false;
		
		for(int i=0; i<line.length() ;i++) {
			char c = line.charAt(i);
						
			if (isEatingComment) {
				if (doubleSlash) 
					;
				else if (c == '*') {
					if (i+1 < line.length()) {
						if (line.charAt(i+1) == '/') {
							isEatingComment = false;
							doubleSlash = true;
							str += "  ";
							i+=1;
							continue;
						}
					}
				}
			}
			else if (c == '/') {
				if (i+1 < line.length()) {
					char n = line.charAt(i+1);
					if (n == '/' || n == '*') {
						isEatingComment = true;
						if (n == '/')
							doubleSlash = true;						
						str += "  ";
						i+=1;
						continue;
					}
				}
			}
			
			if (!isEatingComment)
				str += c;
			else
				str += " ";
		}
		
		if (doubleSlash)
			isEatingComment = false;
		return str;
	}
	
	/*
	 * 获取Token后面的字符串
	 * @return 
	 */	
	public String getTokenEndString(String line, String token) {
		String substring = "";
		int pos = line.indexOf(token);
		if (pos != -1) {
			substring = line.substring(pos+1);
		}
		return substring;
	}
	
	
	public List<String> mergeImports(List<String> otherImports) {
		Iterator<String> it = importLines.iterator();
		while (it.hasNext()) {
			String me = it.next();
			if (otherImports.contains(me)) {
				it.remove();
			}
		}
		return importLines;
	}

}
