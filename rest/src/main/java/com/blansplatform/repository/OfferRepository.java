package com.blansplatform.repository;

import com.blansplatform.entity.Offer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends CrudRepository<Offer, Long> {
    List<Offer> findAll();
    Offer findOfferById(Long id);
    @Query(value = "select * from offer where task_id = ?1 and offer_status = 2", nativeQuery = true)
    List<Offer> findAllActiveOffersFromTask(@Param("task_id") Long id);
    @Query(value = "select * from offer where executor_id = ?1 and offer_status = 2", nativeQuery = true)
    List<Offer> findActiveOffersForExecutor(@Param("executor_id") Long id);
    @Query(value = "select * from offer where task_id = ?1", nativeQuery = true)
    List<Offer> findAllOffersForTask(@Param("task_id") Long taskId);
}
