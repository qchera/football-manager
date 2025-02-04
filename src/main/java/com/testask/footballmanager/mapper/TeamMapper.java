package com.testask.footballmanager.mapper;

import com.testask.footballmanager.model.Team;
import com.testask.footballmanager.model.dto.TeamDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PlayerMapper.class})
public interface TeamMapper {

    Team toTeam(TeamDTO teamDTO);

    TeamDTO toTeamDTO(Team team);

    List<TeamDTO> toTeamDTOList(List<Team> teams);
}
