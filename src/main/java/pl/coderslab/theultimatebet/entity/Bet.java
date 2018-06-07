package pl.coderslab.theultimatebet.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * {@link Entity} responsible for keeping all the information about a single bet.
 * Related to {@link User} - which places the bet, {@link Game} which result is what we bet for,
 * {@link Team} which is chosen to win, plus values in BigDecimal like amount placed in the bet,
 * course for the win of the team chosen (kept in {@link Game}, total amount possible to win / won,
 * LocalDateTime of the creation of the bet and result (String "won" or "lose", null if the game has not
 * been played yet).
 *  TODO: basket for Bets, using HttpSession
 *  TODO: betting on team - winner of the tournament, other positions, position in group etc.
 */
@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Game game;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Team team;

    private BigDecimal amount;

    private BigDecimal course;

    private BigDecimal totalAmount;

    private LocalDateTime created;

    private String result;

    public Bet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCourse() {
        return course;
    }

    public void setCourse(BigDecimal course) {
        this.course = course;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
