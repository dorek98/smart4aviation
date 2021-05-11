package com.smart4aviation.repository;

import com.smart4aviation.model.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaggageRespository extends JpaRepository<Baggage, Long> {
}
