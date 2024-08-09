package org.example.models.Accounts;

import lombok.*;
import org.example.dao.AccountDAO;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.JOINED)
public class PrimeAccount extends Account {
    private long balance;
    public PrimeAccount(String login, String pass){
        super(login,pass);
    }
}
