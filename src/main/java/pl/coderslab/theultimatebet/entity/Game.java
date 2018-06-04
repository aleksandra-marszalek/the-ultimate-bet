package pl.coderslab.theultimatebet.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Game {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team1_id")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_id")
    private Team team2;

    private int pointsTeam1;

    private int pointsTeam2;

    private LocalDateTime gameTime;

    private double oddsForTeam1;

    private double oddsForTeam2;

    private int status;

    private String signature;

    public Game() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public int getPointsTeam1() {
        return pointsTeam1;
    }

    public void setPointsTeam1(int pointsTeam1) {
        this.pointsTeam1 = pointsTeam1;
    }

    public int getPointsTeam2() {
        return pointsTeam2;
    }

    public void setPointsTeam2(int pointsTeam2) {
        this.pointsTeam2 = pointsTeam2;
    }

    public LocalDateTime getGameTime() {
        return gameTime;
    }

    public void setGameTime(LocalDateTime gameTime) {
        this.gameTime = gameTime;
    }

    public double getOddsForTeam1() {
        return oddsForTeam1;
    }

    public void setOddsForTeam1(double oddsForTeam1) {
        this.oddsForTeam1 = oddsForTeam1;
    }

    public double getOddsForTeam2() {
        return oddsForTeam2;
    }

    public void setOddsForTeam2(double oddsForTeam2) {
        this.oddsForTeam2 = oddsForTeam2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}

