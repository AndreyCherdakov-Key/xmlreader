package com.repositories;

import com.entities.RequestDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestDetailsRepository extends JpaRepository<RequestDetailsEntity, String> {
}
