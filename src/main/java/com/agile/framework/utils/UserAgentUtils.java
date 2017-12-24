package com.agile.framework.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

public class UserAgentUtils {

	/**
	 * 获取客户端浏览器类型、编码下载文件名
	 *
	 * @param request
	 * @param fileName
	 * @return String
	 */
	public static String encodeFileName(HttpServletRequest request, String fileName) {
	    String userAgent = request.getHeader("User-Agent");
	    String rtn = "";
	    try {
	        String codedFilename = URLEncoder.encode(fileName, "UTF8");
	        // 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
	        rtn = "filename=\"" + codedFilename + "\"";
	        if (userAgent != null) {
	            userAgent = userAgent.toLowerCase();
	            // IE浏览器，只能采用URLEncoder编码
	            if (userAgent.indexOf("msie") != -1) {
	                rtn = "filename=\"" + codedFilename + "\"";
	            }
	            // Opera浏览器只能采用filename*
	            else if (userAgent.indexOf("opera") != -1) {
	                rtn = "filename*=UTF-8''" + codedFilename;
	            }
	            // Safari浏览器，只能采用ISO编码的中文输出
	            else if (userAgent.indexOf("safari") != -1) {
	                rtn = "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\"";
	            }
	            // Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
	            else if (userAgent.indexOf("applewebkit") != -1) {
	            	codedFilename = MimeUtility.encodeText(fileName, "UTF8", "B");
	                rtn = "filename=\"" + codedFilename + "\"";
	            }
	            // FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
	            else if (userAgent.indexOf("mozilla") != -1) {
	                rtn = "filename*=UTF-8''" + codedFilename;
	            }
	        }
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }
	    return rtn;
	}
	
}
