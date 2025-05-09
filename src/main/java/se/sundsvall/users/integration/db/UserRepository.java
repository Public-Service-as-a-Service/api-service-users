package se.sundsvall.users.integration.db;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.users.integration.db.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

	UserEntity findByEmail(String email);
}
