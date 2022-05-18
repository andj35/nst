package com.silab.service.impl;

import com.silab.dao.UserDao;
import com.silab.entity.User;
import com.silab.exceptions.SilabEntityExistsException;
import com.silab.exceptions.SilabEntityNotFoundException;
import com.silab.security.MyUserDetails;
import com.silab.service.UserService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andjela
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User login(User user) throws SilabEntityNotFoundException {
        String username = user.getUsername();
        logger.debug("Login for user with username {} ", username);
        Optional<User> optionalUser = userDao.findUserByUsername(username);
        if (optionalUser.isEmpty() || !passwordEncoder.matches(user.getPassword(), optionalUser.get().getPassword())) {
            throw new SilabEntityNotFoundException("User with the given username or password doesn't exist");
        }
        return optionalUser.get();
    }

    @Override
    public User register(User user) throws SilabEntityExistsException {
        logger.debug("Adding new user {}", user.getUsername());
        if (userDao.findUserByUsername(user.getUsername()).isPresent()) {
            throw new SilabEntityExistsException("User with username " + user.getUsername() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public User updateUser(User user) throws SilabEntityNotFoundException, SilabEntityExistsException {
        logger.debug("Finding user {}", user.getUsername());
        Optional<User> optionalUser = userDao.findById(user.getId());
        if (optionalUser.isEmpty()) {
            throw new SilabEntityNotFoundException("User doesn't exist");
        }
        if (!optionalUser.get().getUsername().equals(user.getUsername()) && userDao.findUserByUsername(user.getUsername()).isPresent()) {
            throw new SilabEntityExistsException("User with username " + user.getUsername() + " already exists");
        }
        user.setPassword(optionalUser.get().getPassword());
        user.setRoles(optionalUser.get().getRoles());
        return userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userDao.findUserByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User couldn't be found");
        }
        return new MyUserDetails(optionalUser.get());
    }
}
