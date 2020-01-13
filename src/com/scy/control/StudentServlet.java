package com.scy.control;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scy.bean.Student;
import com.scy.service.StudentService;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentService studentService = new StudentService();     

    public StudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("StudentPersonalView"))
			request.getRequestDispatcher("/WEB-INF/student/studentPersonal.jsp").forward(request, response);
		else if(method.equals("StudentScoreView"))
			request.getRequestDispatcher("/WEB-INF/student/studentScore.jsp").forward(request, response);
		else if(method.equals("CLassCourseListView"))
			request.getRequestDispatcher("/WEB-INF/student/classCourse.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("editPassword".equals(method)){ //修改密码
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			studentService.editPassword(account,password);
			response.getWriter().write("success");
		}
		else if("termList".equals(method)){
			String result = studentService.termList();
			response.getWriter().write(result);
		}
		else if("courseList".equals(method)){
			response.setContentType("text/html;charset=UTF-8");
			String termname = request.getParameter("termname");
			HttpSession session = request.getSession();
			Student student= (Student) session.getAttribute("user");
			String clno = student.getClno();
			String result = studentService.getCourseList(clno,termname);
			System.out.println(result);
			response.getWriter().write(result);
		}
		else if("scoreList".equals(method)) {
			response.setContentType("text/html;charset=UTF-8");
			String termname = request.getParameter("termname");
			HttpSession session = request.getSession();
			Student student= (Student) session.getAttribute("user");
			String sno = student.getSno();
			String result = studentService.getScoreList(sno,termname);
			System.out.println(result);
			response.getWriter().write(result);
		}

	}

}
