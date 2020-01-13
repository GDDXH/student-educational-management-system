package com.scy.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.support.RequestPartServletServerHttpRequest;

import com.scy.bean.Course;
import com.scy.bean.Stu;
import com.scy.bean.Student;
import com.scy.bean.Teacher;
import com.scy.service.AdminService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("ScoreListView"))
			request.getRequestDispatcher("/WEB-INF/admin/scoreList.jsp").forward(request, response);
		else if(method.equals("StudentListView"))
			request.getRequestDispatcher("/WEB-INF/admin/studentList.jsp").forward(request, response);
		else if(method.equals("TeacherListView"))
			request.getRequestDispatcher("/WEB-INF/admin/teacherList.jsp").forward(request, response);
		else if(method.equals("MajorListView"))
			request.getRequestDispatcher("/WEB-INF/admin/majorList.jsp").forward(request, response);
		else if(method.equals("ClassListView"))
			request.getRequestDispatcher("/WEB-INF/admin/classList.jsp").forward(request, response);
		else if(method.equals("CourseListView"))
			request.getRequestDispatcher("/WEB-INF/admin/courseList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("scoreList".equals(method)) {
			response.setContentType("text/html;charset=UTF-8");
			String termname = request.getParameter("termname");
			String result = adminService.getScoreList(termname);
			System.out.println(result);
			response.getWriter().write(result);
		}
		else if("studentList".equals(method)) {
			response.setContentType("text/html;charset=UTF-8");
			String result = adminService.getStudentList();
			response.getWriter().write(result);
		}
		else if("teacherList".equals(method)) {
			response.setContentType("text/html;charset=UTF-8");
			String result = adminService.getTeacherList();
			System.out.println(result);
			response.getWriter().write(result);
		}
		else if("courseList".equals(method)) {
			response.setContentType("text/html;charset=UTF-8");
			String result = adminService.getCourseList();
			System.out.println(result);
			response.getWriter().write(result);
		}
		else if("addStudent".equals(method)) {
			response.setContentType("text/html;charset=UTF-8");
			Stu student = new Stu();
			student.setSno(request.getParameter("sno"));
			student.setSname(request.getParameter("sname"));
			student.setSpassword("123456");
			student.setSsex(request.getParameter("ssex"));
			student.setSage(Integer.parseInt(request.getParameter("sage")));
			student.setSyear(Integer.parseInt(request.getParameter("syear")));
			student.setScredit(Integer.parseInt(request.getParameter("scredit")));
			student.setSaddress(request.getParameter("saddress"));
			student.setClno(request.getParameter("clno"));
			String msg = adminService.addStudent(student);
			response.getWriter().write(msg); 
		}
		else if("editStudent".equals(method)) {
			response.setContentType("text/html;charset=UTF-8");
			Stu student = new Stu();
			student.setSno(request.getParameter("sno"));
			student.setSname(request.getParameter("sname"));
			student.setSsex(request.getParameter("ssex"));
			student.setSage(Integer.parseInt(request.getParameter("sage")));
			student.setSyear(Integer.parseInt(request.getParameter("syear")));
			student.setScredit(Integer.parseInt(request.getParameter("scredit")));
			student.setSaddress(request.getParameter("saddress"));
			student.setClno(request.getParameter("clno"));
			String msg = adminService.editStudent(student);
			response.getWriter().write(msg);
		}
		else if("deleteStudent".equals(method)) {
			response.setContentType("text/html;charset=UTF-8");
			Stu student = new Stu();
			student.setSno(request.getParameter("sno"));
			String msg = adminService.deleteStudent(student);
			response.getWriter().write(msg);
		}
		else if("addTeacher".equals(method)) {
			response.setContentType("text/html;charset=UTF-8");
			Teacher teacher = new Teacher();
			teacher.setTno(request.getParameter("tno"));
			teacher.setTname(request.getParameter("tname"));
			teacher.setTpassword("123456");
			teacher.setTsex(request.getParameter("tsex"));
			teacher.setTage(Integer.parseInt(request.getParameter("tage")));
			teacher.setTposition(request.getParameter("tposition"));
			teacher.setTphone(request.getParameter("tphone"));
			String msg = adminService.addTeacher(teacher);
			response.getWriter().write(msg);
		}
		else if("editTeacher".equals(method)) {
			response.setContentType("text/html;charset=UTF-8");
			Teacher teacher = new Teacher();
			teacher.setTno(request.getParameter("tno"));
			teacher.setTname(request.getParameter("tname"));
			teacher.setTsex(request.getParameter("tsex"));
			teacher.setTage(Integer.parseInt(request.getParameter("tage")));
			teacher.setTposition(request.getParameter("tposition"));
			teacher.setTphone(request.getParameter("tphone"));
			String msg = adminService.editTeacher(teacher);
			response.getWriter().write(msg);
		}
		else if("deleteTeacher".equals(method)) {
			response.setContentType("text/html;charset=UTF-8");
			Teacher teacher = new Teacher();
			teacher.setTno(request.getParameter("tno"));
			System.out.println(teacher.getTno());
			String msg = adminService.deleteTeacher(teacher);
			response.getWriter().write(msg);
		}
		else if("addCourse".equals(method)) {
			response.setContentType("text/html;charset=UTF-8");
			Course course = new Course();
			course.setCno(request.getParameter("cno"));
			course.setCname(request.getParameter("cname"));
			course.setCtime(Integer.parseInt(request.getParameter("ctime")));
			course.setCredit(Integer.parseInt(request.getParameter("credit")));
			course.setCtest(request.getParameter("ctest"));
			course.setCterm(request.getParameter("cterm"));
			String msg = adminService.addCourse(course);
			response.getWriter().write(msg);
		}
		else if("editCourse".equals(method)) {
			response.setContentType("text/html;charset=UTF-8");
			Course course = new Course();
			course.setCno(request.getParameter("cno"));
			course.setCname(request.getParameter("cname"));
			course.setCtime(Integer.parseInt(request.getParameter("ctime")));
			course.setCredit(Integer.parseInt(request.getParameter("credit")));
			course.setCtest(request.getParameter("ctest"));
			course.setCterm(request.getParameter("cterm"));
			String msg = adminService.editCourse(course);
			response.getWriter().write(msg);
		}
		else if("deleteCourse".equals(method)) {
			response.setContentType("text/html;charset=UTF-8");
			Course course = new Course();
			course.setCno(request.getParameter("courseid"));
			String msg = adminService.deleteCourse(course);
			response.getWriter().write(msg);
		}
	}
}
