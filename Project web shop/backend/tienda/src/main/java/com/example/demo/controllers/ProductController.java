package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.example.demo.models.ProductEntity;
import com.example.demo.repositorys.ProductEntityRepository;

@Controller
public class ProductController {
	
	@Autowired
	private ProductEntityRepository productEntityRepository;
	
	@GetMapping
	public String FindAll(Model model) {
		model.addAttribute("products", productEntityRepository.findAll());
		return "products_list";
	}
	
	@GetMapping("products/new")
	public String Create(Model model) {
		model.addAttribute("product",new ProductEntity());
		return "product_form";
		
	}
	
	@PostMapping("products/new")
	public String Submit(@ModelAttribute ProductEntity product, @RequestParam("file") MultipartFile file) {
		/*if(!file.isEmpty()) {
			String image = storageService.store(file);
			String imageUrl = MvcUriComponentsBuilder.fromMethodName(FileController.class, "serverFile", image).build().toUriString();
			product.setImage(imageUrl);
					
		}*/
		productEntityRepository.save(product);
		return "product_form";
		
	}
	
	
	

}
