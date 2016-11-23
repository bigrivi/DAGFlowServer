package discompute.flow;

import java.io.Serializable;

/**
 * Created by Patrick on 2016/11/23.
 */
public class Worker implements Serializable{
    private String host;
    private int port;

    public Worker(){

    }

    public Worker(String host, int port){
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
