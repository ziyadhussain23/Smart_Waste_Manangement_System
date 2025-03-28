package com.smart_waste_manangement_system.repository;
import com.smart_waste_manangement_system.model.WasteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Integer> {

}
