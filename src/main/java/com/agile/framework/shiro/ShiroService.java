package com.agile.framework.shiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agile.dao.interfaces.PermissionDao;
import com.agile.dao.interfaces.ResourceDao;
import com.agile.dao.interfaces.RoleDao;
import com.agile.dao.interfaces.UserDao;
import com.agile.dao.interfaces.UserRoleDao;
import com.agile.framework.query.Builder;
import com.agile.model.User;
import com.agile.modules.database.SYS_USER;

@Transactional
@Service("shiroService")
public class ShiroService {

	@Autowired
	public UserDao userDao;

	@Autowired
	public RoleDao roleDao;
	
	@Autowired
	public UserRoleDao userRoleDao;

	@Autowired
	public PermissionDao permissionDao;
	
	@Autowired
	public ResourceDao resourceDao;

	
    /**
     * 根据用户名获取用户
     * @param name 用户名
     * @return 用户实例
     */
	public User getUserByName(String name) {
		Builder query = userDao.queryBuilder();
		query.select(SYS_USER.NAME.eq(name));		
		List<User> data = userDao.getList(query);
		if (data != null && data.size() > 0) {
			return data.get(0);
		}
		return null;
	}
	
    /**
     * 根据用户名获取用户
     * @param name 用户名
     * @return 用户实例
     */
	public User getUserRoles(User user) {
		return null;
	}	
	
}
