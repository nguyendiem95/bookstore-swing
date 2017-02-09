/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBuntil;

import Model.Bill;
import Model.Book;
import Model.Nhanvien;
import Model.Orders;
import java.util.ArrayList;

/**
 *
 * @author MyPC
 */
public interface DBInterface {
    public void getConnection();
    public boolean checkUser(Nhanvien user);
    public ArrayList<Orders> selectBills ();
    public ArrayList<Orders> selectBill (String id);
    public boolean checkBook(String id);
    public int addBook(Book book );
    public ArrayList<Book> timkiemBook(String ten);
    public int suaBook(Book book );
    public int xoaBook(Book book);
    public int xoaBill(Orders b);
    public int addBill(Bill book);
}
