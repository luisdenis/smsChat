package gt.tigo.smsservice;

public class Promotion {
    private String promotionId;
    private String name;
    private String subscribeCode;
    private String subscribeShortnumber;
    private float promotionPrice;
    private String description;
 
    public Promotion() {
		// TODO Auto-generated constructor stub
    	promotionId = name = subscribeCode = subscribeShortnumber = description = "";
    	promotionPrice = 0;
	}
    
    public Promotion(String promotionId, String name, String subscribeCode, String subscribeShortnumber, float promotionPrice, String description) {
    	this.promotionId = promotionId;
    	this.name = name;
    	this.subscribeCode = subscribeCode;
    	this.subscribeShortnumber = subscribeShortnumber;
    	this.promotionPrice = promotionPrice;
    	this.description = description;
    }
    
    public String getDescription() {
		return description;
	}
    
    public void setDescription(String description) {
		this.description = description;
	}
    
    public String getName() {
		return name;
	}
    
    public void setName(String name) {
		this.name = name;
	}
    
    public String getPromotionId() {
		return promotionId;
	}
    
    public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}
    
    public float getPromotionPrice() {
		return promotionPrice;
	}
    
    public void setPromotionPrice(float promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
    
    public String getSubscribeCode() {
		return subscribeCode;
	}
    
    public void setSubscribeCode(String subscribeCode) {
		this.subscribeCode = subscribeCode;
	}
    
    public String getSubscribeShortnumber() {
		return subscribeShortnumber;
	}
    
    public void setSubscribeShortnumber(String subscribeShortnumber) {
		this.subscribeShortnumber = subscribeShortnumber;
	}
    
   
    
}
