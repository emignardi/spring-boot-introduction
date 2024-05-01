package com.practice.springbootintro.student;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfiguration {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			Student s1 = new Student("Eric", "Mignardi");
			Student s2 = new Student("Tadoe", "Mignardi");
			studentRepository.saveAll(List.of(s1,s2));
		};
	}
	
}
