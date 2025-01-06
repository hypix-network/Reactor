package dev.hypix.reactor.internal.console;

import java.io.IOException;

import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.MaskingCallback;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp.Capability;
import org.tinylog.Logger;

import dev.hypix.reactor.api.Reactor;
import dev.hypix.reactor.api.chat.ChatComponent;
import dev.hypix.reactor.api.command.CommandSender;

public final class Console implements CommandSender {

    private final Terminal terminal;
    private final LineReader reader;

    private boolean run = true;

    Console(Terminal terminal, LineReader reader) {
        this.terminal = terminal;
        this.reader = reader;
    }

    @Override
    public void send(ChatComponent[] components) {
 
    }

    @Override
    public void send(ChatComponent component) {
 
    }

    public void run() {
        while(run) {
            final String line;
            try {
                line = reader.readLine("> ", null, (MaskingCallback) null, null).trim();
            } catch (UserInterruptException | EndOfFileException e) {
                Reactor.getServer().stop();
                continue;
            }

            if (line.isEmpty()) {
                continue; 
            }
            switch (line) {
                case "stop":
                    Reactor.getServer().stop();
                    return;
                case "clear":
                    terminal.puts(Capability.clear_screen);
                    break;
            }
        }
    }

    public void stop() {
        try {
            terminal.close();
        } catch (IOException e) {
            Logger.info("Error trying to stop the terminal");
            Logger.error(e);
        } finally {
            run = false;
        }
    }

    @Override
    public String getName() {
        return "CONSOLE";
    }
}