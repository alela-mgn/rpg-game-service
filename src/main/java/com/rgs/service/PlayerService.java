package com.rgs.service;

import com.rgs.entity.Player;

import java.util.List;

public interface PlayerService {

    List<Player> getAllPlayers();

    List<Player> getPlayersCount();

    Player getById(Long id);

    void delete(Long id);

    void create(Player player);

    void update(Player player);

    boolean isValidPlayer(Player player);

    boolean isValidId(Long id);

}
