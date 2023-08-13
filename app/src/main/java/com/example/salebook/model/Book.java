package com.example.salebook.model;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String publisher;
    private int price;
    private int quantity;
    private String image;
    private double rating;
    private int pages;
    private String dimension;
    private Category categoriesId;

    public Book() {
    }

    public Book( String title, String author, String publisher, int price, int quantity, String image, int pages, String dimension, Category category) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.rating = 0;
        this.pages = pages;
        this.dimension = dimension;
        this.categoriesId = category;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public Category getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Category categoriesId) {
        this.categoriesId = categoriesId;
    }
}
