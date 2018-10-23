package com.milesbone.test.http;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Http 请求工具类
 * 
 * @author miles
 * @date 2016-08-05 上午9:17:44
 */
public class HttpProvider {

	private static final Logger logger = LoggerFactory.getLogger(HttpProvider.class);

	public HttpProvider() {

	}

	/**
	 * 初始化创建HttpClient
	 */
	public void init() {

	}

	/**
	 * 配置request参数
	 * 
	 * @param httpRequestBase
	 */
	public static void config(HttpRequestBase httpRequestBase) {
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(10000).setConnectTimeout(10000)
				.setSocketTimeout(10000).build();
		httpRequestBase.setConfig(requestConfig);
	}

	/**
	 * 创建HttpClient对象
	 * 
	 * @param maxTotal
	 *            最大连接数
	 * @param maxPerRoute
	 *            每个路由基础的连接
	 * @param maxRoute
	 *            目标主机的最大连接数
	 * @param maxRetryCount
	 * 			  最大重连次数
	 * @param hostname
	 * @param port
	 * @return
	 */
	public CloseableHttpClient createHttpClient(int maxTotal, int maxPerRoute, int maxRoute, String hostname,
			int port,int maxRetryCount) {
		logger.debug("开始创建CloeseHttp连接...");
		ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", plainsf).build();
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
		cm.setMaxTotal(maxTotal);
		cm.setDefaultMaxPerRoute(maxPerRoute);
		HttpHost httpHost = new HttpHost(hostname, port);
		cm.setMaxPerRoute(new HttpRoute(httpHost), maxRoute);
		
		// 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException exception,
                    int executionCount, HttpContext context) {
                if (executionCount >= 3) {// 如果已经重试了3次，就放弃
                	logger.error("请求重发3次连接失败,连接断开");
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                	logger.error("服务器丢失连接..");
                    return true;
                }
                if (exception instanceof InterruptedIOException) {// 超时
                	logger.error("请求超时..");
                    return false;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达
                	logger.error("目标服务器不可达....");
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                	logger.error("连接被拒绝..");
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext
                        .adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setRetryHandler(httpRequestRetryHandler).build();
        logger.debug("获取请求完成");
        return httpClient;
	}

	/**
	 * Http post同步请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String sendSyncHttpPost(String url, Map<String, Object> params) {
		return null;
	}

	/**
	 * HttpGet同步请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String sendSyncHttpGet(String url, Map<String, Object> params) {
		return null;
	}

	/**
	 * Http多文件提交请求
	 * 
	 * @param url
	 * @param fileParties
	 * @return
	 */
	public static String sendHttpMultiFile(String url, Map<String, Object> fileParties) {
		return null;
	}

	/**
	 * Http Post加密请求
	 * 
	 * @param url
	 * @param params
	 * @param encriptType
	 * @return
	 */
	public static String sendHttpPostWithEncription(String url, Map<String, String> params, String encriptType) {
		return null;
	}

	/**
	 * 销毁HttpClient
	 */
	public static void close() {

	}

	/**
	 * 设置cookie
	 */
	public static void setCookie() {

	}

	/**
	 * 设置请求头
	 */
	public static void setPostHeaders() {

	}

	/**
	 * 设置post参数
	 * 
	 * @param httpost
	 * @param params
	 */
	private static void setPostParams(HttpPost httpost, Map<String, Object> params) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key).toString()));
		}
		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
