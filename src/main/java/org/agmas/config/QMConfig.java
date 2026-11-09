package org.agmas.config;

import com.google.gson.FormattingStyle;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
import net.fabricmc.fabric.impl.datagen.FabricDataGenHelper;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import org.agmas.Quartermaster;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class QMConfig {
    public static HashMap<String, WeaponEnabledStatus> weaponDefaults = new HashMap<>();
    public static HashMap<String, WeaponEnabledStatus> enabledWeapons = new HashMap<>();
    public static ArrayList<String> disabledEnchants = new ArrayList<>();
    public static ArrayList<String> disabledEnchantsDefaults = new ArrayList<>();
    public static boolean mergeFrost = false;
    public static boolean stunEffects = true;

    public static void parse() {
        weaponDefaults.put("cutlass", new WeaponEnabledStatus(true, true,true));
        weaponDefaults.put("morningstar", new WeaponEnabledStatus(true, true,true));
        weaponDefaults.put("estoc", new WeaponEnabledStatus(true, true,true));
        weaponDefaults.put("greataxe", new WeaponEnabledStatus(true, true,true));
        weaponDefaults.put("warhammer", new WeaponEnabledStatus(true, true,true));
        weaponDefaults.put("sickle", new WeaponEnabledStatus(true, true,true));
        weaponDefaults.put("rapier", new WeaponEnabledStatus(false, true,false));

        disabledEnchantsDefaults.add("quartermaster:impact_protection");
        disabledEnchantsDefaults.add("quartermaster:magic_protection");
        disabledEnchantsDefaults.add("quartermaster:frost_protection");
        disabledEnchantsDefaults.add("quartermaster:bladedance");
        disabledEnchantsDefaults.add("quartermaster:singlehanded");
        disabledEnchantsDefaults.add("quartermaster:explosive_kinectivity");

        if (FabricDataGenHelper.ENABLED) {
            weaponDefaults.forEach((s, ignored)->{
                enabledWeapons.put(s, new WeaponEnabledStatus(true,true,true));
            });
            disabledEnchantsDefaults.clear();
            return;
        }
        enabledWeapons.putAll(weaponDefaults);
        File configFile = new File("config/quartermaster.json5");
        if (configFile.exists()) {
            try {
                JsonElement element = JsonParser.parseReader(new FileReader(configFile));
                for (JsonElement weapons : element.getAsJsonObject().getAsJsonArray("enabledWeapons")) {
                    JsonObject object = weapons.getAsJsonObject();
                    enabledWeapons.put(object.get("name").getAsString(), new WeaponEnabledStatus(object.get("tiered").getAsBoolean(), object.get("relic").getAsBoolean(), object.get("bamboo").getAsBoolean()));
                }
                for (JsonElement enchant : element.getAsJsonObject().getAsJsonArray("disabledEnchants")) {
                    disabledEnchants.add(enchant.getAsString());
                }
            } catch (Exception ignored) {}
        } else {
            disabledEnchants.addAll(disabledEnchantsDefaults);
        }
    }

    public static void write() {
        File configFile = new File("config/quartermaster.json5");
        try {
            configFile.createNewFile();
            FileWriter writer = new FileWriter("config/quartermaster.json5");
            JsonWriter jsonWriter = new JsonWriter(writer);
            jsonWriter.setFormattingStyle(FormattingStyle.PRETTY);
            writer.write("\n// IMPORTANT NOTE: This config file should be synced between client and server! It's not *nessecary*, but will cause desync and break mods like Flashback!");
            writer.write("\n");
            writer.write("\n// enabledWeapons");
            writer.write("\n// Allows you to disable weapons, or certain tiers of weapons.");
            writer.write("\n");
            writer.write("\n// disabledEnchants");
            writer.write("// Which enchants should be registered. If you want to disable enchants from the base game or other mods, I would reccomend using Enchancements rather than this.");
            writer.write("// The default disabled enchant list is mostly enchants which only fit for the Apostasy Season 2 modpack. And explosive kinectivity.");
            writer.write("// Does not fully disable the enchant (like enchancements), but makes it unobtainable in survival.");
            writer.write("\n");
            writer.write("\n// mergeFrost");
            writer.write("// If Frost Aspect should be replaced with Enchancement's Frostbite");

            jsonWriter.beginObject();

            // Weapons
            jsonWriter.name("enabledWeapons").beginArray();
            for (Map.Entry<String, WeaponEnabledStatus> enabledWeapon : enabledWeapons.entrySet()) {
                jsonWriter.beginObject();
                jsonWriter.name("name").value(enabledWeapon.getKey());
                jsonWriter.name("tiered").value(enabledWeapon.getValue().tiered);
                jsonWriter.name("relic").value(enabledWeapon.getValue().relic);
                jsonWriter.name("bamboo").value(enabledWeapon.getValue().bamboo);
                jsonWriter.endObject();
            }
            jsonWriter.endArray();


            // Enchants
            jsonWriter.name("disabledEnchants").beginArray();
            for (String disabledEnchant : disabledEnchants) {
                jsonWriter.value(disabledEnchant);
            }
            jsonWriter.endArray();

            jsonWriter.endObject();
            jsonWriter.close();
        } catch (Exception e) {
            Quartermaster.LOGGER.info("Failed to update config: " + e.getMessage());
        }
    }

    public record WeaponEnabledStatus(boolean tiered, boolean relic, boolean bamboo) {}
    
}
