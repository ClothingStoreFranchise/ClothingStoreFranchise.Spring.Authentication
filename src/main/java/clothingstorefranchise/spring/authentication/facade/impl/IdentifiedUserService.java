package clothingstorefranchise.spring.authentication.facade.impl;

import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import clothingstorefranchise.spring.authentication.dto.IdentifiedUserDto;
import clothingstorefranchise.spring.authentication.dto.event.RegisterUserEvent;
import clothingstorefranchise.spring.authentication.facade.IIdentifiedUserService;
import clothingstorefranchise.spring.authentication.model.IdentifiedUser;
import clothingstorefranchise.spring.authentication.repositories.IIdentifiedUserRepository;

@Service
public class IdentifiedUserService implements IIdentifiedUserService{

	private IIdentifiedUserRepository identifiedUserRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public IdentifiedUserService(IIdentifiedUserRepository identifiedUserRepository, ModelMapper modelMapper) {
		this.identifiedUserRepository = identifiedUserRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public IdentifiedUserDto getIdentifiedUserByUsername(String username) {

		IdentifiedUser identifiedUser = identifiedUserRepository.findByUsername(username);
		return modelMapper.map(identifiedUser, IdentifiedUserDto.class);
	}

	@Override
	public void create(RegisterUserEvent registerUserEvent) {
		String encryptedPass = new BCryptPasswordEncoder().encode(registerUserEvent.getPassword());
		registerUserEvent.setPassword(encryptedPass);
		IdentifiedUser identifiedUser = modelMapper.map(registerUserEvent, IdentifiedUser.class);
		identifiedUserRepository.save(identifiedUser);
	}

	@Override
	public void update(IdentifiedUserDto identifiedUserDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<IdentifiedUserDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
