package dev.hypix.dataparser.trim.pattern;

import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson2.JSONObject;

import dev.hypix.dataparser.Parser;

public final class TrimPatterParser implements Parser {


    @Override
    public String requiredFile() {
        return "trim-pattern.json";
    }

    @Override
    public ParsedFile parse(JSONObject json) {
        final Set<Entry<String, Object>> entries = json.entrySet();   
        final StringBuilder builder = new StringBuilder();

        builder.append(
            """
            package dev.hypix.dataparser.trim.pattern;

            import lombok.Getter;

            /* Autogenerated - Don't touch :) */
            @Getter
            public final class TrimPattern {

                public static final TrimPattern[] ALL = new TrimPattern[%amount%];
                private final String id;
                private final String description;
                private final String item;

                public TrimPattern(String id, String description, String item) {
                    this.id = id;
                    this.description = description;
                    this.item = item;
                }

                private TrimPattern(String id, String description, String item, int index) {
                    this(id, description, item);
                    ALL[index] = this;
                }

                public static final TrimPattern        
            """.replaceFirst("%amount%", String.valueOf(entries.size()))
        );

    
        int i = 0;
        for (final Entry<String, Object> entry : entries) {
            if (!(entry.getValue() instanceof JSONObject trimPatter)) {
                System.out.println("Invalid entry : " + entry.getKey() + " need be a json object...");
                return null;
            }
            final String id = trimPatter.getString("asset_id");
            final String description = trimPatter.getJSONObject("description").getString("translate");
            final String item = trimPatter.getString("template_item");

            builder.append("        ");
            builder.append(toFieldName(id));
            builder.append(" = new TrimPattern(");
            append(builder, id, description, item, i);
            builder.append(')');
            builder.append(++i == entries.size() ? ';' : ',');
            builder.append('\n');
        }

        builder.append('}');
    
        return new ParsedFile("trim/pattern/TrimPattern.java", builder.toString());
    }
}