package com.agile.framework.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.hibernate.Query;

import com.agile.framework.utils.StringUtils;

public class SearchMap implements Iterable {
	
	public static final String TYPE_LIKE = "like";
	private static final String TYPE_NOT_LIKE = "not like";
	public static final String TYPE_EQUAL = "=";
	private static final String TYPE_NOT_EQUAL = "<>";
	public static final String TYPE_BETWEEN = "TYPE_BETWEEN";
	public static final String TYPE_GREATER = ">";
	public static final String TYPE_GREATER_EQUAL = ">=";
	public static final String TYPE_LESS = "<";
	public static final String TYPE_LESS_EQUAL = "<=";
	private static final String TYPE_NOT_IN = "not in";
	private static final String TYPE_IN = "in";
	public static final String ORDER_DESC = "DESC";
	public static final String ORDER_ASC = "ASC";
	
	private List<String[]> orderList = new ArrayList<String[]>();
	private List<SearchParameter> searchParameterList = new ArrayList<SearchParameter>();
	private Map<String, Object> searchParameterMap = new HashMap<String, Object>();
	private Map nameMap = new HashMap();
	private String aliasTemplete;

	public void addOrder(String order, String orderType) {
		this.orderList.add(new String[] { order, orderType });
	}

	public void addOrder(String order) {
		addOrder(order, null);
	}

	public Object getValue(String key) {
		Object returnValue = null;
		Object object = this.searchParameterMap.get(key);
		if ((object instanceof SearchParameter)) {
			returnValue = ((SearchParameter) object).getValue();
		} else if ((object instanceof SearchParameter[])) {
			SearchParameter[] searchParameter = (SearchParameter[]) object;
			Object[] returnValues = new Object[searchParameter.length];
			for (int i = 0; i < searchParameter.length; i++) {
				returnValues[i] = searchParameter[i].getValue();
			}
			returnValue = returnValues;
		}
		return returnValue;
	}

	public void remove(String key) {
		Object object = this.searchParameterMap.get(key);
		if ((object instanceof SearchParameter)) {
			this.searchParameterList.remove(object);
		} else if ((object instanceof SearchParameter[])) {
			SearchParameter[] searchParameter = (SearchParameter[]) object;
			for (int i = 0; i < searchParameter.length; i++) {
				this.searchParameterList.remove(searchParameter[i]);
			}
		}
		this.searchParameterMap.remove(key);
	}

	public class SearchParameter {
		private String key;
		private String name;
		private Object value;
		private String type;
		private String alias;

		public SearchParameter(String key, String name, Object value, String type) {
			this.key = key;
			this.name = name;
			this.value = value;
			this.type = type;
		}

		public SearchParameter(String key, String name, Object value, String type, String alias) {
			this.key = key;
			this.name = name;
			this.value = value;
			this.type = type;
			this.alias = alias;
		}

		public String toHQL() {
			StringBuffer sb = new StringBuffer();
			if (this.type.equals("TYPE_BETWEEN")) {
				sb.append("");
			} else if ((this.type.equals("in")) || (this.type.equals("not in"))) {
				sb.append(this.key);
				sb.append(" ");
				sb.append(this.type);
				sb.append("(").append(this.value).append(")");
			} else {
				if (this.value != null) {
					if ((this.value instanceof String)) {
						String tempValue = StringUtils.trimToEmpty((String) this.value).replaceAll("%", "");
						if (("like".equals(this.type)) && (tempValue.length() == 0)) {
							return null;
						}
						sb.append(this.key);
					} else {
						sb.append(this.key);
					}
				}
				sb.append(" ");
				sb.append(this.type);
				sb.append(":");
				sb.append(this.name);
			}
			return sb.toString();
		}

		public void setQuery(Query query) {
			if ((this.type.equals("in")) || (this.type.equals("not in"))) {
				return;
			}
			if (this.value != null) {
				if ((this.value instanceof String)) {
					String tempValue2 = StringUtils.trimToEmpty((String) this.value).replaceAll("%", "");
					if (("like".equals(this.type)) && (tempValue2.length() == 0)) {
						return;
					}
					String tempValue = (String) this.value;

					query.setString(this.name, tempValue);
				} else {
					query.setParameter(this.name, this.value);
				}
			}
		}

		public String getKey() {
			return this.key;
		}

		public String getType() {
			return this.type;
		}

		public Object getValue() {
			return this.value;
		}

		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAlias() {
			return this.alias;
		}
	}

	public void like(String key, Object value) {
		add(key, value, "like", null);
	}

	public void like(String key, Object value, String alias) {
		add(key, value, "like", alias);
	}

	public void notLike(String key, Object value) {
		add(key, value, "not like", null);
	}

	public void notLike(String key, Object value, String alias) {
		add(key, value, "not like", alias);
	}

	public void in(String key, String value) {
		add(key, value, "in", null);
	}

	public void notIn(String key, String value) {
		add(key, value, "not in", null);
	}

	public void between(String key, String valueBegin, String valueEnd) {
		ge(key, valueBegin);
		le(key, valueEnd);
	}

	public void between(String key, String valueBegin, String valueEnd, String alias) {
		ge(key, valueBegin, alias);
		le(key, valueEnd, alias);
	}

	public void eq(String key, Object value) {
		add(key, value, "=", null);
	}

	public void eq(String key, Object value, String alias) {
		add(key, value, "=", alias);
	}

	public void ge(String key, Object value) {
		add(key, value, ">=", null);
	}

