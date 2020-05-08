package com.cds.rumahpasta.Models;

public class Food {
   final private int NO_IMAGE = 1;
   private String foodName;
   public void setType(String type){
       this.type = type;
   }
   private String type;
   private int mImageResource = NO_IMAGE;
   private int foodPrice;

   public Food(String foodName, int mImageResource, int foodPrice, String type){
       this.foodName = foodName;
       this.mImageResource = mImageResource;
       this.foodPrice = foodPrice;
       this.type = type;
   }

    public int getNO_IMAGE() {
        return NO_IMAGE;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getType() {
        return type;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public void setmImageResource(int mImageResource) {
        this.mImageResource = mImageResource;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }
}
