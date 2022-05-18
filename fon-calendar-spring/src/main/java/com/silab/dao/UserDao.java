package com.silab.dao;

import com.silab.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andjela
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {

    public Optional<User> findUserByUsername(String username);
}