	public void ge(String key, Object value, String alias) {
		add(key, value, ">=", alias);
	}

	public void gt(String key, Object value) {
		add(key, value, ">", null);
	}

	public void gt(String key, Object value, String alias) {
		add(key, value, ">", alias);
	}

	public void le(String key, Object value) {
		add(key, value, "<=", null);
	}

	public void le(String key, Object value, String alias) {
		add(key, value, "<=", alias);
	}

	public void notEq(String key, Object value) {
		add(key, value, "<>", null);
	}

	public void notEq(String key, Object value, String alias) {
		add(key, value, "<>", alias);
	}

	public void lt(String key, Object value) {
		add(key, value, "<", null);
	}

	public void lt(String key, Object value, String alias) {
		add(key, value, "<", alias);
	}

	public String getAliasTemplete() {
		return this.aliasTemplete;
	}

	public void setAliasTemplete(String aliasTemplete) {
		this.aliasTemplete = ("( " + aliasTemplete + " )");
	}

	private void add(String key, Object value, String type, String alias) {
		String tempKey = key;
		if ((tempKey != null) && (tempKey.indexOf(".") >= 0)) {
			tempKey = tempKey.substring(tempKey.lastIndexOf(".") + 1);
		}
		Integer count = (Integer) this.nameMap.get(tempKey);
		if ((count != null) && (count.intValue() > 0)) {
			this.nameMap.put(tempKey, new Integer(count.intValue() + 1));
			tempKey = tempKey + (count.intValue() + 1);
		} else {
			this.nameMap.put(tempKey, new Integer(1));
		}
		SearchParameter searchParameter = new SearchParameter(key, tempKey, value, type, alias);
		this.searchParameterList.add(searchParameter);

		Object searchParameterObject = this.searchParameterMap.get(key);
		if (searchParameterObject != null) {
			if ((searchParameterObject instanceof SearchParameter)) {
				SearchParameter[] tempSearchParameter = new SearchParameter[2];
				tempSearchParameter[0] = ((SearchParameter) searchParameterObject);
				tempSearchParameter[1] = searchParameter;
			} else if ((searchParameterObject instanceof SearchParameter[])) {
				SearchParameter[] oldSearchParameter = (SearchParameter[]) searchParameterObject;
				SearchParameter[] tempSearchParameter = new SearchParameter[oldSearchParameter.length + 1];
				for (int i = 0; i < oldSearchParameter.length; i++) {
					tempSearchParameter[i] = oldSearchParameter[i];
				}
				tempSearchParameter[oldSearchParameter.length] = searchParameter;
			}
		} else {
			this.searchParameterMap.put(key, searchParameter);
		}
	}

	public Iterator iterator() {
		return this.searchParameterList.iterator();
	}

	public String toHQL() {
		StringBuffer sb = new StringBuffer("");
		Iterator it = iterator();
		Map<String, String> templeteHQL = new HashMap();
		while (it.hasNext()) {
			SearchParameter searchParameter = (SearchParameter) it.next();
			Object value = searchParameter.getValue();
			if (value != null) {
				if (((value instanceof String)) && (((String) value).length() <= 0)) {
					if ((StringUtils.isNotEmpty(searchParameter.getAlias()))
							&& (getAliasTemplete().indexOf(searchParameter.getAlias()) != -1)) {
						templeteHQL.put(searchParameter.getAlias(), " 1 = 1");
					}
				} else if ((StringUtils.isEmpty(getAliasTemplete()))
						|| (StringUtils.isEmpty(searchParameter.getAlias()))) {
					String hql = searchParameter.toHQL();
					if ((hql != null) && (hql.length() > 0)) {
						if (sb.toString().length() > 0) {
							sb.append(" and ");
						}
						sb.append(hql);
					}
				} else if (getAliasTemplete().indexOf(searchParameter.getAlias()) == -1) {
					String hql = searchParameter.toHQL();
					if ((hql != null) && (hql.length() > 0)) {
						if (sb.toString().length() > 0) {
							sb.append(" and ");
						}
						sb.append(hql);
					}
				} else {
					templeteHQL.put(searchParameter.getAlias(), searchParameter.toHQL());
				}
			}
		}
		if ((StringUtils.isNotEmpty(getAliasTemplete())) && (!templeteHQL.isEmpty())) {
			Iterator<String> aliasKeys = templeteHQL.keySet().iterator();
			String tempHQL = getAliasTemplete();
			while (aliasKeys.hasNext()) {
				String tempAlias = (String) aliasKeys.next();
				if (StringUtils.isEmpty((String) templeteHQL.get(tempAlias))) {
					tempHQL = tempHQL.replaceAll(Pattern.quote(tempAlias), " 1 = 1 ");
				} else {
					tempHQL = tempHQL.replaceAll(Pattern.quote(tempAlias), (String) templeteHQL.get(tempAlias));
				}
			}
			if (sb.toString().length() > 0) {
				sb.append(" and ");
			}
			sb.append(tempHQL);
		}
		return sb.toString();
	}

	public void setQuery(Query query) {
		Iterator it = iterator();
		while (it.hasNext()) {
			SearchParameter searchParameter = (SearchParameter) it.next();
			Object value = searchParameter.getValue();
			if (value != null) {
				if ((value instanceof String)) {
					if (((String) value).length() > 0) {
						searchParameter.setQuery(query);
					}
				} else {
					searchParameter.setQuery(query);
				}
			}
		}
	}

	public List<String[]> getOrderList() {
		return this.orderList;
	}
}
