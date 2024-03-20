import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchDB
 */
@WebServlet("/SearchDB")
public class SearchDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String url = "jdbc:mysql://ec2-3-131-137-17.us-east-2.compute.amazonaws.com:3306/TechExerciseDB" + "?" + //
		         "allowPublicKeyRetrieval=true" + "&" + "useSSL=false";
	static String user = "newmysqlremoteuser2"; // e.g., newmysqlremoteuser
	static String password = "mypassword"; // e.g., mypassword
	static Connection connection = null;
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
		// TODO Auto-generated method stub
		String keyword = request.getParameter("keyword");
	    search(keyword, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	void search(String keyword, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
	            "transitional//en\">\n"; //
	      		out.println(docType + //
	            "<html>\n" + //
	            "<head><title>" + "Progress Tracker Result"  + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h1 align=\"center\">" + "Progress Tracker Result" + "</h1>\n");
	 
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    connection = null;

	    try {
	    	DBConnection.getDBConnection(getServletContext());
	    	connection = DBConnection.connection;
	    	
	    	String selectSQL;
	    	if (keyword.isEmpty()) {
	    		selectSQL = "SELECT * FROM PROGRESSTRACKERTABLE";
	    		preparedStatement = connection.prepareStatement(selectSQL);
	    	}
	    	else {
	    		selectSQL = "SELECT * FROM PROGRESSTRACKERTABLE WHERE " + keyword + " LIKE ?";
	    		String searchTerm = keyword + "%";
			    preparedStatement = connection.prepareStatement(selectSQL);
			    preparedStatement.setString(1, searchTerm);
	    	}
	    	ResultSet rs = preparedStatement.executeQuery();
	    	
	    	while (rs.next()) {
	    		int id = rs.getInt("id");
	    		String fullName = rs.getString("FULLNAME").trim();
	    		String semester = rs.getString("SEMESTER").trim();
	    		String email = rs.getString("EMAIL").trim();
	    		String college = rs.getString("COLLEGE").trim();
	    		
	    		if (keyword.isEmpty() || fullName.contains(keyword)) {
	    			out.println("ID: " + id + ", ");
	    			out.println("Full Name: " + fullName + ",");
	    			out.println("Semester: " + semester + ",");
	    			out.println("Email: " + email + ",");
	    			out.println("College: " + college + "<br>");
	    		}
	    	}
	    	out.println("<a href=/TechExercise/FormInput.html>Search Data</a><br>");
		    out.println("</body></html>");
		    rs.close();
		    preparedStatement.close();
		    connection.close();

	    }
	    catch (Exception Ex){
	    	Ex.printStackTrace();
	    }
	    finally {
	    	try {
	    		if (preparedStatement != null) {
	    			preparedStatement.close();
	    		}
	    	}
	    	catch (Exception Ex) {
	    	}
	    	try {
	    		if (connection != null) {
	    			connection.close();
	    		}
	    	}
	    	catch (Exception Ex) {
	    	}
	    }
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
