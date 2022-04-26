package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Interruption {
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

	public String insertInterrupt(int intpr_id, String intpr_time, String intpr_day, String intpr_zone) {
		Connection con = connect();
		String output = "";
		if (con == null) {
			return "Error while connecting to the database";
		}

		String query = " insert into interruptions (`intpr_id`,`intpr_time`,`intpr_day`,`intpr_zone`)"
				+ " values (?, ?, ?, ?)";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, intpr_id);
			preparedStmt.setString(2, intpr_time);
			preparedStmt.setString(3, intpr_day);
			preparedStmt.setString(4, intpr_zone);



			preparedStmt.execute();
			con.close();
			output = "Inserted Successfully";
		} catch (SQLException e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String readInterrupt() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading Interrupts.";
			}
			output = "<table border='1'><tr><th>Interrupt ID</th><th>Interrupt Time</th><th>Interrupt Day</th><th>Interrupt Zone</th></tr>";

			String query = "select * from interruptions";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String intpr_id = Integer.toString(rs.getInt("intpr_id"));
				String intpr_time = rs.getString("intpr_time");
				String intpr_day = rs.getString("intpr_day");
				String intpr_zone = rs.getString("intpr_zone");

				

				output += "<tr><td>" + intpr_id + "</td>";
				output += "<td>" + intpr_time + "</td>";
				output += "<td>" + intpr_day + "</td>";
				output += "<td>" + intpr_zone + "</td>";
			}
			con.close();

			output += "</table>";
		}

		catch (Exception e) {
			output = "Error while reading the Interrupts.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateInterrupt(int intpr_id, String intpr_time, String intpr_day, String intpr_zone)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			String query = " update interruptions set intpr_time= ? , intpr_day = ?, intpr_zone = ?  where intpr_id = ? ";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, intpr_time);
			preparedStmt.setString(2, intpr_day);
			preparedStmt.setString(3, intpr_zone);			
			preparedStmt.setInt(4, intpr_id);

			preparedStmt.execute();
			con.close();
			output = "Updated Successfully";
		} catch (Exception e) {
			output = "Error while updating the Interrupt.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteInterrupt(int Interrupt_id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			String query = "delete from interruptions where intpr_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, Interrupt_id);

			preparedStmt.execute();
			con.close();
			output = "Deleted Successfully";
		} catch (Exception e) {
			output = "Error while deleting the Interrupt.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
