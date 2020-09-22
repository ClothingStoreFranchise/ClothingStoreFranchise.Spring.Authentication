package clothingstorefranchise.spring.authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clothingstorefranchise.spring.authentication.dto.IdentifiedUserDto;
import clothingstorefranchise.spring.authentication.facade.IIdentifiedUserService;
import clothingstorefranchise.spring.authentication.model.IdentifiedUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("identifieduser")
@Api(value = "Endpoints to manage User´s information")
public class IdentifiedUserController {
	
	@Autowired
	private IIdentifiedUserService identifiedUserService;	

	@GetMapping("/{username}")
	@ApiOperation(value= "Will retrieve the identified user by username", response = IdentifiedUser[].class)
	public ResponseEntity<IdentifiedUserDto> getIdentifiedUserByUsername(@PathVariable String username){
		
		IdentifiedUserDto identifiedUser = identifiedUserService.getIdentifiedUserByUsername(username);
		return ResponseEntity.ok(identifiedUser);
	}
}
