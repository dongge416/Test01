import java.util.List;

public class SqliteTest {

	public static void main(String[] args) {
		SqlUtil sqlUtil = new SqlUtil("/Users/dongge416/Downloads/db_alimama.db3");
		sqlUtil.creatDB();
		OrderModel order = new OrderModel();
		order.setCreateTime("2018-11-11 10:10:10");
		order.setProductInformation("【oh my lady系列】【12.12零点开抢】226元包邮 MG小象毛呢外套");
		order.setProductId("1233");
		order.setIncomeRace("3.00 %");
		order.setProductPrice(52.10f);
		order.setSettlementTime(null);
		sqlUtil.insertData(order);
	
//		List<OrderModel> list = sqlUtil.queryData("117261883743707986");
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).toString());
//		}
	}
	
}
