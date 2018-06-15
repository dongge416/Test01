import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DemoUI {
	
	public static LogUtil  logs= new LogUtil("/Users/dongge416/Downloads/log.txt");
//	public static SqlUtil sqlUtil = new SqlUtil("/Users/dongge416/Downloads/db_alimama.db3");

	public static void main(String[] args) {
		SqlUtil sqlUtil = new SqlUtil("/Users/dongge416/Downloads/db_alimama.db3");
		
		 // 1. 创建一个顶层容器（窗口）
        JFrame jf = new JFrame("测试窗口");          // 创建窗口
        jf.setSize(800, 800);                       // 设置窗口大小
        jf.setLocationRelativeTo(null);             // 把窗口位置设置到屏幕中心
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 当点击窗口的关闭按钮时退出程序（没有这一句，程序不会退出）

        // 2. 创建中间容器（面板容器）
        JPanel panel = new JPanel();                // 创建面板容器，使用默认的布局管理器
//        panel.setBackground(Color.BLUE);
        
        JLabel label = new JLabel("聊天文件位置");
        JTextField tf = new JTextField(40);
        // 3. 创建一个基本组件（按钮），并添加到 面板容器 中
        JButton btn = new JButton("选择");
        JButton btnFilter = new JButton("开始过滤");
        panel.add(label);
        panel.add(tf);
        panel.add(btn);
        panel.add(btnFilter);
        
        
        //更新数据库部分
        Panel panel02 = new Panel();
        panel02.setBackground(Color.DARK_GRAY);;
        JLabel label02 = new JLabel("excel文件位置");
        JTextField tf02 = new JTextField(40);
        JButton btn02 = new JButton("选择");
        JButton btnImport = new JButton("导入数据库");
 
        panel02.add(label02);
        panel02.add(tf02);
        panel02.add(btn02);
        panel02.add(btnImport);

        // 4. 把 面板容器 作为窗口的内容面板 设置到 窗口
//        jf.setContentPane(panel);
        jf.setLayout(new BorderLayout());
        jf.add(panel02,BorderLayout.NORTH);
        jf.add(panel,BorderLayout.CENTER);

       
        // 5. 显示窗口，前面创建的信息都在内存中，通过 jf.setVisible(true) 把内存中的窗口显示在屏幕上。
        jf.setVisible(true);
        
        
        
        btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jFileChooser = new JFileChooser();
                int i = jFileChooser.showOpenDialog(null);
                if(i== jFileChooser.APPROVE_OPTION){ //打开文件
                    String path = jFileChooser.getSelectedFile().getAbsolutePath();
                    String name = jFileChooser.getSelectedFile().getName();
                    logs.infoPrint("当前问紧路径:"+path);
                    tf.setText(path);
//                    System.out.println("当前文件路径："+path+";\n当前文件名："+name);
//                    ArrayList<String> list = new ArrayList<>();
//                    readFileByChars(path, list);
//                    numberFilter(list);
                }else{
                    System.out.println("没有选中文件");
                }
			}
		});
        
        btnFilter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String path = tf.getText().trim();
				if(path.equals("")) {
					JOptionPane.showMessageDialog(null, "请先选择聊天记录文件", "提示", JOptionPane.ERROR_MESSAGE); 
				}else {
                  ArrayList<String> list = new ArrayList<>();
                  readFileByChars(path, list);
                  ArrayList<String> numberList = numberFilter(list);
                  
                  boolean conFlag = sqlUtil.createConnection();
                  logs.infoPrint("打开数据库连接:"+conFlag);
                  
//                  logs.infoPrint("numberList size:"+numberList.size());
                  String logStr = "";
                  for (int i = 0; i < numberList.size(); i++) {
                	  	String orderNo = numberList.get(i);
                	  	if(orderNo.length() > 10) {
//                	  		logs.infoPrint("orderNo:"+orderNo);
                	  		List<OrderModel> orderList = sqlUtil.queryDatas(orderNo);
//                   	  	 logs.infoPrint("orderList size:"+orderList.size());
                	  		
                	  		if(orderList.size()>1) {
                	  			for (int j = 0; j < orderList.size(); j++) {
                	  					OrderModel order = orderList.get(j);
                	  					logStr =logStr+ "订单编号:"+orderNo + "," +order.getProductInformation() + ",付款时间:"+order.getCreateTime()+",结算时间:"+order.getSettlementTime()+"\n";
           							
                           	}
//                	  			logs.infoPrint(log);
                	  		}else {
                	  			logStr =logStr+ "订单编号:"+orderNo+" 找不到对应订单!\n";
//                	  			logs.infoPrint(log);
                	  		}
                	  		
                   	  	
                	  	}
                	  	
                	  	
					
                  }
                  logs.infoPrint(logStr);
                  boolean closeFlag = sqlUtil.closeConnection();
                  logs.infoPrint("关闭数据库连接:"+closeFlag);
                  JOptionPane.showMessageDialog(null, "过滤完毕", "提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
        
        
        //选择数据库事件
        btn02.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jFileChooser = new JFileChooser();
                int i = jFileChooser.showOpenDialog(null);
                if(i== jFileChooser.APPROVE_OPTION){ //打开文件
                    String path = jFileChooser.getSelectedFile().getAbsolutePath();
                    String name = jFileChooser.getSelectedFile().getName();
                    logs.infoPrint("数据库路径:"+path);
                    tf02.setText(path);
                    
                    boolean creatDB = sqlUtil.creatDB();
            			logs.infoPrint("创建数据库:"+creatDB);
                    
                    //读取excel
                    Workbook wb = readExcel(path);
                    Sheet sheet = wb.getSheetAt(0);
                    
                    int maxRow = sheet.getPhysicalNumberOfRows();
                    int maxColumn = sheet.getRow(0).getPhysicalNumberOfCells();
//                    if(maxRow >1) {
//                    		boolean creatDBFlag = sqlUtil.creatDB();
//                    		logs.infoPrint("打开数据库:"+creatDBFlag);
//                    }
                    
                    ArrayList<OrderModel> orderList = new ArrayList<OrderModel>();
                    for (int j = 1; j < maxRow; j++) {
                    		Row row = sheet.getRow(j);
                    		OrderModel order = new OrderModel();
                    		order.setCreateTime(getCellValue(row.getCell(0)));
                    		order.setClickTime(getCellValue(row.getCell(1)));
                    		String information = getCellValue(row.getCell(2));
                    		information = information.replace("'", "");
                    		order.setProductInformation(information);
                    		order.setProductId(getCellValue(row.getCell(3)));
                    		order.setWangwang(getCellValue(row.getCell(4)));
                    		order.setShopName(getCellValue(row.getCell(5)));
                    		
                    		order.setProductCounts(Integer.valueOf(getCellValue(row.getCell(6))));
                    		order.setProductPrice(Float.valueOf(getCellValue(row.getCell(7))));
                    		order.setOrderState(getCellValue(row.getCell(8)));
                    		order.setOrderType(getCellValue(row.getCell(9)));
                    		order.setIncomeRace(getCellValue(row.getCell(10)));
                    		
                    		order.setShareRace(getCellValue(row.getCell(11)));
                    		order.setPay(Float.valueOf(getCellValue(row.getCell(12))));
                    		order.setEffecteStimate(Float.valueOf(getCellValue(row.getCell(13))));
                    		order.setSettlementAmount(Float.valueOf(getCellValue(row.getCell(14))));
                    		order.setIncomeEstimate(Float.valueOf(getCellValue(row.getCell(15))));
                    		order.setSettlementTime(getCellValue(row.getCell(16)));
                    		order.setCommissionRace(getCellValue(row.getCell(17)));
                    		order.setCommission(Float.valueOf(getCellValue(row.getCell(18))));
                    		order.setSubsidyRace(getCellValue(row.getCell(19)));
                    		order.setSubsidy(Float.valueOf(getCellValue(row.getCell(20))));
                    		order.setSubsidyType(getCellValue(row.getCell(21)));
                    		order.setDealPlatform(getCellValue(row.getCell(22)));
                    		order.setThirdParty(getCellValue(row.getCell(23)));
                    		order.setOrderNo(getCellValue(row.getCell(24)));
                    		order.setCategoryName(getCellValue(row.getCell(25)));
                    		order.setMediaId(Integer.valueOf(getCellValue(row.getCell(26))));
                    		order.setMediaName(getCellValue(row.getCell(27)));
                    		order.setAdvertisementId(Integer.valueOf(getCellValue(row.getCell(28))));
                    		order.setAdvertisementName(getCellValue(row.getCell(29)));
                    		orderList.add(order);
//                    		logs.infoPrint(order.toString());
//                    		boolean insertFlag = sqlUtil.insertData(order);
//                    		logs.infoPrint("插入数据库:"+insertFlag);
//                    		String temp = "";
//						for (int k = 0; k < maxColumn; k++) {
//							
//							temp = temp + " "  + getCellValue(row.getCell(k));
//						}
//						temp = temp+ "\n";
//						logs.infoPrint(temp);
					}
                    
                    boolean insertFlag = sqlUtil.insertOrderList(orderList);
                    logs.infoPrint("插入数据库:"+insertFlag);
                    
                }else{
                	 	logs.infoPrint("没有选中文件");
     
                }
			}
		});
        
        
	}
	
	/**
	 * 过滤数字串
	 * @param list
	 */
	private static ArrayList<String> numberFilter(ArrayList<String> list) {
		ArrayList<String> numberList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			
			char tempChar = list.get(i).charAt(0);
			//判断是否为数字
			boolean isDigit = Character.isDigit(tempChar);
			if(isDigit) {
				if(i>= 1) {
					//判断上一个是否也是数字
					char tempPreChar = list.get(i-1).charAt(0);
					boolean isDigittempPreChar = Character.isDigit(tempPreChar);
					
					if(isDigittempPreChar) {
						//上一个字也是数字,把当前数字添加到上一串数字后面
						int size = numberList.size();
						String tempNumArrayPre = numberList.get(size-1) + tempChar;
						numberList.remove(size-1);
						numberList.add(tempNumArrayPre);
					}else {
						//上一个不是数字，单独开始组成数字串
						String tempNumArryay = tempChar + "";
						numberList.add(tempNumArryay);
					}
					
					
				}else {
					//第一个字即为数字都情况,存入list第一个数字组
					String tempNumArryay = tempChar + "";
					numberList.add(tempNumArryay);
				}
				
			}
			
			
			
			
			
			
		}
		
		
