/*
 * chsi
 * Created on 2018-05-21
 */
package com.perfat.boot.httpclient.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * httpclient连接池的配置
 * <p>
 * 使用的时候需要考虑：
 * 1.http连接池不是万能的，过多的长连接会占用服务器资源，导致其他服务受阻
 * 2.http连接池只适用于请求是经常访问同一主机(或同一个接口)的情况下
 * 3.并发数不高的情况下资源利用率低下
 * <p>
 * 使用http连接池的好处：
 * 1.复用http连接，省去了http的3次握手与4次挥手的时间，极大的降低了请求响应时间
 * 2.自动管理tcp连接，不用人为的释放/创建连接
 *
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class HttpClientUtil {
    /**
     * httpclient连接池管理
     * <p>
     * 使用http连接池的流程
     * 1.创建PoolingHttpClientConnectionManager实例
     * 2.给manager设置参数
     * 3.给manager设置重试策略
     * 4.给manager设置连接管理策略
     * 5.开启监控线程,及时关闭被服务器单向断开的连接
     * 6.构建HttpClient实例
     * 7.创建HttpPost/HttpGet实例,并设置参数
     * 8.获取响应,做适当的处理
     * 9.将用完的连接放回连接池
     */
    public static PoolingHttpClientConnectionManager clientConnectionManager;
    /**
     * request请求相关配置
     */
    public static RequestConfig requestConfig;
    /**
     * socket配置（默认配置或者某个host的配置）
     */
    public static SocketConfig socketConfig;

    static {
        clientConnectionManager = new PoolingHttpClientConnectionManager();
        //连接数相关设置，最大连接数
        clientConnectionManager.setMaxTotal(200);
        //默认的每个路由的最大连接数
        clientConnectionManager.setDefaultMaxPerRoute(100);

        //request请求相关配置
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        //连接超时
        requestConfigBuilder.setConnectTimeout(2000);
        //设置读取超时
        requestConfigBuilder.setSocketTimeout(20000);
        //从连接池中获取连接实例超时时间
        requestConfigBuilder.setConnectionRequestTimeout(5000);
        requestConfig = requestConfigBuilder.build();
    }
}
