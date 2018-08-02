package com.hu.test.counter.zk;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
/**
 * zookeeper原生api测试
 * @author japper
 *
 */
public class ZkAPITest {
	 /** zookeeper地址 */
    static final String CONNECT_ADDR = "172.16.23.239:2181";
    /** session超时时间 */
    static final int SESSION_OUTTIME = 2000;//ms 
    /** 信号量，阻塞程序执行，用于等待zookeeper连接成功，发送成功信号 */
    static final CountDownLatch connectedSemaphore = new CountDownLatch(1);
    
    public static void main(String[] args) throws Exception{
    	String path="/testRoot";
        ZooKeeper zk = new ZooKeeper(CONNECT_ADDR, SESSION_OUTTIME, new Watcher(){
            @Override
            public void process(WatchedEvent event) {
                //获取事件的状态
                KeeperState keeperState = event.getState();
                EventType eventType = event.getType();
                System.out.println("++++keeperState:"+keeperState.name()+",++++EventType:"+eventType.name());
                //如果是建立连接
                if(KeeperState.SyncConnected == keeperState){
                    if(EventType.None == eventType){
                        //如果建立连接成功，则发送信号量，让后续阻塞程序向下执行
                        connectedSemaphore.countDown();
                        System.out.println("zk 建立连接");
                    }else if(eventType==EventType.NodeChildrenChanged) {
                    	System.out.println("--"+event.getPath()+" 的子节点改变了");
                    }
                }
            }
        });
        //进行阻塞
        connectedSemaphore.await();
        System.out.println("..");
        //递归节点，可删除一个包含子节点的节点
        rmr(zk,path);
        //创建父节点,存储的数据为data-testRoot,访问列表开放，持久模式节点
        zk.create(path, "data-".concat(path).getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //获取指定路径的子节点并监听改节点子节点的变化，第二个参数如果为true,则使用创建zookeeper时(构造函数)的watcher来接收事件通知。第二个参数也可指定一个新的watcher。（一次watch只能使用一次）
        print(zk.getChildren(path, true));
        zk.create(path+"/child1", "data-".concat(path).getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //将不会再打印watch了
        zk.create(path+"/child2", "data-".concat(path).getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //删除指定version的节点（不能有子节点），-1表示删除整个节点
        zk.delete(path+"/child2", -1);
        //获取节点信息
        Stat stat=new Stat();
        byte[] datas=zk.getData(path+"/child1", new Watcher(){
			@Override
			public void process(WatchedEvent event) {
				 KeeperState keeperState = event.getState();
	                EventType eventType = event.getType();
	                if(KeeperState.SyncConnected == keeperState){
	                    if(eventType==EventType.NodeDataChanged) {
	                    	System.out.println("--"+event.getPath()+" 的数据改变了");
	                    }
	                }
			}
        }, stat);
        System.out.println("getData数据为:"+new String(datas)+",子节点数量:"+stat.getNumChildren()+",版本号:"+stat.getVersion());
        //修改节点数据
        Stat updateStat=zk.setData(path+"/child1", "dataxx111".getBytes(), -1);
        System.out.println("修改后版本号为："+updateStat.getVersion());
        Thread.sleep(5000);
        //创建子节点
//        String ret = zk.create("/testRoot/children", "children data".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        System.out.println("创建子节点"+ret);
        //获取节点洗信息
        /*byte[] data = zk.getData("/testRoot", false, null);
        System.out.println(new String(data));
        System.out.println(zk.getChildren("/testRoot", false));*/
        
        //修改节点的值
/*        zk.setData("/testRoot", "modify data root".getBytes(), -1);
        byte[] data = zk.getData("/testRoot", false, null);
        System.out.println(new String(data));    */    
        
        //判断节点是否存在
//        System.out.println(zk.exists("/testRoot/children", false));
        //同步删除节点
//        zk.delete("/testRoot/children", -1);   
//        //异步删除节点
//        zk.delete("/testRoot/children", -1, new AsyncCallback.VoidCallback() {
//            @Override
//            public void processResult(int rc, String path, Object ctx) {
//                System.out.println("rc====="+rc);
//                System.out.println("path======"+path);
//                System.out.println("ctc======"+path);
//            }
//        } , "回调值");
//        System.out.println(zk.exists("/testRoot/children", false));
        
        zk.close();
    }
    /**
     * 打印
     * @param obj
     */
    private static void print(Object obj) {
    		if(obj instanceof Collection) {
    			Collection<Object> collection=(Collection<Object>)obj;
    			for(Object item:collection) {
    				System.out.println(item);
    			}
    		}else {
    			System.out.println(obj);
    		}
    }
    
    /**
     * 递归删除 因为zookeeper只允许删除叶子节点，如果要删除非叶子节点，只能使用递归
     * @param path
     * @throws IOException
     */
    private static void rmr(ZooKeeper zk,String path) throws Exception {
        //获取路径下的节点
        List<String> children = zk.getChildren(path, false);
        for (String pathCd : children) {
            //获取父节点下面的子节点路径
            String newPath = "";
            //递归调用,判断是否是根节点
            if (path.equals("/")) {
                newPath = "/" + pathCd;
            } else {
                newPath = path + "/" + pathCd;
            }
            rmr(zk,newPath);
        }
        //删除节点,并过滤zookeeper节点和 /节点
        if (path != null && !path.trim().startsWith("/zookeeper") && !path.trim().equals("/")) {
            zk.delete(path, -1);
            //打印删除的节点路径
            System.out.println("被删除的节点为：" + path);
        }
    }
}
