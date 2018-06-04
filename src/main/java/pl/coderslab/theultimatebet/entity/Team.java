package pl.coderslab.theultimatebet.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String seeding;

    private String strength;

    @OneToMany(mappedBy = "team1")
    private List<Game> gamesAsTeam1;

    @OneToMany(mappedBy = "team2")
    private List<Game> gamesAsTeam2;

    public Team() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeeding() {
        return seeding;
    }

    public void setSeeding(String seeding) {
        this.seeding = seeding;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public List<Game> getGamesAsTeam1() {
        return gamesAsTeam1;
    }

    public void setGamesAsTeam1(List<Game> gamesAsTeam1) {
        this.gamesAsTeam1 = gamesAsTeam1;
    }

    public List<Game> getGamesAsTeam2() {
        return gamesAsTeam2;
    }

    public void setGamesAsTeam2(List<Game> gamesAsTeam2) {
        this.gamesAsTeam2 = gamesAsTeam2;
    }
}
