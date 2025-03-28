package com.smart_waste_management_system.repository;
import com.smart_waste_management_system.model.CollectionSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CollectionScheduleRepository extends JpaRepository<CollectionSchedule, Integer> {

    @Query("SELECT c FROM CollectionSchedule c WHERE c.area = :area")
    List<CollectionSchedule> findByArea(@Param("area") String area);

    @Query("SELECT c FROM CollectionSchedule c WHERE c.status = :status")
    List<CollectionSchedule> findByStatus(@Param("status") String status);

    @Query("SELECT c FROM CollectionSchedule c WHERE c.schedule_id = :id")
    Optional<CollectionSchedule> findById(@Param("id") int id);

    @NonNull
    @Query("SELECT c FROM CollectionSchedule c")
    List<CollectionSchedule> findAll();

    @NonNull
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO collection_schedule (area, date, status) VALUES (:#{#schedule.area}, :#{#schedule.date}, :#{#schedule.status})",
            nativeQuery = true)
    void insert(@NonNull @Param("schedule") CollectionSchedule schedule);

    @NonNull
    @Modifying
    @Transactional
    @Query(value = "UPDATE collection_schedule SET area = :#{#schedule.area}, date = :#{#schedule.date}, status = :#{#schedule.status} WHERE schedule_id = :#{#schedule.schedule_id}",
            nativeQuery = true)
    void update(@NonNull @Param("schedule") CollectionSchedule schedule);

    @Query("SELECT c FROM CollectionSchedule c WHERE c.date = :date")
    List<CollectionSchedule> findByDate(@Param("date") LocalDate date);

    @Query("SELECT c FROM CollectionSchedule c WHERE c.date >= :startDate AND c.date <= :endDate")
    List<CollectionSchedule> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}