package discompute.service;

import discompute.flow.FlowContext;
import discompute.flow.Worker;
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
    private static FlowClient flowClient;

    public static FlowClient instance(){
        synchronized (FlowClient.class){
            if(flowClient == null){
                flowClient = new FlowClient();
            }

        }
        return flowClient;
    }

    public FlowContext call(FlowContext flowContext, Worker worker)  {
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
        try {
            ChannelFuture channelFuture = bootstrap.connect(worker.getHost(), worker.getPort()).sync();
            System.out.println("client recieve job！！");
            channelFuture.channel().writeAndFlush(flowContext);
            channelFuture.channel().closeFuture().sync();
            group.shutdownGracefully().sync();

            return flowClientHandler.getFlowContext();

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


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
