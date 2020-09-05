package common;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/20 16:22
 * @description: 使用阻塞队列 实现生产者消费者
 */


public class ProducerConsumerDemo2 {

    private int count = 0;

    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {

        ProducerConsumerDemo2 demo2 = new ProducerConsumerDemo2();
        for (int i = 1; i <= 5; i++) {
            new Thread(demo2.new Producer(), "生产者-" + i).start();
            new Thread(demo2.new Consumer(), "消费者-" + i).start();
        }

    }

    class Producer implements Runnable {

        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    queue.put(1);
                    count++;
                    System.out.println("生产者 " + Thread.currentThread().getName() + " 总共有 " + count + " 个资源");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    queue.take();
                    count--;
                    System.out.println("消费者 " + Thread.currentThread().getName() + " 总共有 " + count + " 个资源");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
