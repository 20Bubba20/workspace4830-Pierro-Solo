
import java.io.IOException;
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

@WebServlet("/ServletDB")
public class ServletDB extends HttpServlet {
   private static final long serialVersionUID = 1L;
   static String url = "jdbc:mysql://ec2-3-131-137-17.us-east-2.compute.amazonaws.com:3306/TechExerciseDB" + "?" + //
         "allowPublicKeyRetrieval=true" + "&" + "useSSL=false";
   static String user = "newmysqlremoteuser2"; // e.g., newmysqlremoteuser
   static String password = "mypassword"; // e.g., mypassword
   static Connection connection = null;
/*
 * url=jdbc:mysql://ec2-3-131-137-17.us-east-2.compute.amazonaws.com:3306/myDB?allowPublicKeyRetrieval=true&useSSL=false
user=newmysqlremoteuser2
password=mypassword
 */
   public ServletDB() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      response.getWriter().println("-------- MySQL College Progress Tracker ------------<br> ");
      // response.getWriter().println(url + "<br>");
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");// ("com.mysql.jdbc.Driver");
      } catch (ClassNotFoundException e) {
         System.out.println("Where is your MySQL JDBC Driver?");
         e.printStackTrace();
         return;
      }
      //response.getWriter().println("MySQL JDBC Driver Registered!<br>");
      connection = null;
      try {
         connection = DriverManager.getConnection(url, user, password);
      } catch (SQLException e) {
         System.out.println("Connection Failed! Check output console");
         e.printStackTrace();
         return;
      }
      if (connection == null) {
         System.out.println("Failed to make connection!");
      }
      try {
         String selectSQL = "SELECT * FROM PROGRESSTRACKERTABLE";// WHERE MYUSER LIKE ?";
         // String theUserName = "user%";
         // response.getWriter().println(selectSQL + "<br>");
         response.getWriter().println("---------------------------------------------------------<br>");
         PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
         // preparedStatement.setString(1, theUserName);
         ResultSet rs = preparedStatement.executeQuery();
         //response.getWriter().println(rs);
         //response.getWriter().println("NAME&nbsp&nbsp&nbsp&nbspEMAIL&nbsp&nbsp&nbsp&nbspSEMESTER&nbsp&nbsp&nbsp&nbspCollege<br>");
         response.getWriter().println("<table> <tr> <th>Name</th> <th>Email</th> <th>Semester</th> <th>College</th> </tr>");
         
         //response.getWriter().println(":");
         while (rs.next()) {
            String id = rs.getString("ID");
            String username = rs.getString("FULLNAME");
            String semester = rs.getString("SEMESTER");
            String email = rs.getString("EMAIL");
            String college = rs.getString("COLLEGE");
            
            //response.getWriter().append("USER ID: " + id + ", ");
            response.getWriter().append("<tr> <td>" + username + "</td>");
            response.getWriter().append("<td>" + email + "</td>");
            response.getWriter().append("<td>" + semester + "</td>");
            response.getWriter().append("<td>" + college + "</td> </tr>");
         }
         response.getWriter().println("</table>");
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
