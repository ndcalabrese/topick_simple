package com.ndcalabrese.topick_simple.repository;

import com.ndcalabrese.topick_simple.model.Subtopick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubtopickRepository extends JpaRepository<Subtopick, Long> {

    Optional<Subtopick> findByName(String subtopickName);

    Optional<Subtopick> findById(Long id);

}
