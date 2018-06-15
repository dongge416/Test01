import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlUtil {

	//数据库路径
	public static String path = "";
	public Connection conn;
	
	public SqlUtil() {
		
	}
	
	public SqlUtil(String path) {
		this.path = path;
	}
	
	public  boolean createConnection() {
		boolean flag = false;
		try {
			Class.forName("org.sqlite.JDBC");
			this.conn = DriverManager.getConnection("jdbc:sqlite:/"+path);
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return flag;
    }
	
	public boolean closeConnection() {
		boolean flag = false;
		try {
			this.conn.close();
			flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	/*
	 * 创建数据库
	 */
	public boolean creatDB() {
		boolean flag = false;
		try
        {
            // 连接SQLite的JDBC
            Class.forName("org.sqlite.JDBC");
            System.out.println("---》"+"jdbc:sqlite://"+path);
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/"+path);
            Statement stat = conn.createStatement();
            String dropSql = "DROP TABLE if exists tb_orders";
            stat.executeUpdate(dropSql);
            String creatSql="create table tb_orders (\n" + 
            		"CreateTime varchar(50),\n" + 
            		"ClickTime varchar(50),\n" + 
            		"ProductInformation varchar(50),\n" + 
            		"ProductId varchar(50) ,\n" + 
            		"Wangwang varchar(50),\n" + 
            		"ShopName  varchar(50),\n" + 
            		"ProductCounts integer,\n" + 
            		"ProductPrice float,\n" + 
            		"OrderState varchar(50),\n" + 
            		"OrderType varchar(50),\n" + 
            		"IncomeRace float,\n" + 
            		"ShareRace float,\n" + 
            		"Pay float,\n" + 
            		"EffecteStimate float,\n" + 
            		"SettlementAmount float,\n" + 
            		"IncomeEstimate float,\n" + 
            		"SettlementTime varchar(50),\n" + 
            		"CommissionRace float,\n" + 
            		"Commission float,\n" + 
            		"SubsidyRace float,\n" + 
            		"Subsidy float,\n" + 
            		"SubsidyType varchar(50),\n" + 
            		"DealPlatform varchar(50),\n" + 
            		"ThirdParty varchar(50),\n" + 
            		"OrderNo varchar(50),\n" + 
            		"CategoryName varchar(50),\n" + 
            		"MediaId integer,\n" + 
            		"MediaName varchar(50),\n" + 
            		"AdvertisementId integer,\n" + 
            		"AdvertisementName varchar(50)\n" + 
            		"\n" + 
            		")";
            stat.executeUpdate(creatSql); // 插入数据
            
            conn.close(); // 结束数据库的连接
            flag = true;
        }
        catch (Exception e)
        {
        		
            e.printStackTrace();
        }
		return flag;
	}
	
	
	
	public boolean insertOrderList(ArrayList<OrderModel> orders)  {
		boolean flag = false;
		try {
		
			Class.forName("org.sqlite.JDBC");
	        System.out.println("---》"+"jdbc:sqlite://"+path);
	        Connection conn = DriverManager.getConnection("jdbc:sqlite:/"+path);
	        Statement stat = conn.createStatement();
			for (int i = 0; i < orders.size(); i++) {
				OrderModel orderModel = orders.get(i);
				  String createTime = orderModel.getCreateTime();
	      		String clickTime = orderModel.getClickTime();
	      		String productInformation = orderModel.getProductInformation();
	      		String productId = orderModel.getProductId();
	      		String wangwang = orderModel.getWangwang();
	      		String shopName = orderModel.getShopName();
	      		int productCounts = orderModel.getProductCounts();
	      		float productPrice = orderModel.getProductPrice();
	      		String orderState = orderModel.getOrderState();
	      		String orderType = orderModel.getOrderType();
	      		String incomeRace = orderModel.getIncomeRace();
	      		String shareRace = orderModel.getShareRace();
	      		float pay = orderModel.getPay();
	      		float effecteStimate = orderModel.getEffecteStimate();
	      		float settlementAmount = orderModel.getSettlementAmount();
	      		float incomeEstimate = orderModel.getIncomeEstimate();
	      		String settlementTime = orderModel.getSettlementTime();
	      		String commissionRace = orderModel.getCommissionRace();
	      		float commission = orderModel.getCommission();
	      		String subsidyRace = orderModel.getSubsidyRace();
	      		float subsidy = orderModel.getSubsidy();
	      		String subsidyType = orderModel.getSubsidyType();
	      		String dealPlatform = orderModel.getDealPlatform();
	      		String thirdParty = orderModel.getThirdParty();
	      		String orderNo = orderModel.getOrderNo();
	      		String categoryName = orderModel.getCategoryName();
	      		int mediaId = orderModel.getMediaId();
	      		String mediaName = orderModel.getMediaName();
	      		int advertisementId = orderModel.getAdvertisementId();
	      		String advertisementName = orderModel.getAdvertisementName();
//		            System.out.println(orderModel.toString());
		            String insertSql = "insert into tb_orders (CreateTime,ClickTime,ProductInformation,ProductId,Wangwang,ShopName,ProductCounts,ProductPrice,"
		        			+ "OrderState,OrderType,IncomeRace,ShareRace,Pay,EffecteStimate,SettlementAmount,IncomeEstimate,SettlementTime,"
		        			+"CommissionRace,Commission,SubsidyRace,Subsidy,SubsidyType,DealPlatform,ThirdParty,OrderNo,CategoryName,MediaId,MediaName,"
		        			+"AdvertisementId,AdvertisementName"
		        			+ ") values ('"+createTime+"','"+clickTime+"','"+productInformation+"','"+productId+"','"+wangwang+"','"+shopName+"','"+productCounts+"',"
		        					+ "'"+productPrice+"','"+orderState+"','"+orderType+"','"+incomeRace+"','"+shareRace+"', "+pay+", "+effecteStimate+", "+settlementAmount+","
		        							+ ""+incomeEstimate+",'"+settlementTime+"', '"+commissionRace+"', "+commission+", '"+subsidyRace+"',"+subsidy+",'"+subsidyType+"',"
		        									+ "'"+dealPlatform+"', '"+thirdParty+"', '"+orderNo+"', '"+categoryName+"', "+mediaId+", '"+mediaName+"', "+advertisementId+","
		        											+ "'"+advertisementName+"')";
		            System.out.println("--->"+insertSql);
		            System.out.println(stat.executeUpdate(insertSql)); // 插入数据
//		            System.out.println("第"+i+"次");
		}
			 conn.close(); // 结束数据库的连接
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	public boolean insertData(OrderModel orderModel) {
		boolean flag = false;
		
		 try
	        {
	            // 连接SQLite的JDBC
	            Class.forName("org.sqlite.JDBC");
	            System.out.println("---》"+"jdbc:sqlite://"+path);
	            Connection conn = DriverManager.getConnection("jdbc:sqlite:/"+path);
	            Statement stat = conn.createStatement();
	            
	         
	            String createTime = orderModel.getCreateTime();
        		String clickTime = orderModel.getClickTime();
        		String productInformation = orderModel.getProductInformation();
        		String productId = orderModel.getProductId();
        		String wangwang = orderModel.getWangwang();
        		String shopName = orderModel.getShopName();
        		int productCounts = orderModel.getProductCounts();
        		float productPrice = orderModel.getProductPrice();
        		String orderState = orderModel.getOrderState();
        		String orderType = orderModel.getOrderType();
        		String incomeRace = orderModel.getIncomeRace();
        		String shareRace = orderModel.getShareRace();
        		float pay = orderModel.getPay();
        		float effecteStimate = orderModel.getEffecteStimate();
        		float settlementAmount = orderModel.getSettlementAmount();
        		float incomeEstimate = orderModel.getIncomeEstimate();
        		String settlementTime = orderModel.getSettlementTime();
        		String commissionRace = orderModel.getCommissionRace();
        		float commission = orderModel.getCommission();
        		String subsidyRace = orderModel.getSubsidyRace();
        		float subsidy = orderModel.getSubsidy();
        		String subsidyType = orderModel.getSubsidyType();
        		String dealPlatform = orderModel.getDealPlatform();
        		String thirdParty = orderModel.getThirdParty();
        		String orderNo = orderModel.getOrderNo();
        		String categoryName = orderModel.getCategoryName();
        		int mediaId = orderModel.getMediaId();
        		String mediaName = orderModel.getMediaName();
        		int advertisementId = orderModel.getAdvertisementId();
        		String advertisementName = orderModel.getAdvertisementName();
	            
	            String insertSql = "insert into tb_orders (CreateTime,ClickTime,ProductInformation,ProductId,Wangwang,ShopName,ProductCounts,ProductPrice,"
	        			+ "OrderState,OrderType,IncomeRace,ShareRace,Pay,EffecteStimate,SettlementAmount,IncomeEstimate,SettlementTime,"
	        			+"CommissionRace,Commission,SubsidyRace,Subsidy,SubsidyType,DealPlatform,ThirdParty,OrderNo,CategoryName,MediaId,MediaName,"
	        			+"AdvertisementId,AdvertisementName"
	        			+ ") values ('"+createTime+"','"+clickTime+"','"+productInformation+"',"+productId+",'"+wangwang+"','"+shopName+"','"+productCounts+"',"
	        					+ "'"+productPrice+"','"+orderState+"','"+orderType+"','"+incomeRace+"','"+shareRace+"', "+pay+", "+effecteStimate+", "+settlementAmount+","
	        							+ ""+incomeEstimate+",'"+settlementTime+"', "+commissionRace+", "+commission+", "+subsidyRace+","+subsidy+",'"+subsidyType+"',"
	        									+ "'"+dealPlatform+"', '"+thirdParty+"', "+orderNo+", '"+categoryName+"', "+mediaId+", '"+mediaName+"', "+advertisementId+","
	        											+ "'"+advertisementName+"')";
	            stat.executeUpdate(insertSql); // 插入数据
	            conn.close(); // 结束数据库的连接
	            flag = true;
	        }
	        catch (Exception e)
	        {
	        		
	            e.printStackTrace();
	        }
		
		return flag;
	}
	
	
	
	
	public List<OrderModel> queryData(String targetOrderNo) {
		 List<OrderModel> list = new ArrayList<>();
		 //List<List<OrderModel>> lists = new ArrayList<>();
		 try
	        {
	            // 连接SQLite的JDBC
	            Class.forName("org.sqlite.JDBC");

	            // 建立一个数据库名zieckey.db的连接，如果不存在就在当前目录下创建之
	            Connection conn = DriverManager.getConnection("jdbc:sqlite:/"+path);
	            Statement stat = conn.createStatement();


	            ResultSet rs = stat.executeQuery("select * from tb_orders where OrderNo = "+targetOrderNo+""); // 查询数据
	           
	            while (rs.next())
	            { 
	            		OrderModel order = new OrderModel();
	            		String createTime = rs.getString("CreateTime");
//	            		System.out.println("createTime:"+createTime);
	            		order.setCreateTime(createTime);
	            		String clickTime = rs.getString("ClickTime");
	            		order.setClickTime(clickTime);
	            		String productInformation = rs.getString("ProductInformation");
	            		order.setProductInformation(productInformation);
	            		String productId = rs.getString("ProductId");
	            		order.setProductId(productId);
	            		String wangwang = rs.getString("Wangwang");
	            		order.setWangwang(wangwang);
	            		String shopName = rs.getString("ShopName");
	            		order.setShopName(shopName);
	            		int productCounts = rs.getInt("ProductCounts");
	            		order.setProductCounts(productCounts);
	            		float productPrice = rs.getFloat("ProductPrice");
	            		order.setProductPrice(productPrice);
	            		String orderState = rs.getString("OrderState");
	            		order.setOrderState(orderState);
	            		String orderType = rs.getString("OrderType");
	            		order.setOrderType(orderType);
	            		String incomeRace = rs.getString("IncomeRace");
	            		order.setIncomeRace(incomeRace);
	            		String shareRace = rs.getString("ShareRace");
	            		order.setShareRace(shareRace);
	            		float pay = rs.getFloat("Pay");
	            		order.setPay(pay);
	            		float effecteStimate = rs.getFloat("EffecteStimate");
	            		order.setEffecteStimate(effecteStimate);
	            		float settlementAmount = rs.getFloat("SettlementAmount");
	            		order.setSettlementAmount(settlementAmount);
	            		float incomeEstimate = rs.getFloat("IncomeEstimate");
	            		order.setIncomeEstimate(incomeEstimate);
	            		String settlementTime = rs.getString("SettlementTime");
	            		order.setSettlementTime(settlementTime);
	            		String commissionRace = rs.getString("CommissionRace");
	            		order.setCommissionRace(commissionRace);
	            		float commission = rs.getFloat("Commission");
	            		order.setCommission(commission);
	            		float subsidyRace = rs.getFloat("SubsidyRace");
	            		order.setSubsidy(subsidyRace);
	            		float subsidy = rs.getFloat("Subsidy");
	            		order.setSubsidy(subsidy);
	            		String subsidyType = rs.getString("SubsidyType");
	            		order.setSubsidyType(subsidyType);
	            		String dealPlatform = rs.getString("DealPlatform");
	            		order.setDealPlatform(dealPlatform);
	            		String thirdParty = rs.getString("ThirdParty");
	            		order.setThirdParty(thirdParty);
	            		String orderNo = rs.getString("OrderNo");
	            		order.setOrderNo(orderNo);
	            		String categoryName = rs.getString("CategoryName");
	            		order.setCategoryName(categoryName);
	            		int mediaId = rs.getInt("MediaId");
	            		order.setMediaId(mediaId);
	            		String mediaName = rs.getString("MediaName");
	            		order.setMediaName(mediaName);
	            		int advertisementId = rs.getInt("AdvertisementId");
	            		order.setAdvertisementId(advertisementId);
	            		String advertisementName = rs.getString("AdvertisementName");
	            		order.setAdvertisementName(advertisementName);
	            		list.add(order);
	            		
	            		
	            		
	            		
	            }

	            rs.close();
	            conn.close(); // 结束数据库的连接
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return list;
	}
	
	public List<OrderModel> queryDatas(String targetOrderNo) {
		 List<OrderModel> list = new ArrayList<>();
		 //List<List<OrderModel>> lists = new ArrayList<>();
		 try
	        {
			 	if(this.conn == null) {
			 		return list;
			 	}
	            Statement stat = this.conn.createStatement();


	            ResultSet rs = stat.executeQuery("select * from tb_orders where OrderNo = '"+targetOrderNo+"'"); // 查询数据
	           
	            while (rs.next())
	            { 
	            		OrderModel order = new OrderModel();
	            		String createTime = rs.getString("CreateTime");
//	            		System.out.println("createTime:"+createTime);
	            		order.setCreateTime(createTime);
	            		String clickTime = rs.getString("ClickTime");
	            		order.setClickTime(clickTime);
	            		String productInformation = rs.getString("ProductInformation");
	            		order.setProductInformation(productInformation);
	            		String productId = rs.getString("ProductId");
	            		order.setProductId(productId);
	            		String wangwang = rs.getString("Wangwang");
	            		order.setWangwang(wangwang);
	            		String shopName = rs.getString("ShopName");
	            		order.setShopName(shopName);
	            		int productCounts = rs.getInt("ProductCounts");
	            		order.setProductCounts(productCounts);
	            		float productPrice = rs.getFloat("ProductPrice");
	            		order.setProductPrice(productPrice);
	            		String orderState = rs.getString("OrderState");
	            		order.setOrderState(orderState);
	            		String orderType = rs.getString("OrderType");
	            		order.setOrderType(orderType);
	            		String incomeRace = rs.getString("IncomeRace");
	            		order.setIncomeRace(incomeRace);
	            		String shareRace = rs.getString("ShareRace");
	            		order.setShareRace(shareRace);
	            		float pay = rs.getFloat("Pay");
	            		order.setPay(pay);
	            		float effecteStimate = rs.getFloat("EffecteStimate");
	            		order.setEffecteStimate(effecteStimate);
	            		float settlementAmount = rs.getFloat("SettlementAmount");
	            		order.setSettlementAmount(settlementAmount);
	            		float incomeEstimate = rs.getFloat("IncomeEstimate");
	            		order.setIncomeEstimate(incomeEstimate);
	            		String settlementTime = rs.getString("SettlementTime");
	            		order.setSettlementTime(settlementTime);
	            		String commissionRace = rs.getString("CommissionRace");
	            		order.setCommissionRace(commissionRace);
	            		float commission = rs.getFloat("Commission");
	            		order.setCommission(commission);
	            		float subsidyRace = rs.getFloat("SubsidyRace");
	            		order.setSubsidy(subsidyRace);
	            		float subsidy = rs.getFloat("Subsidy");
	            		order.setSubsidy(subsidy);
	            		String subsidyType = rs.getString("SubsidyType");
	            		order.setSubsidyType(subsidyType);
	            		String dealPlatform = rs.getString("DealPlatform");
	            		order.setDealPlatform(dealPlatform);
	            		String thirdParty = rs.getString("ThirdParty");
	            		order.setThirdParty(thirdParty);
	            		String orderNo = rs.getString("OrderNo");
	            		order.setOrderNo(orderNo);
	            		String categoryName = rs.getString("CategoryName");
	            		order.setCategoryName(categoryName);
	            		int mediaId = rs.getInt("MediaId");
	            		order.setMediaId(mediaId);
	            		String mediaName = rs.getString("MediaName");
	            		order.setMediaName(mediaName);
	            		int advertisementId = rs.getInt("AdvertisementId");
	            		order.setAdvertisementId(advertisementId);
	            		String advertisementName = rs.getString("AdvertisementName");
	            		order.setAdvertisementName(advertisementName);
	            		list.add(order);
	            		
	            		
	            		
	            		
	            }

	            rs.close();
//	            conn.close(); // 结束数据库的连接
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return list;
	}
	
	
}
