package com.scy.control;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scy.bean.Teacher;

import com.scy.service.TeacherService;

/**
 * Servlet implementation class TeacherServlet
 */
@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	private TeacherService teacherService = new TeacherService();
	public TeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("TeacherPersonalView"))
			request.getRequestDispatcher("/WEB-INF/teacher/teacherPersonal.jsp").forward(request, response);
		else if(method.equals("ScoreManageView"))
			request.getRequestDispatcher("/WEB-INF/teacher/teacherScoreManage.jsp").forward(request, response);
		else if(method.equals("CourseListView"))
			request.getRequestDispatcher("/WEB-INF/teacher/teacherCourse.jsp").forward(request, response);
		else if(method.equals("CourseScoreView"))
			request.getRequestDispatcher("/WEB-INF/teacher/courseScore.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("editPassword".equals(method)){ //修改密码
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			teacherService.editPassword(account,password);
			response.getWriter().write("success");
		}
		else if("termList".equals(method)){
			String result = teacherService.termList();
			System.out.println(result);
			response.getWriter().write(result);
		}
		else if("courseList".equals(method)){
			response.setContentType("text/html;charset=UTF-8");
			HttpSession session = request.getSession();
			Teacher teacher= (Teacher) session.getAttribute("user");
			String tno = teacher.getTno();
			String result;
			String termname = request.getParameter("termname");
			if(termname==null)
				result = teacherService.getCourseList(tno);
			else 
				result = teacherService.getCourseList(tno,termname);		
			System.out.println(result);
			response.getWriter().write(result);
		}
		else if("scoreList".equals(method)) {
			response.setContentType("text/html;charset=UTF-8");
			String cno= request.getParameter("cno");
			String result = teacherService.getScoreList(cno);
			System.out.println(result);
			response.getWriter().write(result);
		}
		else if("studentList".equals(method)) {
			response.setContentType("text/html;charset=UTF-8");
			HttpSession session = request.getSession();
			Teacher teacher= (Teacher) session.getAttribute("user");
			String tno = teacher.getTno();
			String result = teacherService.getStudentList(tno);
			System.out.println(result);
			response.getWriter().write(result);
		}
		else if("editStudentScore".equals(method)) {
			response.setContentType("text/html;charset=UTF-8");

	        String sno = request.getParameter("scy_sno");
	        String cname = request.getParameter("scy_cname");
	        String cterm = request.getParameter("scy_cterm");
	        int report = Integer.parseInt(request.getParameter("scy_report"));
	        double credit = Double.parseDouble(request.getParameter("scy_credit"));
	        if(report>0 &&report<=100)
	        {
	        	teacherService.setStudentScore(sno,cname,cterm,report);
	        	if(report>=60)
	        		teacherService.setStudentCredit(sno,credit);
		        String msg="success";
				response.getWriter().write(msg);        	
	        }
	        else
	        {
		        String msg="fali";
				response.getWriter().write(msg);
	        }
		}
	}
}
