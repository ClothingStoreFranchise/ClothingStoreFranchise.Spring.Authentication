package clothingstorefranchise.spring.authentication.dtos.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import clothingstorefranchise.spring.common.event.IntegrationEvent;
import clothingstorefranchise.spring.common.types.IEntityDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteWarehouseEmployeeEvent extends IntegrationEvent implements IEntityDto<Long> {

	@JsonProperty("Id")
	private Long id;
	
	public DeleteWarehouseEmployeeEvent(Long id) {
		this.id = id;
	}

	@Override
	public Long key() {
		return id;
	}
}
