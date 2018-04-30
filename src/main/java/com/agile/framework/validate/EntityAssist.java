package com.agile.framework.validate;

import javassist.*;
import javassist.bytecode.*;
import javassist.bytecode.annotation.*;

import javax.validation.constraints.*;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 实体类动态添加注解
 * @author linweixuan@gmail.com
 * @date 2017-02-03
 * @version 1.0
 */
public class EntityAssist {

    private Class<?> rawClass = null;
    private ClassFile classFile = null;
    private CtClass assistClass = null;
    private ConstPool constPool = null;
    private Class<?> newClass = null;

    /**
     * 构造函数
     */
    public EntityAssist(Class<?> clazz) {
        this.rawClass = clazz;
    }

    /**
     * 获取模板参数类的CtClass
     * @return CtClass
     */
    public CtClass getCtClass() throws NotFoundException {
        if (assistClass == null) {
            ClassPool classPool = ClassPool.getDefault();
            assistClass = classPool.get(rawClass.getName());
            classFile = assistClass.getClassFile();
            constPool = classFile.getConstPool();
        }
        return assistClass;
    }

    public Class<?> getTypeClass() {
        return newClass;
    }

    /**
     * 获取T的属性注解
     * @return fieldInfo
     */
    public Annotation getAnnotation(FieldInfo fieldInfo, Class<?> annotationClazz) throws NotFoundException {
        try {
            AnnotationsAttribute attribute = (AnnotationsAttribute) fieldInfo.getAttribute(AnnotationsAttribute.visibleTag);
            String className = annotationClazz.getName();
            return attribute.getAnnotation(className);
        }catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }

    class AnnotationWrap {
        CtClass clazz;
        CtField field;
        FieldInfo fieldInfo;
        AnnotationsAttribute attribute;
        Annotation annotation;

        public void addMemberValue(String name, MemberValue value) {
            if (annotation != null) {
                annotation.addMemberValue(name, value);
            }
        }

        public void addMessage(String message) {
            if (annotation != null) {
                annotation.addMemberValue("message", new StringMemberValue(message, constPool));
            }
        }

        public Class<?> toClass() {
            Class<?> theClass = null;
            try {
                EntityClassLoader loader = new EntityClassLoader(EntityAssist.class.getClassLoader());
                theClass = clazz.toClass(loader, null);
                clazz.detach();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return theClass;
        }

        public void save() {
            try {
                clazz.writeFile();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void save(String path) {
            try {
                byte[] byteArr = clazz.toBytecode();
                FileOutputStream fos = new FileOutputStream(new File(path));
                fos.write(byteArr);
                fos.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 添加属性注解（如果注解不存在则添加）
     * @param property
     * @param annotationClazz
     */
    public AnnotationWrap addAnnotation(String property, Class<?> annotationClazz)  {
        AnnotationWrap wrape = new AnnotationWrap();
        try {
            CtClass clazz = getCtClass();
            CtField field = clazz.getField(property);
            FieldInfo fieldInfo = field.getFieldInfo();
            Annotation annotation = getAnnotation(fieldInfo, annotationClazz);
            if (annotation == null) {
                AnnotationsAttribute attribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
                annotation = new Annotation(annotationClazz.getName(), constPool);
                attribute.addAnnotation(annotation);
                fieldInfo.addAttribute(attribute);
                wrape.clazz = clazz;
                wrape.field = field;
                wrape.annotation = annotation;
                wrape.attribute = attribute;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return wrape;
    }

    public void addAnnotationMessage(AnnotationWrap wrap, String message)  {
        Annotation annotation = wrap.annotation;
        AnnotationsAttribute attribute = wrap.attribute;
        if (annotation != null) {
            annotation.addMemberValue("message", new StringMemberValue(message, constPool));
            classFile.addAttribute(attribute);
            //classFile.setVersionToJava5();
        }
    }

    /**
     * 保存修改后的类
     * @param path
     */
    public void save(String path) {
        try {
            byte[] byteArr = assistClass.toBytecode();
            FileOutputStream fos = new FileOutputStream(new File(path));
            fos.write(byteArr);
            fos.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    // JSR校验注解
    //
    public void setNotNull(String property) {
        AnnotationWrap wrap = addAnnotation(property, Null.class);
    }

    public Class<?> setNotNull(String property, String message) {
        AnnotationWrap wrap = addAnnotation(property, NotNull.class);
        wrap.addMessage(message);
        wrap.save();
        return wrap.toClass();
    }

    public void setAssertTrue(String property) {
        AnnotationWrap wrap = addAnnotation(property, AssertTrue.class);
    }

    public void setAssertFalse(String property) {
        AnnotationWrap wrap = addAnnotation(property, AssertFalse.class);
    }

    public void setMin(String property, Long value) {
        AnnotationWrap wrap = addAnnotation(property, Min.class);
        wrap.addMemberValue(null, new LongMemberValue(value, constPool));
    }

    public void setMax(String property, Long value) {
        AnnotationWrap wrap = addAnnotation(property, Max.class);
        wrap.addMemberValue(null, new LongMemberValue(value, constPool));
    }

    public void setDecimalMin(String property, Object value) {
    }

    public void setDecimalMax(String property, Object value) {
    }

    public void setSize(String property, Object min, Object max) {
    }

    public void setDigits(String property) {
    }

    public void setPast(String property) {
    }

    public void setFuture(String property) {
    }

    public void setPattern(String property) {
    }

    //
    // Hibernate校验注解
    //
    public void setNotBlank(String property) {
    }

    public void setNotEmpty(String property) {
    }

    public void setLength(String property, int min, int max) {
    }


    public void setRange(String property, Object min, Object max) {
    }

    public void setEmail(String property) {
    }

}

