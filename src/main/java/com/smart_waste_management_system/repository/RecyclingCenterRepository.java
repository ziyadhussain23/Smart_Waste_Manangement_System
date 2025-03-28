package com.smart_waste_management_system.repository;
import com.smart_waste_management_system.model.RecyclingCenter;
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
public interface RecyclingCenterRepository extends JpaRepository<RecyclingCenter, Integer> {

    @NonNull
    @Query("SELECT r FROM RecyclingCenter r")
    List<RecyclingCenter> findAll();

    @NonNull
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO recycling_center (location, contact_info) VALUES (:#{#center.location}, :#{#center.contact_info})",
            nativeQuery = true)
    void insert(@NonNull @Param("center") RecyclingCenter center);

    @NonNull
    @Modifying
    @Transactional
    @Query(value = "UPDATE recycling_center SET location = :#{#center.location}, contact_info = :#{#center.contact_info} WHERE center_id = :#{#center.center_id}",
            nativeQuery = true)
    void update(@NonNull @Param("center") RecyclingCenter center);

    @NonNull
    @Query("SELECT r FROM RecyclingCenter r WHERE r.center_id = :id")
    Optional<RecyclingCenter> findById(@Param("id") int id);

    @NonNull
    @Modifying
    @Transactional
    @Query("DELETE FROM RecyclingCenter r WHERE r.center_id = :id")
    void deleteById(@Param("id") int id);
}