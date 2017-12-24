package com.agile.test;

import com.agile.dao.interfaces.UserDao2;
import com.agile.dao.mybatis.UserDaoImpl;
import com.agile.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

public class TestMyBatis {

    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static{
        try{
            URL path = Thread.currentThread().getContextClassLoader().getResource("");
            System.out.println("resource file:" + path);

            File file = new File("src/main/resources/mybatis-config.xml");
            InputStream inputStream = new FileInputStream(file);
            String xml = file.getAbsolutePath();
            System.out.println("resource file:" + xml);

            //reader = Resources.getResourceAsReader(xml);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }

    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            User user4 = (User) session.selectOne("com.agile.dao.mybatis.UserMapper.get", 10);
        	UserDao2 userOperation=session.getMapper(UserDao2.class);
            User uu = userOperation.test();

            User u2 = userOperation.get(10);
            //UserDaoImpl userDao = new UserDaoImpl();
            //userDao.setSessionFactory(sqlSessionFactory);
            //User u = userDao.get(10);

            //User user2 = userOperation.selectByPrimaryKey(1);
            //User user = (User) session.selectOne("com.agile.mapping.UserMapper.selectByPrimaryKey", 10);

            //System.out.println(user.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
    }
}