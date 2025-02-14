package com.itsjambo.thunderspacebetterpotions.utils;

import net.md_5.bungee.api.ChatColor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtils {

    private static final Pattern GRADIENT_PATTERN = Pattern.compile("&#([0-9a-fA-F]{6})");

    public static String parse(String input) {
        return applyGradient(ChatColor.translateAlternateColorCodes('&', input));
    }

    public static String toHex(String input) {
        return applyGradient(input).trim();
    }

    public static List<String> parseLore(String input) {
        List<String> lore = new ArrayList<>();
        lore.add(applyGradient(ChatColor.translateAlternateColorCodes('&', input)));
        return lore;
    }

    private static String applyGradient(String result) {
        Matcher matcher = GRADIENT_PATTERN.matcher(result);
        StringBuilder output = new StringBuilder();
        int lastIndex = 0;

        while (matcher.find()) {
            output.append(result, lastIndex, matcher.start());
            String hexColor = matcher.group(1);
            output.append(ChatColor.of(Color.decode("#" + hexColor)));
            lastIndex = matcher.end();
        }

        output.append(result.substring(lastIndex));
        return output.toString();
    }
}