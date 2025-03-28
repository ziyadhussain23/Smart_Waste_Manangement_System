package com.smart_waste_management_system.repository;
import com.smart_waste_management_system.model.RecyclingCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecyclingCenterRepository extends JpaRepository<RecyclingCenter, Integer> {

}