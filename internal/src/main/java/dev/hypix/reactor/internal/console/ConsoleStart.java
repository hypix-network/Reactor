package dev.hypix.reactor.internal.console;

import java.io.IOException;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.tinylog.Logger;

public final class ConsoleStart {

    public Console createConsole() {
        try {
            final Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .build();

            final LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .highlighter(new ConsoleHighlighter())
                .completer(new ConsoleCompleter())
                .build();

            final Console console = new Console(terminal, reader);

            return console;
        } catch (IOException e) {
            Logger.error("Error creating the console");
            Logger.error(e);
        }
        return null;
    }
}