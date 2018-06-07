package pl.coderslab.theultimatebet.entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Simple entity keeping the info about wallet - most of all - balance of the wallet;
 * also strictly related to {@link User}, {@link Operation}, {@link Withdrawal}.
 */
@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nullable
    private BigDecimal balance;

    @OneToOne
    User user;

    @OneToMany(mappedBy = "wallet")
    private List<Operation> operations;

    @OneToMany(mappedBy = "wallet")
    private List<Withdrawal> withdrawal;

    public Wallet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public List<Withdrawal> getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(List<Withdrawal> withdrawal) {
        this.withdrawal = withdrawal;
    }
}
