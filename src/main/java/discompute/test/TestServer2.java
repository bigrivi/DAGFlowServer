package discompute.test;

import discompute.service.FlowServer;

/**
 * Created by wyj on 2016/11/23.
 */
public class TestServer2 {
    public static void main(String args[]){
        FlowServer flowServer2 = new FlowServer(9888);
        flowServer2.start();
    }
}
