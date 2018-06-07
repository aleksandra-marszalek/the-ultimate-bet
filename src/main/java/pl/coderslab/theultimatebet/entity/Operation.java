package pl.coderslab.theultimatebet.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity to keep all the info about the operations, for example adding money, withdraw money from the wallet,
 * placing the bet etc.
 * When something like those happens, a specific title is used and the amount of the operation, date is automatically
 * created and {@link Wallet} is related to complete the operation.
 */
@Entity
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "operation_id")
    private int id;

    private String title;

    private BigDecimal amount;

    private LocalDateTime created;

    @ManyToOne
    private Wallet wallet;

    public Operation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
