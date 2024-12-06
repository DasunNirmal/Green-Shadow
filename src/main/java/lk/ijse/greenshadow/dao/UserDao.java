package lk.ijse.greenshadow.dao;

import lk.ijse.greenshadow.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String userName);
}
