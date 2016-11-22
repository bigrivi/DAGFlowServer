package discompute.service;

import discompute.flow.FlowContext;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by Patrick on 2016/11/22.
 */
public class FlowServer {

    private int port = 8888;
    private EventLoopGroup group = new NioEventLoopGroup();

    public FlowServer(){
    }

    public FlowServer(int port){
        this.port = port;
    }

    public FlowContext start(){
        ServerBootstrap bootstrap = new ServerBootstrap();// 引导辅助程序
        try {
            bootstrap.group(group);
            bootstrap.channel(NioServerSocketChannel.class);// 设置nio类型的channel
            bootstrap.localAddress(new InetSocketAddress(port));// 设置监听端口
            //有连接到达时会创建一个channel
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel ch) throws Exception {
                    // pipeline管理channel中的Handler，在channel队列中添加一个handler来处理业务
                    ch.pipeline().addLast("myHandler", new FlowServerHandler());
                }
            });
            ChannelFuture future = bootstrap.bind().sync();// 配置完成，开始绑定server，通过调用sync同步方法阻塞直到绑定成功
            System.out.println( " server started and listen on " + future.channel().localAddress());
            future.channel().closeFuture().sync();// 应用程序会一直等待，直到channel关闭
        } catch (Exception e) {
          //todo
        }
        return null;
    }

    public void stop(){
        try {
            group.shutdownGracefully().sync();//关闭EventLoopGroup，释放掉所有资源包括创建的线程
        } catch (InterruptedException e) {
            //todo
        }
    }
    class FlowServerHandler extends ChannelInboundHandlerAdapter {

        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            System.out.println("server received data :" + msg);
            ctx.write(msg);//写回数据，
        }

        public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }

    public static void main(String args[]){
        FlowServer flowServer = new FlowServer();
        flowServer.start();
    }


}
