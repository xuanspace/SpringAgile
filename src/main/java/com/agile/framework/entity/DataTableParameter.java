/*
 * Copyright (C) 2016-2017 Spring Agile. All rights reserved.
 * Licensed under the Apache License, Version 2.0
 */

package com.agile.framework.entity;

import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/* 
 * DataTable Ajax请求参数
 *   https://datatables.net
 */
public class DataTableParameter {

	// 请求序号
	private Integer sEcho;

	// 显示的列数
	private Integer iColumns;

	// 列字段名
	private String sColumns;

	// 显示起始位置
	private Integer iDisplayStart;

	// 显示的行数(页面大小)
	private Integer iDisplayLength;

	// 全局搜索字符串
	private String sSearch;
	
	// 全局搜索是否正则式
	private Boolean bRegex;
		
	// 排序的列数
	private Integer iSortingCols;

	// 表列设置
	private Column columns[];

	// 排序列设置
	private Sort sorts[];

	// 搜索条件
	private SearchCondition searchs[];
	
	// 列参数
	public class Column {
		String mDataProp;		// 属性		
		String sSearch;			// 搜索的字符串
		Boolean bRegex;			// 是否使用正则搜索
		Boolean bSearchable;    // 客服端设置为可搜索
		Boolean bSortable;		// 客服端设置为可排序
		
		public String getmDataProp() {
			return mDataProp;
		}
		public void setmDataProp(String mDataProp) {
			this.mDataProp = mDataProp;
		}
		public String getsSearch() {
			return sSearch;
		}
		public void setsSearch(String sSearch) {
			this.sSearch = sSearch;
		}
		public Boolean getbRegex() {
			return bRegex;
		}
		public void setbRegex(Boolean bRegex) {
			this.bRegex = bRegex;
		}
		public Boolean getbSearchable() {
			return bSearchable;
		}
		public void setbSearchable(Boolean bSearchable) {
			this.bSearchable = bSearchable;
		}
		public Boolean getbSortable() {
			return bSortable;
		}
		public void setbSortable(Boolean bSortable) {
			this.bSortable = bSortable;
		}		
	}
	
	// 排序参数
	public class Sort {
		Integer iSortCol; 		// 排序列索引号
		String sSortDir;		// 排序方向,Asc或Desc
		String sSortName; 		// 排序列名
		
		public Integer getiSortCol() {
			return iSortCol;
		}
		public void setiSortCol(Integer iSortCol) {
			this.iSortCol = iSortCol;
		}
		public String getsSortDir() {
			return sSortDir;
		}
		public void setsSortDir(String sSortDir) {
			this.sSortDir = sSortDir;
		}
		public String getsSortName() {
			return sSortName;
		}
		public void setsSortName(String sSortName) {
			this.sSortName = sSortName;
		}		
	}

    public DataTableParameter() {

    }

        /**
         * 获取DataTable请求参数
         * @param request
         */
	public DataTableParameter(HttpServletRequest request) {		
		String json = request.getParameter("aoData");
		if (json != null) {
			parseAoData(json);
		}
	}	

    /**
     * 获取aoData参数各个字段
     * @param aoData DataTable的请求json字符串
     */ 	
	public void parseAoData(String aoData) {
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		getTableParameters(jsonArray);
		getColumnParameters(jsonArray);
		getSortParameters(jsonArray);
	}

