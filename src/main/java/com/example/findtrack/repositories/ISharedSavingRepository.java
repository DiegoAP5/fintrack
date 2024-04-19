package com.example.findtrack.repositories;

import com.example.findtrack.entities.SharedSaving;
import com.example.findtrack.entities.projections.SavingsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISharedSavingRepository extends JpaRepository<SharedSaving, Long> {

    @Query(value = "SELECT s.* FROM SharedSavings s JOIN Shared d ON s.user_id = d.user_id "+
                    "WHERE d.user_id = :userId",nativeQuery = true)
    List<SavingsProjection> getSharedSavingByUserId(Long id);
}
