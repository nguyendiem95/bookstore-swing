/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;



/**
 *
 * @author MyPC
 */
public class Orders {
    private String idbill;
    private String diachi;
    private String sdt;
    private String hinhthucthanhtoan;
    private String date;
    private Cart cart;

    public Orders() {
    }

    public Orders(String idbill, String diachi, String sdt, String hinhthucthanhtoan, String date, Cart cart) {
        this.idbill = idbill;
        this.diachi = diachi;
        this.sdt = sdt;
        this.hinhthucthanhtoan = hinhthucthanhtoan;
        this.date = date;
        this.cart = cart;
    }

    public String getIdbill() {
        return idbill;
    }

    public void setIdbill(String idbill) {
        this.idbill = idbill;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getHinhthucthanhtoan() {
        return hinhthucthanhtoan;
    }

    public void setHinhthucthanhtoan(String hinhthucthanhtoan) {
        this.hinhthucthanhtoan = hinhthucthanhtoan;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
