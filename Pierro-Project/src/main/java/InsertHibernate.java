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
		String fullName = request.getParameter("fullName").trim();
		String semester = request.getParameter("semester").trim();
		String email = request.getParameter("email").trim();
		String college = request.getParameter("college").trim();
		UtilDB.createStudents(fullName, semester, college, email);

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
	        "<h1 align=\"center\">" + title + "</h1>\n" +
	        "<ul>\n" + 
	        "<li><b>Full Name</b>" + fullName + "\n" +
	        "<li><b>Semester</b>" + semester + "\n" +
	        "<li><b>Email</b>" + email + "\n" +
	        "<li><b>College</b>" + college + "\n</ul>\n");
      out.println("<a href=/" + Info.projName + "/" + Info.searchWebName + ">Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
