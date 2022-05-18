package com.silab.service.impl;

import com.silab.dao.RoleDao;
import com.silab.entity.Role;
import com.silab.service.RoleService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andjela
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    private final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Override
    public List<Role> getRoles() {
        logger.debug("Finding all roles");
        return roleDao.findAll();
    }

}
