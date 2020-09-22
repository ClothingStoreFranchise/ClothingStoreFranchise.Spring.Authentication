package clothingstorefranchise.spring.authentication.dto.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import clothingstorefranchise.spring.common.event.IntegrationEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserEvent extends IntegrationEvent {
	@JsonProperty("Username")
	private String username;
	
	@JsonProperty("Password")
	private String password;
	
	@JsonProperty("Role")
    private String role;
}
