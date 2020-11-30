package clothingstorefranchise.spring.authentication.controllers;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clothingstorefranchise.spring.common.security.util.BaseIdentifiedUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("user-jwt")
@Api(value = "Endpoints to manage User´s information available in the token")
public class UserInfoController {
	@GetMapping(path = "info")
	@ApiOperation(value= "Will retrieve the information from the user available in the token", response = BaseIdentifiedUser[].class)
	public ResponseEntity<BaseIdentifiedUser> getUserInfo(Principal principal){
		BaseIdentifiedUser identifiedUser = (BaseIdentifiedUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
		return ResponseEntity.ok(identifiedUser);
	}
}