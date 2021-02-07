package clothingstorefranchise.spring.authentication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import clothingstorefranchise.spring.authentication.model.IdentifiedUser;

public interface IIdentifiedUserRepository extends JpaRepository<IdentifiedUser, Long>{	
	Optional<IdentifiedUser> findByUsername(String username);
}
