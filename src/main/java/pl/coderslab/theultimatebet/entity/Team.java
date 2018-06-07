package pl.coderslab.theultimatebet.entity;

import javax.persistence.*;
import java.util.List;

/**
 * One of the most important entities - keeps all the info about the team.
 * Related to {@link Game}, has also info about name, seeding, strength, number of won matches, lost matches, point
 * balance, final Standings (only available after the tournament ends) and some helpful signature.
 * Also related to {@link Group}(important at a group stage to arrange the matches).
 * Implements the Comparable, in order to sort the groups right (in the api, but who knows..).
 * All data taken from the external api.
 */
@Entity
public class Team implements Comparable<Team>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long apiId;

    private String name;

    private int seeding;

    private double strength;

    @OneToMany(mappedBy = "team1")
    private List<Game> gamesAsTeam1;

    @OneToMany(mappedBy = "team2")
    private List<Game> gamesAsTeam2;

    @ManyToOne
    private Group group;

    private int placeInGroup;

    private int won;

    private int lost;

    private int pointBalance;

    private String loserWinerSignature;

    private int finalStanding;

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

    public int getSeeding() {
        return seeding;
    }

    public void setSeeding(int seeding) {
        this.seeding = seeding;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getPlaceInGroup() {
        return placeInGroup;
    }

    public void setPlaceInGroup(int placeInGroup) {
        this.placeInGroup = placeInGroup;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getPointBalance() {
        return pointBalance;
    }

    public void setPointBalance(int pointBalance) {
        this.pointBalance = pointBalance;
    }

    public String getLoserWinerSignature() {
        return loserWinerSignature;
    }

    public void setLoserWinerSignature(String loserWinerSignature) {
        this.loserWinerSignature = loserWinerSignature;
    }

    public int getFinalStanding() {
        return finalStanding;
    }

    public void setFinalStanding(int finalStanding) {
        this.finalStanding = finalStanding;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    @Override
    public int compareTo(Team team) {
        if (this.getWon()>team.getWon()) {
            return 1;
        } else if (this.getWon()<team.getWon()) {
            return -1;
        } else {
            if (this.getPointBalance()>team.getPointBalance()) {
                return 1;
            } else if (this.getPointBalance()<team.getPointBalance()){
                return -1;
            } if (this.getSeeding()>team.getSeeding()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
