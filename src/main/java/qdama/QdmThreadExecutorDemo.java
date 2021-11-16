package qdama;

/**
 * @Desctiption <Template>
 * @Author wujiaming
 * @Date 2021/5/18
 */
public class QdmThreadExecutorDemo {
    public static void main(String[] args) throws InterruptedException {
        QdmThreadPoolExecutor pool = new QdmThreadPoolExecutor(3);
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            pool.execute(() -> {
                try {
                    System.out.println("输出：" + finalI);
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
