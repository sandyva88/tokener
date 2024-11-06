package com.activehub.ActiveHub.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    int id;
    @Column(name = "lastname")
    String lastname;
    @Column(name = "firstname")
    String firstname;
    @Column(name = "country")
    String country;
    @Column(name = "password")
    String password;
    @Basic
    @Column(name = "email")
    String username;
    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "id_rol", foreignKey = @ForeignKey(name = "users_rol_fkey"))
    Role role;
    @Column(name = "status")
    Boolean status;
    @Column(name = "creation_date")
    Date creationDate;
    @Column(name = "edit_date")
    Date editDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
