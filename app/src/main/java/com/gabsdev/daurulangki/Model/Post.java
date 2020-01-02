package com.gabsdev.daurulangki.Model;

public class Post {
    private String postid;
    private String postimage;
    private String description;
    private String title;
    private String publisher;
    private String location;
    private String price;
    private String number;
    private String imageurl;

    public Post(String postid, String postimage, String description, String title, String publisher, String location, String price, String number, String imageurl) {
        this.postid = postid;
        this.postimage = postimage;
        this.description = description;
        this.title = title;
        this.publisher = publisher;
        this.location = location;
        this.price = price;
        this.number = number;
        this.imageurl = imageurl;
    }

    public Post() {
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getPostimage() {
        return postimage;
    }

    public void setPostimage(String postimage) {
        this.postimage = postimage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String price) {
        this.number = number;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
