/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */

package com.agile.framework.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;

import com.agile.framework.utils.DiffPatch;
import com.agile.framework.utils.DiffPatch.Diff;
import com.agile.framework.utils.DiffPatch.Patch;
import com.agile.framework.utils.StringUtils;

@SuppressWarnings("all")  
public class CodeBuilder {  
      
    private static final String TEMPLATE_PATH = "templates";
    
	private SchemaHelper schema;
	private String templatePath;	
	private Template templateModel;
	private Template templateDao;
	private Template templateDaoInterface; 
    private Template templateServiceImpl; 
    private Template templateServiceInterface;
    private Template templateController;
	private Template templateValidator;
    private Template templateJspView;
    private Template templateTable;
    private Template templateTables;    
    
	public CodeBuilder() throws IOException {
		schema = new SchemaHelper();

		// 获取模板路径
        String path = new java.io.File( "." ).getCanonicalPath();        
        templatePath = path + "/src/main/resources/templates/"; 
        System.out.println("Template dir:" + path);
        
        // FreeMaker初始化
        Configuration config = new Configuration(); 
        FileTemplateLoader fileLoader = new FileTemplateLoader(new File(templatePath));  
        ClassTemplateLoader classLoader = new ClassTemplateLoader(getClass(), TEMPLATE_PATH);  
        TemplateLoader[] loaders = new TemplateLoader[] { fileLoader, classLoader };  
        MultiTemplateLoader multi = new MultiTemplateLoader(loaders);  
        config.setTemplateLoader(multi);          
        config.setObjectWrapper(new DefaultObjectWrapper());

        // 各个类的模板
        templateModel = config.getTemplate("model.ftl");
        templateDao = config.getTemplate("dao.ftl");
        templateDaoInterface = config.getTemplate("idao.ftl");
        templateServiceImpl = config.getTemplate("service.ftl");
        templateServiceInterface = config.getTemplate("iservice.ftl");
        templateController = config.getTemplate("controller.ftl");
		templateValidator = config.getTemplate("validator.ftl");
        templateJspView = config.getTemplate("jspview.ftl");        
        templateTable = config.getTemplate("table.ftl");
        templateTables = config.getTemplate("tables.ftl");
	}
	   
    /*** 
     * 将代码文本写入Java类文件 
     * @param filePath 文件路径
     * @param content  代码文本
     */ 
	public void writeJavaFile(String filePath, String content) {
		try {
			File file = new File(filePath);
			FileOutputStream outputStream = new FileOutputStream(file);
			Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.out.println("写文件出错");
		}
	}

