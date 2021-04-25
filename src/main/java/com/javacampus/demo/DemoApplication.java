package com.javacampus.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoApplication {

	@GetMapping(value = "/home")
	public String home() {
		return "Hello World with Spring Boot";
	}

	public static void main(String[] args) {
		// SpringApplication.run(DemoApplication.class, args);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("simple-jpa-application");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			// 등록
			// Member member = new Member();
			// member.setId(1L);
			// member.setName("HelloA");
			// em.persist(member);

			// 검색
			Member findMember = em.find(Member.class, 3L);
			System.out.println("findMember.getId : " + findMember.getId());
			System.out.println("findMember.getName : " + findMember.getName());
			
			// 삭제
			// em.remove(findMember);

			// 수정
			findMember.setName("Hello-A");

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();	
		}
		
		emf.close();
	}

}
