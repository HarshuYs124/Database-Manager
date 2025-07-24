import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/sales";  // Update with your database URL
        String dbUser = "root";  // Your database username
        String dbPassword = "1234";  // Your database password
        
        // Get username and password from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Flag to check if user is valid
        boolean userValid = false;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            
            // SQL query to check the credentials
            String sql = "SELECT * FROM saleschart WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery();
            
            // Check if a user exists with the provided username and password
            if (resultSet.next()) {
                userValid = true; // User found
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
            return;
        }

        if (userValid) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("dashboard.html"); // Update with your dashboard page
        } else {
            response.sendRedirect("index.html?error=true"); // Update with your login page
        }
    }
}