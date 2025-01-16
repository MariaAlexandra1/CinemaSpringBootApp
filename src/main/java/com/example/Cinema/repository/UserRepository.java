package com.example.Cinema.repository;

import com.example.Cinema.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"roles"})
    Optional<User> findByUsername(String username);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"roles"})
    List<User> findAll();

    @Query("SELECT u FROM User u WHERE u.username = ?#{ principal.username}")   //obiectul de securitate curent, utilizatorul autentificat curent
    Optional<User> findLoginUser();

    boolean existsByEmailAddress(String emailAddress);

    @Modifying
    @Query("DELETE FROM User u WHERE u.username = :username")
    void deleteUserByUsername(@Param("username") String username);

}
