package se.sundsvall.users.integration.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.sundsvall.users.integration.db.model.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByPartyId(String personalNumber);

	void deleteByEmail(String email);
}
