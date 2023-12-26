package fr.aelita.betterwhitelist.listener;

import fr.aelita.betterwhitelist.Info;
import fr.aelita.betterwhitelist.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        if (!Main.getInstance().getWhitelistManager().containsPlayer(player.getName()) && !player.isOp() && Main.getInstance().getWhitelistManager().isActivate()) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, Info.PREFIX + "§cVous n'êtes pas dans la liste blanche.");
        }
    }
}
