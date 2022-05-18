package com.silab.controller;

import com.silab.entity.Role;
import com.silab.service.RoleService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andjela
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    RoleService roleService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> roles = roleService.getRoles();
        logger.info("Successfully retrieved {} roles", roles.size());
        return ResponseEntity.ok(roles);
    }

}
