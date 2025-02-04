package com.testask.footballmanager.mapper;

import com.testask.footballmanager.exception.TeamNotFoundException;
import com.testask.footballmanager.model.Player;
import com.testask.footballmanager.model.Team;
import com.testask.footballmanager.model.dto.PlayerDTO;
import com.testask.footballmanager.repository.TeamRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = TeamMapper.class)
public abstract class PlayerMapper {

    @Autowired
    protected TeamRepository teamRepository;

    @Mapping(source = "team", target = "team", qualifiedByName = "stringToTeam")
    public abstract Player toPlayer(PlayerDTO playerDTO);

    @Mapping(source = "team.name", target = "team")
    public abstract PlayerDTO toPlayerDTO(Player player);

    public abstract List<PlayerDTO> toPlayerDTOList(List<Player> players);

    @Named("stringToTeam")
    protected Team stringToTeam(String teamName) {
        return teamRepository.findByName(teamName)
                             .orElseThrow(() -> new TeamNotFoundException("Team with name '" + teamName + "' not found"));
    }
}
