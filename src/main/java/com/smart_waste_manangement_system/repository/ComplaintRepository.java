package com.smart_waste_manangement_system.repository;
import com.smart_waste_manangement_system.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

    @Query("SELECT c FROM Complaint c WHERE c.user.user_id = ?1")
    List<Complaint> findByUserId(int userId);

    List<Complaint> findByStatus(String status);
}
