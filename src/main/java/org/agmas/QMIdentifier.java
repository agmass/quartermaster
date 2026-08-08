package org.agmas;

//? if >=1.21.11 {
import net.minecraft.resources.Identifier;
//? } else {
/*import net.minecraft.resources.ResourceLocation;
*///? }

public class QMIdentifier {
    //? if >=1.21.11 {
    public Identifier id;
    //? } else {
    /*public ResourceLocation id;
    *///? }


    //? if >=1.21.11 {
    public QMIdentifier(Identifier id) {
    //? } else {
    /*public QMIdentifier(ResourceLocation id) {
    *///? }
        this.id = id;
    }

    //? if >=1.21.11 {
    public static QMIdentifier of(String path) {
        return new QMIdentifier(Identifier.fromNamespaceAndPath(Quartermaster.MOD_ID, path));
    }
    public static QMIdentifier ofVanilla(String path) {
        return new QMIdentifier(Identifier.fromNamespaceAndPath("minecraft", path));
    }
    //? } else {
    /*public static QMIdentifier of(String path) {
        return new QMIdentifier(ResourceLocation.fromNamespaceAndPath(Quartermaster.MOD_ID, path));
    }
    public static QMIdentifier ofVanilla(String path) {
        return new QMIdentifier(ResourceLocation.fromNamespaceAndPath("minecraft", path));
    }*/
    //? }
}
