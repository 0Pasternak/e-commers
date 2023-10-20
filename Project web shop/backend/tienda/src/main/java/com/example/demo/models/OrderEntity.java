package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime creationDate;
	
	@ManyToOne
	private UserEntity userEntity;
	
	public OrderEntity() {}
	

	public OrderEntity(LocalDateTime creationDate, UserEntity userEntity) {
		super();
		this.creationDate = creationDate;
		this.userEntity = userEntity;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}


	@Override
	public int hashCode() {
		return Objects.hash(creationDate, id, userEntity);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderEntity other = (OrderEntity) obj;
		return Objects.equals(creationDate, other.creationDate) && Objects.equals(id, other.id)
				&& Objects.equals(userEntity, other.userEntity);
	}
	
	
	

}
