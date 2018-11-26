package com.myweb.lucky;

public class Course {
    private String id_course;
    private String pic;
    private String name_course;
    private String price_course;
    private String amount;

    public String getId_course() {
        return id_course;
    }

    public void setId_course(String id_course) {
        this.id_course = id_course;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name_course;
    }

    public void setName(String name_course) {
        this.name_course = name_course;
    }

    public String getPrice() {
        return price_course;
    }

    public void setPrice(String price_course) {
        this.price_course = price_course;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount (String amount) {
        this.amount = amount;
    }

}
