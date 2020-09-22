package clothingstorefranchise.spring.authentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdentifiedUser {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(insertable = true)
    private Long id;
	@NotNull(message = "The field 'username' is mandatory")
    @Column(nullable = false, unique = true)
    private String username;
	@ToString.Exclude
    @NotNull(message = "The field 'password' is mandatory")
    @Column(nullable = false)
    private String password;
    @NotNull(message = "The field 'role' is mandatory")
    @Column(nullable = false)
    private String role;
    public IdentifiedUser(@NotNull IdentifiedUser user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.role = user.getRole();
	}
}
