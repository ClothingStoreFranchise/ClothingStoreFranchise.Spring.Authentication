package clothingstorefranchise.spring.authentication.facade;

import clothingstorefranchise.spring.authentication.dtos.IdentifiedUserDto;

public interface IIdentifiedUserService {
	
	IdentifiedUserDto getIdentifiedUserByUsername(String username);
	
	IdentifiedUserDto create(IdentifiedUserDto user);
			
	void delete(Long id);
}
