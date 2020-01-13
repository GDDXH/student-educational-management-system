package com.scy.control;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scy.bean.Admin;
import com.scy.bean.Student;
import com.scy.bean.Teacher;
import com.scy.service.LoginService;
import com.scy.tool.ValidateCode;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService loginService = new LoginService();
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		
		if("GetVCode".equalsIgnoreCase(method)){
			//创建验证码生成器对象
			ValidateCode validateCode = new ValidateCode();
			//生成验证码
			String vcode = validateCode.generatorVCode();
			//将验证码保存在session域中,以便判断验证码是否正确
			request.getSession().setAttribute("vcode", vcode);
			//生成验证码图片
			BufferedImage vImg = validateCode.generatorRotateVCodeImage(vcode, true);
			//输出图像
			ImageIO.write(vImg, "gif", response.getOutputStream());
		}
		else if("LoginOut".equals(method)){ 
			request.getSession().removeAttribute("user");
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath+"/login.jsp");
		} 
		else if("AdminView".equalsIgnoreCase(method)){ 
			request.getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request, response);
		} 
		else if("StudentView".equals(method)){ 
			request.getRequestDispatcher("/WEB-INF/student/student.jsp").forward(request, response);
		} 
		else if("TeacherView".equals(method)){ 
			request.getRequestDispatcher("/WEB-INF/teacher/teacher.jsp").forward(request, response);
		} 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户输入的账户
		String account = request.getParameter("account");
		//获取用户输入的密码
		String password = request.getParameter("password");
		//获取用户输入的验证码
		String vcode = request.getParameter("vcode");
		//获取登录类型
		int type = Integer.parseInt(request.getParameter("type"));
		
		//返回信息
		String msg = "";
		
		//获取session中的验证码
		String sVcode = (String) request.getSession().getAttribute("vcode");
		//判断验证码
		if(!sVcode.equalsIgnoreCase(vcode))
			msg = "vcodeError";
		else{	
			if(type == 1) {
				Student loginUser = loginService.LoginStudent(account, password);
				if(loginUser == null)
					msg = "loginError";
				else{ 
						msg = "student";
						HttpSession session = request.getSession();
						session.setAttribute("user", loginUser);	
						session.setAttribute("sno", loginUser.getSno());
				} 
			}
			else if(type == 2) {
				Teacher loginUser = loginService.LoginTeacher(account, password);
				if(loginUser == null)
					msg = "loginError";
				else{
					msg = "teacher";
					HttpSession session = request.getSession();
					session.setAttribute("user", loginUser);
				}
			}
			else{
				Admin loginUser = loginService.LoginAdmin(account, password);
				if(loginUser == null)
					msg = "loginError";
				else{
					msg = "admin";
					HttpSession session = request.getSession();
					session.setAttribute("user", loginUser);
				}	 		
			}
		}
		response.getWriter().write(msg);
	}
}
