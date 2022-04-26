package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Role {
	
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eg", "root", "admin");
			System.out.print("\nSuccessfully Connected to the Database");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public String insertRole(int role_id, String role_name, String role_description) {
		Connection con = connect();
		String output = "";
		if (con == null) {
			return "Error while connecting to the database";
		}

		String query = " insert into roles (`role_id`,`role_name`,`role_description`)"
				+ " values (?, ?, ?)";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, role_id);
			preparedStmt.setString(2, role_name);
			preparedStmt.setString(3, role_description);


			preparedStmt.execute();
			con.close();
			output = "Inserted Successfully";
		} catch (SQLException e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String readRoles() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading Roles.";
			}
			output = "<table border='1'><tr><th>Role ID</th><th>Role Name</th><th>Description</th></tr>";

			String query = "select * from roles";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String role_id = Integer.toString(rs.getInt("role_id"));
				String role_name = rs.getString("role_name");
				String role_description = rs.getString("role_description");
				

				output += "<tr><td>" + role_id + "</td>";
				output += "<td>" + role_name + "</td>";
				output += "<td>" + role_description + "</td>";
			}
			con.close();

			output += "</table>";
		}

		catch (Exception e) {
			output = "Error while reading the Roles.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateRole(int role_id, String role_name, String role_description)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			String query = " update roles set role_name= ? , role_description = ?  where role_id = ? ";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, role_name);
			preparedStmt.setString(2, role_description);			
			preparedStmt.setInt(3, role_id);

			preparedStmt.execute();
			con.close();
			output = "Updated Successfully";
		} catch (Exception e) {
			output = "Error while updating the Role.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteRole(int role_id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			String query = "delete from roles where role_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, role_id);

			preparedStmt.execute();
			con.close();
			output = "Deleted Successfully";
		} catch (Exception e) {
			output = "Error while deleting the Role.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
