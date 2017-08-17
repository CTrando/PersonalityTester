package com.trando.personalitytester.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.logging.FileHandler;

public class SoundManager {
    private static HashMap<String, Sound> sounds = new HashMap<String, Sound>();

    public static void init() {
        String[] assets = Gdx.files.internal("sounds.txt").readString().split(",");
        for(String asset: assets) {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal(asset));
            String name = asset.substring(0, asset.indexOf("."));
            sounds.put(name, sound);
        }
    }

    public static void playSound(String sound, float vol) {
        sounds.get(sound).play(vol);
    }

    public static void playSound(String sound) {
        playSound(sound, 1f);
    }
}
