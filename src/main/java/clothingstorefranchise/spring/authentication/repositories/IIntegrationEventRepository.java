package clothingstorefranchise.spring.authentication.repositories;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import clothingstorefranchise.spring.authentication.model.IntegrationEventLog;

public interface IIntegrationEventRepository extends JpaRepository<IntegrationEventLog, UUID>{
	List<IntegrationEventLog> findByEventIdAndStateOrderByCreationTimeDesc(UUID eventId, int state);
}
