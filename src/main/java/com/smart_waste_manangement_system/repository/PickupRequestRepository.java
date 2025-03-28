package com.smart_waste_manangement_system.repository;
import com.smart_waste_manangement_system.model.PickupRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PickupRequestRepository extends JpaRepository<PickupRequest, Integer> {

    @Query("SELECT p FROM PickupRequest p WHERE p.user.user_id = ?1")
    List<PickupRequest> findByUserId(int userId);

    @Query("SELECT p FROM PickupRequest p WHERE p.category.category_id = ?1")
    List<PickupRequest> findByCategoryId(int categoryId);

    List<PickupRequest> findByStatus(String status);
}
