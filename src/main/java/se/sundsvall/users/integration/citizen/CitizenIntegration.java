package se.sundsvall.users.integration.citizen;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CitizenIntegration {

	private static final Logger LOG = LoggerFactory.getLogger(CitizenIntegration.class);
	private final CitizenClient client;
	private final ObjectMapper objectMapper;

	public CitizenIntegration(final CitizenClient client, final ObjectMapper objectMapper) {
		this.client = client;
		this.objectMapper = objectMapper;
	}

	public String getCitizenPartyId(final String CitizenPartyId) {
		try {
			String answer = client.getCitizenPartyId(CitizenPartyId);
			return objectMapper.readValue(answer, String.class);
		} catch (final Exception e) {
			LOG.info("Unable to get this", e);
			return null;
		}
	}
}
