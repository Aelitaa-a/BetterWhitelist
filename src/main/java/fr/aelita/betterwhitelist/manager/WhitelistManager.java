package fr.aelita.betterwhitelist.manager;

import java.util.ArrayList;
import java.util.UUID;

public class WhitelistManager {
    private ArrayList<String> whitelistedPlayers;

    private boolean activate;

    public WhitelistManager() {
        this.whitelistedPlayers = new ArrayList<>();
        this.activate = false;
    }

    public void addPlayerToWhiteList(String name) {
        this.whitelistedPlayers.add(name);
    }

    public void removePlayerToWhitelist(String name) {
        this.whitelistedPlayers.remove(name);
    }

    public boolean containsPlayer(String name){
        for (String playerName : this.whitelistedPlayers) {
            if (playerName.equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    public ArrayList<String> getWhitelistedPlayers() {
        return whitelistedPlayers;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }
}
