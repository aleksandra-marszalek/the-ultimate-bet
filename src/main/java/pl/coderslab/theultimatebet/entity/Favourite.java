package pl.coderslab.theultimatebet.entity;

import javax.persistence.*;
import java.util.List;

/**
 *Simple entity directly related to {@link User}, which enables the {@link User} keeping the information about their favourite {@link Team}.
 */
@Entity
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;


    @OneToMany
    private List<Team> teams;

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

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
