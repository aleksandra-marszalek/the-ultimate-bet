package pl.coderslab.theultimatebet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * Class used to convert the Json objects into {@link pl.coderslab.theultimatebet.entity.Game} objects.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameDto {

    @JsonProperty("id")
    private Long apiId;

    @JsonProperty("team1_id")
    private Long team1Id;

    @JsonProperty("team2_id")
    private Long team2Id;

    @JsonProperty("pointsTeam1")
    private int pointsTeam1;

    @JsonProperty("pointsTeam2")
    private int pointsTeam2;

    @JsonProperty("oddsForTeam1")
    private double oddsForTeam1;

    @JsonProperty("oddsForTeam2")
    private double oddsForTeam2;

    @JsonProperty("gameTime")
    private LocalDateTime gameTime;

    @JsonProperty("status")
    private int status;

    @JsonProperty("signature")
    private String signature;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }


    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    public Long getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(Long team1Id) {
        this.team1Id = team1Id;
    }

    public Long getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(Long team2Id) {
        this.team2Id = team2Id;
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

    public LocalDateTime getGameTime() {
        return gameTime;
    }

    public void setGameTime(LocalDateTime gameTime) {
        this.gameTime = gameTime;
    }
}
