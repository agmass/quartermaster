package org.agmas.config;

import com.google.gson.FormattingStyle;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import org.agmas.Quartermaster;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QMConfig {
    public static List<WeaponEnabledStatus> weaponDefaults = List.of(
                    new WeaponEnabledStatus("cutlass", true, true),
                    new WeaponEnabledStatus("morningstar", true, true),
                    new WeaponEnabledStatus("estoc", true, true),
                    new WeaponEnabledStatus("greataxe", true, true),
                    new WeaponEnabledStatus("warhammer", true, true),
                    new WeaponEnabledStatus("rapier", false, true)
    );
    public static ArrayList<WeaponEnabledStatus> enabledWeapons = new ArrayList<>();

    public static void parse() {
        File configFile = new File("config/quartermaster.json");
        if (configFile.exists()) {
            try {
                JsonElement element = JsonParser.parseReader(new FileReader(configFile));
                for (JsonElement weapons : element.getAsJsonObject().getAsJsonArray("enabledWeapons")) {
                    JsonObject object = weapons.getAsJsonObject();
                    enabledWeapons.add(new WeaponEnabledStatus(object.get("name").getAsString(), object.get("tiered").getAsBoolean(), object.get("relic").getAsBoolean()));
                }
            } catch (Exception ignored) {}
        } else {
            enabledWeapons.addAll(weaponDefaults);
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
            for (WeaponEnabledStatus enabledWeapon : enabledWeapons) {
                jsonWriter.beginObject();
                jsonWriter.name("name").value(enabledWeapon.weaponName);
                jsonWriter.name("tiered").value(enabledWeapon.tiered);
                jsonWriter.name("relic").value(enabledWeapon.relic);
                jsonWriter.endObject();
            }
            
            jsonWriter.endArray();
            jsonWriter.endObject();
            jsonWriter.close();
        } catch (Exception e) {
            Quartermaster.LOGGER.info("Failed to update config: " + e.getMessage());
        }
    }

    public record WeaponEnabledStatus(String weaponName, boolean tiered, boolean relic) {

    }
}
