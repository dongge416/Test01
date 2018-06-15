

public class OrderModel {

	//创建时间	
	private String CreateTime;
	//点击时间	
	private String ClickTime;
	//商品信息
	private String ProductInformation;
	//商品ID
	private String ProductId;
	//掌柜旺旺
	private String Wangwang;
	//所属店铺
	private String ShopName;
	//商品数
	private int ProductCounts;
	//商品单价
	private float ProductPrice;
	//----
	
	//订单状态
	private String OrderState;
	//订单类型
	private String OrderType;
	//收入比率
	private String IncomeRace;
	//分成比率
	private String ShareRace;
	//付款金额
	private float Pay;
	//效果预估
	private float EffecteStimate;
	//结算金额
	private float SettlementAmount;
	//预估收入
	private float IncomeEstimate;
	//结算时间
	private String SettlementTime;
	//佣金比率
	private String CommissionRace;
	//佣金金额
	private float Commission;
	//补贴比率
	private String SubsidyRace;
	//----------
	//补贴金额
	private float Subsidy;
	//补贴类型
	private String SubsidyType;
	//成交平台	
	private String DealPlatform;
	//第三方服务来源	
	private String ThirdParty;
	//订单编号	
	private String OrderNo;
	//类目名称
	private String CategoryName;
	//来源媒体ID	
	private int MediaId;
	//来源媒体名称	
	private String MediaName;
	//广告位ID	
	private int AdvertisementId;
	//广告位名称
	private String AdvertisementName;
	

	
	public OrderModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getCreateTime() {
		return CreateTime;
	}


	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}


	public String getClickTime() {
		return ClickTime;
	}


	public void setClickTime(String clickTime) {
		ClickTime = clickTime;
	}


	public String getProductInformation() {
		return ProductInformation;
	}


	public void setProductInformation(String productInformation) {
		ProductInformation = productInformation;
	}


	public String getProductId() {
		return ProductId;
	}


	public void setProductId(String productId) {
		ProductId = productId;
	}


	public String getWangwang() {
		return Wangwang;
	}


	public void setWangwang(String wangwang) {
		Wangwang = wangwang;
	}


	public String getShopName() {
		return ShopName;
	}


	public void setShopName(String shopName) {
		ShopName = shopName;
	}


	public int getProductCounts() {
		return ProductCounts;
	}


	public void setProductCounts(int productCounts) {
		ProductCounts = productCounts;
	}


	public float getProductPrice() {
		return ProductPrice;
	}


	public void setProductPrice(float productPrice) {
		ProductPrice = productPrice;
	}


	public String getOrderState() {
		return OrderState;
	}


	public void setOrderState(String orderState) {
		OrderState = orderState;
	}


	public String getOrderType() {
		return OrderType;
	}


	public void setOrderType(String orderType) {
		OrderType = orderType;
	}


	public String getIncomeRace() {
		return IncomeRace;
	}


	public void setIncomeRace(String incomeRace) {
		IncomeRace = incomeRace;
	}


	public String getShareRace() {
		return ShareRace;
	}


	public void setShareRace(String shareRace) {
		ShareRace = shareRace;
	}


	public float getPay() {
		return Pay;
	}


	public void setPay(float pay) {
		Pay = pay;
	}


	public float getEffecteStimate() {
		return EffecteStimate;
	}


	public void setEffecteStimate(float effecteStimate) {
		EffecteStimate = effecteStimate;
	}


	public float getSettlementAmount() {
		return SettlementAmount;
	}


	public void setSettlementAmount(float settlementAmount) {
		SettlementAmount = settlementAmount;
	}


	public float getIncomeEstimate() {
		return IncomeEstimate;
	}


	public void setIncomeEstimate(float incomeEstimate) {
		IncomeEstimate = incomeEstimate;
	}


	public String getSettlementTime() {
		return SettlementTime;
	}


	public void setSettlementTime(String settlementTime) {
		SettlementTime = settlementTime;
	}


	public String getCommissionRace() {
		return CommissionRace;
	}


	public void setCommissionRace(String commissionRace) {
		CommissionRace = commissionRace;
	}


	public float getCommission() {
		return Commission;
	}


	public void setCommission(float commission) {
		Commission = commission;
	}


	public String getSubsidyRace() {
		return SubsidyRace;
	}


	public void setSubsidyRace(String subsidyRace) {
		SubsidyRace = subsidyRace;
	}


	public float getSubsidy() {
		return Subsidy;
	}


	public void setSubsidy(float subsidy) {
		Subsidy = subsidy;
	}


	public String getSubsidyType() {
		return SubsidyType;
	}


	public void setSubsidyType(String subsidyType) {
		SubsidyType = subsidyType;
	}


	public String getDealPlatform() {
		return DealPlatform;
	}


	public void setDealPlatform(String dealPlatform) {
		DealPlatform = dealPlatform;
	}


	public String getThirdParty() {
		return ThirdParty;
	}


	public void setThirdParty(String thirdParty) {
		ThirdParty = thirdParty;
	}


	public String getOrderNo() {
		return OrderNo;
	}


	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}


	public String getCategoryName() {
		return CategoryName;
	}


	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}


	public int getMediaId() {
		return MediaId;
	}


	public void setMediaId(int mediaId) {
		MediaId = mediaId;
	}


	public String getMediaName() {
		return MediaName;
	}


	public void setMediaName(String mediaName) {
		MediaName = mediaName;
	}


	public int getAdvertisementId() {
		return AdvertisementId;
	}


	public void setAdvertisementId(int advertisementId) {
		AdvertisementId = advertisementId;
	}


	public String getAdvertisementName() {
		return AdvertisementName;
	}


	public void setAdvertisementName(String advertisementName) {
		AdvertisementName = advertisementName;
	}


	@Override
	public String toString() {
		return "OrderModel [CreateTime=" + CreateTime + ", ClickTime=" + ClickTime + ", ProductInformation="
				+ ProductInformation + ", ProductId=" + ProductId + ", Wangwang=" + Wangwang + ", ShopName=" + ShopName
				+ ", ProductCounts=" + ProductCounts + ", ProductPrice=" + ProductPrice + ", OrderState=" + OrderState
				+ ", OrderType=" + OrderType + ", IncomeRace=" + IncomeRace + ", ShareRace=" + ShareRace + ", Pay="
				+ Pay + ", EffecteStimate=" + EffecteStimate + ", SettlementAmount=" + SettlementAmount
				+ ", IncomeEstimate=" + IncomeEstimate + ", SettlementTime=" + SettlementTime + ", CommissionRace="
				+ CommissionRace + ", Commission=" + Commission + ", SubsidyRace=" + SubsidyRace + ", Subsidy="
				+ Subsidy + ", SubsidyType=" + SubsidyType + ", DealPlatform=" + DealPlatform + ", ThirdParty="
				+ ThirdParty + ", OrderNo=" + OrderNo + ", CategoryName=" + CategoryName + ", MediaId=" + MediaId
				+ ", MediaName=" + MediaName + ", AdvertisementId=" + AdvertisementId + ", AdvertisementName="
				+ AdvertisementName + "]";
	}

	
	
}
