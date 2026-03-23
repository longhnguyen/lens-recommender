package com.longnguyen.lensrecommender.repository;

import com.longnguyen.lensrecommender.model.entity.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Database access for the Camera table
 */
@Repository
public interface CameraRepository extends JpaRepository<Camera, Integer> {

    /**
     * Finds a camera by searching model name
     *
     * @param model user-provided camera name
     * @return matching camera if found
     */
    Optional<Camera> findByModel(String model);
}
