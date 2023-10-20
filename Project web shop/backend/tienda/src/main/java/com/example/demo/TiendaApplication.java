package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.models.UserAuthority;
import com.example.demo.models.UserEntity;
import com.example.demo.repositorys.OrderEntityRepository;
import com.example.demo.repositorys.ProductEntityRepository;
import com.example.demo.repositorys.UserEntityRepository;

@SpringBootApplication
public class TiendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaApplication.class, args);
	}
	
	public CommandLineRunner initData(UserEntityRepository userEntityRepository, 
			OrderEntityRepository orderEntityRepository, 
			ProductEntityRepository productEntityRepository,
			PasswordEncoder encoder
			) {
		return args -> {
			UserEntity user1 = new UserEntity("user1", encoder.encode("1234"), "user@gmail.com", new ArrayList<>(List.of(UserAuthority.ADMIN, UserAuthority.USER))
					);
			UserEntity user2 = new UserEntity("user2", encoder.encode("1234"), "user2@gmail.com", new ArrayList<>(List.of(UserAuthority.USER))
					);
			userEntityRepository.save(user1);
			userEntityRepository.save(user2);
		};
		
		
		
	}



}
