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
public class Account extends AccountDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String pass;

    public Account(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }
}
