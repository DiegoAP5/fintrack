package com.example.findtrack.repositories;

import com.example.findtrack.entities.Saving;
import com.example.findtrack.entities.projections.SavingsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISavingRepository extends JpaRepository<Saving, Long> {

    @Query(value = "SELECT savings.*, users.id AS userId FROM savings "+
                    "INNER JOIN users ON savings.user_id = users.id "+
                    "WHERE user_id = :id",nativeQuery = true)
    List<SavingsProjection> getSavingsByUserId(Long id);

    @Query(value = "SELECT savings.*, users.id AS userId FROM savings "+
            "INNER JOIN users ON savings.user_id = users.id "+
            "WHERE savings.id = :id",nativeQuery = true)
    Saving getSavingsById(Long id);
}
