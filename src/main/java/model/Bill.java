package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Bill {
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
	
	
	public String readBills() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading Bills.";
			}

			output = "<table border='1'><tr><th>Bill ID</th><th>Customer ID</th><th>Payement Date</th>" + "<th>Invoice No</th>"+ "<th>Units</th>"
					+ "<th>Total Amount</th> </tr>";

			String query = "select * from bills";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String bill_id = Integer.toString(rs.getInt("bill_id"));
				String bill_cus_id = rs.getString("bill_cus_id");
				String payment_date = rs.getString("payment_date");
				String invoice_no = rs.getString("invoice_no");
				String units = rs.getString("units");
				String total_amount = rs.getString("total_amount");


				output += "<tr><td>" + bill_id + "</td>";
				output += "<td>" + bill_cus_id + "</td>";
				output += "<td>" + payment_date + "</td>";
				output += "<td>" + invoice_no + "</td>";
				output += "<td>" + units + "</td>";
				output += "<td>" + total_amount + "</td>";

			}
			con.close();

			output += "</table>";
		}

		catch (Exception e) {
			output = "Error while reading the Bills.";
			System.err.println(e.getMessage());
		}
		return output;
	}


	public String insertBill(int bill_id, String bill_cus_id, String payment_date, String invoice_no, String units, String total_amount) {
		Connection con = connect();
		String output = "";
		if (con == null) {
			return "Error while connecting to the database";
		}


		String query = " insert into bills (`bill_id`,`bill_cus_id`,`payment_date`,`invoice_no`,`units`,`total_amount`)"
				+ " values (?, ?, ?, ?, ?, ?)";
		
		
		
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(query);
			
			int unit = Integer.parseInt(units);
			int total = unit * 20;
			
			preparedStmt.setInt(1, bill_id);
			preparedStmt.setString(2, bill_cus_id);
			preparedStmt.setString(3, payment_date);
			preparedStmt.setString(4, invoice_no);
			preparedStmt.setString(5, units);
			preparedStmt.setInt(6, total);

			preparedStmt.execute();
			con.close();
			output = "Inserted Successfully";
		} catch (SQLException e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}

		return output;
	}



	public String updateBill(int bill_id, String bill_cus_id, String payment_date, String invoice_no, String units, String total_amount)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement

			String query = " update bills set bill_cus_id= ? , payment_date = ? , invoice_no = ? , units = ?, total_amount = ?  where bill_id = ? ";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			int unit = Integer.parseInt(units);
			int total = unit * 20;
			
			preparedStmt.setString(1, bill_cus_id);
			preparedStmt.setString(2, payment_date);
			preparedStmt.setString(3, invoice_no);
			preparedStmt.setString(4, units);
			preparedStmt.setInt(5, total);
			preparedStmt.setInt(6, bill_id);

			preparedStmt.execute();
			con.close();
			output = "Updated Successfully";
		} catch (Exception e) {
			output = "Error while updating the Bill.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteBill(int BillID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			String query = "delete from bills where bill_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, BillID);

			preparedStmt.execute();
			con.close();
			output = "Deleted Successfully";
		} catch (Exception e) {
			output = "Error while deleting the Bill.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
