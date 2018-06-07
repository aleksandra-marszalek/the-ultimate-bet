package pl.coderslab.theultimatebet.entity;

import pl.coderslab.theultimatebet.validationGroups.ValidationUser;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.groups.Default;
import java.util.Set;


/**
 * One of the most important entities in the app. Keeps the info about the user. Has String attributes username,
 * firstname, lastname, email, password and boolean isAdult - must be true to enable saving to DB.
 * Also related to {@link Wallet} - each user has their waller, {@link Favourite} to mark their favourite {@link Team},
 * {@link Role} - to provide the user different authorities for different roles (TODO).
 */
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(nullable = false, unique = true)
    @NotEmpty(groups = {ValidationUser.class, Default.class})
    private String username;

    @NotEmpty(groups = ValidationUser.class, message = "cannot be empty")
    private String firstname;

    @NotEmpty(groups = ValidationUser.class, message = "cannot be empty")
    private String lastname;


    private boolean adult;

    @Email
    @NotEmpty(groups = ValidationUser.class, message = "cannot be empty")
    @Column(unique = true)
    private String email;

    @NotEmpty(groups = ValidationUser.class)
    private String password;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    private Wallet wallet;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    private Favourite favourite;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Favourite getFavourite() {
        return favourite;
    }

    public void setFavourite(Favourite favourite) {
        this.favourite = favourite;
    }
}
