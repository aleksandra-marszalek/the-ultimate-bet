package pl.coderslab.theultimatebet.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Simple entity to keep the information about the group of the teams in the group stage of the tournament.
 * Related mainly to {@link Team}. All info taken from the external api and transformed to the object of Group
 * by {@link pl.coderslab.theultimatebet.dto.GroupDto}
 */
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long apiId;

    private String name;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<Team> teams;


    public Group() {
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

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }
}
