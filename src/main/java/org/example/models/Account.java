package org.example.models;

import javax.persistence.*;


import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "accounts")
public class Account extends AccountDAO {
    public Account(String login, String pass) {
        super(login, pass);
    }
}

