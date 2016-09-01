package test.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import test.api.User;

@Path("/fast-user")
@Produces(MediaType.APPLICATION_JSON)
public class FastUserResource {

	private ExternalUserResourceService userService;

	public FastUserResource(ExternalUserResourceService userService) {
		this.userService = userService;
	}

	@GET
	public User getfastUser() {
		return userService.loadFastUser();
	}
}
