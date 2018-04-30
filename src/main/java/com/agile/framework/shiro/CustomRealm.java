package com.agile.framework.shiro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.agile.model.User;
import com.agile.service.interfaces.UserService;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;



public class CustomRealm extends AuthorizingRealm {
	
    public static final Logger logger = LoggerFactory.getLogger(CustomRealm.class);
	
	@Autowired
	private UserService userService;

    /**
     * 
     * 用户授权认证
     * 1.根据用户user->2.获取角色id->3.根据角色id获取权限permission
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 获得user对象
		User user = (User)principalCollection.getPrimaryPrincipal();
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//simpleAuthorizationInfo.setRoles(userService.queryRolesByName(userName));
		return simpleAuthorizationInfo;
	}

	/**
	 * 用户登陆认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = null; //shiroService.getUserByUserName(token.getUsername());
        System.out.println("2");
        if(user==null){
            return null;
        }
        //最后的比对需要交给安全管理器
        //三个参数进行初步的简单认证信息对象的包装
        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getSimpleName());

        return info;
        
        /*
		String userName = authenticationToken.getPrincipal().toString();
		user = null; //userService.queryUserByName(userName);
		if (user != null) {
			AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getName(), user.getPassword(), "peng");
			return authenticationInfo;
		}
		return null;
		*/
	}
}
