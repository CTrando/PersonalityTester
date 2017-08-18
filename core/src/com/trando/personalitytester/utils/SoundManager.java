package com.trando.personalitytester.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.*;

import java.util.HashMap;
import java.util.logging.FileHandler;

public class SoundManager {
    private static HashMap<String, Sound> sounds = new HashMap<String, Sound>();
    private static HashMap<String, Music> music = new HashMap<String, Music>();

    public static void init() {
        String[] sounds = Gdx.files.internal("sounds.txt").readString().split(",");
        for(String soundName: sounds) {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal(soundName));
            String name = soundName.substring(0, soundName.indexOf("."));
            SoundManager.sounds.put(name, sound);
        }

        String[] music = Gdx.files.internal("music.txt").readString().split(",");
        for(String musicName: music) {
            Music m = Gdx.audio.newMusic(Gdx.files.internal(musicName));
            String name = musicName.substring(0, musicName.indexOf("."));
            SoundManager.music.put(name, m);
        }
    }

    public static void playMusic(String musicName, boolean repeating) {
        music.get(musicName).setLooping(repeating);
        music.get(musicName).play();
    }

    public static void playMusic(String musicName, boolean repeating, float vol) {
        music.get(musicName).setLooping(repeating);
        music.get(musicName).setVolume(vol);
        music.get(musicName).play();
    }

    public static void playMusic(String musicName) {
        playMusic(musicName, false);
    }

    public static void playSound(String sound, float vol) {
        sounds.get(sound).play(vol);
    }

    public static void playSound(String sound) {
        playSound(sound, 1f);
    }
}
