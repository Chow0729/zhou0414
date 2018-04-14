package com.zhou.grad.wechat.model;

public class FoodDetailModel {
    private Integer foodId;

    private String imageUrl;

    private Double originalPrice;

    private String name;

    private String isFeatured;

    private Double packingFee;

    private Double recentRating;

    private Double price;

    private String soldOut;

    private String recentPopularity;

    private Integer stock;

    private Integer minPurchase;

    private Integer monthSales;

    private Integer ratinigCount;

    private String tips;

    private String remark;
    
    //增加了种类的id
    private Integer categoryId;
    
    //增加了种类的拼音名
    private String pinyinName;
    
    //增加了种类的名称
    private String categoryName;

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(String isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Double getPackingFee() {
        return packingFee;
    }

    public void setPackingFee(Double packingFee) {
        this.packingFee = packingFee;
    }

    public Double getRecentRating() {
        return recentRating;
    }

    public void setRecentRating(Double recentRating) {
        this.recentRating = recentRating;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSoldOut() {
        return soldOut;
    }

    public void setSoldOut(String soldOut) {
        this.soldOut = soldOut;
    }

    public String getRecentPopularity() {
        return recentPopularity;
    }

    public void setRecentPopularity(String recentPopularity) {
        this.recentPopularity = recentPopularity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getMinPurchase() {
        return minPurchase;
    }

    public void setMinPurchase(Integer minPurchase) {
        this.minPurchase = minPurchase;
    }

    public Integer getMonthSales() {
        return monthSales;
    }

    public void setMonthSales(Integer monthSales) {
        this.monthSales = monthSales;
    }

    public Integer getRatinigCount() {
        return ratinigCount;
    }

    public void setRatinigCount(Integer ratinigCount) {
        this.ratinigCount = ratinigCount;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getPinyinName() {
        return pinyinName;
    }

    public void setPinyinName(String pinyinName) {
        this.pinyinName = pinyinName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "FoodDetailModel [foodId=" + foodId + ", imageUrl=" + imageUrl + ", originalPrice=" + originalPrice
                + ", name=" + name + ", isFeatured=" + isFeatured + ", packingFee=" + packingFee + ", recentRating="
                + recentRating + ", price=" + price + ", soldOut=" + soldOut + ", recentPopularity=" + recentPopularity
                + ", stock=" + stock + ", minPurchase=" + minPurchase + ", monthSales=" + monthSales + ", ratinigCount="
                + ratinigCount + ", tips=" + tips + ", remark=" + remark + ", categoryId=" + categoryId
                + ", pinyinName=" + pinyinName + ", categoryName=" + categoryName + "]";
    }
}
