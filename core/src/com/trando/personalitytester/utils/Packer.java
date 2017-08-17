package com.trando.personalitytester.utils;


import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class Packer {
    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.edgePadding = false;
        settings.stripWhitespaceX = true;
        settings.stripWhitespaceY = true;
        settings.paddingX = 0;
        settings.paddingY = 0;
        TexturePacker.process(settings, "rawassets", "core/assets", "resources");
    }
}
