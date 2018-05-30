package pl.coderslab.theultimatebet.service;

import pl.coderslab.theultimatebet.entity.User;

public interface UserService {
    public User findByUserName(String name);
    public void saveUser(User user); }
