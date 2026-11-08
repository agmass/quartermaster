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

    public static void parse() {
        weaponDefaults.put("cutlass", new WeaponEnabledStatus(true, true,true));
        weaponDefaults.put("morningstar", new WeaponEnabledStatus(true, true,true));
        weaponDefaults.put("estoc", new WeaponEnabledStatus(true, true,true));
        weaponDefaults.put("greataxe", new WeaponEnabledStatus(true, true,true));
        weaponDefaults.put("warhammer", new WeaponEnabledStatus(true, true,true));
        weaponDefaults.put("rapier", new WeaponEnabledStatus(false, true,false));
        if (FabricDataGenHelper.ENABLED) {
            weaponDefaults.forEach((s, ignored)->{
                enabledWeapons.put(s, new WeaponEnabledStatus(true,true,true));
            });
            return;
        }
        enabledWeapons.putAll(weaponDefaults);
        File configFile = new File("config/quartermaster.json");
        if (configFile.exists()) {
            try {
                JsonElement element = JsonParser.parseReader(new FileReader(configFile));
                for (JsonElement weapons : element.getAsJsonObject().getAsJsonArray("enabledWeapons")) {
                    JsonObject object = weapons.getAsJsonObject();
                    enabledWeapons.put(object.get("name").getAsString(), new WeaponEnabledStatus(object.get("tiered").getAsBoolean(), object.get("relic").getAsBoolean(), object.get("bamboo").getAsBoolean()));
                }
            } catch (Exception ignored) {}
        }
    }

    public static void write() {
        File configFile = new File("config/quartermaster.json");
        try {
            configFile.createNewFile();
            FileWriter writer = new FileWriter("config/quartermaster.json");
            JsonWriter jsonWriter = new JsonWriter(writer);
            jsonWriter.setFormattingStyle(FormattingStyle.PRETTY);

            jsonWriter.beginObject();
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
            jsonWriter.endObject();
            jsonWriter.close();
        } catch (Exception e) {
            Quartermaster.LOGGER.info("Failed to update config: " + e.getMessage());
        }
    }

    public record WeaponEnabledStatus(boolean tiered, boolean relic, boolean bamboo) {

    }
}
