package org.example.models.Accounts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dao.AccountDAO;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.JOINED)
public class SimpleAccount extends Account {
    public SimpleAccount(String login, String pass){
        super(login,pass);
    }
}
