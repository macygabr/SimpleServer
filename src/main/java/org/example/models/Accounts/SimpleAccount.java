package org.example.models.Accounts;

import javax.persistence.*;

@Entity
public class SimpleAccount extends Account {
    public SimpleAccount() {
        super();
    }

    public SimpleAccount(String login, String pass) {
        super(login, pass);
    }
}
