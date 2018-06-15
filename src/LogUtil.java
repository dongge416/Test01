import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {

	    public static File file;  
	    public static String filePath;  
	    public LogUtil(String filePath){  
	          this.filePath = filePath;
	    }  
	    public static void  infoPrint(String msg){  
//	        filePath="D:\\ClareTest.log";  
	    		System.out.println(msg);
	        file =new File(filePath);  
	        if (file==null || !file.exists()){  
	            try {  
	                file.createNewFile();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	          
	        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH-mm-ss");  
	        String dateString=df.format(new Date());  
	        try {  
	            FileWriter writer = new FileWriter(file, true);  
	            String content="["+dateString+"] "+msg+"\r\n";  
	            writer.write(content);  
	            writer.close();  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	    }  
	  
	
}
