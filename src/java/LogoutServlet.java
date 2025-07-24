import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Invalidate the session if it exists
        HttpSession session = request.getSession(false);
        if (session != null) {
            // Optionally remove any attributes that may have been stored in the session
            session.removeAttribute("username"); // Example: remove 'username' attribute if present

            // Invalidate the session to clear all session data
            session.invalidate();
        }

        // Redirect to the login page (index.html or login.jsp)
        response.sendRedirect("index.html"); // Or use "login.jsp" depending on your setup
    }
}
