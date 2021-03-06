package com.agile.framework.validate;

/**
 * 实体类加载器
 * 该加载器主要用于运行时动态修改实体后，重新装载实体 
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0
 */
public class EntityClassLoader extends ClassLoader {

    private ClassLoader parent;

    public EntityClassLoader(ClassLoader parent) {
        this.parent = parent;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return this.loadClass(name, false);
    }

    @Override
    protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz = this.findLoadedClass(name);
        if (null != parent) {
            clazz = parent.loadClass(name);
        }
        if (null == clazz) {
            this.findSystemClass(name);
        }
        if (null == clazz) {
            throw new ClassNotFoundException();
        }
        if (null != clazz && resolve) {
            this.resolveClass(clazz);
        }
        return clazz;
    }
}