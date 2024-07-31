package org.example.models;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends UserDAO{
    public User(String name) {
        super(name);
    }
}
