package dev.hypix.reactor.protocol;

import org.tinylog.Logger;

import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.util.buffer.DataSize;
import dev.hypix.reactor.api.util.buffer.ExpectedSizeBuffer;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

final class PacketEncoder extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(final ChannelHandlerContext ctx, final Object msg, final ChannelPromise promise) throws Exception {
        final PacketOutbound packetOutbound = (PacketOutbound)msg;
        final byte[] packetBuffer = packetOutbound.write();
        final int outboundIdLength = DataSize.varInt(packetOutbound.getId());
        final int packetLength = packetBuffer.length + outboundIdLength;

        final ExpectedSizeBuffer header = new ExpectedSizeBuffer(DataSize.varInt(packetLength) + outboundIdLength);

        header.writeVarInt(packetLength);
        header.writeVarInt(packetOutbound.getId());

        ctx.write(Unpooled.wrappedBuffer(header.buffer));
        ctx.write(Unpooled.wrappedBuffer(packetBuffer));
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) {
        Logger.error("Error encoding packet");
        Logger.error(cause);
        ctx.close();
    }
}
