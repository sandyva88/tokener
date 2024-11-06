package com.activehub.ActiveHub.Repository;


import com.activehub.ActiveHub.models.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<AccessToken, Integer> {

    Optional<AccessToken> findByUsername(String username);
}
