package com.agile.framework.utils;

public class PathUtils {

    public String getWebClassesPath() {
        String path = getClass().getProtectionDomain().getCodeSource()
                .getLocation().getPath();
        path = trim(path);
        return path;

    }

    public String getWebInfPath() throws IllegalAccessException {
        String path = getWebClassesPath();
        if (path.indexOf("WEB-INF") > 0) {
            path = path.substring(0, path.indexOf("WEB-INF") + 8);
            path = trim(path);
        } else {
            throw new IllegalAccessException("路径获取错误");
        }
        return path;
    }

    public String getWebRoot() throws IllegalAccessException {
        String path = getWebClassesPath();
        if (path.indexOf("WEB-INF") > 0) {
            path = path.substring(0, path.indexOf("WEB-INF/classes"));
            path = trim(path);
        } else {
            throw new IllegalAccessException("路径获取错误");
        }
        return path;
    }

    private String trim(String s){
        if(s.startsWith("/")||s.startsWith("\\")){
            s = s.substring(1);
            trim(s);
        }
        return s;
    };

    public static void main(String[] args) throws Exception {
        PathUtils p = new PathUtils();
        System.out.println(p.getWebClassesPath());
        System.out.println(p.getWebInfPath());
        System.out.println(p.getWebRoot());
    }
}
