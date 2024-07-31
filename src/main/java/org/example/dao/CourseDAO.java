package org.example.models;

import lombok.*;

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

    @java.lang.Override
    public java.lang.String toString() {
        return name;
    }
}