package com.example.findtrack.repositories;

import com.example.findtrack.entities.SharedSaving;
import com.example.findtrack.entities.projections.SavingsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISharedSavingRepository extends JpaRepository<SharedSaving, Long> {

    @Query(value = "SELECT s.* FROM sharedsavings s JOIN shared d ON s.id = d.shared_id "+
                    "WHERE d.user_id = :id",nativeQuery = true)
    List<SavingsProjection> getSharedSavingByUserId(Long id);

    @Query(value = "SELECT s.* FROM sharedsavings s JOIN shared d ON s.id = d.shared_id "+
            "WHERE d.shared_id = :id",nativeQuery = true)
    SharedSaving getSharedSavingById(Long id);
}
