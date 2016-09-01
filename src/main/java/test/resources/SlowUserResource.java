package test.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import test.api.User;

@Path("/slow-user")
@Produces(MediaType.APPLICATION_JSON)
public class SlowUserResource {

	private ExternalUserResourceService userService;

	public SlowUserResource(ExternalUserResourceService userService) {
		this.userService = userService;
	}

	@GET
	public User getSlowUser() {
		return userService.loadSlowUser();
	}

}
