package com.dicka.examplevm.repo;

import com.dicka.examplevm.entity.Marketer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarketerRepo extends JpaRepository<Marketer, String> {

    /** find by email marketer **/
    Optional<Marketer> findByEmail(String email);

    /** find by nick markter **/
    Optional<Marketer> findByNick(String nick);

}
