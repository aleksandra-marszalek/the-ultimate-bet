package pl.coderslab.theultimatebet.service;

import pl.coderslab.theultimatebet.entity.User;

public interface UserService {
    public User findByUserName(String name);

    public User findByEmail(String email);

    public void saveUser(User user);

    public boolean checkUsername(User user);

    public void save(User user);

    public boolean checkEmail(User user);
}
