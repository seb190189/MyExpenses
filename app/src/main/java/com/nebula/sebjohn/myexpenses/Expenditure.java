package com.nebula.sebjohn.myexpenses;

/**
 * Created by Sebastian John on 3/22/2017.
 */

public class Expenditure {
    private int id;
    private String place;
    private String date;
    private int amount;
    private String category ;
    private String additional_info;

    public Expenditure(String place,String date,int amount,String category,String additional_info){

        this.place=place;
        this.date=date;
        this.amount=amount;
        this.category=category;
        this.additional_info=additional_info;
    }

    public Expenditure(int id,String place,String date,int amount,String category,String additional_info){
        this.id=id;
        this.place=place;
        this.date=date;
        this.amount=amount;
        this.category=category;
        this.additional_info=additional_info;
    }

    public int getId(){
        return id;
    }

    public void setId(){
        this.id=id;
    }

    public String getPlace(){
        return place;
    }

    public void setPlace(){
        this.place=place;
    }

    public String getDate(){
        return date;
    }

    public void setDate(){
        this.date=date;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(){
        this.amount=amount;
    }

    public String getCategories(){
        return category;
    }

    public void  setCategories(){
        this.category=category;
    }

    public String getAdditional_Info(){
        return additional_info;
    }

    public void setAdditional_Info(){
        this.additional_info=additional_info;
    }

}
