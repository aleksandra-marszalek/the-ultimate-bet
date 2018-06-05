package pl.coderslab.theultimatebet.entity;

import javax.persistence.*;

@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
