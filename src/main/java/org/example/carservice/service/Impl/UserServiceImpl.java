//package org.example.carservice.service.Impl;
//
//import org.example.carservice.model.User;
//import org.example.carservice.repository.UserRepository;
//import org.example.carservice.service.UserService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserServiceImpl implements UserService {
//    private final PasswordEncoder encoder;
//    private final UserRepository userRepository;
//
//    public UserServiceImpl(PasswordEncoder encoder, UserRepository userRepository) {
//        this.encoder = encoder;
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public User add(User user) {
//        user.setPassword(encoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }
//
//    @Override
//    public User get(Long id) {
//        return userRepository.getById(id);
//    }
//
//    @Override
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//}
