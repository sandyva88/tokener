package com.activehub.ActiveHub.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "access_token")
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_authentication")
    private int idAuthentication;

    @Column(name = "username")
    private String username;

    @Column(name = "session_open")
    private Boolean sessionOpen;

    @Column(name = "access_validity")
    private String accessValidity;

    @Column(name = "last_session")
    private Date lastSession;
}
