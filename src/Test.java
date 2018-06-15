import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Test {
	
	

//	public static void testCreateFirstExcel07() throws Exception {  
//        Workbook wb = new XSSFWorkbook();  
//        FileOutputStream fileOut = new FileOutputStream("C:/workbook.xlsx");  
//        wb.write(fileOut);  
//        fileOut.close();  
//    }
	
	
	
	public static void main(String[] args) throws Exception {
		
		 JFrame frame = new JFrame("BorderLayoutDemo");
	      frame.setBounds(500, 200, 300, 300);
	      frame.setLayout(new BorderLayout(10, 10));
//	      frame.add(new JButton("北"), BorderLayout.NORTH);
//	      frame.add(new JButton("东"), BorderLayout.EAST);
	      frame.add(new JButton("南"), BorderLayout.SOUTH);
//	      frame.add(new JButton("西"), BorderLayout.WEST);
	      JButton covertBtn = new JButton("开始采集");
	      frame.add(covertBtn, BorderLayout.SOUTH);
	      frame.setVisible(true);
	      
	      covertBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
//				File input = new File("D:\\采集小工具\\待采集数据\\淘宝联盟.html");
				File input = new File("/Users/dongge416/Downloads/4/淘宝联盟.html");
				
				Workbook wb = new HSSFWorkbook(); 
		        //创建工作表  
		        Sheet sheet = wb.createSheet("测试Excel");

					Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
					Elements element = doc.getElementsByTag("p");
					for (int i = 0; i < element.size(); i++) {
						
						if(i>1 && i <52) {
							
							String titleName = element.get(i).getElementsByTag("a").attr("title");
							String value = element.get(i).parent().parent().child(1).child(0).child(1).text();
							if(value.contains("元")) {
								value = element.get(i).parent().parent().child(2).child(0).child(1).text();
							}
							
							 int rowIndex = i-2;
							 Row row = sheet.createRow(rowIndex);   //起始从0开始  
						        
						        
								Cell cell = row.createCell(0);  
								
								cell.setCellValue(value);  
								Cell cell1 = row.createCell(1);  
								
								cell1.setCellValue(titleName);  
							
							
						
						}
					}
					
				    FileOutputStream fileOut;
					
//						fileOut = new FileOutputStream("D:\\\\采集小工具\\\\输出数据\\\\输出数据.xls");
						fileOut = new FileOutputStream("/Users/dongge416/Downloads/输出数据.xls");
					
			        wb.write(fileOut);  
			        fileOut.close(); 
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  
					System.out.println("转换成功");
			}
		});
	      
//	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
//		File input = new File("/Users/dongge416/Downloads/2/淘宝联盟.html");
//		
//		Workbook wb = new HSSFWorkbook(); 
//        //创建工作表  
//        Sheet sheet = wb.createSheet("测试Excel");
//
//			Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
//			Elements element = doc.getElementsByTag("p");
//			for (int i = 0; i < element.size(); i++) {
//				
//				if(i>1 && i <52) {
//					
//					String titleName = element.get(i).getElementsByTag("a").attr("title");
//					String value = element.get(i).parent().parent().child(1).child(0).child(1).text();
//					if(value.contains("元")) {
//						value = element.get(i).parent().parent().child(2).child(0).child(1).text();
//					}
//					
//					 int rowIndex = i-2;
//					 Row row = sheet.createRow(rowIndex);   //起始从0开始  
//				        
//				        
//						Cell cell = row.createCell(0);  
//						
//						cell.setCellValue(value);  
//						Cell cell1 = row.createCell(1);  
//						
//						cell1.setCellValue(titleName);  
//					
//					
//				
//				}
//			}
//			
//		    FileOutputStream fileOut = new FileOutputStream("/Users/dongge416/Downloads/test_exc.xls");  
//	        wb.write(fileOut);  
//	        fileOut.close(); 
//			
//			System.out.println("转换成功");
			
	
		
	}
	
	
	public void covert() throws Exception {
		File input = new File("/Users/dongge416/Downloads/2/淘宝联盟.html");
		
		Workbook wb = new HSSFWorkbook(); 
        //创建工作表  
        Sheet sheet = wb.createSheet("测试Excel");

			Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
			Elements element = doc.getElementsByTag("p");
			for (int i = 0; i < element.size(); i++) {
				
				if(i>1 && i <52) {
					
					String titleName = element.get(i).getElementsByTag("a").attr("title");
					String value = element.get(i).parent().parent().child(1).child(0).child(1).text();
					if(value.contains("元")) {
						value = element.get(i).parent().parent().child(2).child(0).child(1).text();
					}
					
					 int rowIndex = i-2;
					 Row row = sheet.createRow(rowIndex);   //起始从0开始  
				        
				        
						Cell cell = row.createCell(0);  
						
						cell.setCellValue(value);  
						Cell cell1 = row.createCell(1);  
						
						cell1.setCellValue(titleName);  
					
					
				
				}
			}
			
		    FileOutputStream fileOut = new FileOutputStream("/Users/dongge416/Downloads/test_exc.xls");  
	        wb.write(fileOut);  
	        fileOut.close(); 
			
			System.out.println("转换成功");
	}
	
}