//		for (int j = 0; j < numberList.size(); j++) {
//			if(numberList.get(j).length()>10) {
//				LogUtil.infoPrint(numberList.get(j));
//			}
//			
//		}
		
		return numberList;
	}
	
	/**
	 * 读取文本字节
	 * @param fileName
	 * @param list
	 */
	public static void readFileByChars(String fileName,ArrayList<String> list) {

		
		File file = new File(fileName);

		Reader reader = null;

		try {

			System.out.println("以字符为单位读取文件内容，一次读一个字节：");

			// 一次读一个字符

			reader = new InputStreamReader(new FileInputStream(file));

			int tempchar;

			while ((tempchar = reader.read()) != -1) {
//				reader.re
				// 对于windows下，\r\n这两个字符在一起时，表示一个换行。

				// 但如果这两个字符分开显示时，会换两次行。

				// 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。

				if (((char) tempchar) != '\r') {
					// System.out.print("");
					char temp = (char)tempchar;
					list.add(temp+"");
					if(Character.isDigit(temp));
//					System.out.print("是数字:"+(char) tempchar + "\n");

				}
//				for (int i = 0; i < list.size(); i++) {
//					System.out.println(i+":"+list.get(i));
//				}
//				list.clear();

			}

			reader.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

		try {

			System.out.println("\n以字符为单位读取文件内容，一次读多个字节：");

			// 一次读多个字符

			char[] tempchars = new char[30];

			int charread = 0;

			reader = new InputStreamReader(new FileInputStream(fileName));

			// 读入多个字符到字符数组中，charread为一次读取字符数

			while ((charread = reader.read(tempchars)) != -1) {

				// 同样屏蔽掉\r不显示

				if ((charread == tempchars.length)

						&& (tempchars[tempchars.length - 1] != '\r')) {

					// System.out.print(tempchars);

				} else {

					for (int i = 0; i < charread; i++) {

						if (tempchars[i] == '\r') {

							continue;

						} else {

							// System.out.print(tempchars[i]);

						}

					}

				}

			}

		} catch (Exception e1) {

			e1.printStackTrace();

		} finally {

			if (reader != null) {

				try {

					reader.close();

				} catch (IOException e1) {

				}

			}

		}

	}
	
	//读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
    
  //获取单元格的值  
    private static String getCellValue(Cell cell) {  
        String cellValue = "";  
        DataFormatter formatter = new DataFormatter();  
        if (cell != null) {  
            //判断单元格数据的类型，不同类型调用不同的方法  
            switch (cell.getCellType()) {  
                //数值类型  
                case Cell.CELL_TYPE_NUMERIC:  
                    //进一步判断 ，单元格格式是日期格式   
                    if (DateUtil.isCellDateFormatted(cell)) {  
                        cellValue = formatter.formatCellValue(cell);  
                    } else {  
                        //数值  
                        double value = cell.getNumericCellValue();  
                        int intValue = (int) value;  
                        cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);  
                    }  
                    break;  
                case Cell.CELL_TYPE_STRING:  
                    cellValue = cell.getStringCellValue();  
                    break;  
                case Cell.CELL_TYPE_BOOLEAN:  
                    cellValue = String.valueOf(cell.getBooleanCellValue());  
                    break;  
                    //判断单元格是公式格式，需要做一种特殊处理来得到相应的值  
                case Cell.CELL_TYPE_FORMULA:{  
                    try{  
                        cellValue = String.valueOf(cell.getNumericCellValue());  
                    }catch(IllegalStateException e){  
                        cellValue = String.valueOf(cell.getRichStringCellValue());  
                    }  
                      
                }  
                    break;  
                case Cell.CELL_TYPE_BLANK:  
                    cellValue = "";  
                    break;  
                case Cell.CELL_TYPE_ERROR:  
                    cellValue = "";  
                    break;  
                default:  
                    cellValue = cell.toString().trim();  
                    break;  
            }  
        }  
        return cellValue.trim();  
    }  
    
	
}
