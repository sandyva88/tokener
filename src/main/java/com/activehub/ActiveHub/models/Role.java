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
@Table(name = "roles", schema = "public")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private int idRol;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "edit_date")
    private Date editDate;
}
