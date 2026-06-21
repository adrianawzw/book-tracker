package com.booktracker.book_tracker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.booktracker.book_tracker.entities.User;
import com.booktracker.book_tracker.repositories.UserRepository;
import com.booktracker.book_tracker.util.RolesEnum;

@SpringBootApplication
public class BookTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookTrackerApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			UserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		return args -> {
			// Opcional: Crear un usuario por defecto para pruebas
			String adminEmail = "admin@local.local";
            if (!userRepository.existsByEmail(adminEmail)) {
                User admin = new User();
                admin.setNombres("Admin");
                admin.setApellidos("System");
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode("adminpassword"));
                admin.setRol(RolesEnum.ADMIN);
                userRepository.save(admin);
            }
		};
	}
}
