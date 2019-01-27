package com.wujie;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
//import java.sql.ResultSet;
import java.sql.Statement;
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;

public class LoginDao implements LoginService{
	private JdbcUtils jdbcUtils = null;
	public LoginDao() {
		// TODO Auto-generated constructor stub
		jdbcUtils = new JdbcUtils();
	}

	/* 完成用户对注册的Dao的编写
	 * @see com.product.register.service.RegisterService#registerUser(java.util.List)
	 */
	@Override
	public boolean loginUser(List<Object> params) {
		// TODO Auto-generated method stub
		boolean flag = false;
		jdbcUtils.getConnection();
		String sql = "select * from userinfo where username=? and pswd=?";
		//System.out.println("kk");
		try{
			//flag = jdbcUtils.updateByPreparedStatement(sql, params);
			Map<String, Object> map = jdbcUtils.findSimpleResult(sql, params);
			flag = !map.isEmpty() ? true : false;
			System.out.println("true");
		}catch(Exception e){
			System.out.println("false");
			e.printStackTrace();
		}
		finally{
			jdbcUtils.releaseConn();
		}
		
		return flag;
	}

	public List<String> user(List<Object> params) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		//
		conn = jdbcUtils.getConnection();
		List<String> list = new ArrayList<String>();
		try {
			//
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from userinfo where username=‘"
			+ params.get(0) + "‘");
			System.out.println(params.get(0));
			while (rs.next()) {
			String username = rs.getString("username");
			System.out.println(username);
			String password = rs.getString("pswd");
			System.out.println(password);
			/*String realname = rs.getString("realname");
			System.out.println(realname);
			String sex = rs.getString("sex");
			System.out.println(sex);*/
			// User user = new User(username, password, realname, sex);
			list.add(username);
			list.add(password);
			//list.add(realname);
			//list.add(sex);
			System.out.println(list);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}
}
