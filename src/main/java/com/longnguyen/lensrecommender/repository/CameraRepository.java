package com.longnguyen.lensrecommender.repository;

import com.longnguyen.lensrecommender.model.entity.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Integer>
{
    Optional<Camera> findByModel(String model);
}
