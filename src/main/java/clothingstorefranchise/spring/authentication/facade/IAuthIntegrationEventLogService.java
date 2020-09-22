package clothingstorefranchise.spring.authentication.facade;

import clothingstorefranchise.spring.common.event.IntegrationEvent;

public interface IAuthIntegrationEventLogService {
	void publishEventsThroughEventBus(IntegrationEvent event);
	
	void saveIntegrationEventLog(IntegrationEvent event);
}
