package pl.coderslab.theultimatebet.service;

import org.json.JSONObject;

import java.util.ArrayList;

public interface ApiService {

    public ArrayList<JSONObject> getGroups();

    public ArrayList<JSONObject> getTeams();

    public ArrayList<JSONObject> getAllGames();

    public ArrayList<JSONObject> getScheduledGames();

    public ArrayList<JSONObject> getFinishedGames();
}
