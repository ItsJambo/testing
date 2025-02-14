package com.itsjambo.thunderspacebetterpotions.commands;

import com.itsjambo.thunderspacebetterpotions.utils.ColorUtils;
import com.itsjambo.thunderspacebetterpotions.ThunderSpaceBetterPotions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IdCommand implements CommandExecutor {

    private final ThunderSpaceBetterPotions plugin;

    public IdCommand(ThunderSpaceBetterPotions plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!sender.hasPermission("tsbp-id")) {
            sender.sendMessage(ColorUtils.parse("&#FF1C39[✘] You do not have permission to use this command!"));
            return true;
        }

        if (args.length != 2) {
            sender.sendMessage(ColorUtils.parse("&#FF1C39[✘] Usage: /tsbp-id [nickname] [id]"));
            return true;
        }

        String nickname = args[0];
        String potionId = "id-" + args[1];
        FileConfiguration config = plugin.getPotionsConfig();

        Player targetPlayer = plugin.getServer().getPlayer(nickname);
        if (targetPlayer == null) {
            sender.sendMessage(ColorUtils.parse("&#FF1C39[✘] Player " + nickname + " not found!"));
            return true;
        }

        if (!config.contains("potions." + potionId)) {
            sender.sendMessage(ColorUtils.parse("&#FF1C39[✘] Potion with ID " + potionId + " not found!"));
            return true;
        }

        String name = config.getString("potions." + potionId + ".name");
        List<String> descriptionList = config.getStringList("potions." + potionId + ".description");

        plugin.givePotion(targetPlayer, ColorUtils.parse(name), descriptionList, potionId); // Передаємо список опису
        targetPlayer.sendMessage(ColorUtils.parse("&#08FB52[✔] Potion " + name + " has been given successfully!"));

        return true;
    }
}