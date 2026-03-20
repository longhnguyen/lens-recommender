package com.longnguyen.lensrecommender.repository;

import com.longnguyen.lensrecommender.model.entity.CameraAlias;
import com.longnguyen.lensrecommender.model.enums.CameraBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CameraAliasRepository extends JpaRepository<CameraAlias, Integer>
{
    Optional<CameraAlias> findByAliasAndCamera_Brand(String alias, CameraBrand cameraBrand);
}
