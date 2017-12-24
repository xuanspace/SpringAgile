package com.agile.dao.mybatis;

import com.agile.framework.persistence.AbstractMyBatisDao;
import org.springframework.stereotype.Repository;
import com.agile.dao.interfaces.UserDao;
import com.agile.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractMyBatisDao<User> implements UserDao {

    public UserDaoImpl() {
    }

}
