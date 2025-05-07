package se.sundsvall.users.integration;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.users.integration.model.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

	UserEntity findByEmail(String email);
}
