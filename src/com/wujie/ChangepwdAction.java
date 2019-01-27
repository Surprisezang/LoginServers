package com.wujie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangepwdAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChangepwdService service;

	/**
	 * Constructor of the object.
	 */
	public ChangepwdAction() {
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
			String pswd = request.getParameter("pswd");
			String new_pswd = request.getParameter("new_pswd");
			
			System.out.println("username = " + username + " pswd = " + pswd+ " new_pswd = " + new_pswd);
			List<Object> params = new ArrayList<Object>();
			params.add(username);
			params.add(pswd);
			params.add(new_pswd);
			boolean flag = service.changepwdUser(params);

			//boolean fla = service.LoginCat(params);
			//boolean fl = service.LoginDog(params);
		//List<String> list = service.user(params);
			if(flag){
				out.println("修改成功");
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
				out.println("修改失败");
			}
			/*JSONObject jsonobj = new JSONObject();
			if (list.size() != 0) {
			jsonobj.put("用户名", list.get(0));
			jsonobj.put("密码", list.get(1));
			//jsonobj.put("真实姓名", list.get(2));
			//jsonobj.put("性别", list.get(3));
			}
			if (flag == true) {
				out.println(jsonobj);
				response.sendRedirect(path + "/index.jsp");
			}*/
			/*if (fla == true) {
			out.print("用户名不存在请重新输入，登陆失败");
			}
			if (fl == true) {
			out.print("密码错误，登陆失败");
			}*/
			
			out.flush();
			out.close();
		}

			/**
			* Initialization of the servlet. <br>
			* 
			* @throws ServletException
			* if an error occurs
			*/
			public void init() throws ServletException {
			// Put your code here
				service = new ChangepwdDao();
			}
}
