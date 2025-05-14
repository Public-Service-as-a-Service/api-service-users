package se.sundsvall.users.integration.db;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.users.integration.db.model.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

	Optional<UserEntity> findByEmail(String email);

	void deleteByEmail(String email);
}
