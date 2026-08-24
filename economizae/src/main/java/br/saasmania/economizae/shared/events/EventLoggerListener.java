package br.saasmania.economizae.shared.events;

import br.saasmania.economizae.identityaccess.domain.events.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;

@Component
public class EventLoggerListener {

    private static final Logger logger = LoggerFactory.getLogger("DomainEvents");

    private final JsonMapper jsonMapper;

    public EventLoggerListener(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @EventListener
    public void onDomainEvent(DomainEvent event) {
        try {
            logger.info("{} -> {}", event.eventName(), jsonMapper.writeValueAsString(event));
        } catch (JacksonException e) {
            logger.warn("Failed to serialize payload for event {}", event.eventName(), e);
        }
    }
}