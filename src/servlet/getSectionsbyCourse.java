package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseDao;
import dao.DataAccess;
import service.sectionService;

/**
 * Servlet implementation class getSectionsbyCourse
 */
@WebServlet("/getSectionsbyCourse")
public class getSectionsbyCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getSectionsbyCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utf-8");
		sectionService ss=new sectionService();
		PrintWriter out = response.getWriter();	
		String courseName = request.getParameter("courseName");
		String courseNo;
		if(courseName!=null){
			CourseDao cd=DataAccess.createCourseDao();
			courseNo=cd.searchCourseNo(courseName);
		}else{
			courseNo=request.getParameter("courseNo");
		}	
		String jsonData = ss.getSectionsbyCourse(courseNo);
		out.print(jsonData);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
