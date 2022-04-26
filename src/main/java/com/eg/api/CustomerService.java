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
import model.Customer;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/customer")
public class CustomerService {

	Customer customerObj = new Customer();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomers() {
		return customerObj.readCustomers();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(@FormParam("CustomerID") int CustomerID,@FormParam("CustomerName") String CustomerName,
			@FormParam("CustomerEmail") String CustomerEmail, @FormParam("CustomerType") String CustomerType,
			@FormParam("CustomerContact") String CustomerContact) {
		String output = customerObj.insertCustomer(CustomerID,CustomerName, CustomerEmail, CustomerType, CustomerContact);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(String customerData) {

		JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject();

		String CustomerID = customerObject.get("CustomerID").getAsString();
		String CustomerName = customerObject.get("CustomerName").getAsString();
		String CustomerEmail = customerObject.get("CustomerEmail").getAsString();
		String CustomerType = customerObject.get("CustomerType").getAsString();
		String CustomerContact = customerObject.get("CustomerContact").getAsString();
		String output = customerObj.updateCustomer(CustomerID, CustomerName, CustomerEmail, CustomerType,
				CustomerContact);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(String customerData) {

		JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject();


		int CustomerID = customerObject.get("CustomerID").getAsInt();
		String output = customerObj.deleteCustomer(CustomerID);
		return output;
	}
	
	
	
}
