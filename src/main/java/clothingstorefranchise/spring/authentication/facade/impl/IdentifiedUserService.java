package clothingstorefranchise.spring.authentication.facade.impl;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clothingstorefranchise.spring.authentication.RabbitMqConfig;
import clothingstorefranchise.spring.authentication.dtos.IdentifiedUserDto;
import clothingstorefranchise.spring.authentication.dtos.events.DeleteShopEmployeeEvent;
import clothingstorefranchise.spring.authentication.dtos.events.DeleteWarehouseEmployeeEvent;
import clothingstorefranchise.spring.authentication.facade.IIdentifiedUserService;
import clothingstorefranchise.spring.authentication.model.IdentifiedUser;
import clothingstorefranchise.spring.authentication.repositories.IIdentifiedUserRepository;
import clothingstorefranchise.spring.common.constants.Rol;
import clothingstorefranchise.spring.common.exceptions.EntityAlreadyExistsException;
import clothingstorefranchise.spring.common.exceptions.EntityDoesNotExistException;
import clothingstorefranchise.spring.common.exceptions.InvalidDataException;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;

@Service
public class IdentifiedUserService extends BaseService<IdentifiedUser, Long, IIdentifiedUserRepository> implements IIdentifiedUserService{
	
	@Autowired
	private IntegrationEventLogService integrationEventService;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	public IdentifiedUserService(IIdentifiedUserRepository identifiedUserRepository, ModelMapper modelMapper) {
		super(IdentifiedUser.class, identifiedUserRepository);
	}
	
	public IdentifiedUserDto getIdentifiedUserByUsername(String username) {

		IdentifiedUser identifiedUser = repository.findByUsername(username).orElseThrow(() -> new EntityDoesNotExistException("Entity not found "+username));
		return modelMapper.map(identifiedUser, IdentifiedUserDto.class);
	}

	@Override
	public IdentifiedUserDto create(IdentifiedUserDto userDto){
		createValidationActions(userDto);
		
		String encryptedPass = new BCryptPasswordEncoder().encode(userDto.getPassword());
		IdentifiedUser identifiedUser = modelMapper.map(userDto, IdentifiedUser.class);
		identifiedUser.setPassword(encryptedPass);
		repository.save(identifiedUser);
		
		return modelMapper.map(identifiedUser, IdentifiedUserDto.class);
	}
	
	@Transactional
	@Override
	public void delete(Long id) {
		var user = repository.findById(id).orElseThrow(() -> new EntityDoesNotExistException("Entity not found "+id));
		if(user.getRole().equals(Rol.SHOP_EMPLOYEE)) {
			super.delete(id);
			DeleteShopEmployeeEvent event = new DeleteShopEmployeeEvent(id);
			integrationEventService.saveEvent(event);
			rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, DeleteShopEmployeeEvent.class.getSimpleName(), event);
		}else if(user.getRole().equals(Rol.WAREHOUSE_EMPLOYEE)){
			super.delete(id);
			DeleteWarehouseEmployeeEvent event = new DeleteWarehouseEmployeeEvent(id);
			integrationEventService.saveEvent(event);
			rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, DeleteWarehouseEmployeeEvent.class.getSimpleName(), event);
		}
	}

	protected boolean entityAlreadyExistsCondition(IdentifiedUserDto dto) {
		return repository.findByUsername(dto.getUsername()).isPresent();
	}

	protected boolean isValid(IdentifiedUserDto dto) {
		return nullValidation(dto);
	}
	
	private void createValidationActions(IdentifiedUserDto dto) {
		if(!isValid(dto)) 
			throw new InvalidDataException("Invalid data");
				
		if(entityAlreadyExistsCondition(dto))
			throw new EntityAlreadyExistsException("There is an account with that username: "+dto.getUsername());
	}
	
	private static boolean nullValidation(IdentifiedUserDto dto) {
		return dto != null
			&& !StringUtils.isAnyBlank(dto.getUsername())
			&& !StringUtils.isAnyBlank(dto.getPassword());
	}
}
