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
import model.Role;

@Path("/role")
public class RoleService {

	Role role = new Role();
		
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readRoles() {
		return role.readRoles();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertRole(
			@FormParam("role_id") int role_id,
			@FormParam("role_name") String role_name,
			@FormParam("role_description") String role_description) {
		String output = role.insertRole(role_id,role_name, role_description);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateRole(String id) {

		JsonObject roleObj = new JsonParser().parse(id).getAsJsonObject();
		int role_id = roleObj.get("role_id").getAsInt();
		String role_name = roleObj.get("role_name").getAsString();
		String role_description = roleObj.get("role_description").getAsString();
		String output = role.updateRole(role_id, role_name, role_description);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteRole(String id) {

		JsonObject roleObj = new JsonParser().parse(id).getAsJsonObject();
		int role_id = roleObj.get("role_id").getAsInt();
		String output = role.deleteRole(role_id);
		return output;
	}
	
}
