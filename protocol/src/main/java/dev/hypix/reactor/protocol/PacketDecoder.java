package dev.hypix.reactor.protocol;

import java.util.List;

import org.tinylog.Logger;

import dev.hypix.reactor.protocol.inbound.PacketInData;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

final class PacketDecoder extends ByteToMessageDecoder {

    private final PlayerConnectionImpl connection;

    public PacketDecoder(PlayerConnectionImpl connection) {
        this.connection = connection;
    }

    @Override
    protected void decode(final ChannelHandlerContext channelhandlercontext, final ByteBuf in, final List<Object> out) throws Exception {
        if (in.readableBytes() < 1) {
            return;
        }
    
        in.markReaderIndex();
        final int packetLength = readVarIntSafely(in);
    
        if (packetLength == -1) {
            in.resetReaderIndex();
            return;
        }
    
        if (packetLength <= 0) {
            throw new IllegalArgumentException("Invalid packet length: " + packetLength);
        }
    
        if (in.readableBytes() < packetLength) {
            in.resetReaderIndex();
            return;
        }

        final ByteBuf packetData = in.readBytes(packetLength);
        final PacketInData packetInData = new PacketInData(packetData);
        final int id = packetInData.readVarInt();
        Logger.info("id: {} state: {}", id, connection.state);
        connection.state.handlerStorage.execute(connection, id, packetInData);

        out.add(packetData);
    }
    
    private static int readVarIntSafely(final ByteBuf buf) {
        int numRead = 0;
        int result = 0;

        while (numRead < 5) {
            if (!buf.isReadable()) {
                return -1;
            }
    
            byte read = buf.readByte();
            int value = (read & 0x7F);
            result |= (value << (7 * numRead));
    
            numRead++;
    
            if ((read & 0x80) != 0x80) {
                return result;
            }
        }
        throw new RuntimeException("VarInt is too big");
    }


    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) {
        Logger.error("Error decoding packet");
        Logger.error(cause);
        ctx.close();
    }
}