package com.testask.footballmanager.controller;

import com.testask.footballmanager.service.TransferService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
@Validated
public class TransferController {

    private final TransferService transferService;

    @PostMapping("/by-id")
    public ResponseEntity<String> transferPlayerById(@RequestParam @Min(value = 1,message = "Player id can't be smaller than 1") Long playerId,
                                                     @RequestParam @Min(value = 1,message = "Team id can't be smaller than 1") Long teamId) {
        boolean success = transferService.transferPlayer(playerId, teamId);
        return success ? ResponseEntity.ok("Successfully transfer player with id '" + playerId + "' to team with id '" + teamId + "'") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transfer failed");
    }

    @PostMapping("/by-name")
    public ResponseEntity<String> transferPlayerByName(@RequestParam @NotBlank(message = "Player name can't be blank") String playerName,
                                                       @RequestParam @NotBlank(message = "Team name can't be blank") String teamName) {
        boolean success = transferService.transferPlayer(playerName, teamName);
        return success ? ResponseEntity.ok("Successfully transfer '" + playerName + "' to '" + teamName + "'") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transfer failed");
    }
}
