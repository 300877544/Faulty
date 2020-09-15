package com.dexlock.book.Book.repository;

import com.dexlock.book.Book.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(
            value = "Select * from users where username = ?",
            nativeQuery = true
    )
   public  User findByUsername(String username);
}
