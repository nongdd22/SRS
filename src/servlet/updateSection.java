package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseDao;
import dao.DataAccess;
import dao.ProfessorDao;
import dao.SectionDao;
import model.Course;
import model.Professor;
import model.Section;

/**
 * Servlet implementation class updateSection
 */
@WebServlet("/updateSection")
public class updateSection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateSection() {
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
		String sNo=request.getParameter("secNo");
		System.out.println(sNo);
		int sectionNo=Integer.parseInt(sNo);
		String dayOfWeek=request.getParameter("dayOfWeek");
		String timeOfDay=request.getParameter("timeOfDay");
		String courseName=request.getParameter("courseName");
		String courseNo=cd.searchCourseNo(courseName);
		Course course=cd.searchCourse(courseNo);
		String room=request.getParameter("room");
		int seatingCapacity=Integer.parseInt(request.getParameter("seatingCapacity"));
		Section section=new Section(sectionNo,dayOfWeek,timeOfDay,course,room,seatingCapacity);
		SectionDao sd=DataAccess.createSectionDao();
		sd.updateSection(section);
		String professorname=request.getParameter("professor");
		ProfessorDao pd=DataAccess.createProfessorDao();
		String PSsn=pd.searchPSsn(professorname);
		Professor professor=pd.searchProfessor(PSsn);
		pd.teachSection(professor, section);
		response.sendRedirect("html/sectionIndex.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
