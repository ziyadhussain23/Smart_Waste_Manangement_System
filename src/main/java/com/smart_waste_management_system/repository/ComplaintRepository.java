package com.smart_waste_management_system.repository;
import com.smart_waste_management_system.model.Complaint;
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
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

    @NonNull
    @Query("SELECT c FROM Complaint c")
    List<Complaint> findAll();

    @Query("SELECT c FROM Complaint c WHERE c.complaint_id = :id")
    Optional<Complaint> findById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO complaint (description, status, user_id) VALUES (:#{#complaint.description}, :#{#complaint.status}, :#{#complaint.user.user_id})",
            nativeQuery = true)
    void insert(@NonNull @Param("complaint") Complaint complaint);

    @Modifying
    @Transactional
    @Query(value = "UPDATE complaint SET description = :#{#complaint.description}, status = :#{#complaint.status}, user_id = :#{#complaint.user.user_id} WHERE complaint_id = :#{#complaint.complaint_id}",
            nativeQuery = true)
    void update(@NonNull @Param("complaint") Complaint complaint);

    @Query("SELECT COUNT(c) FROM Complaint c WHERE c.status <> 'Resolved'")
    long count();
}