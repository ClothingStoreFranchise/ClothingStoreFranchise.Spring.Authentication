package clothingstorefranchise.spring.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto extends IdentifiedUserDto {
			
	private String lastName;
	
	private String name;
	
	private String address;
	
	private String country;
	
	private String phoneNumber;
	
	private String email;
	
	@Override
	public String getExtensibleEntityName() {
		
		return CustomerDto.class.getName();
	}
}
