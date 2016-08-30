package test.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import test.api.User;


@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	
	private ExternalUserResourceService userService;
	
	public UserResource(ExternalUserResourceService userService){
		this.userService = userService;
	}
    
	@GET
    @Path("/slow-user")
    public User getSlowUser() {
        return userService.loadSlowUser();
    }
	
	
	@GET
    @Path("/fast-user")
    public User getfastUser() {
        return userService.loadFastUser();
    }
}
