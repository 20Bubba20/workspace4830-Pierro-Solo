import java.io.PrintWriter;
import java.util.List;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Student;
import util.UtilDB;

/**
 * Servlet implementation class ServletHibernateDB
 */
@WebServlet("/ServletHibernateDB")
public class ServletHibernateDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletHibernateDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	void retrieveDisplayData(PrintWriter out) {
		//PrintWriter out = response.getWriter();
		//String title = "Insert Data to Progress Tracker DB";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	    out.println(docType + 
	    	"<html>\n" + //
	        "<head><title>" + "Database Result"  + "</title></head>\n" + 
	        "<body bgcolor=\"#f0f0f0\">\n" + 
	        "<h1 align=\"center\">" + "Progress Tracker Insert" + "</h1>\n");
	    out.println("<ul>");
	    List<Student> listStudents = UtilDB.listStudents();
	    for (int i = 0; i < listStudents.size(); i++) {
	    	//" + listStudents.get(i).getID() + ", " + listStudents.get(i).getFullName() 
	    	System.out.printf("[DBG] %d, %s, %d, %s, %s", listStudents.get(i).getID().intValue(), 
	    			listStudents.get(i).getFullName(), listStudents.get(i).getSemester().intValue(),
	    			listStudents.get(i).getEmail(), listStudents.get(i).getCollege());
	    	out.printf("<li> %d, %s, %d, %s, %s", listStudents.get(i).getID().intValue(), 
	    			listStudents.get(i).getFullName(), listStudents.get(i).getSemester().intValue(),
	    			listStudents.get(i).getEmail(), listStudents.get(i).getCollege());
	    }
	    out.println("</ul>");
	    out.println("</body></html>");
	}

}
