package com.eg.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.Bill;

@Path("/bill")
public class BillService {
	
	Bill bill = new Bill();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBills() {
		return bill.readBills();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBill(
			@FormParam("bill_id") int bill_id,
			@FormParam("bill_cus_id") String bill_cus_id,
			@FormParam("payment_date") String payment_date, 
			@FormParam("invoice_no") String invoice_no,
			@FormParam("units") String units,
			@FormParam("total_amount") String total_amount) {
		String output = bill.insertBill(bill_id,bill_cus_id, payment_date, invoice_no, units, total_amount);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBill(String BillData) {
		// Convert the input string to a JSON object
		JsonObject billect = new JsonParser().parse(BillData).getAsJsonObject();
		// Read the values from the JSON object
		int bill_id = billect.get("bill_id").getAsInt();
		String bill_cus_id = billect.get("bill_cus_id").getAsString();
		String payment_date = billect.get("payment_date").getAsString();
		String invoice_no = billect.get("invoice_no").getAsString();
		String units = billect.get("units").getAsString();
		String total_amount = billect.get("total_amount").getAsString();

		String output = bill.updateBill(bill_id, bill_cus_id, payment_date, invoice_no, units, total_amount);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(String BillData) {
		// Convert the input string to an XML document
		JsonObject billect = new JsonParser().parse(BillData).getAsJsonObject();

		// Read the value from the element <BillID>
		int bill_id = billect.get("bill_id").getAsInt();
		String output = bill.deleteBill(bill_id);
		return output;
	}
}
