package xhj.wdc.util;

import java.io.IOException;
import java.util.Properties;

public class GetPropertiesUtil {

	// 得到url
	public static String getUrl() {
		try {
			// 从xxx.properties文件里读出地址
			Properties prop = new Properties();
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("url.properties"));
			return (String) prop.get("url");

		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}
