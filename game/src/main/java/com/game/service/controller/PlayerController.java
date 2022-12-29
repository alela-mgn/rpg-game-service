package com.game.service.controller;

import com.game.service.entity.Player;
import com.game.service.entity.Profession;
import com.game.service.entity.Race;
import com.game.service.service.PlayerService;
import com.game.service.service.impl.PlayerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("rest/players")
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerServiceImpl playerServiceImpl;


    public PlayerController(PlayerService playerService, PlayerServiceImpl playerServiceImpl) {
        this.playerService = playerService;
        this.playerServiceImpl = playerServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers(String name, String title, Race race, Profession profession,
                                                      Long after, Long before, Boolean banned, Integer minExperience,
                                                      Integer maxExperience, Integer minLevel, Integer maxLevel,
                                                      PlayerOrder order, Integer pageNumber, Integer pageSize) {
        PlayerOrder playerOrder = order;
        if (pageSize == null) {
            pageSize = 3;
        }
        if (pageNumber == null) {
            pageNumber = 0;
        }
        if (order == null) {
            order = PlayerOrder.ID;
        }

        List<Player> players = playerService.getAllPlayers().stream()
                .sorted(((player1, player2) -> {
                    if (PlayerOrder.LEVEL.equals(playerOrder)) {
                        return player1.getLevel().compareTo(player2.getLevel());
                    }
                    if (PlayerOrder.BIRTHDAY.equals(playerOrder)) {
                        return player1.getBirthday().compareTo(player2.getBirthday());
                    }

                    if (PlayerOrder.EXPERIENCE.equals(playerOrder)) {
                        return player1.getExperience().compareTo(player2.getExperience());
                    }
                    if (PlayerOrder.NAME.equals(playerOrder)) {
                        return player1.getName().compareTo(player2.getName());
                    }
                    return player1.getId().compareTo(player2.getId());
                }))
                .filter(player -> name == null || player.getName().contains(name))
                .filter(player -> title == null || player.getTitle().contains(title))
                .filter(player -> race == null || player.getRace().equals(race))
                .filter(player -> profession == null || player.getProfession().equals(profession))
                .filter(player -> after == null || player.getBirthday().getTime() > after)
                .filter(player -> before == null || player.getBirthday().getTime() < before)
                .filter(player -> banned == null || player.getBanned().equals(banned))
                .filter(player -> minExperience == null || player.getExperience() >= minExperience)
                .filter(player -> maxExperience == null || player.getExperience() <= maxExperience)
                .filter(player -> minLevel == null || player.getLevel() >= minLevel)
                .filter(player -> maxLevel == null || player.getLevel() <= maxLevel)
                .skip(pageSize * pageNumber)
                .limit(pageSize)
                .collect(Collectors.toList());

        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<List<Player>> getCountPlayer(String name, String title, Race race, Profession profession,
                                                       Long after, Long before, Boolean banned, Integer minExperience,
                                                       Integer maxExperience, Integer minLevel,
                                                       Integer maxLevel, PlayerOrder order) {
        PlayerOrder playerOrder = order;
        if (order == null) {
            order = PlayerOrder.ID;
        }
        List<Player> players = playerService.getCountPlayers().stream()
                .sorted(((player1, player2) -> {
                    if (PlayerOrder.LEVEL.equals(playerOrder)) {
                        return player1.getLevel().compareTo(player2.getLevel());
                    }
                    if (PlayerOrder.BIRTHDAY.equals(playerOrder)) {
                        return player1.getBirthday().compareTo(player2.getBirthday());
                    }

                    if (PlayerOrder.EXPERIENCE.equals(playerOrder)) {
                        return player1.getExperience().compareTo(player2.getExperience());
                    }
                    if (PlayerOrder.NAME.equals(playerOrder)) {
                        return player1.getName().compareTo(player2.getName());
                    }
                    return player1.getId().compareTo(player2.getId());
                }))
                .filter(player -> name == null || player.getName().contains(name))
                .filter(player -> title == null || player.getTitle().contains(title))
                .filter(player -> race == null || player.getRace().equals(race))
                .filter(player -> profession == null || player.getProfession().equals(profession))
                .filter(player -> after == null || player.getBirthday().getTime() > after)
                .filter(player -> before == null || player.getBirthday().getTime() < before)
                .filter(player -> banned == null || player.getBanned().equals(banned))
                .filter(player -> minExperience == null || player.getExperience() >= minExperience)
                .filter(player -> maxExperience == null || player.getExperience() <= maxExperience)
                .filter(player -> minLevel == null || player.getLevel() >= minLevel)
                .filter(player -> maxLevel == null || player.getLevel() <= maxLevel)
                .collect(Collectors.toList());
        return new ResponseEntity(players.size(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getByIdPlayer(@PathVariable("id") Long playerId) {

        if (!playerServiceImpl.isValidated(playerId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Player player = playerService.getById(playerId);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Player> deleteByIdPlayer(@PathVariable("id") Long id) {
        if (!playerServiceImpl.isValidated(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Player player = playerService.getById(id);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {

        if (!player.isCheckedBody()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!playerServiceImpl.checkedCreateAndUpdatePlayer(player)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (player.getBanned() == null) {
            player.setBanned(false);
        }
        player.ignoringIdLevelUntil();
        playerService.create(player);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player updatePlayer) {
        if (!playerServiceImpl.isValidated(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Player player = playerService.getById(id);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        player.updateChecked(updatePlayer);
        if (!player.isCheckedBody()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!playerServiceImpl.checkedCreateAndUpdatePlayer(player)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        playerService.update(player);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }
}
