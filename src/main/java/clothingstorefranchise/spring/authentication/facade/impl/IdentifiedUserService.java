package clothingstorefranchise.spring.authentication.facade.impl;

import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clothingstorefranchise.spring.authentication.dtos.IdentifiedUserDto;
import clothingstorefranchise.spring.authentication.facade.IIdentifiedUserService;
import clothingstorefranchise.spring.authentication.model.IdentifiedUser;
import clothingstorefranchise.spring.authentication.repositories.IIdentifiedUserRepository;
import clothingstorefranchise.spring.common.exceptions.EntityAlreadyExistsException;

@Service
public class IdentifiedUserService extends BaseService<IdentifiedUser, Long, IIdentifiedUserRepository> implements IIdentifiedUserService{
		
	@Autowired
	public IdentifiedUserService(IIdentifiedUserRepository identifiedUserRepository, ModelMapper modelMapper) {
		super(IdentifiedUser.class, identifiedUserRepository);
	}
	
	public IdentifiedUserDto getIdentifiedUserByUsername(String username) {

		IdentifiedUser identifiedUser = repository.findByUsername(username);
		return modelMapper.map(identifiedUser, IdentifiedUserDto.class);
	}

	@Override
	public IdentifiedUserDto create(IdentifiedUserDto userDto){
		if(checkIfUsernameExists(userDto.getUsername())) {
			throw new EntityAlreadyExistsException(
		              "There is an account with that username: " 
		              + userDto.getUsername());
		}
		
		String encryptedPass = new BCryptPasswordEncoder().encode(userDto.getPassword());
		IdentifiedUser identifiedUser = modelMapper.map(userDto, IdentifiedUser.class);
		identifiedUser.setPassword(encryptedPass);
		repository.save(identifiedUser);
		
		return modelMapper.map(identifiedUser, IdentifiedUserDto.class);
	}
	
	private boolean checkIfUsernameExists(String username) {
		return repository.findByUsername(username) != null;
	}
}
