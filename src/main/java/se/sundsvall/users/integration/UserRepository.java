package se.sundsvall.users.integration;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.users.integration.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
