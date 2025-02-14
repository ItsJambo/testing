package com.itsjambo.thunderspacebetterpotions;

import com.itsjambo.thunderspacebetterpotions.commands.*;
import com.itsjambo.thunderspacebetterpotions.utils.CompleterUtils;
import com.itsjambo.thunderspacebetterpotions.utils.ColorUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.entity.Player;
import org.bukkit.Color;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class ThunderSpaceBetterPotions extends JavaPlugin {

    private File potionsFile;
    private FileConfiguration potionsConfig;
    private Set<String> allowedEffects;

    @Override
    public void onEnable() {
        registerCommands();
        getLogger().info("[PLUGIN] ThunderSpaceBetterPotions has been enabled!");
        loadPotionsConfig();
        loadAllowedEffects();
    }

    public void loadAllowedEffects() {
        File effectsFile = new File(getDataFolder(), "effects.yml");
        if (!effectsFile.exists()) {
            saveResource("effects.yml", false);
        }
        FileConfiguration effectsConfig = YamlConfiguration.loadConfiguration(effectsFile);
        allowedEffects = new HashSet<>(effectsConfig.getStringList("effects"));
    }

    public boolean isEffectAllowed(String effectName) {
        return allowedEffects.contains(effectName.toUpperCase());
    }

    @Override
    public void onDisable() {
        getLogger().info("[PLUGIN] ThunderSpaceBetterPotions has been disabled!");
        savePotionsConfig();
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("tsbp-create")).setExecutor(new CreateCommand(this));
        Objects.requireNonNull(getCommand("tsbp-help")).setExecutor(new HelpCommand());
        Objects.requireNonNull(getCommand("tsbp-reload")).setExecutor(new ReloadCommand(this));
        Objects.requireNonNull(getCommand("tsbp-id")).setExecutor(new IdCommand(this));
        Objects.requireNonNull(getCommand("tsbp-addeffect")).setExecutor(new AddEffectCommand(this));

        Objects.requireNonNull(getCommand("tsbp-create")).setTabCompleter(new CompleterUtils());
        Objects.requireNonNull(getCommand("tsbp-addeffect")).setTabCompleter(new CompleterUtils());
    }

    public void loadPotionsConfig() {
        potionsFile = new File(getDataFolder(), "potions.yml");
        if (!potionsFile.exists()) {
            saveResource("potions.yml", false);
        }
        potionsConfig = YamlConfiguration.loadConfiguration(potionsFile);
    }

    public FileConfiguration getPotionsConfig() {
        return potionsConfig;
    }

    public void savePotionsConfig() {
        try {
            potionsConfig.save(potionsFile);
        } catch (IOException e) {
            getLogger().severe("Could not save potions.yml!");
        }
    }

    public void givePotion(Player player, String name, List<String> description, String potionId) {
        ItemStack potion = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        if (meta != null) {
            List<String> effects = getPotionsConfig().getStringList("potions." + potionId + ".effects");

            for (String effectEntry : effects) {
                String effectName = effectEntry.split(" \\| ")[0];
                Color color = getPotionColor(effectName);
                meta.setColor(color);
                break;
            }

            addEffectsToPotionMeta(meta, effects);

            meta.setDisplayName(name);
            meta.setLore(description);

            potion.setItemMeta(meta);
            player.getInventory().addItem(potion);
        }
    }

    private void addEffectsToPotionMeta(PotionMeta meta, List<String> effects) {
        for (String effectEntry : effects) {
            String[] effectParts = effectEntry.split(" \\| ");
            String effectName = effectParts[0];
            int level = Integer.parseInt(effectParts[1].split(" ")[0]);
            int duration = Integer.parseInt(effectParts[2].split(" ")[0]) * 20;
            PotionEffectType effectType = PotionEffectType.getByName(effectName);
            if (effectType != null) {
                meta.addCustomEffect(new PotionEffect(effectType, duration, level), true);
            }
        }
    }

    private Color getPotionColor(String effectName) {
        return switch (effectName) {
            case "SPEED" -> Color.fromRGB(0, 191, 255);
            case "SLOW" -> Color.fromRGB(176, 176, 176);
            case "HEAL" -> Color.fromRGB(209, 0, 91);
            case "JUMP" -> Color.fromRGB(68, 255, 0);
            case "FAST_DIGGING" -> Color.fromRGB(0, 255, 238);
            case "SLOW_DIGGING" -> Color.fromRGB(128, 128, 128);
            case "INCREASE_DAMAGE" -> Color.fromRGB(255, 36, 197);
            case "HARM" -> Color.fromRGB(128, 10, 51);
            case "CONFUSION" -> Color.fromRGB(2, 138, 0);
            case "REGENERATION" -> Color.fromRGB(250, 171, 0);
            case "DAMAGE_RESISTANCE" -> Color.fromRGB(14, 57, 225);
            case "FIRE_RESISTANCE" -> Color.fromRGB(255, 162, 0);
            case "WATER_BREATHING" -> Color.fromRGB(77, 201, 255);
            case "INVISIBILITY" -> Color.fromRGB(229, 102, 255);
            case "NIGHT_VISION" -> Color.fromRGB(140, 0, 179);
            case "WEAKNESS" -> Color.fromRGB(97, 97, 97);
            case "POISON" -> Color.fromRGB(36, 123, 35);
            case "WITHER" -> Color.fromRGB(36, 41, 36);
            case "HEALTH_BOOST" -> Color.fromRGB(255, 20, 169);
            case "ABSORPTION" -> Color.fromRGB(0, 132, 255);
            case "SATURATION" -> Color.fromRGB(184, 116, 0);
            case "GLOWING" -> Color.fromRGB(224, 249, 255);
            case "LEVITATION" -> Color.fromRGB(255, 255, 255);
            case "DOLPHINS_GRACE" -> Color.fromRGB(0, 255, 238);
            case "BAD_OMEN" -> Color.fromRGB(0, 0, 0);
            case "HERO_OF_THE_VILLAGE" -> Color.fromRGB(121, 219, 0);
            case "STRENGTH" -> Color.fromRGB(255, 0, 0);
            default -> throw new IllegalStateException("Unexpected value: " + effectName);
        };
    }
}
