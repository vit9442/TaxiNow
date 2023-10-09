package org.top.taxinow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="dispatcher_t")
public class Dispatchers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="name_f", nullable = false)
    private String name;

    @Column(name="login_f", nullable = false)
    private String login;

    @Column(name="password_f", nullable = false)
    private String password;

    @Column(name="role_f", nullable = false)
    private String role;
}
