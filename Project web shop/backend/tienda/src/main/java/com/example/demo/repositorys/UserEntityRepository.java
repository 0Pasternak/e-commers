package com.example.demo.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
	//localizamos al usuario por el nombre
	Optional<UserEntity> findByUserName(String userName);

}
