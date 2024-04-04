package com.example.findtrack.repositories;

import com.example.findtrack.entities.SharedSaving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISharedSavingRepository extends JpaRepository<SharedSaving, Long> {

    @Query(value = "SELECT s.* FROM SharedSavings s JOIN Shared d ON s.id = d.shared_id "
                    "WHERE d.user_id = :userId",nativeQuery = true)
    List<Object[]> getSharedSavingByUserId(Long id);
}
