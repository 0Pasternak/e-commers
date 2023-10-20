package com.example.demo.controllers;

 import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.models.OrderEntity;
import com.example.demo.models.ProductEntity;
import com.example.demo.models.UserEntity;
import com.example.demo.repositorys.OrderEntityRepository;
import com.example.demo.repositorys.ProductEntityRepository;
import com.example.demo.repositorys.UserEntityRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ShopCartController {
	@Autowired
	private OrderEntityRepository orderEntityRepository;
	
	@Autowired
	private ProductEntityRepository productEntityRepository;
	
	@Autowired
	private UserEntityRepository userEntityRepository;
	
	private HttpSession session;
	
	@GetMapping("/shopcart")
	public String show(Model model) {
		List<Long> productsIds = Optional.ofNullable((List<Long>) session.getAttribute("shopcart_products"))
				.orElse(new ArrayList<>());
		model.addAttribute("products",productEntityRepository.findAllById(productsIds));
		return "shopcart";
		
		
		
	}
	@GetMapping("/shopcart/add/{id}")
	public String add(@PathVariable Long id) {
		List<Long> productsIds = Optional.ofNullable((List<Long>) session.getAttribute("shopcart_products"))
		.orElse(new ArrayList<>());/*al guardarlo en sesion si esto se apaga se peirde*//*ver mejor forma de haerlo probablemnte lo meor es en bbdd*/
		
		if(!productsIds.contains(id)) productsIds.add(id);
		
		
		session.setAttribute("shopcart_products", productsIds);
		return "redirect:/shopcart";
		
		
	}
	@GetMapping("/shopcart/add/{id}")
	public String remove(@PathVariable Long id) {
		List<Long> productsIds = Optional.ofNullable((List<Long>) session.getAttribute("shopcart_products"))
		.orElse(new ArrayList<>());/*al guardarlo en sesion si esto se apaga se peirde*//*ver mejor forma de haerlo probablemnte lo meor es en bbdd*/
		
		productsIds.removeIf(productId -> productId.equals(id));
		
		
		session.setAttribute("shopcart_products", productsIds);
		return "redirect:/shopcart";
		
		
	}
	@GetMapping("/shopcart/checkout")
	public String checkout(Model model) {
		List<Long> productsIds = Optional.ofNullable((List<Long>) session.getAttribute("shopcart_products"))
				.orElse(new ArrayList<>());
		List<ProductEntity> products = productEntityRepository.findAllById(productsIds);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity user = userEntityRepository.findByUserName(username).orElseThrow();
		
		OrderEntity order = new OrderEntity(LocalDateTime.now(), user );
		
		orderEntityRepository.save(order);
		
		products.forEach(p -> p.setOrderEntity(order));
		productEntityRepository.saveAll(products);
		
		session.removeAttribute("shopcart_products");
		
		return "rediret:/orders" + order.getId();
	}
	
	@GetMapping("/shopcart_price")
	public Double calculatetotal(Model model) {
		List<Long> productsIds = Optional.ofNullable((List<Long>) session.getAttribute("shopcart_products"))
				.orElse(new ArrayList<>());
		List<ProductEntity> products = productEntityRepository.findAllById(productsIds);
		
		return products.stream().mapToDouble(ProductEntity::getPrice).sum();
	}
	
	
	
	
	
}
