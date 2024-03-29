<%@ page import="com.model.helper.DatabaseHelper" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>

<%
    try {
        String yourName = request.getParameter("yourname");
        String emailAddress = request.getParameter("emailaddress");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String dobString = request.getParameter("dob");
        java.sql.Date dob = java.sql.Date.valueOf(dobString);
        String course = request.getParameter("course");
        String fileName = request.getParameter("file");
        String password1 = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmpassword");

        int rowsAffected = DatabaseHelper.insertAdmissionFormData(yourName, emailAddress, phone, gender, dob, course, fileName, password1);
																
        if (rowsAffected > 0) {
            out.println("<script>alert('Thanks for filling out our form!');</script>");
            response.sendRedirect(request.getContextPath() + "/index.html#course_call");
        } else {
            out.println("<script>alert('Failed to submit the form. Please try again.');</script>");
        }
    } catch (Exception e) {
        out.println("<script>alert('An error occurred: " + e.getMessage() + "');</script>");
        e.printStackTrace();
    }
%>
