package com.agile.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.agile.model.User;
import com.agile.framework.persistence.BaseJdbcDao;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;


public class UserJdbcDaoImpl extends BaseJdbcDao<User, Integer>  {

	@Override
	public User get(Integer id) {
		String sql = "SELECT * FROM sys_user WHERE id = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		User entity = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return entity;
	}

	@Override
	public User get(String sql, Object... values) {
		User entity = jdbcTemplate.queryForObject(sql, User.class, values);
		return entity;
	}

	@Override
	public List<User> getList(String sql, Object... values) {
		List<User> entities = jdbcTemplate.queryForList(sql, User.class, values);
		return entities;
	}

	@Override
	public List<User> getList(String sql, int pageIndex, int pageSize, Object... values) {
		List<User> entities = jdbcTemplate.queryForList(sql, User.class, values);
		return entities;
	}

	@Override
	public boolean contains(User t) {
		User entity = get(t.getId());
		if (entity == null)
			return false;
		else
			return true;
	}

	@Override
	public void refresh(User t) {
		User entity = get(t.getId());
		if (entity != null) {
			t.setName(entity.getName());
		}
	}

	@Override
	public void update(User t) {
		String sql = "UPDATE sys_user SET name = :name";
		Map<String, Object> params = new HashMap<>();
		params.put("name", t.getName());
		jdbcTemplate.update(sql, params);
	}

	@Override
	public void save(User t) {
		String sql = "INSERT INTO sys_user(last_name, email, dept_id) VALUES(:lastName,:email,:dpetId)";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(t);
		jdbcTemplate.update(sql, paramSource);
	}

	@Override
	public void saveOrUpdate(User t) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void delete(User t) {
		String sql = "DELETE sys_user WHERE id = :id";
		jdbcTemplate.execute(sql);
	}

	@Override
	public boolean delete(Integer Id) {
		String sql = "DELETE sys_user WHERE id = :id";
		jdbcTemplate.execute(sql);
		return false;
	}

	@Override
	public void delete(Collection<User> entities) {
		String sql = "DELETE sys_user WHERE id = ?";
		for (User t: entities) {
			jdbcTemplate.execute(sql);
		}
	}

	@Override
	public void execute(String sql, Object... values) {
		jdbcTemplate.execute(sql);
	}

	@Override
	public long count() {
		String sql = "SELECT count(id) FROM sys_user";
		long value = jdbcTemplate.queryForObject(sql, Long.class);
		return value;
	}

	@Override
	public long count(String sql, Object... values) {
		return 0;
	}

	// 展示ResultSetExtractor用法
	public boolean isExist(User user) {
		String sql = "SELECT count(*) FROM user WHERE name = ? and passwd = ?";
		Object[] params = new Object[] { user.getName(), user.getPassword() };
		int[] types = new int[] { Types.NVARCHAR, Types.NVARCHAR };

		ResultSetExtractor<Integer> extractor = new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				rs.next();
				return new Integer(rs.getInt(0));
			}
		};

		Integer rows = getJdbcTemplate().query(sql, params, types, extractor);
		//if (rows != null && rows. == 1)
		//	return true;
		return false;
	}

	public String findUserNameById(Integer id) {
		  String sql = "SELECT name FROM user WHERE id = ?";		  
		  String name = (String)getJdbcTemplate().queryForObject(
		      sql, new Object[] { id }, String.class);		 
		  return name;
	}
	
	public User findUserById(Integer id) {
		  String sql = "SELECT * FROM user WHERE id = ?";
		  User user = (User)getJdbcTemplate().queryForObject(
		      sql, new Object[] { id }, 
		      new UserMapper());
		  return user;
	}
	
	public List<User> findAll() {
		String sql = "SELECT * FROM user";
		return getJdbcTemplate().query(sql, new UserMapper());
	}
	
	protected class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User item = new User();
			item.setId(rs.getInt("id"));
			item.setName(rs.getString("name"));
			item.setPassword(rs.getString("password"));			
			return item;
		}

	}

	@Override
	public List<User> getList() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public List<User> getList(int pageIndex, int pageSize) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void update(Collection<User> entities) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void save(Collection<User> entities) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void saveOrUpdate(Collection<User> entities) {
		// TODO 自动生成的方法存根
		
	}


	@Override
	public Object getList(Object params) {
		// TODO Auto-generated method stub
		return null;
	}


	/*
	public static <T> List<T> convertToList(ResultSet rs, Class<T> t) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
		int columnCount = md.getColumnCount();
		while (rs.next()) {
			Map<String, Object> rowData = new HashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(md.getColumnName(i), rs.getObject(i));
			}
			list.add(rowData);
		}
		//JSONArray jr = JSONArray.fromObject(list);
		List<T> resultList = JSONArray.toList(jr, t);
		return resultList;
	}
	*/
}
