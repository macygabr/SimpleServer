package org.example.models.Accounts;

import lombok.Setter;
import javax.persistence.*;

@Entity
@Setter
public class PrimeAccount extends Account {
    private long balance;

    public PrimeAccount() {
        super();
    }

    public PrimeAccount(String login, String pass) {
        super(login, pass);
    }
}
