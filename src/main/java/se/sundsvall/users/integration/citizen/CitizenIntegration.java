package se.sundsvall.users.integration.citizen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CitizenIntegration {

    private static final Logger LOG = LoggerFactory.getLogger(CitizenIntegration.class);
    private final CitizenClient client;

    public CitizenIntegration(final CitizenClient client) {
        this.client = client;
    }

    public String getTest(final String test) {
        try {
            return client.getTest(test);
        } catch (final Exception e) {
            LOG.info("Unable to get this", e);
            return null;
        }
    }
}
