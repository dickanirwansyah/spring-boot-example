package com.dicka.examplevm.repo;

import com.dicka.examplevm.entity.Marketer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketerRepo extends JpaRepository<Marketer, String> {
}
