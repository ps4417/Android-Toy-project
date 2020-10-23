package com.example.ws;

public class Item {
    String id;
    String name;
    String img;
    int rank;

    public Item() {
    }

    public Item(String id, String name, String img, int rank) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.rank = rank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
