package clothingstorefranchise.spring.authentication.facade;

import java.util.Collection;

import clothingstorefranchise.spring.authentication.dtos.IdentifiedUserDto;

public interface IIdentifiedUserService {
	
	IdentifiedUserDto getIdentifiedUserByUsername(String username);
	
	IdentifiedUserDto create(IdentifiedUserDto registerUserEvent);
			
	void delete(Long id);
}