    /*** 
     * 读取Java类文件的代码内容 
     * @param filePath 文件路径
     * @return 代码文本 
     */ 	
	public String readJavaFile(String filePath) {
		byte[] strBuffer = null;
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				FileInputStream in = new FileInputStream(file);
				int length = (int)file.length();
				strBuffer = new byte[length];
				in.read(strBuffer, 0, length);
				in.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读文件出错");
		}
		if (strBuffer == null)
			return "";
		return new String(strBuffer);
	}

    /*** 
     * 将产生的代码与以前的代码合并 
     * @param originalFile 原来的代码文件
     * @param generateFile 产生的代码文件
     */ 		
	public void mergeJavaFile(String originalFile, String generateFile) {
	    String originalCode = readJavaFile(originalFile);
	    String generateCode = readJavaFile(generateFile);
	    String contentMerge = "";
	    
	    if(StringUtils.isEmpty(originalCode)) {
	    	contentMerge = generateCode;
	    }else{	    	
		    DiffPatch merge = new DiffPatch();
			LinkedList<Patch> diffs = merge.patch_make(generateCode, originalCode);
			Object[] results = merge.patch_apply(diffs, generateCode);
			boolean[] boolArray = (boolean[]) results[1];
			contentMerge = (String)results[0];
	    }
	    
		writeJavaFile(originalFile, contentMerge);
	}

    /*** 
     * 将表名转成驼峰命名的类名 
     * @param tableName 数据库表名
     * @return 类名
     */ 
	public String getClassName(String tableName) {
    	// 去掉表名有前缀xxx_
    	String className = tableName;
    	int prefix = tableName.indexOf('_');
    	if (prefix > 0) {
    		className = tableName.substring(prefix+1);
    	}
    	// 将表名转成驼峰命名    	
    	return StringUtils.toCapitalizeCamelCase(className);
	}

    /*** 
     * 表的字段是否有日期类型 
     * @param columns 表字段对象
     */ 	
	public boolean hasDateType(List<ColumnProperty> columns) {
		boolean found = false;
		for (ColumnProperty column: columns) {
			if (column.sqlType == Types.DATE) {
				found = true;
			}
			else if (column.sqlType == Types.TIMESTAMP) {
				found = true;
			}
			else if (column.sqlType == Types.TIME) {
				found = true;
			}						
		}
		return found;
	}
	
	/**
	 * 获取表的主键的定义类型
	 * @param columns
	 * @return
	 */
	public String getPrimaryKeyType(List<ColumnProperty> columns) {
		String type = "";
		for (ColumnProperty column: columns) {
			if (column.primaryKey) {
				type = column.javaType.toString();
				int pos = type.lastIndexOf('.');
				type = type.substring(pos+1);
				break;
			}
		}
		return type;
	}
	 
    /*** 
     * 生成Tables类代码 
     * @param tableName
     * @throws Exception 
     */  
    public void generateTablesClass() throws Exception {
    	
		List tables = schema.getTables();
		for (int i=0; i<tables.size(); i++) {
			String name = (String)tables.get(i);
		}

	    // 设置模板需要的参数
	    Map<String, Object> model = new HashMap<String, Object>();
	    model.put("tables", tables);
		
	    // 生成文件的路径
	    String fileName = "Tables.java";
        String path = new java.io.File(".").getCanonicalPath();        
        path += "/src/main/java/com/agile/modules/database/";
	    File outputFile = new File(path + fileName);
	    System.out.println("Entity: " + path + fileName);
        
	    // 根据模板生成代码
	    FileOutputStream outputStream = new FileOutputStream(outputFile);
	    Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
	    templateTables.process(model, writer);
	    writer.flush(); 
	    writer.close(); 
    }  
    
    /*** 
     * 生成Table类代码 
     * @param tableName
     * @throws Exception 
     */  
    public void generateTableClass(String tableName) throws Exception {    	
    	// 获取类名和属性
    	String className = getClassName(tableName);
	    String fileName = tableName.toUpperCase() + ".java";
	    List<ColumnProperty> columns = schema.getColumns(tableName);
	    ColumnProperty primaryKey = schema.getPrimaryKeyProperty(columns);
	    
	    // 设置模板需要的参数
	    Map<String, Object> model = new HashMap<String, Object>();
	    model.put("hasDateType", hasDateType(columns));
	    model.put("className", className);
	    model.put("tableName", tableName);
	    model.put("primaryKey", primaryKey);
	    model.put("columns", columns);
	 
	    // 生成文件的路径
        String path = new java.io.File(".").getCanonicalPath();        
        path += "/src/main/java/com/agile/modules/database/";
	    File outputFile = new File(path + fileName);
	    System.out.println("Entity: " + path + fileName);
        
	    // 根据模板生成代码
	    FileOutputStream outputStream = new FileOutputStream(outputFile);
	    Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
	    templateTable.process(model, writer);
	    writer.flush(); 
	    writer.close(); 
    } 
    
    /*** 
     * 生成实体类代码 
     * @param tableName
     * @throws Exception 
     */  
    public void generateEntityClass(String tableName) throws Exception {
    	// 获取类名和属性
    	String className = getClassName(tableName);
	    String fileName = className + ".java";
	    List<ColumnProperty> columns = schema.getColumns(tableName);
	    ColumnProperty primaryKey = schema.getPrimaryKeyProperty(columns);
	    
	    // 设置模板需要的参数
	    Map<String, Object> model = new HashMap<String, Object>();
	    model.put("hasDateType", hasDateType(columns));
	    model.put("className", className);
	    model.put("tableName", tableName);
	    model.put("primaryKey", primaryKey);
	    model.put("columns", columns);
	 
	    // 生成文件的路径
        String path = new java.io.File(".").getCanonicalPath();        
        path += "/src/main/java/com/agile/model/";
	    File outputFile = new File(path + fileName);
	    System.out.println("Entity: " + path + fileName);
        
	    // 根据模板生成代码
	    FileOutputStream outputStream = new FileOutputStream(outputFile);
	    Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
	    templateModel.process(model, writer);
	    writer.flush(); 
	    writer.close(); 
    }  
      
    /*** 
     * 生成dao接口类代码 
     * @param tableName 
     * @throws Exception 
     */  
    public void generateDaoInterfaceClass(String tableName) throws Exception {
    	// 获取类名和属性
    	String className = getClassName(tableName);
	    List<ColumnProperty> columns = schema.getColumns(tableName);
	    ColumnProperty primaryKey = schema.getPrimaryKeyProperty(columns);
	    
	    // 生成文件的路径
        String path = new java.io.File(".").getCanonicalPath();        
        path += "/src/main/java/com/agile/dao/interfaces";
        
	    // 设置模板需要的参数
	    Map<String, Object> model = new HashMap<String, Object>();
	    model.put("className", className);
	    model.put("primaryKey", primaryKey);
	    model.put("primaryKeyType", getPrimaryKeyType(columns));
	    model.put("columns", columns);

	    // 根据模板生成代码        
        String tempFileName = path + className + "Dao.temp";
	    File tempFile = new File(tempFileName);
	    FileOutputStream outputStream = new FileOutputStream(tempFile);
	    Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
	    templateDaoInterface.process(model, writer);
	    writer.flush(); 
	    writer.close();
	    
	    // 合并生成的代码	    
	    String classFileName = path + className + "Dao.java";
	    System.out.println("Dao interface: " + classFileName);
	    mergeJavaFile(classFileName, tempFileName);
	    tempFile.delete();
    } 
    
    /*** 
     * 生成dao接口实现类代码 
     * @param tableName 
     * @throws Exception 
     */  
    public void generateDaoImplementClass(String tableName) throws Exception {
    	// 获取类名和属性
    	String className = getClassName(tableName);	    
	    List<ColumnProperty> columns = schema.getColumns(tableName);

	    // 生成文件的路径
        String path = new java.io.File(".").getCanonicalPath();        
        path += "/src/main/java/com/agile/dao/hibernate/";
	    
	    // 设置模板需要的参数
	    Map<String, Object> model = new HashMap<String, Object>();
	    model.put("className", className);
	    model.put("columns", columns);
	    
  	    // 根据模板生成代码
	    String tempFileName = path + className + "DaoImpl.temp";
	    File tempFile = new File(tempFileName);
	    FileOutputStream outputStream = new FileOutputStream(tempFile);
	    Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
	    templateDao.process(model, writer);
	    writer.flush(); 
	    writer.close();
	    
	    // 合并生成的代码
	    String classFileName = path + className + "DaoImpl.java";
	    System.out.println("Dao implement: " + classFileName);
	    mergeJavaFile(classFileName, tempFileName);
	    tempFile.delete();
    }
      
      
    /*** 
     * 创建Service的接口类 
     * generateServiceClass 
     * @param tableName 
     */  
    public void generateServiceInterfaceClass(String tableName) throws Exception {
    	// 获取类名和属性
    	String className = getClassName(tableName);
	    List<ColumnProperty> columns = schema.getColumns(tableName);

	    // 生成文件的路径
        String path = new java.io.File(".").getCanonicalPath();        
        path += "/src/main/java/com/agile/service/interfaces/";

	    // 设置模板需要的参数
	    Map<String, Object> model = new HashMap<String, Object>();
	    model.put("className", className);
	    model.put("columns", columns);
	    
	    // 根据模板生成代码
	    String tempFileName = path + className + "Service.temp";
        File tempFile = new File(tempFileName);	    
	    FileOutputStream outputStream = new FileOutputStream(tempFile);
	    Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
	    templateServiceInterface.process(model, writer);
	    writer.flush(); 
	    writer.close();
	    
	    // 合并生成的代码
	    String classFileName = path + className + "Service.java";
	    System.out.println("Dao implement: " + classFileName);
	    System.out.println("Service interface: " + classFileName);
	    mergeJavaFile(classFileName, tempFileName);
	    tempFile.delete();	    
    }  

    /*** 
     * 创建Service的实现类 
     * generateServiceClass 
     * @param tableName 
     */  
    public void generateServiceImplementClass(String tableName) throws Exception {
    	// 获取类名和属性
    	String className = getClassName(tableName);
	    List<ColumnProperty> columns = schema.getColumns(tableName);
	    
	    // 生成文件的路径
        String path = new java.io.File(".").getCanonicalPath();        
        path += "/src/main/java/com/agile/service/hibernate/";
	    	    
        // 设置模板需要的参数
	    Map<String, Object> model = new HashMap<String, Object>();
	    model.put("className", className);
	    model.put("columns", columns);
	    
  	    // 根据模板生成代码        
	    String tempFileName = path + className + "ServiceImpl.temp";
	    File tempFile = new File(tempFileName);
	    FileOutputStream outputStream = new FileOutputStream(tempFile);
	    Writer writer = new OutputStreamWriter(outputStream, "UTF-8");	    
	    templateServiceImpl.process(model, writer);
	    writer.flush(); 
	    writer.close();

	    // 合并生成的代码
	    String classFileName = path + className + "ServiceImpl.java";
	    System.out.println("Service implementation: " + classFileName);
	    mergeJavaFile(classFileName, tempFileName);
	    tempFile.delete();
    }
    
    /*** 
     * 创建控制层类Controller 
     * @param tableName 
     */  
    public void generateControllerClass(String tableName)  throws Exception {
    	// 获取类名和属性
    	String className = getClassName(tableName);
	    List<ColumnProperty> columns = schema.getColumns(tableName);
	    ColumnProperty primaryKey = schema.getPrimaryKeyProperty(columns);
	    
	    // 生成文件的路径
        String path = new java.io.File(".").getCanonicalPath();        
        path += "/src/main/java/com/agile/controller/";
	    
	    // 设置模板需要的参数
	    Map<String, Object> model = new HashMap<String, Object>();
	    model.put("className", className);
	    model.put("columns", columns);
	    model.put("primaryKey", primaryKey);

	    // 根据模板生成代码
	    String tempFileName = path + className + "Controller.temp";
	    File tempFile = new File(tempFileName);
	    FileOutputStream outputStream = new FileOutputStream(tempFile);
	    Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
	    templateController.process(model, writer);
	    writer.flush(); 
	    writer.close();
	    
	    // 合并生成的代码
	    String classFileName = path + className + "Controller.java";
	    System.out.println("Controller implementation: " + classFileName);
	    mergeJavaFile(classFileName, tempFileName);
	    tempFile.delete();	    
    }


	/***
	 * 创建Validator
	 * @param tableName
	 */
	public void generateValidatorClass(String tableName)  throws Exception {
		// 获取类名和属性
		String className = getClassName(tableName);
		List<ColumnProperty> columns = schema.getColumns(tableName);
		ColumnProperty primaryKey = schema.getPrimaryKeyProperty(columns);

		// 生成文件的路径
		String path = new java.io.File(".").getCanonicalPath();
		path += "/src/main/java/com/agile/validator/";

		// 设置模板需要的参数
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("className", className);
		model.put("columns", columns);
		model.put("primaryKey", primaryKey);

		// 根据模板生成代码
		String tempFileName = path + className + "Validator.temp";
		File tempFile = new File(tempFileName);
		FileOutputStream outputStream = new FileOutputStream(tempFile);
		Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
		templateValidator.process(model, writer);
		writer.flush();
		writer.close();

		// 合并生成的代码
		String classFileName = path + className + "Validator.java";
		System.out.println("Validator implementation: " + classFileName);
		mergeJavaFile(classFileName, tempFileName);
		tempFile.delete();
	}

	/***
     * 创建JSP页面. 
     * 以bootstrap3.x为主. 
     * @param tableName 
     */  
    public void generateJspView(String tableName)throws Exception{    
    	String className = getClassName(tableName);
	    String fileName = className + "Service.java";
	    List<ColumnProperty> columns = schema.getColumns(tableName);
	    
	    // Build the entity for FreeMarker
	    Map<String, Object> model = new HashMap<String, Object>();
	    model.put("class", className);
	 
	    // FreeMarker process the entity with the template
	    File outputFile = new File(fileName);
	    FileOutputStream outputStream = new FileOutputStream(outputFile);
	    Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
	    templateJspView.process(model, writer);
	    writer.flush(); 
	    writer.close();            
    }  

    /*** 
     * 创建所有模板代码. 
     * @param 
     */      
    public void generateAll() {
    	try {
    		// 生成Tables.java类
    		generateTablesClass();
    		
    		// 生产各个表相应的类
			List tables = schema.getTables();
			for (int i=0; i<tables.size(); i++) {
				String name = (String)tables.get(i);
				generateEntityClass(name);
				generateDaoInterfaceClass(name);
				generateDaoImplementClass(name);
				generateServiceInterfaceClass(name);
				generateServiceImplementClass(name);
				generateControllerClass(name);
				generateValidatorClass(name);
				//generateJspView(name);
				generateTableClass(name);
				System.out.println("Code generate done.");
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }  
    
}  