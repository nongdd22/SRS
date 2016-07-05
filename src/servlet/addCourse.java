package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseDao;
import dao.DataAccess;
import model.Course;

/**
 * Servlet implementation class addCourse
 */
@WebServlet("/addCourse")
public class addCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		CourseDao cd=DataAccess.createCourseDao();
		String courseNo=request.getParameter("courseNo");
		String courseName=request.getParameter("courseName");
		String credits=request.getParameter("credits");
		String prerequisites=request.getParameter("prerequisites");
		Course course=new Course(courseNo,courseName,Double.parseDouble(credits));
		cd.addCourse(course);
		if(!prerequisites.equals("0")){
			String preNo=cd.searchCourseNo(prerequisites);
			Course pre=cd.searchCourse(preNo);
			cd.addCourseprerequisites(pre,course);
		}		
		response.sendRedirect("html/courseIndex.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
