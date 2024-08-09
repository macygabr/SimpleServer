package org.example.models.Accounts;

import javax.persistence.*;

import lombok.*;
import org.example.dao.AccountDAO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account extends AccountDAO {
    public Account(String login, String pass) {
        super(login, pass);
    }
}

