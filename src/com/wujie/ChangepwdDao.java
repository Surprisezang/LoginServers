package com.wujie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChangepwdDao implements ChangepwdService{
	private JdbcUtils jdbcUtils = null;
	public ChangepwdDao() {
		// TODO Auto-generated constructor stub
		jdbcUtils = new JdbcUtils();
	}

	/* 完成用户对注册的Dao的编写
	 * @see com.product.register.service.RegisterService#registerUser(java.util.List)
	 */
	@Override
	public boolean changepwdUser(List<Object> params) {		
		jdbcUtils.getConnection();
		boolean _flag = false;
		String _sql = "select * from userinfo where username=? and pswd=?";
		List<Object> params2 = new ArrayList<Object>();
		params2.add(params.get(0));
		params2.add(params.get(1));
		try{
			//flag = jdbcUtils.updateByPreparedStatement(sql, params);
			Map<String, Object> _map = jdbcUtils.findSimpleResult(_sql, params2);
			_flag = !_map.isEmpty() ? true : false;
			System.out.println("true");
		}catch(Exception e){
			System.out.println("false");
			e.printStackTrace();
		}
		
		if(_flag) {
			// TODO Auto-generated method stub
			boolean flag = false;
			//String sql = "select * from userinfo where username=? and pswd=?";
			String sql = "update userinfo set pswd = ? where username = ? ";
			List<Object> params1 = new ArrayList<Object>();
			params1.add(params.get(2));
			params1.add(params.get(0));
			System.out.println(params);
			try{
				flag = jdbcUtils.updateByPreparedStatement(sql, params1);
				System.out.println("true");
			}catch(Exception e){
				System.out.println("false");
				e.printStackTrace();
			}			
			finally{
				jdbcUtils.releaseConn();
			}
		}
		
		return _flag;
	}
	
/*	public List<String> user(List<Object> params) {
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
			//String realname = rs.getString("realname");
			//System.out.println(realname);
			//String sex = rs.getString("sex");
			//System.out.println(sex);
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
*/
}
