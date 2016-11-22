package discompute.service;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by Patrick on 2016/11/22.
 */
public class FlowClient {
    private final String host;
    private final int port;

    public FlowClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void call(String message) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        FlowClientHandler flowClientHandler = new FlowClientHandler();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group);
            b.channel(NioSocketChannel.class);
            b.remoteAddress(new InetSocketAddress(host, port));
            b.handler(new ChannelInitializer<SocketChannel>() {

                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(flowClientHandler);
                }
            });
            ChannelFuture f = b.connect().sync();
            f.channel().writeAndFlush(message);
            f.channel().closeFuture().sync();
            System.out.println("client get string" + flowClientHandler.getMessage());
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    class FlowClientHandler extends  ChannelInboundHandlerAdapter


    {
        private String message;

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
        {
            this.message = (String)msg;
            //log.debug("client接收到服务器返回的消息");
        }

        public String getMessage(){
            return this.message + "fasdfas!!!!!!!!!!!!!!!!";
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
        {

        }
    }

    public static void main(String[] args) throws Exception {

        new FlowClient("127.0.0.1", 8888).call("okfasdf");
    }
}
