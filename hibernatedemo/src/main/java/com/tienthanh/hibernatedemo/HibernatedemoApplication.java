package com.tienthanh.hibernatedemo;

import com.tienthanh.hibernatedemo.dao.StudentDAO;
import com.tienthanh.hibernatedemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class HibernatedemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernatedemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner ->{
			createMultipleStudents(studentDAO);
		};
	}

	private void deleteAllStudent(StudentDAO studentDAO){
		System.out.println("Delete stud num "+studentDAO.deleteAll());
	}

	private void deleteStudent(StudentDAO studentDAO){
		int studentId =3;
		System.out.println("Deleting student id "+studentId);

		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO){
		int studentId = 1;
		Student student = studentDAO.findById(studentId);
		student.setFirstName("name update");

		studentDAO.update(student);

		System.out.println("Updated stud "+student);

	}

	private void queryForStudents(StudentDAO studentDAO){
		List<Student> theStudents = studentDAO.findAll();

		for(Student student : theStudents){
			System.out.println(student);
		}
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO){
		List<Student> students = studentDAO.findByLastName("Pham");
		System.out.println("Checkpoint 2");

		for(Student student : students){
			System.out.println(student);
		}
 	}


	private void readStudent(StudentDAO studentDAO){
		System.out.println("Creating new student object");
		Student student = new Student("Thanh","Pham","Thnah213@mail");
		studentDAO.save(student);

		int id = student.getId();

		System.out.println("student from db "+studentDAO.findById(id));


	}

	private void createMultipleStudents(StudentDAO studentDAO){
		System.out.println("Creating 3 student objects");
		Student tempStudent1 = new Student("Thanh1","Pham1","tp1@gmail.com");
		Student tempStudent2 = new Student("Thanh2","Pham2","tp2@gmail.com");
		Student tempStudent3 = new Student("Thanh3","Pham3","tp3@gmail.com");

		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

	}

	private void createStudent(StudentDAO studentDAO){
		Student tempStudent = new Student("Thanh","Pham","tp@gmail.com");

		studentDAO.save(tempStudent);
		System.out.println("student id "+tempStudent.getId());
	}
}
