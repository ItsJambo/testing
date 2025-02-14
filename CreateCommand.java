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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CreateCommand implements CommandExecutor {

    private final ThunderSpaceBetterPotions plugin;

    public CreateCommand(ThunderSpaceBetterPotions plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!sender.hasPermission("tsbp-create")) {
            sender.sendMessage(ColorUtils.parse("&#FF1C39[✘] You do not have permission to use this command!"));
            return true;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage(ColorUtils.parse("&#FF1C39[✘] This command only for players!"));
            return true;
        }

        if (args.length < 5) {
            sender.sendMessage(ColorUtils.parse("&#FF1C39[✘] Usage: /tsbp-create [name] [description] [effect] [level] [duration]"));
            return true;
        }

        String name = args[0];
        List<String> descriptionList = new ArrayList<>();
        for (int i = 1; i < args.length - 3; i++) {
            descriptionList.add(args[i]);
        }
        String effectName = args[args.length - 3].toUpperCase();
        int level, duration;

        try {
            level = Integer.parseInt(args[args.length - 2]);
            duration = Integer.parseInt(args[args.length - 1]) * 20;
        } catch (NumberFormatException e) {
            sender.sendMessage(ColorUtils.parse("&#FF1C39[✘] Incorrect level or duration format!"));
            return true;
        }

        PotionEffectType effectType = PotionEffectType.getByName(effectName);
        if (effectType == null) {
            sender.sendMessage(ColorUtils.parse("&#FF1C39[✘] Unknown effect: " + effectName));
            return true;
        }

        FileConfiguration config = plugin.getPotionsConfig();
        String potionId = generatePotionId(config);
        savePotionConfig(config, potionId, name, descriptionList, effectName, level, duration);
        plugin.savePotionsConfig();
        plugin.givePotion(player, ColorUtils.parse(name), Collections.singletonList(descriptionList.toString()), potionId);
        player.sendMessage(ColorUtils.parse("&#08FB52[✔] Potion " + name + " &#08FB52created and given successfully!"));

        return true;
    }

    private String generatePotionId(FileConfiguration config) {
        return "id-" + (config.getConfigurationSection("potions") == null ? 1 : Objects.requireNonNull(config.getConfigurationSection("potions")).getKeys(false).size() + 1);
    }

    private void savePotionConfig(FileConfiguration config, String potionId, String name, List<String> description, String effectName, int level, int duration) {
        String hexName = name.contains("&#") ? ColorUtils.toHex(name) : name;
        List<String> hexDescription = new ArrayList<>();
        for (String desc : description) {
            hexDescription.add(ColorUtils.toHex(desc));
        }
        config.set("potions." + potionId + ".name", hexName);
        config.set("potions." + potionId + ".description", hexDescription);
        String effectEntry = effectName + " | " + level + " level | " + (duration / 20) + " seconds";
        config.set("potions." + potionId + ".effects", List.of(effectEntry));
    }
}