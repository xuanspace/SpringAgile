package com.agile.test;

import java.util.Iterator;  
import java.util.List;  
  
import javax.persistence.EntityManager;  
import javax.persistence.EntityManagerFactory;  
import javax.persistence.EntityTransaction;  
import javax.persistence.Persistence;

import com.agile.model.User;  

public class TestEntityManager {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence  
                .createEntityManagerFactory("helloworld");  
  
        /*----------1------*/  
        EntityManager em = emf.createEntityManager();  
        EntityTransaction tx = em.getTransaction();  
        tx.begin();  
  
        User message = new User();  
        message.setName("hello world");  
        em.persist(message);  
  
        tx.commit();  
        em.close();  
        /*----------2------*/  
        EntityManager newEm = emf.createEntityManager();  
        EntityTransaction newTx = newEm.getTransaction();  
        newTx.begin();  
  
        List messages = newEm.createQuery("select m from Message m order by m.text asc")  
                .getResultList();  
        System.out.println("messages.size() = " + messages.size());  
  
        for (Iterator iter = messages.iterator(); iter.hasNext();) {  
        	User loadedMsg = (User) iter.next();  
            System.out.println(loadedMsg.getName());  
        }  
  
        newTx.commit();  
        newEm.close();  
  
        emf.close();  
	}

}
