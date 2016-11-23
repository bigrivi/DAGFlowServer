package discompute.test;

import discompute.service.FlowServer;

/**
 * Created by wyj on 2016/11/23.
 */
public class TestServer {
    public static void main(String args[]){
        FlowServer flowServer1 = new FlowServer(8888);
        flowServer1.start();
    }

}
