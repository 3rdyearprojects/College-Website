package com.servlet;
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

@WebServlet("/GetStudentDetailsServlet")
public class GetStudentDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String uid = request.getParameter("uid");

        try {
            // Establish a database connection
            Connection con = DriverManager.getConnection("jdbc:mysql://your_database_url", "username", "password");

            // Retrieve student details and pending fees
            String query = "SELECT student_name, pending_fees FROM your_table WHERE uid = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, uid);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String studentName = rs.getString("student_name");
                double pendingFees = rs.getDouble("pending_fees");

                // Send the response to the client-side
                out.print(studentName + "," + pendingFees);
            } else {
                out.print("not_found");
            }

            con.close();
        } catch (Exception e) {
            out.print("error");
            e.printStackTrace();
        }
    }
}
