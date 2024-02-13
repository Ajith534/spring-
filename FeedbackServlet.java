import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class FeedbackServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve feedback data from request parameters
        String name = request.getParameter("name");
        String bookName = request.getParameter("bookName");
        String feedback = request.getParameter("feedback");

        // JDBC connection parameters
        String url = "jdbc:mysql://localhost:3306/your_database";
        String username = "your_username";
        String password = "your_password";

        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create a prepared statement
            String sql = "INSERT INTO feedback (name, book_name, feedback) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, bookName);
            statement.setString(3, feedback);

            // Execute the statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                response.getWriter().println("Feedback saved!");
            }

            // Close the connection
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: Feedback not saved!");
        }
    }
}
