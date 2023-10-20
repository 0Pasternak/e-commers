package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.models.OrderEntity;
import com.example.demo.repositorys.OrderEntityRepository;
import com.example.demo.repositorys.ProductEntityRepository;
import com.example.demo.repositorys.UserEntityRepository;

@Controller
public class OrderController {
	
	@Autowired
	private OrderEntityRepository orderEntityRepository;
	@Autowired
	private ProductEntityRepository productEntityRepository;

	
	@GetMapping("/orders") 
	public String findAll(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<OrderEntity> orders = orderEntityRepository.findAllByUserUsername(username);
		model.addAttribute("orders", orders);
		return "order_list";	
	}
	
	@GetMapping("/orders/{id}")
	public String findById(Model model, @PathVariable Long id) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		OrderEntity order = orderEntityRepository.findByIdAndUserUsername(id, username).orElseThrow(null);
		model.addAttribute("order", order);
		model.addAttribute("producs", productEntityRepository.findAllByOrderId(id));
		return "order_detail";		
	}
	
	
	

}
