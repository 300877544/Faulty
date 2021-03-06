package com.dexlock.book.Book.services;

import com.dexlock.book.Book.model.User;
import com.dexlock.book.Book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){return userRepository.save(user);}
}
