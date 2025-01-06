package dev.hypix.reactor.api.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatComponent {

    public static ChatComponent NEW_LINE = new ChatComponent("\n");

    private String text;
    private ChatColor color;
    private boolean bold = false;
    private boolean italic = false;
    private boolean underlined = false;
    private boolean obfuscated = false;
    private boolean strikethrough = false;

    public ChatComponent() {
    }

    public ChatComponent(final String text) {
        this.text = text;
    }

    public ChatComponent(final String text, final ChatColor color) {
        this.text = text;
        this.color = color;
    }

    public ChatComponent(final String text, final ChatColor color, final boolean bold, final boolean italic, final boolean underlined, final boolean obfuscated, final boolean strikethrough) {
        this.text = text;
        this.color = color;
        this.bold = bold;
        this.italic = italic;
        this.underlined = underlined;
        this.obfuscated = obfuscated;
        this.strikethrough = strikethrough;
    }

    public boolean containsAnyStyle() {
        return color != null || bold || italic || underlined || obfuscated || strikethrough;
    }

    public String toJson() {
        final StringBuilder builder = new StringBuilder();
        builder.append("{\"text\":\"");
        builder.append(text);
        builder.append('"');
        if (color != null) {
            builder.append(",\"color\":\"" + color.getName() + '"');
        }
        if (bold) {
            builder.append(",\"bold\":true");
        }
        if (italic) {
            builder.append(",\"italic\":true");
        }
        if (underlined) {
            builder.append(",\"underlined\":true");
        }
        if (obfuscated) {
            builder.append(",\"obfuscated\":true");
        }
        if (strikethrough) {
            builder.append(",\"strikethrough\":true");
        }
        builder.append("}");
        return builder.toString();
    }

    public static String toJson(final ChatComponent... components) {
        if (components.length == 1) {
            return components[0].toJson();
        }
        final StringBuilder builder = new StringBuilder();
        builder.append('[');
        final int size = components.length;
        for (int i = 0; i < size; i++) {
            builder.append(components[i].toJson());
            if (i+1 != size) {
                builder.append(',');
            }
        }
        builder.append(']');
        return builder.toString();
    }
}