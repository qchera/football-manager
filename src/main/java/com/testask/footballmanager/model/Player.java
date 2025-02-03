package com.testask.footballmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "players")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    public Player(String name, Integer age, Integer experience, Team team) {
        this.name = name;
        this.age = age;
        this.experience = experience;
        this.team = team;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    private Integer experience;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
