package com.testask.footballmanager.service;

import com.testask.footballmanager.model.dto.TeamDTO;

import java.util.List;

public interface TeamService {

    TeamDTO createTeam(TeamDTO teamDTO);

    List<TeamDTO> getAllTeams();

    TeamDTO getTeamById(Long id);

    TeamDTO getTeamByName(String name);

    TeamDTO updateTeam(TeamDTO teamDTO);

    void deleteTeam(Long id);
}
