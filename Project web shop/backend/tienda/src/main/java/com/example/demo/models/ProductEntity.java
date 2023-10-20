package com.example.demo.models;


import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private Double price;
	private String image;
	
	@ManyToOne
	private OrderEntity orderEntity;
	
	public ProductEntity() {}

	public ProductEntity(String name, Double price, String image) {
		super();
		this.name = name;
		this.price = price;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public OrderEntity getOrderEntity() {
		return orderEntity;
	}

	public void setOrderEntity(OrderEntity orderEntity) {
		this.orderEntity = orderEntity;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id, image, name, orderEntity, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductEntity other = (ProductEntity) obj;
		return Objects.equals(id, other.id) && Objects.equals(image, other.image) && Objects.equals(name, other.name)
				&& Objects.equals(orderEntity, other.orderEntity) && Objects.equals(price, other.price);
	}

	@Override
	public String toString() {
		return "ProductEntity [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image
				+ ", orderEntity=" + orderEntity + "]";
	}
	
	
	

}
