import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Student;
import util.UtilDB;

/**
 * Servlet implementation class SearchDB
 */
@WebServlet("/SearchDB")
public class SearchDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String title = "Oops, Wrong Site";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		out.println(docType + //
				"<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h2 align=\"center\">" + title + "</h2>\n" +
	            "<p> Go to: ./SearchHibernateDB.html </p>\n");
				
	      
		out.println("</body></html>");
    	//response.setContentType("text/html");
        //retrieveDisplayData(response.getWriter());
     }

     void retrieveDisplayData(PrintWriter out) {
        String title = "Database Result";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + 
              "transitional//en\">\n"; 
        out.println(docType + 
              "<html>\n" + 
              "<head><title>" + title + "</title></head>\n" + 
              "<body bgcolor=\"#f0f0f0\">\n" + 
              "<h1 align=\"center\">" + title + "</h1>\n");
        out.println("<ul>");
        List<Student> listStudents = UtilDB.listStudents();
        for (int i = 0; i < listStudents.size(); i++) {
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

     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
     }
  }