package clothingstorefranchise.spring.authentication.eventhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import clothingstorefranchise.spring.authentication.RabbitMqConfig;
import clothingstorefranchise.spring.authentication.dto.event.RegisterUserEvent;
import clothingstorefranchise.spring.authentication.facade.IIdentifiedUserService;
import clothingstorefranchise.spring.common.event.IIntegrationEventHandler;

@Component
public class RegisterUserHandler implements IIntegrationEventHandler<RegisterUserEvent> {
	
	private final Logger logger = LoggerFactory.getLogger(RabbitMqConfig.class);

	@Autowired
	private IIdentifiedUserService identifiedUserService;
    
    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    public void handle(RegisterUserEvent event) {
    	
    	logger.info("RegisterUserEvent recived");
    	identifiedUserService.create(event);
    }
}
