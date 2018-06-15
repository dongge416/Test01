import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class ShortUrlGenerator {
	//新浪key
	//2849184197,
	//iphone新浪微博客户端 App Key：5786724301

	//iPad新浪客户端App Key：2849184197

	//Google.Nexus浪客户端App Key：1206405345

	//周博通微博管家App Key：202088835

	//Weico App Key：211160679

	 public static void main(String[] args) {
//	       String url = "https://api.weibo.com/2/short_url/"
//	       		+ "shorten.json?source=2849184197&url_long=https://nbnbnb2.kuaizhan.com/?taowords=&pic=";
//	       String result = sendGet(url, "");
//	       System.out.println(result);
	 }
	 
	 
	 /**
	     * 向指定URL发送GET方法的请求
	     * 
	     * @param url
	     *            发送请求的URL
	     * @param param
	     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	     * @return URL 所代表远程资源的响应结果
	     */
	    public static String sendGetShortUrl(String taowords) {
	        String result = "";
	        BufferedReader in = null;
	        try {
	            String urlNameString = "https://api.weibo.com/2/short_url/"
	    	       		+ "shorten.json?source=2849184197&url_long=https://nbnbnb2.kuaizhan.com/?taowords="+taowords+"&pic=";;
	            URL realUrl = new URL(urlNameString);
	            // 打开和URL之间的连接
	            URLConnection connection = realUrl.openConnection();
	            // 设置通用的请求属性
	            connection.setRequestProperty("accept", "*/*");
	            connection.setRequestProperty("connection", "Keep-Alive");
	            connection.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            // 建立实际的连接
	            connection.connect();
	            // 获取所有响应头字段
	            Map<String, List<String>> map = connection.getHeaderFields();
	            // 遍历所有的响应头字段
	            for (String key : map.keySet()) {
	                System.out.println(key + "--->" + map.get(key));
	            }
	            // 定义 BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(new InputStreamReader(
	                    connection.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            System.out.println("发送GET请求出现异常！" + e);
	            e.printStackTrace();
	        }
	        // 使用finally块来关闭输入流
	        finally {
	            try {
	                if (in != null) {
	                    in.close();
	                }
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        }
	        return result;
	    }
	 
}
