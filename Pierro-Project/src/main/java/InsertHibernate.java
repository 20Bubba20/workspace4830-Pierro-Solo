import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Info;
import util.UtilDB;


/**
 * Servlet implementation class InsertHibernate
 */
@WebServlet("/InsertHibernate")
public class InsertHibernate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertHibernate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String fullName = request.getParameter("fullName");
		String semester = request.getParameter("semester");
		String email = request.getParameter("email");
		String college = request.getParameter("college");
		
		UtilDB.createStudents(fullName, semester, college, email);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Insert Data to Progress Tracker DB";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	    out.println(docType + 
	    	"<html>\n" + //
	        "<head><title>" + "Progress Tracker Insert"  + "</title></head>\n" + 
	        "<body bgcolor=\"#f0f0f0\">\n" + 
	        "<h1 align=\"center\">" + "Progress Tracker Insert" + "</h1>\n" +
	        "<ul>\n" + 
	        "<li><b>Full Name</b>" + fullName + "\n" +
	        "<li><b>Semester</b>" + semester + "\n" +
	        "<li><b>Email</b>" + email + "\n" +
	        "<li><b>College</b>" + college + "\n</ul>\n");
	    out.println("<a href=/TechExercise/SearchDB.html>Search Data</a><br>");
	    out.println("</body></html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
