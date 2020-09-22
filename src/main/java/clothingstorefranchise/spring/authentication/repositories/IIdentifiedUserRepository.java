package clothingstorefranchise.spring.authentication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import clothingstorefranchise.spring.authentication.model.IdentifiedUser;


public interface IIdentifiedUserRepository extends JpaRepository<IdentifiedUser, Long>{	
	IdentifiedUser findByUsername(String username);
}
