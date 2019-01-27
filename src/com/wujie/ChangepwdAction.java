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
				out.println("�޸ĳɹ�");
				// ���÷����ַ���
				response.setContentType("charset=utf-8");
				String result = "{\"result\":\"success\"}";
				// ��������
				response.getWriter().print(result);
				//response.sendRedirect(path + "/index.jsp");
			}else{
				response.setContentType("charset=utf-8");
				String result = "{\"result\":\"failure\"}";
				// ��������
				response.getWriter().print(result);
				out.println("�޸�ʧ��");
			}
			/*JSONObject jsonobj = new JSONObject();
			if (list.size() != 0) {
			jsonobj.put("�û���", list.get(0));
			jsonobj.put("����", list.get(1));
			//jsonobj.put("��ʵ����", list.get(2));
			//jsonobj.put("�Ա�", list.get(3));
			}
			if (flag == true) {
				out.println(jsonobj);
				response.sendRedirect(path + "/index.jsp");
			}*/
			/*if (fla == true) {
			out.print("�û������������������룬��½ʧ��");
			}
			if (fl == true) {
			out.print("������󣬵�½ʧ��");
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
