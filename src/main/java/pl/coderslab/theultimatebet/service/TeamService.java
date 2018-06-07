package pl.coderslab.theultimatebet.service;

import pl.coderslab.theultimatebet.entity.Team;

import java.util.List;

public interface TeamService {

    public Team findByApiId(Long id);

    public Team findById (Long id);

    public List<Team> findAllOrderByStandings();

   public List<Team> findAllOrderBySeeding ();
}
