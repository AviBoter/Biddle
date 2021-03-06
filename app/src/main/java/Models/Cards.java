package Models;

import java.util.Date;

public class Cards {

    private String productId;
    private String productName;
    private String currentPrice;
    private String endingDate;
    private Date dateType;
    private String productCategory;
    private String imgPath;
    private String CurCostumerID;

    public Cards(String productName, String currentPrice, String endingDate, String id, Date date, String category, String imgPath , String CurCostumerID) {
        this.productName = productName;
        this.currentPrice = currentPrice;
        this.endingDate = endingDate;
        this.productId = id;
        this.dateType = date;
        this.productCategory = category;
        this.imgPath = imgPath;
        this.CurCostumerID = CurCostumerID;
    }

    public Cards(){}

    public String getCurCostumerID() {
        return CurCostumerID;
    }

    public void setCurCostumerID(String CurCostumerID) {
        this.CurCostumerID = CurCostumerID;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getProductId() {return productId; }

    public void setProductId(String productId) { this.productId = productId; }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public Date getDateType() {
        return dateType;
    }

    public void setDateType(Date dateType) {
        this.dateType = dateType;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}