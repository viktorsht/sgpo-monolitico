package com.sgpo.monolithicSGP.users.repositories;

import com.sgpo.monolithicSGP.users.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
