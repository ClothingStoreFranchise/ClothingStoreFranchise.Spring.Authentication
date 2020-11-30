package clothingstorefranchise.spring.authentication.facade.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clothingstorefranchise.spring.authentication.RabbitMqConfig;
import clothingstorefranchise.spring.authentication.facade.IAuthIntegrationEventLogService;
import clothingstorefranchise.spring.common.event.IIntegrationEventLogService;
import clothingstorefranchise.spring.common.event.IntegrationEvent;

@Service
public class AuthIntegrationEventLogService implements IAuthIntegrationEventLogService{
	
	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private IIntegrationEventLogService inEventLogService;
		
	public void publishEventsThroughEventBus(IntegrationEvent event) {
/*
		try {
			//logs
			inEventLogService.putEventAsInProgress(event.getEventId());
			rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, event.getClass().getSimpleName(), event);
			inEventLogService.putEventAsPublished(event.getEventId());
		}catch(Exception e) {
			inEventLogService.putEventAsFailed(event.getEventId());
		}*/
	}
	
	public void saveIntegrationEventLog(IntegrationEvent event) {
		inEventLogService.saveEvent(event);
	}
}
