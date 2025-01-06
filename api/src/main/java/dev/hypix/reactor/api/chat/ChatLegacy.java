package dev.hypix.reactor.api.chat;

import java.util.LinkedList;
import java.util.List;

public final class ChatLegacy {

    public static ChatComponent[] from(final String legacyText) {
        return fromTextToList('&', legacyText).toArray(new ChatComponent[0]);
    }

    public static ChatComponent[] fromColorChar(final String legacyText) {
        return fromTextToList(ChatColor.COLOR_CHAR, legacyText).toArray(new ChatComponent[0]);
    }

    public static List<ChatComponent> fromTextToList(final char specialChar, final String legacyText) {
        final List<ChatComponent> components = new LinkedList<>();
        final int length = legacyText.length();

        TextComponent textComponent = new TextComponent();

        for (int i = 0; i < length; i++) {
            char character = legacyText.charAt(i);
            if (character == '\n') {
                textComponent.add(components);
                textComponent = new TextComponent();

                components.add(ChatComponent.NEW_LINE);
                continue;
            }
            if (character != specialChar) {
                textComponent.builder.append(character);
                continue;
            }
            if (i++ >= length) {
                continue;
            }
            character = legacyText.charAt(i);
            ChatColor color = ChatColor.byCode(character);

            if (color != null) {
                if (textComponent.builder.length() > 0) {
                    if (color != textComponent.getColor()) {
                        textComponent.add(components);
                        textComponent = new TextComponent();    
                    }
                }
    
                textComponent.setColor(color);
                continue;
            }
            if (textComponent.builder.length() > 0) {
                final ChatColor oldColor = textComponent.getColor();
                textComponent.add(components);
                textComponent = new TextComponent();
                textComponent.setColor(oldColor);
            }
            switch (character) {
                case 'l': textComponent.setBold(true); break;
                case 'o': textComponent.setItalic(true); break;
                case 'n': textComponent.setUnderlined(true); break;
                case 'm': textComponent.setStrikethrough(true); break;
                case 'k': textComponent.setObfuscated(true); break;
                case 'r':
                    textComponent.add(components);
                    textComponent = new TextComponent();
                    break;
            }
        }

        textComponent.add(components);
        return components;
    }

    private static final class TextComponent extends ChatComponent {
        private final StringBuilder builder = new StringBuilder();

        private void add(List<ChatComponent> components) {
            if (builder.length() == 0) {
                return;
            }
            setText(builder.toString());
            components.add(this);
        }
    }
}