package com.example.demo.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.ProductEntity;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long>{
	//devuelve los productos que estan en la orden con el id x
	List<ProductEntity> findAllByOrderId(Long id);

}
