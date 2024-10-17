package com.activehub.ActiveHub.Repository;

import com.activehub.ActiveHub.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
