package com.agile.test;

//import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import javassist.*;
import javassist.util.HotSwapper;

import java.io.IOException;

public class TestJavassist {

    public void doSomething() {
        System.out.println("doSomething");
    }

    public static void main(String[] args) {
        TestJavassist standard = new TestJavassist();
        standard.doSomething();
        /*
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass clazz = pool.get(TestJavassist.class.getName());
            CtMethod cm = clazz.getDeclaredMethod("doSomething");
            cm.insertAt(1,"{System.out.println(\"hello HotSwapper.\");}");  // clazz完全可以是全新的，这里只是为了测试方便而已
            HotSwapper swap = new HotSwapper(8000);
            swap.reload("com.agile.test.TestJavassist", clazz.toBytecode());
            standard.doSomething();
        }catch (CannotCompileException | IOException | IllegalConnectorArgumentsException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        */
    }
}
