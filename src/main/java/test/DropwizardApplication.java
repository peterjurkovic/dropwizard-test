package test;


import org.apache.http.client.HttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.Application;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import test.resources.ExternalUserResourceService;
import test.resources.UserResource;

public class DropwizardApplication extends Application<DropwizardConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DropwizardConfiguration config,   final Environment environment) {
    	final HttpClient httpClient = new HttpClientBuilder(environment).using(config.getHttpClientConfiguration()).build("httpClient");
    	final ObjectMapper objectMapper = new ObjectMapper();
    	final ExternalUserResourceService userService = new ExternalUserResourceService(httpClient, objectMapper);
    	final UserResource userSevice = new UserResource(userService);
        environment.jersey().register(userSevice);
    }

}
