package com.httpclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangzhilong
 * @date 2017/12/5
 */
public class BatchRequestTest {

    private static final Logger LOG = LoggerFactory.getLogger(BatchRequestTest.class);
    public static final int COUNT = 10;

    static class CallHttpRequest implements Runnable {
        private String url;
        private CountDownLatch begin;
        private CountDownLatch end;

        public CallHttpRequest(String url, CountDownLatch begin, CountDownLatch end) {
            this.url = url;
            this.begin = begin;
            this.end = end;
        }

        public void run() {
            try {
                long id = Thread.currentThread().getId();
                LOG.info("thread({}) is ready!!", id);
                begin.await();
                String body = HTTPUtils.sendGetRequest(url);
                LOG.info("thread({}) result:{}", id,body);
                end.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void relayLogin() throws InterruptedException {
        CountDownLatch begin = new CountDownLatch(1);
        ExecutorService exec = Executors.newFixedThreadPool(COUNT);
        CountDownLatch end = new CountDownLatch(COUNT);
        String url = "http://localhost:1111/zuul/user/login?loginName=lower_test_001&password=Test1234&loginType=1" ;
        for (int i = 0; i < COUNT; i++) {
            exec.execute(new CallHttpRequest(url,begin,end));
        }
        begin.countDown();
        end.await();
        exec.shutdown();
        LOG.info("all method end");

    }


    public static void main(String[] args) throws InterruptedException {
        relayLogin();
    }



}
