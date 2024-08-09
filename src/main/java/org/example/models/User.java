package org.example.models;

import lombok.*;
import org.example.dao.UserDAO;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends UserDAO {
    public User(String name) {
        super(name);
    }
}
