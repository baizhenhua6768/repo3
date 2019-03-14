/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: HttpClientPool
 * Author:   17061157_王薛
 * Date:     2018/8/29 10:23
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.message.impl;

import java.util.concurrent.TimeUnit;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 功能描述：HttpClient 池
 *
 * @author 17061157_王薛
 * @since 2018/8/29
 */
@Component
public class HttpClientPool implements InitializingBean {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpClientPool.class);

    private static final long RELEASE_CONNECTION_WAIT_TIME = 5000;// 监控连接间隔

    private static PoolingHttpClientConnectionManager connManager = null;

    static {
        // 初始化连接池管理器
        connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(200);
        connManager.setDefaultMaxPerRoute(20);

        Thread idleConnectionMonitorThread = new IdleConnectionMonitorThread(connManager);
        idleConnectionMonitorThread.setDaemon(true);
        idleConnectionMonitorThread.start();

    }

    public static CloseableHttpClient getHttpClient() {
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                .setConnectTimeout(1000).setConnectionRequestTimeout(1000)
                .setSocketTimeout(1000).setRedirectsEnabled(true).build();
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager)
                .setDefaultRequestConfig(globalConfig).build();
        return client;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    /**
     * 释放连接池内的无效连接和空闲连接
     */
    public static class IdleConnectionMonitorThread extends Thread {

        private static volatile boolean shutdown = false;
        private PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;

        public IdleConnectionMonitorThread(PoolingHttpClientConnectionManager poolingHttpClientConnectionManager) {
            this.poolingHttpClientConnectionManager = poolingHttpClientConnectionManager;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (IdleConnectionMonitorThread.class) {
                        IdleConnectionMonitorThread.class.wait(RELEASE_CONNECTION_WAIT_TIME);
                        // Close expired connections
                        poolingHttpClientConnectionManager.closeExpiredConnections();
                        // that have been idle longer than 30 sec
                        poolingHttpClientConnectionManager.closeIdleConnections(2, TimeUnit.MINUTES);
                    }
                }
            } catch (InterruptedException ex) {
                LOGGER.error("释放连接池连接出错.");
            }
        }

    }

}
