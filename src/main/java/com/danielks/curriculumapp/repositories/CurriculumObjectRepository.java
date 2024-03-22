package com.danielks.curriculumapp.repositories;

import com.danielks.curriculumapp.entities.CurriculumObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CurriculumObjectRepository extends JpaRepository<CurriculumObject, UUID> {
}
