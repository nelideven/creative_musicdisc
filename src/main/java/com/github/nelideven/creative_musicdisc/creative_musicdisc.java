package com.github.nelideven.creative_musicdisc;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.Rarity;

import java.util.function.Function;

public class creative_musicdisc implements ModInitializer {
    public static final String MOD_ID = "creative_musicdisc";

    // --- Helpers & Factories ---
    public static Identifier id(String name) {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }

    public static ResourceKey<Item> create(String name) {
        return ResourceKey.create(Registries.ITEM, id(name));
    }

    public static ResourceKey<JukeboxSong> registerSongKey(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, id(name));
    }

    public static SoundEvent registerSound(String name) {
        Identifier soundId = id(name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, soundId, SoundEvent.createVariableRangeEvent(soundId));
    }

    public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        Item item = itemFactory.apply(settings.setId(itemKey));
        return Registry.register(BuiltInRegistries.ITEM, itemKey, item);
    }

    // --- 1. Sound Events ---
    public static final SoundEvent SOUND_ARIA_MATH = registerSound("music_disc.aria_math");
    public static final SoundEvent SOUND_BIOME_FEST = registerSound("music_disc.biome_fest");
    public static final SoundEvent SOUND_BLIND_SPOTS = registerSound("music_disc.blind_spots");
    public static final SoundEvent SOUND_DREITON = registerSound("music_disc.dreiton");
    public static final SoundEvent SOUND_HAUNT_MUSKIE = registerSound("music_disc.haunt_muskie");
    public static final SoundEvent SOUND_TASWELL = registerSound("music_disc.taswell");

    // --- 2. Jukebox Song Keys ---
    public static final ResourceKey<JukeboxSong> SONG_ARIA_MATH = registerSongKey("aria_math");
    public static final ResourceKey<JukeboxSong> SONG_BIOME_FEST = registerSongKey("biome_fest");
    public static final ResourceKey<JukeboxSong> SONG_BLIND_SPOTS = registerSongKey("blind_spots");
    public static final ResourceKey<JukeboxSong> SONG_DREITON = registerSongKey("dreiton");
    public static final ResourceKey<JukeboxSong> SONG_HAUNT_MUSKIE = registerSongKey("haunt_muskie");
    public static final ResourceKey<JukeboxSong> SONG_TASWELL = registerSongKey("taswell");

    // --- 3. Items ---
    public static final Item MUSIC_DISC_ARIA_MATH = register(
        create("music_disc_aria_math"), Item::new, new Item.Properties().jukeboxPlayable(SONG_ARIA_MATH).stacksTo(1).rarity(Rarity.UNCOMMON)
    );
    public static final Item MUSIC_DISC_BIOME_FEST = register(
        create("music_disc_biome_fest"), Item::new, new Item.Properties().jukeboxPlayable(SONG_BIOME_FEST).stacksTo(1).rarity(Rarity.UNCOMMON)
    );
    public static final Item MUSIC_DISC_BLIND_SPOTS = register(
        create("music_disc_blind_spots"), Item::new, new Item.Properties().jukeboxPlayable(SONG_BLIND_SPOTS).stacksTo(1).rarity(Rarity.UNCOMMON)
    );
    public static final Item MUSIC_DISC_DREITON = register(
        create("music_disc_dreiton"), Item::new, new Item.Properties().jukeboxPlayable(SONG_DREITON).stacksTo(1).rarity(Rarity.UNCOMMON)
    );
    public static final Item MUSIC_DISC_HAUNT_MUSKIE = register(
        create("music_disc_haunt_muskie"), Item::new, new Item.Properties().jukeboxPlayable(SONG_HAUNT_MUSKIE).stacksTo(1).rarity(Rarity.UNCOMMON)
    );
    public static final Item MUSIC_DISC_TASWELL = register(
        create("music_disc_taswell"), Item::new, new Item.Properties().jukeboxPlayable(SONG_TASWELL).stacksTo(1).rarity(Rarity.UNCOMMON)
    );

    // --- Functions ---
    @Override
    public void onInitialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(creativeTab -> {
            creativeTab.accept(MUSIC_DISC_ARIA_MATH);
            creativeTab.accept(MUSIC_DISC_BIOME_FEST);
            creativeTab.accept(MUSIC_DISC_BLIND_SPOTS);
            creativeTab.accept(MUSIC_DISC_DREITON);
            creativeTab.accept(MUSIC_DISC_HAUNT_MUSKIE);
            creativeTab.accept(MUSIC_DISC_TASWELL);
        });
    }
}