package com.zhou.grad.entity;

public class Food {
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
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
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
        this.name = name == null ? null : name.trim();
    }

    public String getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(String isFeatured) {
        this.isFeatured = isFeatured == null ? null : isFeatured.trim();
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
        this.soldOut = soldOut == null ? null : soldOut.trim();
    }

    public String getRecentPopularity() {
        return recentPopularity;
    }

    public void setRecentPopularity(String recentPopularity) {
        this.recentPopularity = recentPopularity == null ? null : recentPopularity.trim();
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
        this.tips = tips == null ? null : tips.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "Food [foodId=" + foodId + ", imageUrl=" + imageUrl + ", originalPrice=" + originalPrice + ", name="
                + name + ", isFeatured=" + isFeatured + ", packingFee=" + packingFee + ", recentRating=" + recentRating
                + ", price=" + price + ", soldOut=" + soldOut + ", recentPopularity=" + recentPopularity + ", stock="
                + stock + ", minPurchase=" + minPurchase + ", monthSales=" + monthSales + ", ratinigCount="
                + ratinigCount + ", tips=" + tips + ", remark=" + remark + "]";
    }
}