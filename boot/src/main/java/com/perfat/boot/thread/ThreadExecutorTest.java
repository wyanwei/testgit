/*
 * Chsi
 * Created on 2020-08-04
 */
package com.perfat.boot.thread;

import com.perfat.boot.security.entity.UserInfoData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class ThreadExecutorTest {

    public static void main(String[] args){
        //创建一个可缓存的线程
        //ExecutorService cacheThreadPool = Executors.newCachedThreadPool();

        //固定个数的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        //ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor()

        //定时线程
        ScheduledExecutorService scheduThreadPool = Executors.newScheduledThreadPool(1);

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        //系统资源
        System.out.println(Runtime.getRuntime().availableProcessors());

       /* long startTime = System.currentTimeMillis();
        scheduThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟1s后每3秒执行一次");
            }
        }, 1, 3, TimeUnit.SECONDS);

        System.out.println("耗时：" + (System.currentTimeMillis()- startTime)+"ms");*/

        for ( int i = 0; i < 10; i++ ) {
            final int index = i;
            /*fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("启动线程" + Thread.currentThread().getName()+ "打印当前值:" + index);
                    *//*try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*//*
                }
            });
*/
            fixedThreadPool.execute(() -> {
                System.out.println("线程" + Thread.currentThread().getName()+ "打印当前值:" + index);
                /*try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
              }
            );
        }



    }
}
