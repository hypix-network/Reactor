package dev.hypix.reactor.api.chat;

import lombok.Getter;

@Getter
public final class ChatColor {

    public static final char COLOR_CHAR = '\u00A7';

    public static final ChatColor
        BLACK = new ChatColor("black", '0', 0,0,0),
        DARK_BLUE = new ChatColor("dark_blue", '1', 0, 0, 170),
        DARK_GREEN = new ChatColor("dark_green", '2', 0, 170, 0),
        DARK_AQUA = new ChatColor("dark_aqua", '3', 0, 170, 170),
        DARK_RED = new ChatColor("dark_red", '4', 170,0,0),
        DARK_PURPLE = new ChatColor("dark_purple", '5', 170, 0, 170),
        GOLD = new ChatColor("gold", '6', 255, 170, 0),
        GRAY = new ChatColor("gray", '7', 170, 170, 170),
        DARK_GRAY = new ChatColor("dark_gray", '8', 85, 85, 85),
        BLUE = new ChatColor("blue", '9', 85, 85, 255),
        GREEN = new ChatColor("green", 'a', 85, 255, 85),
        AQUA = new ChatColor("aqua", 'b', 85, 255, 255),
        RED = new ChatColor("red", 'c', 255,85,85),
        LIGHT_PURPLE = new ChatColor("light_purple", 'd', 255, 85, 255),
        YELLOW = new ChatColor("yellow", 'e', 255, 255, 85),
        WHITE = new ChatColor("white", 'f', 255, 255, 255);

    private final char code;
    private final String name;
    private final int red, blue, green;

    private ChatColor(String name, char code, int red, int blue, int green) {
        this.name = name;
        this.code = code;
        this.red = red;
        this.blue = blue;
        this.green = green;
    }

    public static ChatColor byCode(final char code) {
        return switch (code) {
            case '4' -> DARK_RED;
            case 'c' -> RED;
            case '6' -> GOLD;
            case 'e' -> YELLOW;
            case '2' -> DARK_GREEN;
            case 'a' -> GREEN;
            case 'b' -> AQUA;
            case '3' -> DARK_AQUA;
            case '1' -> DARK_BLUE;
            case '9' -> BLUE;
            case 'd' -> LIGHT_PURPLE;
            case '5' -> DARK_PURPLE;
            case 'f' -> WHITE;
            case '7' -> GRAY;
            case '8' -> DARK_GRAY;
            case '0' -> BLACK;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return COLOR_CHAR + String.valueOf(code);
    }
}
