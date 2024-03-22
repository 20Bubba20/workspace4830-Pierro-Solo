import java.io.PrintWriter;
import java.util.List;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Student;
import util.Info;
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
        String keyword = "";
    	if (request.getParameter("keyword") != null) {
        	keyword = request.getParameter("keyword").trim();
        }
    	
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Database Result";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
        out.println(docType + //
              "<html>\n" + //
              "<head><title>" + title + "</title></head>\n" + //
              "<body bgcolor=\"#f0f0f0\">\n" + //
              "<h1 align=\"center\">" + title + "</h1>\n");
        out.println("<ul>");

        List<Student> listStudents = null;
        if (keyword != null && !keyword.isEmpty()) {
           listStudents = UtilDB.listStudents(keyword);
        } else {
           listStudents = UtilDB.listStudents();
        }
        display(listStudents, out);
        out.println("</ul>");
        out.println("<a href=/" + Info.projName + "/" + Info.searchWebName + ">Search Data</a> <br>");
        out.println("</body></html>");
     }

     void display(List<Student> listStudents, PrintWriter out) {
    	 for (int i = 0; i < listStudents.size(); i++) {
    		 System.out.printf("[DBG] %d, %s, %d, %s, %s", listStudents.get(i).getID().intValue(), 
    				 listStudents.get(i).getFullName(), listStudents.get(i).getSemester().intValue(),
    				 listStudents.get(i).getEmail(), listStudents.get(i).getCollege());
  		
    		 out.printf("<li> %d, %s, %d, %s, %s", listStudents.get(i).getID().intValue(), 
    				 listStudents.get(i).getFullName(), listStudents.get(i).getSemester().intValue(),
    				 listStudents.get(i).getEmail(), listStudents.get(i).getCollege());
    	 }
     }

     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
     }
  }

