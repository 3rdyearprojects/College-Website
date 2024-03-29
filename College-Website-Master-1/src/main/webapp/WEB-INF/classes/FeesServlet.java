import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;  // Import from org.json library

@WebServlet("/FeesServlet")
public class FeesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String[] courses = request.getParameterValues("course");
        String uid = request.getParameter("uid");

        JSONObject jsonResponse = new JSONObject();

        try {
            if (courses != null && courses.length > 0) {
                String url = "jdbc:oracle:thin:@localhost:1521:orcl";
                String username = "raj";
                String password = "tancent";

                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection conn = DriverManager.getConnection(url, username, password);

                String query = "SELECT student_name, pending_fees_detail FROM fees_payment WHERE uid_number = ? AND course = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, uid);
                pstmt.setString(2, courses[0]);

                ResultSet resultSet = pstmt.executeQuery();

                if (resultSet.next()) {
                    String studentName = resultSet.getString("student_name");
                    String pendingFees = resultSet.getString("pending_fees_detail");

                    jsonResponse.put("success", true);
                    jsonResponse.put("studentName", studentName);
                    jsonResponse.put("pendingFees", pendingFees);
                } else {
                    jsonResponse.put("success", false);
                    jsonResponse.put("message", "No data found for the given UID and Course.");
                }

                resultSet.close();
                pstmt.close();
                conn.close();
            } else {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "No course selected.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Error fetching data from the database: " + e.getMessage());
        }

        out.print(jsonResponse);
        out.flush();
    }
}
