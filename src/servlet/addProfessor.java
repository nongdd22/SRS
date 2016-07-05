package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DataAccess;
import dao.ProfessorDao;
import model.Professor;

/**
 * Servlet implementation class addProfessor
 */
@WebServlet("/addProfessor")
public class addProfessor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addProfessor() {
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
		String PSsn=request.getParameter("PSsn");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String title=request.getParameter("title");
		String department=request.getParameter("department");
		Professor professor=new Professor(PSsn,name,password,title,department);
		ProfessorDao pd=DataAccess.createProfessorDao();
		pd.addProfessor(professor);
		
		response.sendRedirect("html/professorIndex.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
