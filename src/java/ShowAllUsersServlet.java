import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

@WebServlet("/ShowAllUsersServlet")
public class ShowAllUsersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jdbcURL = "jdbc:mysql://localhost:3306/sales";
        String dbUser = "root";
        String dbPassword = "1234";

        try {
            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            
            // Query to get all users
            String sql = "SELECT * FROM saleschart";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // Set result in request attribute to forward to JSP
            request.setAttribute("resultSet", resultSet);
            
            // Forward to JSP to display users
            RequestDispatcher dispatcher = request.getRequestDispatcher("show_users.jsp");
            dispatcher.forward(request, response);

            // Close connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}