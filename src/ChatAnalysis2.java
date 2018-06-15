import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ChatAnalysis2 {

	public static void main(String[] args) throws Exception {
//		File input = new File("/Users/dongge416/Downloads/网页格式/content/陈潇潇.html");
//
//		System.out.println(input.getName().trim());
//
//		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

		// Elements chatItemContents = doc.getElementsByClass("chatItem you");
		//
		// int len = chatItemContents.size();
		// System.out.println(len);
		//
		// for (int i = len; i > 0; i--) {
		// String cloudfriend =
		// chatItemContents.get(len-1).children().select("div[cloudfriend]").attr("cloudfriend").trim();
		// String cloudid =
		// chatItemContents.get(len-1).children().select("div[cloudid]").attr("cloudid").trim();
		// String timeText = "";
		// timeText =
		// timeText+chatItemContents.get(i-1).child(0).select("div.time").text();
		//
		// System.out.println(i+":cloudfriend:"+cloudfriend+",cloudid:"+cloudid+",timeText:"+timeText);
		// if(!timeText.equals("")) {
		// break;
		// }
		// }
		// System.out.println(chatItemContents.get(len-1).children().select("div[cloudfriend]").attr("cloudfriend"));
		// System.out.println(chatItemContents.get(len-2).children().select("div[cloudfriend]").attr("cloudfriend"));

		File dir = new File("/Users/dongge416/Downloads/网页格式/content/");
		File[] files = dir.listFiles();
		Workbook wb = new HSSFWorkbook();
		// 创建工作表
		Sheet sheet = wb.createSheet("测试Excel");
		int rowIndex = 0;
		Row row = sheet.createRow(rowIndex); // 起始从0开始
		Cell nameCell = row.createCell(0);
		nameCell.setCellValue("微信昵称");
		Cell cloudidCell = row.createCell(1);
		cloudidCell.setCellValue("微信ID");
		Cell cloudfriendCell = row.createCell(2);
		cloudfriendCell.setCellValue("微信号");
		Cell chatItemCountCell = row.createCell(3);
		chatItemCountCell.setCellValue("聊天次数");
		Cell textTimeCell = row.createCell(4);
		textTimeCell.setCellValue("最后聊天时间");
		rowIndex = rowIndex + 1;
		
		System.out.println("文件数:"+files.length);
		
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().trim().contains(".html")) {
				String wechatName = files[i].getName().replace(".html", "");
				Document htmlDoc = Jsoup.parse(files[i], "UTF-8", "http://example.com/");
				Elements chatItemContents = htmlDoc.getElementsByClass("chatItem you");
				int len = chatItemContents.size();
//				System.out.println(chatItemContents.text());
				PersonChatModel chatModel = null;
				
				for (int j = len; j > 0; j--) {
					String cloudfriend = "";
					if (cloudfriend.equals("")) {
						cloudfriend = chatItemContents.get(len - 1).children().select("div[cloudfriend]")
								.attr("cloudfriend").trim();
						
					}
					String cloudid = "";
					if (cloudid.equals("")) {
						cloudid = chatItemContents.get(len - 1).children().select("div[cloudid]").attr("cloudid")
								.trim();
					}

					String timeText = "";
					if (timeText.equals("")) {
						timeText = timeText + chatItemContents.get(j - 1).child(0).select("div.time").text().trim();
					}

					if (!timeText.equals("") && !cloudid.equals("") && !cloudfriend.equals("")) {
						chatModel = new PersonChatModel();
						chatModel.setChatItemCount(len + "");
						chatModel.setCloudfriend(cloudfriend);
						chatModel.setCloudid(cloudid);
						chatModel.setTimeText(timeText);
						chatModel.setWechatName(wechatName);
						System.out.println("生成chatModel\n"+chatModel.toString());
						
						
						
							Row tempRow = sheet.createRow(rowIndex); // 起始从0开始
							Cell wechatNameCell = tempRow.createCell(0);
							wechatNameCell.setCellValue(chatModel.getWechatName());
							Cell tempcloudidCell = tempRow.createCell(1);
							tempcloudidCell.setCellValue(chatModel.getCloudid());
							Cell tempcloudfriendCell = tempRow.createCell(2);
							tempcloudfriendCell.setCellValue(chatModel.getCloudfriend());
							Cell tempchatItemCountCell = tempRow.createCell(3);
							tempchatItemCountCell.setCellValue(chatModel.getChatItemCount());
							Cell temptextTimeCell = tempRow.createCell(4);
							temptextTimeCell.setCellValue(chatModel.getTimeText());
							rowIndex = rowIndex + 1;
							System.out.println("生成excel行");
//						}
						
						
						break;
					}
					

					//筛选出没有买过都人
					
//					String buyContent = chatItemContents.get(len - 1).children().text();
					
//					System.out.println(buyContent);
					
					
				}

			}

		}

		
		FileOutputStream fileOut;
		fileOut = new FileOutputStream("/Users/dongge416/Downloads/输出聊天数据.xls");
		wb.write(fileOut);
		fileOut.close();
		System.out.println("输出成功");
	}

}

class PersonChatModel {
	private String wechatName;
	private String cloudfriend;
	private String cloudid;
	private String timeText;
	private String chatItemCount;

	public String getWechatName() {
		return wechatName;
	}

	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}

	public PersonChatModel() {

	}

	public String getCloudfriend() {
		return cloudfriend;
	}

	public void setCloudfriend(String cloudfriend) {
		this.cloudfriend = cloudfriend;
	}

	public String getCloudid() {
		return cloudid;
	}

	public void setCloudid(String cloudid) {
		this.cloudid = cloudid;
	}

	public String getTimeText() {
		return timeText;
	}

	public void setTimeText(String timeText) {
		this.timeText = timeText;
	}

	public String getChatItemCount() {
		return chatItemCount;
	}

	public void setChatItemCount(String chatItemCount) {
		this.chatItemCount = chatItemCount;
	}

	@Override
	public String toString() {
		return "ChatModel [cloudfriend=" + cloudfriend + ", cloudid=" + cloudid + ", timeText=" + timeText
				+ ", chatItemCount=" + chatItemCount + "]";
	}

	
	
}