package fr.aelita.betterwhitelist.commands;

import fr.aelita.betterwhitelist.Info;
import fr.aelita.betterwhitelist.Main;
import fr.aelita.betterwhitelist.manager.WhitelistManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhitelistCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        Player player = (Player) commandSender;
        String args = strings[0];
        if (!player.isOp()) {
            player.sendMessage(Info.PREFIX + "§cVous n'avez pas la permission d'effectuer ceci.");
            return false;
        }
        WhitelistManager whitelistManager = Main.getInstance().getWhitelistManager();
        switch (args) {
            case "add": {
                if (strings.length < 2) {
                    return false;
                }
                for (int i = 1; i < strings.length; i++) {
                    String name = strings[i];
                    if (whitelistManager.containsPlayer(name)) {
                        player.sendMessage(Info.PREFIX + "§a§l" + name + " §aest déjà présent dans la liste blanche.");
                    }
                    Main.getInstance().getWhitelistManager().addPlayerToWhiteList(name);
                    player.sendMessage(Info.PREFIX + "§aVous avez ajouté §l" + name + " §aà la liste blanche.");
                }
                break;
            }
            case "remove": {
                if (strings.length < 2) {
                    return false;
                }
                for (int i = 1; i < strings.length; i++) {
                    String name = strings[i];
                    if (!whitelistManager.containsPlayer(name)) {
                        player.sendMessage(Info.PREFIX + "§c§l" + name + " §cn'est pas présent dans la liste blanche.");
                    }
                    Main.getInstance().getWhitelistManager().removePlayerToWhitelist(name);
                    player.sendMessage(Info.PREFIX + "§cVous avez retiré §l" + name + " §cde la liste blanche.");
                }
            }
            break;
            case "list": {
                player.sendMessage(Info.PREFIX + "§aVoici la liste de tous les joueurs dans la liste blanche §8(§7" + whitelistManager.getWhitelistedPlayers().size() + " joueurs§8) §a:");
                for (String name : whitelistManager.getWhitelistedPlayers()) {
                    Player whitelistedPlayers = Bukkit.getPlayer(name);
                    player.sendMessage("§7 - " + (whitelistedPlayers == null ? "§c" : "§a"));
                }
            }
            break;
            case "on": {
                if (!whitelistManager.isActivate()) {
                    player.sendMessage(Info.PREFIX + "§aVous avez activé la liste blanche.");
                    whitelistManager.setActivate(true);
                } else {
                    player.sendMessage(Info.PREFIX + "§aLa liste blanche est déjà activée.");
                }
            }
            break;
            case "off": {
                if (whitelistManager.isActivate()) {
                    player.sendMessage(Info.PREFIX + "§aVous avez désactivé la liste blanche.");
                    whitelistManager.setActivate(false);
                } else {
                    player.sendMessage(Info.PREFIX + "§cLa liste blanche est déjà désactivée.");
                }
            }
        }
        return false;
    }
}
