package com.dicka.examplevm.repo;

import com.dicka.examplevm.entity.VerifyToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VerifyTokenRepo extends JpaRepository<VerifyToken, String> {

    VerifyToken findByEmailAndConfirmKey(String confirmKey, String email);
    Optional<VerifyToken> findByEmail(String email);
    //List<VerifyToken> findByConfirmKeyAndEmail(String confirmKey, String email);
}
