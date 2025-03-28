package com.smart_waste_management_system.repository;
import com.smart_waste_management_system.model.WasteCategory;
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
public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Integer> {

    @NonNull
    @Query("SELECT w FROM WasteCategory w")
    List<WasteCategory> findAll();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO waste_category (name, description) VALUES (:#{#category.name}, :#{#category.description})",
            nativeQuery = true)
    void insert(@Param("category") WasteCategory category);

    @Modifying
    @Transactional
    @Query(value = "UPDATE waste_category SET name = :#{#category.name}, description = :#{#category.description} WHERE category_id = :#{#category.category_id}",
            nativeQuery = true)
    void update(@Param("category") WasteCategory category);

    @Query("SELECT w FROM WasteCategory w WHERE w.category_id = :id")
    Optional<WasteCategory> findById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM WasteCategory w WHERE w.category_id = :id")
    void deleteById(@Param("id") int id);
}