package com.smart_waste_manangement_system.repository;
import com.smart_waste_manangement_system.model.CollectionSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CollectionScheduleRepository extends JpaRepository<CollectionSchedule, Integer> {
    List<CollectionSchedule> findByArea(String area);

    List<CollectionSchedule> findByStatus(String status);
}
