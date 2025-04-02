package com.smart_waste_management_system.repository;
import com.smart_waste_management_system.model.User;
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
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

    @NonNull
    @Query("SELECT u FROM User u")
    List<User> findAll();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (username, password, role) VALUES (:#{#user.username}, :#{#user.password}, :#{#user.role})",
            nativeQuery = true)
    void insert(@NonNull @Param("user") User user);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user SET username = :#{#user.username}, password = :#{#user.password}, role = :#{#user.role} WHERE user_id = :#{#user.user_id}",
            nativeQuery = true)
    void update(@NonNull @Param("user") User user);
}