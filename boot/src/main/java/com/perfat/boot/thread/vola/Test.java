/*
 * Chsi
 * Created on 2020-07-31
 */
package com.perfat.boot.thread.vola;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class Test {
    volatile String count = "0s";
    private Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        Thread1 thread1 = test.new Thread1();
        Thread2 thread2 = test.new Thread2();

        thread2.start();
        thread1.start();
    }

    /**
     * <p>
     *    乐观锁：认为别人不会修改，不上锁，但是在更新数据的时候会更新这个数据，采取在写时先读出当前版本号。然后加锁操作，其实就是跟上次的版本
     *    号进行比较，一致更新，不一致则重新读-比较-写
     *    悲观锁：每次拿数据的时候都会认为别人在修改，读写数据的时候都会加上锁。
     *    自旋锁：等待别人释放锁，认为很短时间内就可以。占用CPU.超时则会阻塞起来。
     *
     *    同步锁：synchronized,他可以把任何一个非NULL的对象当做锁。
     *          针对方法：锁住当前对象
     *          针对静态方式：锁住Class实例
     *
     * <p/>
     */
    public class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    /*try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    count = count + "1";
                    System.out.println("线程1运行" + i + "次，此时count:" + count);
                }
            }
        }
    }


    public class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                for ( int j = 0; j< 10; j++) {
                        count = count + "2";
                        System.out.println("线程2运行"+j+"次，此时count:" + count);
                }
            }
        }
    }
}
