package com.bsuir.nikitayasiulevich;

import java.io.Serializable;

public class Jelly implements Serializable {
    private String id;
    private String flavor;
    private int quantity;

    public Jelly(String id, String flavor, int quantity) {
        this.id = id;
        this.flavor = flavor;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
