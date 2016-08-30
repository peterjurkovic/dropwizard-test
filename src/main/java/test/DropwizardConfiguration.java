package test;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import io.dropwizard.util.Duration;

public class DropwizardConfiguration extends Configuration {

	@NotEmpty
	@JsonProperty
	private String defaultName = "Stranger";

	@Valid
	@NotNull
	private HttpClientConfiguration httpClient;

	public String getDefaultName() {
		return defaultName;
	}

	public DropwizardConfiguration() {
		httpClient = new HttpClientConfiguration();
		httpClient.setMaxConnections(2048);
		httpClient.setMaxConnectionsPerRoute(2048);
		httpClient.setConnectionTimeout(Duration.milliseconds(5000));
		httpClient.setConnectionRequestTimeout(Duration.milliseconds(5000));
		httpClient.setKeepAlive(Duration.seconds(1));
	}

	@JsonProperty("httpClient")
	public HttpClientConfiguration getHttpClientConfiguration() {
		return httpClient;
	}

	@JsonProperty("httpClient")
	public void setHttpClientConfiguration(HttpClientConfiguration httpClient) {
		this.httpClient = httpClient;
	}

}
