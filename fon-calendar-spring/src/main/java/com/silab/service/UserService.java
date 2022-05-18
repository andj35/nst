package com.silab.service;

import com.silab.entity.User;
import com.silab.exceptions.SilabEntityExistsException;
import com.silab.exceptions.SilabEntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Andjela
 */
public interface UserService extends UserDetailsService{

    public User login(User user) throws SilabEntityNotFoundException;

    public User register(User user) throws SilabEntityExistsException;

    public User updateUser(User user) throws SilabEntityNotFoundException, SilabEntityExistsException;
}
