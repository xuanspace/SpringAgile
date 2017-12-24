package com.agile.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.dao.jpa.UserRepository;
import com.agile.model.User;

@Transactional
@Service("userJpaService")
public class UserJpaService {

	@Autowired
	private UserRepository userDao;
	
	public UserJpaService() {

	}

    public List<User> findAll() {
        return (List<User>) userDao.findAll();
    }
    
    public void saveUser(User user) {
        userDao.save(user);
    }
    
    public void deleteUser(Long id) {
        userDao.delete(id);
    }    
    
	public List<User> findAllUsers() {
		return (List<User>) userDao.findAll();
	}
	
    public Page<User> findAllUserByPage(PageRequest page) {
        return (Page<User>) userDao.findAll(page);
    }
}
