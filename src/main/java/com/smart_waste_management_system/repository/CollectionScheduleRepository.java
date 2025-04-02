package com.smart_waste_management_system.repository;
import com.smart_waste_management_system.model.CollectionSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface CollectionScheduleRepository extends JpaRepository<CollectionSchedule, Integer> {

    @NonNull
    @Query("SELECT c FROM CollectionSchedule c")
    List<CollectionSchedule> findAll();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO collection_schedule (area, date, status) VALUES (:#{#schedule.area}, :#{#schedule.date}, :#{#schedule.status})",
            nativeQuery = true)
    void insert(@NonNull @Param("schedule") CollectionSchedule schedule);

    @Modifying
    @Transactional
    @Query(value = "UPDATE collection_schedule SET area = :#{#schedule.area}, date = :#{#schedule.date}, status = :#{#schedule.status} WHERE schedule_id = :#{#schedule.schedule_id}",
            nativeQuery = true)
    void update(@NonNull @Param("schedule") CollectionSchedule schedule);

    @Query("SELECT COUNT(c) FROM CollectionSchedule c WHERE c.status <> 'Completed'")
    long count();

}