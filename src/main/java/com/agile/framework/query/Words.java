package com.agile.framework.query;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据库保留字
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0   
 */
public class Words {
    static String SELECT = "select";
    static String INSERT = "insert";
    static String UPDATE = "update";
    static String DELETE = "delete";
    static String FROM = "from";
    static String WHERE = "where";
    static String DISTINCT = "distinct";
    static String AND = "and";
    static String OR = "or";
    static String IN = "in";
    static String BETWEEN = "between";
    static String LIKE = "like";
    static String ORDER_BY = "order by";
    static String GROUP_BY = "group by";
    static String HAVING = "having";
    static String ALIAS = "alias";
    static String UNION = "union";
    static String INNER_JOIN = "inner jon";
    static String LEFT_JOIN = "left jon";

    private static Map<String, String> wordMap = Words.init();

    public Words() {

    }

    static Map<String, String> init() {
        Map<String, String> map = new HashMap<String,String>();
        map.put(SELECT, "select");
        map.put(INSERT, "insert");
        map.put(UPDATE, "update");
        map.put(DELETE, "delete");
        map.put(FROM, "from");
        map.put(WHERE, "where");
        map.put(DISTINCT, "distinct");
        map.put(AND, "and");
        map.put(OR, "or");
        map.put(IN, "in");
        map.put(BETWEEN, "between");
        map.put(LIKE, "like");
        map.put(ORDER_BY, "order by");
        map.put(GROUP_BY, "group by");
        map.put(HAVING, "having");
        map.put(ALIAS, "alias");
        map.put(UNION, "union");
        map.put(INNER_JOIN, "inner jon");
        map.put(LEFT_JOIN, "left jon");
        return map;
    }

    static boolean isReservedWord(String word) {
        String value = Words.wordMap.get(word);
        if (value != null)
            return true;
        return false;
    }
}
