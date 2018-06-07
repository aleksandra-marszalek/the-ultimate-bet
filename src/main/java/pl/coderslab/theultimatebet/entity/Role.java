package pl.coderslab.theultimatebet.entity;

import javax.persistence.*;

/**
 * Simple entity related to {@link User}. Normally should be used to give different authorities to the users.
 * As for now, in this app there is only possibility to add regular user, but in the future for sure should be at least
 * an admin / moderator, too.
 */
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "role_id")
    private int id;

    @Column(name = "role")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
