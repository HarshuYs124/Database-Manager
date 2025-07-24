import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the updated user details from the form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String id = request.getParameter("id");
        String product = request.getParameter("product");
        String cost = request.getParameter("cost");
        String orders = request.getParameter("orders");

        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/sales";
        String dbUser = "root";
        String dbPassword = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Update the user details in the database
            String sql = "UPDATE saleschart SET password=?, id=?, product=?, cost=?, orders=? WHERE username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, password);
            statement.setString(2, id);
            statement.setString(3, product);
            statement.setString(4, cost);
            statement.setString(5, orders);
            statement.setString(6, username);

            statement.executeUpdate();

            // Close connection
            statement.close();
            connection.close();

            // After updating, redirect back to the ShowAllUsersServlet (or wherever you want)
            response.sendRedirect("ShowAllUsersServlet");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
