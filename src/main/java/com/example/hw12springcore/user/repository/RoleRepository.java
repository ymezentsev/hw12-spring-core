package com.example.hw12springcore.user.repository;

import com.example.hw12springcore.user.entity.ERole;
import com.example.hw12springcore.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole (ERole role);

    @Query("FROM Role r WHERE r.role IN :roles")
    Set<Role> findByRoles(@Param("roles") Collection<ERole> roles);
}