    /**
     * 获取DataTable全局参数设置
     * @param jsonArray json数组
     */ 		
	private void getTableParameters(JSONArray jsonArray) {
		// 获取全局参数设置
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject obj = (JSONObject) jsonArray.get(i);
			String name = (String)obj.get("name");
			
			// 表分页设置			
			if (name.equals("sEcho"))
				sEcho = obj.getInt("value");
			else if (name.equals("iDisplayStart"))
				iDisplayStart = obj.getInt("value");
			else if (name.equals("iDisplayLength"))
				iDisplayLength = obj.getInt("value");
			
			// 表字段集合串
			else if (name.equals("iColumns"))
				iColumns = obj.getInt("value");
			else if (name.equals("sColumns"))
				sColumns = obj.get("value").toString();
			
			// 全局搜索设置
			else if (name.equals("sSearch"))
				sSearch = obj.get("value").toString();
			else if (name.equals("bRegex"))
				bRegex = obj.getBoolean("value");			
			else if (obj.get("name").equals("iSortingCols"))
				iSortingCols = obj.getInt("value");
			
			// 额外搜索条件
			else if (obj.get("name").equals("extra")) {
				String jsonString = obj.getString("value");
				getSearchParameters(jsonString);
			}
		}		
	}

    /**
     * 获取DataTable各列参数设置
     * @param jsonArray json数组
     */ 			
	private void getColumnParameters(JSONArray jsonArray) {
		// 创建列表参数设置
		if (iColumns != null) {			
			columns = new Column[iColumns];
			for (int i=0;i<iColumns;i++)
				columns[i] = new Column(); 
		}
		else
			return;
		
		// 获取各列表参数设置
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject obj = (JSONObject) jsonArray.get(i);
			String name = (String)obj.get("name");

			// 属性字符串
			if (name.startsWith("mDataProp_")) {
				Integer index = getStringPostfixValue(name);
				if (index != null && index < columns.length) {
					columns[index].mDataProp = obj.getString("value");					
				}
			}
			// 搜索字符串
			else if (name.startsWith("sSearch_")) {
				Integer index = getStringPostfixValue(name);
				if (index != null && index < columns.length) {
					columns[index].sSearch = obj.getString("value");
				}
			}			
			// 是否正则
			else if (name.startsWith("bRegex_")) {
				Integer index = getStringPostfixValue(name);
				if (index != null && index < columns.length) {
					columns[index].bRegex = obj.getBoolean("value");
				}
			}
			// 是否可搜索
			else if (name.startsWith("bSearchable_")) {
				Integer index = getStringPostfixValue(name);
				if (index != null && index < columns.length) {
					columns[index].bSearchable = obj.getBoolean("value");
				}
			}
			// 是否可排序
			else if (name.startsWith("bSortable_")) {
				Integer index = getStringPostfixValue(name);
				if (index != null && index < columns.length) {
					columns[index].bSortable = obj.getBoolean("value");
				}
			}			
		}
	}

    /**
     * 获取DataTable排序参数设置
     * @param jsonArray json数组
     */ 				
	private void getSortParameters(JSONArray jsonArray) {
		// 创建列表排序数组
		if (iSortingCols != null){ 
			sorts = new Sort[iSortingCols];
			for (int i=0; i<iSortingCols; i++)
				sorts[i] = new Sort(); 			
		}
		else
			return;
		
		// 获取列表名数组
		String[] columnNames = sColumns.split(",");
		
		// 获取排序参数
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject obj = (JSONObject) jsonArray.get(i);
			String name = (String)obj.get("name");

			// 排序字段名和索引
			if (name.startsWith("iSortCol_")) {
				Integer index = getStringPostfixValue(name);
				if (index != null && index < sorts.length) {
					sorts[index].iSortCol = obj.getInt("value");
					if (columnNames !=null && index < columnNames.length)
						sorts[index].sSortName = columnNames[index];
				}
			}
			// 排序方向Asc或Desc
			else if (name.startsWith("sSortDir_")) {
				Integer index = getStringPostfixValue(name);
				if (index != null && index < sorts.length) {
					sorts[index].sSortDir = obj.getString("value");
				}
			}
		}
	}
	
    /**
     * 获取DataTable搜索参数设置
     * @param jsonString json数组
     */ 			
	private void getSearchParameters(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		searchs = new SearchCondition[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject obj = (JSONObject) jsonArray.get(i);			
			searchs[i] = new SearchCondition();
			searchs[i].setFieldName(obj.getString("name"));	
			searchs[i].setFieldType(obj.getString("type"));
			searchs[i].setFieldValue(obj.getString("value"));
			searchs[i].setFieldOperator(obj.getString("operator"));
		}
	}
	
    /**
     * 获取字符串_后面数值
     * @param name 字符串
     */ 					
    private Integer getStringPostfixValue(String name) {
    	String substr = name.substring(name.lastIndexOf('_')+1);
    	if (substr != null) {
    		 return Integer.parseInt(substr);
    	}
    	return null;
    }

    
	public Integer getsEcho() {
		return sEcho;
	}

	public void setsEcho(Integer sEcho) {
		this.sEcho = sEcho;
	}

	public Integer getiColumns() {
		return iColumns;
	}

	public void setiColumns(Integer iColumns) {
		this.iColumns = iColumns;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	public Integer getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(Integer iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public Integer getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(Integer iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	public Boolean getbRegex() {
		return bRegex;
	}

	public void setbRegex(Boolean bRegex) {
		this.bRegex = bRegex;
	}

	public Integer getiSortingCols() {
		return iSortingCols;
	}

	public void setiSortingCols(Integer iSortingCols) {
		this.iSortingCols = iSortingCols;
	}

	public Column[] getColumns() {
		return columns;
	}

	public void setColumns(Column[] columns) {
		this.columns = columns;
	}

	public Sort[] getSorts() {
		return sorts;
	}

	public void setSorts(Sort[] sorts) {
		this.sorts = sorts;
	}

	public SearchCondition[] getSearchs() {
		return searchs;
	}

	public void setSearchs(SearchCondition[] searchs) {
		this.searchs = searchs;
	}

	public String toSql(Class<?> cls) {
		String sql = "";
		return sql;
	}	
}
