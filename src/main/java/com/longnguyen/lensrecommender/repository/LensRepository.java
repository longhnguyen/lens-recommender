package com.longnguyen.lensrecommender.repository;

import com.longnguyen.lensrecommender.model.entity.Lens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LensRepository extends JpaRepository<Lens, Integer>
{
}
