package com.agile.test;

import com.agile.framework.query.Builder;
import com.agile.modules.database.SYS_USER;
import com.agile.modules.database.Tables;

import java.util.ArrayDeque;

public class TestBuilder {

	public void test1() {

		Builder build = new Builder();

		build = new Builder();
		build.selectFrom(Tables.SYS_USER.as("Test"))
			 .where(SYS_USER.ID.ge(10));
		build.toString();

		build = new Builder();
		build.where(SYS_USER.ID.ge(9).and(SYS_USER.ID.ge(10)))
             .order(SYS_USER.ID.desc())
             .limit(10)
             .offset(20);
		build.toString();
		
		build = new Builder();
		build.delete(Tables.SYS_USER)
			 .where(SYS_USER.ID.ge(10).and(SYS_USER.ID.ge(10)));
		build.toString();
		
		build = new Builder();
		build.update(Tables.SYS_USER)
			 .set(SYS_USER.ID, 1)
			 .set(SYS_USER.ID, 1)
			 .where(SYS_USER.ID.ge(10));
		build.toString();
	}
	
	public static void main(String args[]) {
        TestBuilder  test = new TestBuilder();
		test.test1();
	}	
}
