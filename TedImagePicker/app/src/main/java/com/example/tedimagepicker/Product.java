package com.example.tedimagepicker;

public class Product {
    private String title;
    private String description;
    private String author;
    private String imageLink;
    private int numberOfPage;
    private long price;
    private int categoryId;

    public Product(String title, String description, String author, String imgLink, int numberOfPage, long price, int categoryId) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.imageLink = imgLink;
        this.numberOfPage = numberOfPage;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Product() {
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgLink() {
        return imageLink;
    }

    public void setImgLink(String imgLink) {
        this.imageLink = imgLink;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", imgLink='" + imageLink + '\'' +
                ", numberOfPage=" + numberOfPage +
                ", price=" + price +
                ", categoryId=" + categoryId +
                '}';
    }
}
