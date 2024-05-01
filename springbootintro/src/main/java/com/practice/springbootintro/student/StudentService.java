package com.practice.springbootintro.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
	
	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}

	public void registerStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentById(student.getId());
		if (studentOptional.isPresent()) {
			throw new IllegalStateException("ID Taken");
		}
		studentRepository.save(student);
	}

	public void deleteStudent(int id) {
		boolean exists = studentRepository.existsById(id);
		if (!exists) {
			throw new IllegalStateException("Student with ID:" + id + " does not exist.");
		}
		studentRepository.deleteById(id);
	}

	@Transactional
	public void updateStudent(int id, String firstName, String lastName) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Student with ID:" + id + " does not exist."));
		if (firstName != null && firstName.length() > 0 && !Objects.equals(student.getFirstName(), firstName)) {
			student.setFirstName(firstName);
		}
		if (lastName != null && lastName.length() > 0 && !Objects.equals(student.getLastName(), lastName)) {
			student.setLastName(lastName);
		}
	}

}
