package discompute.service;

import discompute.flow.FlowContext;
import discompute.flow.model.Task;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by wyj on 2016/11/22.
 */
public class FlowServer {

    private  int port = 8889;
    private  EventLoopGroup bossGroup = new NioEventLoopGroup();
    private  EventLoopGroup workerGroup = new NioEventLoopGroup();

    public FlowServer(){
    }

    public FlowServer(int port){
        this.port = port;
    }

    public void start(){
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("encode", new ObjectEncoder());
                pipeline.addLast("decode", new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(null)));
                pipeline.addLast(new FlowServerHandler());
            }
        });
        try {
            bootstrap.bind(port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        try {
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        } catch (InterruptedException e) {
            //todo
        }
    }
    static class FlowServerHandler extends ChannelInboundHandlerAdapter {

        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            //todo
            System.out.println("server recieve job！！");
            FlowContext flowContext = (FlowContext) msg;
            Task task = flowContext.getCurrentTask();
            Map<String, String> map;
            try {
                 map = task.getTaskExecutor().exec(task,flowContext);
            } catch (Exception e) {
                 map = new HashMap<>();
            }
            flowContext.setParams(map);
            ctx.writeAndFlush(flowContext);
            ctx.close();
        }

        public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) {
            ctx.close();
        }
    }


}
