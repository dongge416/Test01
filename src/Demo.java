import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class Demo {

	public static LogUtil  logs= new LogUtil("/Users/dongge416/Downloads/log.txt");
	
	public static void main(String[] args) {
		
		ArrayList<String> list = new ArrayList<>();
		readFileByChars("/Users/dongge416/Downloads/网页格式/content/蓝.html",list);
		numberFilter(list);
	}

	private static void numberFilter(ArrayList<String> list) {
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
		
		
		for (int j = 0; j < numberList.size(); j++) {
			if(numberList.get(j).length()>10) {
//				System.out.println(j+":"+numberList.get(j));
				LogUtil.infoPrint("输入信息:+++++++++");
			}
			
		}
	}

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

}
