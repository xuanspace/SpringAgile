package com.agile.test;

import com.agile.framework.query.Builder;
import com.agile.framework.query.BuilderException;
import com.agile.framework.query.SQL;
import com.agile.framework.query.SQLField;
import com.agile.model.User;
import com.agile.model.UserRole;
import com.agile.modules.database.SYS_USER;
import com.agile.modules.database.SYS_USER_ROLE;
import com.agile.modules.database.DB;
import com.agile.modules.database.SYS_AREA;
import com.agile.modules.database.SYS_ROLE;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class TestBuilder {

	public void test1()  {
		String sql = "";
		
		Builder build = new Builder(UserRole.class);
		build.select(DB.SYS_ROLE);
		build.join(SYS_USER_ROLE.ROLE_ID).on(SYS_ROLE.ID);
		build.where(SYS_USER_ROLE.USER_ID.eq(1));		
		sql = build.toString();

		
		build = new Builder(UserRole.class);
		build.setSql("select :name, :age from user??");
		build.setParameter("name", "lwx");
		build.setParameter("age", 11);
		sql = build.toString();
		
		//build.setSql("select a, b from user??");
		//build.setParameter(1, "name");
		//build.setParameter(2, "age");
		//build.parseSqlString();
		//sql = build.getParameterizedString();

		
		build = new Builder(User.class);
		build.limit(10).offset(3);
		sql = build.toString();

		build = new Builder(User.class);
		build.from(DB.SYS_USER.as("Test"))
			 .where(SYS_USER.ID.ge(10));
		sql = build.toString();

		build = new Builder();
		build.update(DB.SYS_USER)
			 .set(SYS_USER.ID, 1)
			 .set(SYS_USER.ID, 1)
			 .where(SYS_USER.ID.ge(10));
		sql = build.toString();
		
		build = new Builder(User.class);
		build.where(SYS_USER.ID.ge(9).and(SYS_USER.ID.ge(10)))
             .order(SYS_USER.ID.desc())
             .limit(10)
             .offset(20);
		sql = build.toString();
		
		build = new Builder(User.class);
		build.delete(DB.SYS_USER)
			 .where(SYS_USER.ID.ge(10).and(SYS_USER.ID.ge(10)));
		sql = build.toString();
		
	}
	
	public static void main(String args[]) {
        TestBuilder  test = new TestBuilder();
		test.test1();
	}	
}
