package com.smart_waste_management_system.repository;
import com.smart_waste_management_system.model.PickupRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PickupRequestRepository extends JpaRepository<PickupRequest, Integer> {
    @NonNull
    @Query("SELECT p FROM PickupRequest p")
    List<PickupRequest> findAll();

    @Query("SELECT p FROM PickupRequest p WHERE p.request_id = :id")
    Optional<PickupRequest> findById(@Param("id") int id);

    @NonNull
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO pickup_request (quantity, status, user_id, category_id) VALUES (:#{#request.quantity}, :#{#request.status}, :#{#request.user.user_id}, :#{#request.category.category_id})",
            nativeQuery = true)
    void insert(@NonNull @Param("request") PickupRequest request);

    @NonNull
    @Modifying
    @Transactional
    @Query(value = "UPDATE pickup_request SET quantity = :#{#request.quantity}, status = :#{#request.status}, user_id = :#{#request.user.user_id}, category_id = :#{#request.category.category_id} WHERE request_id = :#{#request.request_id}",
            nativeQuery = true)
    void update(@NonNull @Param("request") PickupRequest request);

    @Query("SELECT COUNT(p) FROM PickupRequest p WHERE p.status <> 'Collected'")
    long count();
}