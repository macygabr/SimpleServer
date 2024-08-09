package org.example.dao;

import lombok.*;
import org.example.models.Accounts.Account;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class CourseDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "courses")
    private Set<Account> accounts = new HashSet<>();

    protected CourseDAO(String name) {
        this.name = name;
    }

    @Override
    public java.lang.String toString() {
        return name;
    }
}