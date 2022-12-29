package com.game.service.service;

import com.game.service.entity.Player;

import java.util.List;

public interface PlayerService {

    List<Player> getAllPlayers();

    List<Player> getCountPlayers();

    Player getById(Long id);

    void delete(Long id);

    void create(Player player);

    void update(Player player);

    Boolean checkedCreateAndUpdatePlayer(Player player);

    boolean isValidated(Long id);

}
