package ru.naumovweb.sitesstat.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.naumovweb.sitesstat.models.User;
import ru.naumovweb.sitesstat.repositories.contracts.IUserRepository;
import ru.naumovweb.sitesstat.services.contracts.IUserService;

import java.util.Date;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreated_at(new Date());
        user.setUpdated_at(new Date());

        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
