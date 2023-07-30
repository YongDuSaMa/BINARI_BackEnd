package ydsm.binari.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ydsm.binari.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);

	Optional<User> findByProviderAndProviderId(String provider, String providerId);
}


