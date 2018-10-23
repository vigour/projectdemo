package com.manmanyun.test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.milesbone.http.HttpClientUtil;

import junit.framework.TestCase;

public class MMYDemo extends TestCase {
	
	private static final Logger log = LoggerFactory.getLogger(MMYDemo.class);
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddhhMMss");
	
	final String username = "foxconn";
    final String timestamp = String.valueOf(System.currentTimeMillis());
    final String apiKey = "Foxconn@2018";
	final String domainid = "2717,2712,2414";
    String url = "https://api.chinamaincloud.cn/";
    String apiurl = null;
    String token = null;
    
    Calendar calander = null;
	public static void main(String[] args) {
        final String username = "foxconn";
        final String timestamp = String.valueOf(System.currentTimeMillis());
        final String apiKey = "Foxconn@2018";
        // 生成token: 5633e9389cd093bba10854eb51ac821f
        String token = getMD5(getMD5(username) + timestamp + apiKey);
        System.out.println(timestamp);
        System.out.println(token);
    }
	
	protected void setUp() throws Exception {
		super.setUp();
		 calander = Calendar.getInstance();
        // 生成token: 5633e9389cd093bba10854eb51ac821f
		token = getMD5(getMD5(username) + timestamp + apiKey);
		log.info(timestamp);
		log.info(token);
	}
	

	@Test
	public void testGetDomain() {
		apiurl = "/api/public/domain?timestamp={timestamp}&username={username}&token={token}";
		apiurl= apiurl.replaceAll("\\{timestamp\\}", timestamp).replaceAll("\\{username\\}", username).replaceAll("\\{token\\}", token);
		log.info(url+apiurl);
		String result = HttpClientUtil.get(url+apiurl);
		log.info("My domain is:" + result);
	}
	
	@Test
	public void testTopURL() {
		calander.set(2018, 8, 18 ,10, 0, 0);
		String starttime = "201809170000";
		
		String endtime = "201809180000";;
		apiurl = "/api/public/statistic/topurl?timestamp={timestamp}&username={username}&token={token}"
				+ "&domains={domains}&starttime={starttime}&endtime={endtime}";
		apiurl= apiurl.replaceAll("\\{timestamp\\}", timestamp).replaceAll("\\{username\\}", username).replaceAll("\\{token\\}", token)
					.replaceAll("\\{domains\\}", domainid).replaceAll("\\{starttime\\}", starttime+"").replaceAll("\\{endtime\\}", endtime+"");
		
		log.info(url + apiurl);
		
		String result = HttpClientUtil.get(url+apiurl);
		log.info("TOP url:" + result);
	}

    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes("UTF-8"));
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new RuntimeException("MD5加密出现错误");
        }
    }
}
