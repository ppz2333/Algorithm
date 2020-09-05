package common;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/8/20 16:04
 * @description: ReentrantLock 实现生产者消费者
 */



public class ProducerConsumerDemo1 {

    private int count = 0;

    private final static int FULL = 10;

    private Lock lock;

    private Condition notEmptyCondition;

    private Condition notFullCondition;

    {
        lock = new ReentrantLock();
        notEmptyCondition = lock.newCondition();
        notFullCondition = lock.newCondition();

    }

    class Producer implements Runnable {

        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lock.lock();

                try {
                    while(count == FULL) {
                        try {
                            notFullCondition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                    count++;
                    System.out.println("生产者 " + Thread.currentThread().getName() + " 总共有 " + count + " 个资源");
                    notEmptyCondition.signal();


                } finally {
                    lock.unlock();
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
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lock.lock();

                try {

                    while(count == 0) {

                        try {
                            notEmptyCondition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                    count--;
                    System.out.println("消费者 " + Thread.currentThread().getName() + " 总共有 " + count + " 个资源");
                    notFullCondition.signal();


                } finally {
                    lock.unlock();
                }


            }

        }

    }

    public static void main(String[] args) {

        ProducerConsumerDemo1 demo1 = new ProducerConsumerDemo1();
        for (int i = 1; i <= 5; i++) {
            new Thread(demo1.new Producer(), "生产者-" + i).start();
            new Thread(demo1.new Consumer(), "消费者-" + i).start();
        }

    }

}
