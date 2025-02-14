package com.itsjambo.thunderspacebetterpotions.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CompleterUtils implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        List<String> completions = new ArrayList<>();

        if (command.getName().equalsIgnoreCase("tsbp-create")) {
            if (args.length == 1) {
                completions.add("PotionName");
            } else if (args.length == 2) {
                completions.add("PotionDesciption");
            } else if (args.length == 3) {
                completions.add("SPEED");
                completions.add("SLOW");
                completions.add("HEAL");
                completions.add("FAST_DIGGING");
                completions.add("SLOW_DIGGING");
                completions.add("INCREASE_DAMAGE");
                completions.add("HARM");
                completions.add("JUMP");
                completions.add("CONFUSION");
                completions.add("REGENERATION");
                completions.add("DAMAGE_RESISTANCE");
                completions.add("FIRE_RESISTANCE");
                completions.add("WATER_BREATHING");
                completions.add("INVISIBILITY");
                completions.add("NIGHT_VISION");
                completions.add("WEAKNESS");
                completions.add("POISON");
                completions.add("WITHER");
                completions.add("HEALTH_BOOST");
                completions.add("ABSORPTION");
                completions.add("SATURATION");
                completions.add("GLOWING");
                completions.add("LEVITATION");
                completions.add("DOLPHINS_GRACE");
                completions.add("BAD_OMEN");
                completions.add("HERO_OF_THE_VILLAGE");
            } else if (args.length == 4) {
                completions.add("1");
                completions.add("3");
                completions.add("5");
            } else if (args.length == 5) {
                completions.add("30");
                completions.add("60");
                completions.add("120");
                completions.add("240");
            }
        } else if (command.getName().equalsIgnoreCase("tsbp-addeffect")) {
            if (args.length == 1) {
                completions.add("1");
                completions.add("2");
                completions.add("3");
                completions.add("4");
                completions.add("5");
                completions.add("6");
                completions.add("7");
                completions.add("8");
                completions.add("9");
                completions.add("10");
            } else if (args.length == 2) {
                completions.add("SPEED");
                completions.add("SLOW");
                completions.add("HEAL");
                completions.add("FAST_DIGGING");
                completions.add("SLOW_DIGGING");
                completions.add("INCREASE_DAMAGE");
                completions.add("HARM");
                completions.add("JUMP");
                completions.add("CONFUSION");
                completions.add("REGENERATION");
                completions.add("DAMAGE_RESISTANCE");
                completions.add("FIRE_RESISTANCE");
                completions.add("WATER_BREATHING");
                completions.add("INVISIBILITY");
                completions.add("NIGHT_VISION");
                completions.add("WEAKNESS");
                completions.add("POISON");
                completions.add("WITHER");
                completions.add("HEALTH_BOOST");
                completions.add("ABSORPTION");
                completions.add("SATURATION");
                completions.add("GLOWING");
                completions.add("LEVITATION");
                completions.add("DOLPHINS_GRACE");
                completions.add("BAD_OMEN");
                completions.add("HERO_OF_THE_VILLAGE");
            } else if (args.length == 3) {
                completions.add("1");
                completions.add("3");
                completions.add("5");
            } else if (args.length == 4) {
                completions.add("30");
                completions.add("60");
                completions.add("120");
                completions.add("240");
            }
        }
        return completions;
    }
}