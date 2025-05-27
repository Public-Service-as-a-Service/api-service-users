package se.sundsvall.users.integration.citizen;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import se.sundsvall.users.integration.citizen.configuration.CitizenIntegrationConfiguration;

import static se.sundsvall.users.integration.citizen.configuration.CitizenIntegrationConfiguration.CLIENT_ID;

@FeignClient(
	name = CLIENT_ID,
	url = "${integration.citizen.base-url}",
	configuration = CitizenIntegrationConfiguration.class)
@CircuitBreaker(name = CLIENT_ID)
public interface CitizenClient {
	@GetMapping("/api/v2/citizen/{personNumber}/guid")
	String getCitizenPartyId(@PathVariable String personNumber, @RequestParam("municipalityId") String municipalityId);
}
