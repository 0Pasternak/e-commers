package com.example.demo.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.OrderEntity;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long>{
	//Develve las ordenes asociadas al nombre de un usuario
	List<OrderEntity> findAllByUserUsername(String UserEntity);
	
	//Devuelve las ordenes asociadas al id y nombre de un usuario
	Optional<OrderEntity> findByIdAndUserUsername(Long id, String username);
	
	


}


