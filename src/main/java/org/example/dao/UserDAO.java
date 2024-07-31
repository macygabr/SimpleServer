package org.example.models;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class UserDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;

    private String name;
    protected UserDAO(String name){
        this.name = name;
    }
}