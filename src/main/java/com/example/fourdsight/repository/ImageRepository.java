package com.example.fourdsight.repository;

import com.example.fourdsight.model.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, String> {

}
