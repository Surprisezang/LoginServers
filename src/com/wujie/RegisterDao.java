package com.wujie;

import java.util.List;

public class RegisterDao implements RegisterService {
	private JdbcUtils jdbcUtils = null;
	public RegisterDao() {
		// TODO Auto-generated constructor stub
		jdbcUtils = new JdbcUtils();
	}

	/* 完成用户对注册的Dao的编写
	 * @see com.product.register.service.RegisterService#registerUser(java.util.List)
	 */
	@Override
	public boolean registerUser(List<Object> params) {
		// TODO Auto-generated method stub
		boolean flag = false;
		jdbcUtils.getConnection();
		//String sql = "insert into userinfo(username, pswd, realname) values (?, ?, ?)";
		String sql = "insert into userinfo(username, pswd, phone) values (?, ?, ?)";
		try{
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
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

}
