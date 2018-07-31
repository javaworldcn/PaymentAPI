package com.grapefs.demo.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpUtil {

	private Logger log = LoggerFactory.getLogger(getClass());

	public static final String ACCEPT_TYPE_JSON = "application/json";
	
	public static final String CHARSET = "UTF-8";
	
	private PoolingHttpClientConnectionManager cm;

	private RequestConfig requestConfig;

	
	public HttpUtil() {
		cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(200);
		cm.setDefaultMaxPerRoute(50);
		// 设置http的状态参数
		requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000)
				.setConnectionRequestTimeout(60000).build();
	}

	public String get(String url) {
		return this.get(url, "text/html", null, CHARSET);
	}

	public String get(String url, String acceptType) {
		return this.get(url, acceptType, null, CHARSET);
	}

	public String get(String url, String acceptType, String auth) {
		return this.get(url, acceptType, auth, CHARSET);
	}

	public String get(String url, String acceptType, String auth, String charset) {
		String result = null;
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClients.custom().setConnectionManager(cm).setConnectionManagerShared(true).build();
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			httpGet.setHeader("Accept", acceptType);
			httpGet.setHeader("Content-Type", acceptType + ";charset=" + charset);
			if (auth != null) {
				httpGet.setHeader("Authorization", auth);
			}
			// 执行客户端请求
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, charset);
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			// 关闭连接
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
		return result;
	}

	public String post(String url, String params) {
		return this.post(url, params, ACCEPT_TYPE_JSON, null, CHARSET);
	}

	public String post(String url, String params, String acceptType, String auth) {
		return this.post(url, params, acceptType, auth, CHARSET);
	}

	public String post(String url, String params, String acceptType, String auth, String charset) {
		String result = null;
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClients.custom().setConnectionManager(cm).setConnectionManagerShared(true).build();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			httpPost.setHeader("Accept", acceptType);
			httpPost.setHeader("Content-Type", acceptType + ";charset=" + charset);
			if (auth != null) {
				httpPost.setHeader("Authorization", auth);
			}
			BasicHttpEntity requestBody = new BasicHttpEntity();
			requestBody.setContent(new ByteArrayInputStream(params.getBytes(charset)));
			requestBody.setContentLength(params.getBytes(charset).length);
			httpPost.setEntity(requestBody);
			// 执行客户端请求

			trustAllHosts();
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, charset);
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			// 关闭连接
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
		return result;
	}

	/**
	 * Trust every server - dont check for any certificate
	 */
	public static void trustAllHosts() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new java.security.cert.X509Certificate[] {};
			}

			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}
		} };

		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
