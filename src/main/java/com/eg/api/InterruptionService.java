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
import model.Interruption;

@Path("/interrupt")
public class InterruptionService {
	
	Interruption intr = new Interruption();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readInterrupts() {
		return intr.readInterrupt();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertInterrupt(
			@FormParam("intpr_id") int intr_id,
			@FormParam("intpr_time") String intr_time,
			@FormParam("intpr_day") String intr_day,
			@FormParam("intpr_zone") String intr_zone) {
		String output = intr.insertInterrupt(intr_id,intr_time,intr_day, intr_zone);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateInterrupt(String id) {

		JsonObject InterruptObj = new JsonParser().parse(id).getAsJsonObject();
		int intr_id = InterruptObj.get("intpr_id").getAsInt();
		String intr_time = InterruptObj.get("intpr_time").getAsString();
		String intr_day = InterruptObj.get("intpr_day").getAsString();
		String intr_zone = InterruptObj.get("intpr_zone").getAsString();
		String output = intr.updateInterrupt(intr_id, intr_time, intr_day, intr_zone);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInterrupt(String id) {

		JsonObject InterruptObj = new JsonParser().parse(id).getAsJsonObject();
		int intpr_id = InterruptObj.get("intpr_id").getAsInt();
		String output = intr.deleteInterrupt(intpr_id);
		return output;
	}
}
