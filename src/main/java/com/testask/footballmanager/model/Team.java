package com.testask.footballmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "teams")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    public Team(String name, Integer commission, List<Player> players) {
        this.name = name;
        this.commission = commission;
        this.players = players;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer commission;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Player> players;
}
