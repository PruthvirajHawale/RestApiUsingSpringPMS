package com.mycompany.projectmanagement.Repository;

import com.mycompany.projectmanagement.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmailAndPassword(String email,String password);
    Optional<UserEntity> findByEmail(String email);

}
