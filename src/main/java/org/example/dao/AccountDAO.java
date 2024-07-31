package org.example.models;

import javax.persistence.*;


import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AccountDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(unique = true)
    private String login;
    private String pass;


    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "account_course",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    protected AccountDAO(String login, String pass){
        this.login = login;
        this.pass = pass;
    }

    protected AccountDAO(){}
}

