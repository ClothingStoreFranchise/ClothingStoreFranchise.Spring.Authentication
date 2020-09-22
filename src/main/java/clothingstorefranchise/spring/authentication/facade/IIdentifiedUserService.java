package clothingstorefranchise.spring.authentication.facade;

import java.util.Collection;

import clothingstorefranchise.spring.authentication.dto.IdentifiedUserDto;
import clothingstorefranchise.spring.authentication.dto.event.RegisterUserEvent;

public interface IIdentifiedUserService {
	
	IdentifiedUserDto getIdentifiedUserByUsername(String username);
	
	void create(RegisterUserEvent registerUserEvent);
	
	void update(IdentifiedUserDto identifiedUserDto);
	
	void delete(String username);
	
	Collection<IdentifiedUserDto> getAll();
}
