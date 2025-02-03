package com.testask.footballmanager.service;

import com.testask.footballmanager.model.Team;

import java.util.List;

public interface TeamService {

    void createTeam(Team team);

    List<Team> getAllTeams();

    Team getTeamById(Long id);

    Team getTeamByName(String name);

    void updateTeam(Team team);

    void deleteTeam(Long id);
}
