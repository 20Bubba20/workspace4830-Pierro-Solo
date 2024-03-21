import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormInput
 */
@WebServlet("/FormInput")
public class FormInput extends HttpServlet {
	private static final long serialVersionUID = 1L;  /**
     * @see HttpServlet#HttpServlet()
     */
    public FormInput() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
        //String username = rs.getString("FULLNAME");
        //String semester = rs.getString("SEMESTER");
        //String email = rs.getString("EMAIL");
        //String college = rs.getString("COLLEGE");
		String fullName = request.getParameter("fullName");
		String semester = request.getParameter("semester");
		String email = request.getParameter("email");
		String college = request.getParameter("college");
		
		Connection connection = null;
		String insertSql = " INSERT INTO PROGRESSTRACKERTABLE(ID, FULLNAME, SEMESTER, EMAIL, COLLEGE) values (default, ?, ?, ?, ?)";
		
		try {
			DBConnection.getDBConnection(getServletContext());
			connection = DBConnection.connection;
			//connection = DriverManager.getConnection(url, user, password);
			PreparedStatement statement = connection.prepareStatement(insertSql);
			//fullName = "Jackson Cunningham";
			statement.setString(1, fullName);
			statement.setString(2, semester);
			statement.setString(3, email);
			statement.setString(4, college);
			statement.execute();
			connection.close();
		}
		catch (Exception Ex) {
			Ex.printStackTrace();
		}
		
		response.setContentType("text/html;charset=UTF-8");
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
