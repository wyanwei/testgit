/*
 * chsi
 * Created on 2018-05-21
 */
package com.perfat.boot.httpclient.vo;

import org.apache.http.Header;

/**
 * http访问后的响应信息
 *
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class HttpResponseInfo {
    /**
     * 响应状态(-100 请求失败 请求代码参考HttpServletResponse)
     */
    private int status;
    /**
     * 请求头信息
     */
    private Header[] headers;
    /**
     * 返回的信息
     */
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Header[] getHeaders() {
        return headers;
    }

    public void setHeaders(Header[] headers) {
        this.headers = headers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
