package discompute.service;

import discompute.flow.FlowContext;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * Created by wyj on 2016/11/22.
 */
public class FlowClient {
    private final String host;
    private final int port;

    public FlowClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public FlowContext call(FlowContext flowContext) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        final FlowClientHandler flowClientHandler=new FlowClientHandler();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("encode", new ObjectEncoder());
                pipeline.addLast("decode", new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(null)));
                pipeline.addLast("handler", flowClientHandler);
            }
        });
        ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
        channelFuture.channel().writeAndFlush(flowContext);
        channelFuture.channel().closeFuture().sync();
        group.shutdownGracefully().sync();

        return flowClientHandler.getFlowContext();

    }

    class FlowClientHandler extends  ChannelInboundHandlerAdapter {
        private FlowContext flowContext;

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg)
                throws Exception {
            this.flowContext = (FlowContext)msg;
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
                throws Exception {
            ctx.close();
        }

        public FlowContext getFlowContext(){
            return this.flowContext;
        }
    }
}
