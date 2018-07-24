package zookeeper_lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

public class ZookeeperLockUtils {

    private String root = "/locks";
    private ZooKeeper zooKeeper;

    private String lockName;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    private ThreadLocal<String> nodeId = new ThreadLocal<>();

    private byte[] bytes = new byte[0];

    private String lockString ;

    public ZookeeperLockUtils() {
        try {
            zooKeeper = new ZooKeeper("192.168.4.224:2181", 3000, (WatchedEvent event) -> {
                if (event.getState() == Watcher.Event.KeeperState.SyncConnected) countDownLatch.countDown();
                ;
            });
            countDownLatch.await();
            Stat stat = zooKeeper.exists(root, false);
            if (null == stat) {
                zooKeeper.create(root, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public void lock(String lockName) {
        try {
            String actualPath = zooKeeper.create(root + "/" + lockName, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            List<String> children = zooKeeper.getChildren(root, false);
            TreeSet<String> treeSet = new TreeSet<>();
            for (String subPath : children) {
                treeSet.add(root + "/" + subPath);
            }
            String smallNode = treeSet.first();
            String preNode = treeSet.lower(actualPath);
            lockString = smallNode;
            if (actualPath.equals(smallNode)) {
                nodeId.set(actualPath);
                return;
            }
            CountDownLatch latch = new CountDownLatch(1);
            Stat stat = zooKeeper.exists(preNode, event -> {
                if (event.getType() == Watcher.Event.EventType.NodeDeleted) {
                    latch.countDown();
                }
            });
            if (stat != null) {
                latch.await();
                nodeId.set(actualPath);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unlock() {
        if (null != nodeId) {
            try {
                zooKeeper.delete(lockName,-1);
                nodeId.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (KeeperException e) {
                e.printStackTrace();
            }
        }
    }
}
