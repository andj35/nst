package com.silab.dao;

import com.silab.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**

 * @author Andjela
 */
@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

    public Optional<Role> findRoleByName(String name);
}
