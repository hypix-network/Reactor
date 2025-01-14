/*
* Autogenerated file. Please don't touch :)
* Class generator: dev.hypix.dataparser.registry.jukebox.JukeboxParser (module data-parser)
* Date: Year: 2025. Month: 01. Day: 14. Time: 16:14:35
*/
package dev.hypix.reactor.api.world.block;
import lombok.Getter;

import java.util.Collection;

import dev.hypix.reactor.api.util.collection.CopyOnWriteCollection;

@Getter
public final class JukeboxSong {

    public static final Collection<JukeboxSong> ALL = new CopyOnWriteCollection<>(19);

    private final int comparatorOutput, lengthInSeconds;
    private final String name, description, soundEvent;

    public JukeboxSong(String name, String description, String soundEvent, int comparatorOutput, int lengthInSeconds) {
        this.name = name;
        this.description = description;
        this.soundEvent = soundEvent;
        this.comparatorOutput = comparatorOutput;
        this.lengthInSeconds = lengthInSeconds;
    }

    private JukeboxSong(String name, int index, String description, String soundEvent, int comparatorOutput, int lengthInSeconds) {
        this(name, description, soundEvent, comparatorOutput, lengthInSeconds);
        ALL.add(this);
    }

    public static final JukeboxSong 
        ELEVEN = new JukeboxSong("11",0,"jukebox_song.minecraft.11","minecraft:music_disc.11",11,71),
        CHIRP = new JukeboxSong("chirp",1,"jukebox_song.minecraft.chirp","minecraft:music_disc.chirp",4,185),
        THIRTEEN = new JukeboxSong("13",2,"jukebox_song.minecraft.13","minecraft:music_disc.13",1,178),
        CREATOR = new JukeboxSong("creator",3,"jukebox_song.minecraft.creator","minecraft:music_disc.creator",12,176),
        WAIT = new JukeboxSong("wait",4,"jukebox_song.minecraft.wait","minecraft:music_disc.wait",12,238),
        RELIC = new JukeboxSong("relic",5,"jukebox_song.minecraft.relic","minecraft:music_disc.relic",14,218),
        BLOCKS = new JukeboxSong("blocks",6,"jukebox_song.minecraft.blocks","minecraft:music_disc.blocks",3,345),
        MALL = new JukeboxSong("mall",7,"jukebox_song.minecraft.mall","minecraft:music_disc.mall",6,197),
        WARD = new JukeboxSong("ward",8,"jukebox_song.minecraft.ward","minecraft:music_disc.ward",10,251),
        PIGSTEP = new JukeboxSong("pigstep",9,"jukebox_song.minecraft.pigstep","minecraft:music_disc.pigstep",13,149),
        PRECIPICE = new JukeboxSong("precipice",10,"jukebox_song.minecraft.precipice","minecraft:music_disc.precipice",13,299),
        FIVE = new JukeboxSong("5",11,"jukebox_song.minecraft.5","minecraft:music_disc.5",15,178),
        OTHERSIDE = new JukeboxSong("otherside",12,"jukebox_song.minecraft.otherside","minecraft:music_disc.otherside",14,195),
        FAR = new JukeboxSong("far",13,"jukebox_song.minecraft.far","minecraft:music_disc.far",5,174),
        CAT = new JukeboxSong("cat",14,"jukebox_song.minecraft.cat","minecraft:music_disc.cat",2,185),
        STAL = new JukeboxSong("stal",15,"jukebox_song.minecraft.stal","minecraft:music_disc.stal",8,150),
        CREATOR_MUSIC_BOX = new JukeboxSong("creator_music_box",16,"jukebox_song.minecraft.creator_music_box","minecraft:music_disc.creator_music_box",11,73),
        MELLOHI = new JukeboxSong("mellohi",17,"jukebox_song.minecraft.mellohi","minecraft:music_disc.mellohi",7,96),
        STRAD = new JukeboxSong("strad",18,"jukebox_song.minecraft.strad","minecraft:music_disc.strad",9,188);
}