package com.longnguyen.lensrecommender.repository;

import com.longnguyen.lensrecommender.model.entity.CameraAlias;
import com.longnguyen.lensrecommender.model.enums.CameraBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Database access for the Camera_Alias table
 */
@Repository
public interface CameraAliasRepository extends JpaRepository<CameraAlias, Integer> {

    /**
     * Finds a camera alias for a camera model
     *
     * @param alias user-provided camera name
     * @param cameraBrand filter by camera brand in case different cameras share a common alias
     * @return matching alias if found
     */
    Optional<CameraAlias> findByAliasAndCamera_Brand(String alias, CameraBrand cameraBrand);
}
