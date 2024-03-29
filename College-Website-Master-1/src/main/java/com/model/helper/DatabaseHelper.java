package com.model.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseHelper {
	private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String username = "raj";
	private static final String password = "tancent";

	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection(url, username, password);
	}

	public static String getStudentName(String uid) {
		String studentName = "";
		try {
			Connection con = getConnection();
			String query = "SELECT student_name FROM fees_payment WHERE uid_number = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, uid);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				studentName = rs.getString("student_name");
			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentName;
	}

	public static String getPendingFees(String uid) {
		String pendingFees = "";
		try {
			Connection con = getConnection();
			String query = "SELECT pending_fees_detail FROM fees_payment WHERE uid_number = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, uid);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				pendingFees = rs.getString("pending_fees_detail");
			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pendingFees;
	}

	public static int insertAdmissionFormData(String yourName, String emailAddress, String phone, String gender,
			java.sql.Date dob, String course, String fileName, String password1) {
		try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO admission_form (your_name, email_address, phone, gender, dob, course, file_name, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
			pstmt.setString(1, yourName);
			pstmt.setString(2, emailAddress);
			pstmt.setString(3, phone);
			pstmt.setString(4, gender);
			pstmt.setDate(5, dob);
			pstmt.setString(6, course);
			pstmt.setString(7, fileName);
			pstmt.setString(8, password1);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1; // Indicates an error
		}
	}

}
