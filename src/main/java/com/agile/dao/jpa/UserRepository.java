package com.agile.dao.jpa;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.agile.model.User;


public interface UserRepository extends PagingAndSortingRepository<User, Long>{

	List<User> findByName(String name);

}

