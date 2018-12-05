package com.dicka.examplevm;

import com.dicka.examplevm.entity.Influencer;
import com.dicka.examplevm.entity.VerifyToken;
import com.dicka.examplevm.repo.InfluencerRepo;
import com.dicka.examplevm.repo.VerifyTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@SpringBootApplication
public class ExampleVmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleVmApplication.class, args);
	}
}

@Component
@Service
class CommadLine implements CommandLineRunner{

	@Autowired
	private VerifyTokenRepo verifyTokenRepo;

	@Autowired
	private InfluencerRepo influencerRepo;

	@Override
	public void run(String... args) throws Exception {

		//Optional<VerifyToken> verifyToken = verifyTokenRepo.findByEmail("dicka@gmail.com");
		//System.out.println("data email : "+ verifyToken);
		VerifyToken cariTokenDanEmail = verifyTokenRepo
				.findByEmailAndConfirmKey("6655e0b7-e67e-4c19-afb4-ac55b0a8480e", "dickanirwansyah@gmail.com");
		System.out.println("data : "+cariTokenDanEmail);
		Optional<VerifyToken> token = verifyTokenRepo.findById("6655e0b7-e67e-4c19-afb4-ac55b0a8480e");
		System.out.println("data : "+token);
		Optional<Influencer> influencer = influencerRepo.findById("dickanirwansyah@gmail.com");
		System.out.println("data : "+influencer);
	}
}
