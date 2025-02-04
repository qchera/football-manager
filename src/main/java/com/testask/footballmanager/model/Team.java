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

    private Double balance;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Player> players;

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void transferPlayer(Player player, Team newTeam, Double transferSum) {

        players.remove(player);
        player.setTeam(newTeam);
        newTeam.addPlayer(player);

        newTeam.balance -= transferSum;
        this.balance += transferSum;
    }
}
