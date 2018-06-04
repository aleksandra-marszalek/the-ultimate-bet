package pl.coderslab.theultimatebet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.coderslab.theultimatebet.entity.Game;
import pl.coderslab.theultimatebet.entity.Group;
import pl.coderslab.theultimatebet.entity.Team;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("seeding")
    private int seeding;

    @JsonProperty("strength")
    private double strength;

    @JsonProperty("gameAsTeam1")
    private List<Game> gamesAsTeam1;

    @JsonProperty("gameAsTeam2")
    private List<Game> gamesAsTeam2;

    @JsonProperty("group_id")
    private Long group_id;

    @JsonProperty("placeInGroup")
    private int placeInGroup;

    @JsonProperty("won")
    private int won;

    @JsonProperty("lost")
    private int lost;

    @JsonProperty("pointBalance")
    private int pointBalance;

    @JsonProperty("loserWinerSignature")
    private String loserWinerSignature;

    @JsonProperty("finalStanding")
    private int finalStanding;


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

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
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
}
