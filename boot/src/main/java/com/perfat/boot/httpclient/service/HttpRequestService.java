/*
 * chsi
 * Created on 2018-05-21
 */
package com.perfat.boot.httpclient.service;


import com.perfat.boot.httpclient.vo.HttpResponseInfo;

import java.util.Map;

/**
 * Httpclient发送http请求
 * <p>
 * 1.通过HTTP协议访问网络资源，提供高效、最新、功能丰富的支持Http协议
 *
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public interface HttpRequestService {

    /**
     * 通过Httpclient发送GET服务请求
     *
     * @param url          请求url
     * @param headerMap    请求头部信息
     * @param paramMap     请求参数
     * @param requestBody  请求体信息
     * @param bodyMimeType 请求体的类型
     * @return 响应信息
     */
    HttpResponseInfo getRequest(String url, Map<String, String> headerMap, Map<String, String> paramMap, String requestBody, String bodyMimeType);

    /**
     * 通过Httpclient发送POST服务请求
     *
     * @param url          请求url
     * @param headerMap    请求头部信息
     * @param paramMap     请求参数
     * @param requestBody  请求体信息
     * @param bodyMimeType 请求体的类型
     * @return 响应信息
     */
    HttpResponseInfo postRequest(String url, Map<String, String> headerMap, Map<String, String> paramMap, String requestBody, String bodyMimeType);
}
