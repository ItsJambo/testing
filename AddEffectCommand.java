package com.itsjambo.thunderspacebetterpotions.commands;

import com.itsjambo.thunderspacebetterpotions.utils.ColorUtils;
import com.itsjambo.thunderspacebetterpotions.ThunderSpaceBetterPotions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AddEffectCommand implements CommandExecutor {

    private final ThunderSpaceBetterPotions plugin;

    public AddEffectCommand(ThunderSpaceBetterPotions plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!sender.hasPermission("tsbp-addeffect")) {
            sender.sendMessage(ColorUtils.parse("You do not have permission to use this command!"));
            return true;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage(ColorUtils.parse("&#FF1C39[✘] This command is only for players!"));
            return true;
        }

        if (args.length != 4) {
            sender.sendMessage(ColorUtils.parse("&#FF1C39[✘] Usage: /tsbp-addeffect [potionID] [effect] [level] [duration]"));
            return true;
        }

        String potionId = "id-" + args[0];
        String effectName = args[1].toUpperCase();
        int level, duration;

        try {
            level = Integer.parseInt(args[2]);
            duration = Integer.parseInt(args[3]) * 20;
        } catch (NumberFormatException e) {
            sender.sendMessage(ColorUtils.parse("&#FF1C39[✘] Incorrect level or duration format!"));
            return true;
        }

        PotionEffectType effectType = PotionEffectType.getByName(effectName);
        if (effectType == null || !plugin.isEffectAllowed(effectName)) {
            sender.sendMessage(ColorUtils.parse("&#FF1C39[✘] Unknown or disallowed effect: " + effectName));
            return true;
        }

        FileConfiguration config = plugin.getPotionsConfig();
        if (!config.contains("potions." + potionId)) {
            sender.sendMessage(ColorUtils.parse("&#08FB52[✘] Potion with ID " + potionId + " &#08FB52not found!"));
            return true;
        }

        String effectEntry = effectName + " | " + level + " level | " + (duration / 20) + " seconds";
        List<String> effects = config.getStringList("potions." + potionId + ".effects");
        effects.add(effectEntry);
        config.set("potions." + potionId + ".effects", effects);

        plugin.savePotionsConfig();
        player.sendMessage(ColorUtils.parse("&#08FB52[✔] Effect " + effectName + " &#08FB52added to potion " + potionId + " &#08FB52successfully!"));

        return true;
    }
}