package pl.coderslab.theultimatebet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.coderslab.theultimatebet.entity.Team;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupDto {

        @JsonProperty("id")
        private Long apiId;

        @JsonProperty("name")
        private String name;

        @JsonProperty("teams")
        private List<Team> teams;

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
}
