/*
 * chsi
 * Created on 2018-05-21
 */
package com.perfat.boot.httpclient.service.impl;

import com.perfat.boot.httpclient.service.HttpRequestService;
import com.perfat.boot.httpclient.util.HttpClientUtil;
import com.perfat.boot.httpclient.vo.HttpResponseInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Repository("httpRequestService")
public class HttpRequestServiceImpl implements HttpRequestService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public HttpResponseInfo getRequest(String url, Map<String, String> headerMap, Map<String, String> paramMap, String requestBody, String bodyMimeType) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        HttpUriRequest httpUriRequest;
        if (paramMap == null || paramMap.isEmpty()) {
            return request(new HttpGet(url), headerMap, url);
        }
        StringBuilder builder = new StringBuilder(url);
        if (StringUtils.contains(url, "?")) {
            builder.append("&");
        } else {
            builder.append("?");
        }
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            builder.append(String.format("%s=%s&", entry.getKey(), entry.getValue()));
        }
        return request(new HttpGet(builder.substring(0, builder.length() - 1)), headerMap, url);
    }

    @Override
    public HttpResponseInfo postRequest(String url, Map<String, String> headerMap, Map<String, String> paramMap, String requestBody, String bodyMimeType) {
        HttpPost httpPost = new HttpPost(url);
        if (paramMap != null && !paramMap.isEmpty()) {
            List<NameValuePair> paramList = new ArrayList<>();
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        return null;
    }

    private HttpResponseInfo request(HttpUriRequest httpUriRequest, Map<String, String> headerMap, String url) {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(HttpClientUtil.clientConnectionManager)
                .setDefaultRequestConfig(HttpClientUtil.requestConfig)
                .build();
        CloseableHttpResponse closeableHttpResponse = null;
        long startRequestTime = System.currentTimeMillis();
        HttpResponseInfo responseInfo = new HttpResponseInfo();
        try {
            setHeader(httpUriRequest, headerMap);
            closeableHttpResponse = httpClient.execute(httpUriRequest);
            responseInfo.setStatus(closeableHttpResponse.getStatusLine().getStatusCode());
            responseInfo.setHeaders(closeableHttpResponse.getAllHeaders());
            responseInfo.setMessage(EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8"));
            return responseInfo;
        } catch (IOException e) {
            log.error("访问url:{}异常:{}", url, e);
            responseInfo.setStatus(-100);
            responseInfo.setMessage(e.getMessage());
            return responseInfo;
        } finally {
            long endRequestTime = System.currentTimeMillis();
            long requestTime = endRequestTime - startRequestTime;
            log.info("访问url:{},耗时:{},状态码:{},message:{}", new Object[]{url, requestTime, responseInfo.getStatus(), responseInfo.getMessage()});
            if (null != closeableHttpResponse) {
                EntityUtils.consumeQuietly(closeableHttpResponse.getEntity());
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                    log.error("异常信息：{}", e);
                }
            }
        }
    }

    private void setHeader(HttpUriRequest httpUriRequest, Map<String, String> headerMap) {
        if (headerMap == null || headerMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            httpUriRequest.setHeader(entry.getKey(), entry.getValue());
        }
    }
}
