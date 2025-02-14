package com.itsjambo.thunderspacebetterpotions.commands;

import com.itsjambo.thunderspacebetterpotions.utils.ColorUtils;
import com.itsjambo.thunderspacebetterpotions.ThunderSpaceBetterPotions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {

    private final ThunderSpaceBetterPotions plugin;

    public ReloadCommand(ThunderSpaceBetterPotions plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!sender.hasPermission("tsbp-help")) {
            sender.sendMessage(ColorUtils.parse("&#FF1C39[✘] You do not have permission to use this command!"));
            return true;
        }

        plugin.loadPotionsConfig();
        sender.sendMessage(ColorUtils.parse("&#08FB52[✔] Configuration reloaded successfully!"));
        return true;
    }
}