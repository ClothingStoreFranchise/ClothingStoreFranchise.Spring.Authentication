package clothingstorefranchise.spring.authentication.dtos;

import clothingstorefranchise.spring.authentication.model.IdentifiedUser;
import clothingstorefranchise.spring.common.extensible.AbstractExtensibleEntityDto;
import clothingstorefranchise.spring.common.types.IEntityDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class IdentifiedUserDto extends AbstractExtensibleEntityDto implements IEntityDto<String>{

	private Long id;
	
	private String username;
	
    private String role;

    private String password;

	@Override
	public String getExtensibleEntityName() {
		
		return IdentifiedUser.class.getSimpleName();
	}

	@Override
	public String key() {
		return username;
	}
}
