import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddUserServlet1")
public class AddUserServlet1 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jdbcURL = "jdbc:mysql://localhost:3306/sales";
        String dbUser = "root";
        String dbPassword = "1234";

        // Fetch parameters from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String id = request.getParameter("id");
        String product = request.getParameter("product");
        String cost = request.getParameter("cost");
        String orders = request.getParameter("orders");

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to the database
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // SQL query to insert the new user with new columns
            String sql = "INSERT INTO saleschart (username, password, id, product, cost, orders) VALUES (?, ?, ?, ?, ?, ?)";

            // Prepare the statement with the SQL query
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, id);
            statement.setString(4, product);
            statement.setString(5, cost);
            statement.setString(6, orders);

            // Execute the query
            statement.executeUpdate();

            // Redirect to dashboard.jsp after successfully adding the user
            response.sendRedirect("dashboard.jsp");
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        } finally {
            try {
                // Close resources to avoid memory leaks
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // If someone tries to access this servlet with a GET request, redirect to the form page
        response.sendRedirect("AddUser.html");  // Change this to the appropriate form or page you want to display
    }
} 