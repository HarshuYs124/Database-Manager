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
import javax.servlet.RequestDispatcher;

@WebServlet("/EditProductServlet")
public class EditProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");

        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/sales";
        String dbUser = "root";
        String dbPassword = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Get user details for the specified username
            String sql = "SELECT * FROM saleschart WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Set user data as request attributes for the edit form
                request.setAttribute("username", resultSet.getString("username"));
                request.setAttribute("password", resultSet.getString("password"));
                request.setAttribute("id", resultSet.getString("id"));
                request.setAttribute("product", resultSet.getString("product"));
                request.setAttribute("cost", resultSet.getString("cost"));
                request.setAttribute("orders", resultSet.getString("orders"));
                
                // Forward to the edit user JSP
                RequestDispatcher dispatcher = request.getRequestDispatcher("editUserForm.jsp");
                dispatcher.forward(request, response);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
