package com.wujie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class RegisterAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RegisterService service;

	/**
	 * Constructor of the object.
	 */
	public RegisterAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		//String realname = request.getParameter("realname");
		String pswd = request.getParameter("pswd");
		System.out.println("username = " + username
				+" pswd = " + pswd);
		
		String phone = request.getParameter("phone");
		
		List<Object> params = new ArrayList<Object>();
		params.add(username);
		params.add(pswd);
		params.add(phone);
		boolean flag = service.registerUser(params);
		if(flag){
			out.println("注册成功");
			// 设置返回字符集
			response.setContentType("charset=utf-8");
			String result = "{\"result\":\"success\"}";
			// 返回数据
			response.getWriter().print(result);
			//response.sendRedirect(path + "/index.jsp");
		}else{
			response.setContentType("charset=utf-8");
			String result = "{\"result\":\"failure\"}";
			// 返回数据
			response.getWriter().print(result);
			out.println("注册失败");
		}
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		service = new RegisterDao(); 
	}

}
