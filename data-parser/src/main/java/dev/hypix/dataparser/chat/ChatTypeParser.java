package dev.hypix.dataparser.chat;

import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson2.JSONObject;

import dev.hypix.dataparser.Parser;

public final class ChatTypeParser implements Parser {

    @Override
    public String requiredFile() {
        return "chat.json";
    }

    @Override
    public ParsedFile parse(final JSONObject json) {
        final Set<Entry<String, Object>> entries = json.entrySet();
        final StringBuilder builder = new StringBuilder();
        builder.append(
            """
            package dev.hypix.dataparser.chat;

            import lombok.Getter;

            /* Autogenerated - Don't touch :) */
            @Getter
            public final class ChatType {

                public static final ChatType[] ALL = new ChatType[7];
                private final Data chat, narration;
                private final String id;

                public ChatType(String id, Data chat, Data narration) {
                    this.id = id;
                    this.chat = chat;
                    this.narration = narration;
                }

                private ChatType(String id, Data chat, Data narration, int index) {
                    this.id = id;
                    this.chat = chat;
                    this.narration = narration;
                    ALL[index] = this;
                }

                public static final record Data(
                    String[] parameters,
                    String translationKey
                ){}

                public static final ChatType
            """.replaceFirst("%amount%", String.valueOf(entries.size()))
        );

        int i = 0;
        for (final Entry<String, Object> entry : entries) {
            if (!(entry.getValue() instanceof JSONObject chatType)) {
                System.out.println("Invalid entry : " + entry.getKey() + " need be a json object...");
                return null;
            }
            final JSONObject chat = chatType.getJSONObject("chat");
            if (chat == null) {
                System.out.println("Chat is null " + entry.getKey());
                return null;
            }
            final JSONObject narration = chatType.getJSONObject("narration");
            if (narration == null) {
                System.out.println("Narration is null " + entry.getKey());
                return null;
            }

            builder.append("        ");
            builder.append(toFieldName(entry.getKey()));
            builder.append(" = new ChatType(");
            appendString(entry.getKey(), builder);
            builder.append(',');
            append(false, false, builder, loadData(chat), loadData(narration), i);
            builder.append(')');

            builder.append(++i == entries.size() ? ';' : ',');
            builder.append('\n');
        }

        builder.append('}');
        return new ParsedFile("chat/ChatType.java", builder.toString());
    }

    private String loadData(final JSONObject data) {
        final StringBuilder builder = new StringBuilder();
        builder.append("new ChatType.Data(");
        builder.append("new String[] {");
        append(builder, data.getJSONArray("parameters").toArray());
        builder.append("},");
        appendString(data.getString("translation_key"), builder);
        builder.append(')');
        return builder.toString();
    }
}
