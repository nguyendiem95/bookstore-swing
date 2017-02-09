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
public class Bill {
    private Orders order;
    private String ngaynhanhang;

    public Bill() {
    }

    public Bill(Orders order, String ngaynhanhang) {
        this.order = order;
        this.ngaynhanhang = ngaynhanhang;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public String getNgaynhanhang() {
        return ngaynhanhang;
    }

    public void setNgaynhanhang(String ngaynhanhang) {
        this.ngaynhanhang = ngaynhanhang;
    }
}
