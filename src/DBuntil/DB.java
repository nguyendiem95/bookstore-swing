/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBuntil;

import Model.Bill;
import Model.Book;
import Model.Cart;
import Model.Nhanvien;
import Model.Orders;
import Model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author MyPC
 */
public class DB implements DBInterface {

    private Connection con;

    @Override
    public void getConnection() {
        String urlclass = "jdbc:mysql://localhost:3306/gui";
        String user = "root";
        String pass = "root";
        try {
            this.con = DriverManager.getConnection(urlclass, user, pass);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean checkUser(Nhanvien user) {
        getConnection();
        String sql = "SELECT * FROM nhanvien"
                + " WHERE user =" + "'" + user.getUser()+ "'" + "AND pass =" + "'" + user.getPass() + "'";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Orders> selectBills() {
        getConnection();
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Orders> bills = new ArrayList<>();
        String sql = "Select *from orders";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            Orders bill;
            while (rs.next()) {
                bill = new Orders();
                Cart c = new Cart();
                Book b = new Book();
                b.setId(rs.getString("id"));
                b.setName(rs.getString("name"));
                b.setPrice(rs.getFloat("price"));
                User u = new User();
                u.setUser(rs.getString("user"));
                c.setQuantity(rs.getFloat("quantity"));
                c.setBook(b);
                c.setUser(u);
                bill.setCart(c);
                bill.setDate(rs.getString("date"));
                bill.setDiachi(rs.getString("address"));
                bill.setHinhthucthanhtoan(rs.getString("hinhthucthanhtoan"));
                bill.setIdbill(rs.getString("idbill"));
                bill.setSdt(rs.getString("phone"));
                if(bills.size()==0){
                    bills.add(bill);
                }else {
                    for( Orders bl:bills){
                        if(bill.getIdbill().equals(bl.getIdbill())){
                            
                        }else{
                            bills.add(bill);
                            break;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    @Override
    public boolean checkBook(String id) {
        getConnection();
        String sql = "SELECT * FROM Book "
                + " WHERE id=" + "'" + id + "'";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int addBook(Book book) {
        getConnection();
        String sql = "INSERT INTO book(id,name,price,author) VALUES(?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, book.getId());
            ps.setString(2, book.getName());
            ps.setFloat(3, book.getPrice());
            ps.setString(4, book.getAuthor()); 
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    @Override
    public ArrayList<Book> timkiemBook(String ten) {
        getConnection();
        ArrayList<Book> acc = new ArrayList();
        try {
            Statement st = con.createStatement();
            String sql = "Select *from Book where name LIKE '%" + ten + "%'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Book  a = new Book ();
                a.setId(rs.getString("id"));
                a.setName(rs.getString("name"));
                a.setPrice(rs.getFloat("price"));
                a.setAuthor(rs.getString("author"));
                acc.add(a);
            }
        } catch (SQLException ex) {
            
        }
        return acc;
    }
    
    @Override
    public int suaBook(Book  bd) {
        getConnection();
        String sql = "UPDATE Book SET " + 
                "name = ?, "+ 
                "price = ?, "+ 
                "author = ? "+
                "WHERE id = ?"; 
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, bd.getName());
            ps.setFloat(2, bd.getPrice());
            ps.setString(3, bd.getAuthor());
            ps.setString(4, bd.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    
    @Override
    public int xoaBook(Book book){
        getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM Book "+ "where id = ?";
        try {
            ps= con.prepareStatement(sql);
            ps.setString(1, book.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }
    }
    
    @Override
    public ArrayList<Orders> selectBill(String id) {
        getConnection();
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Orders> bills = new ArrayList<>();
        String sql = "SELECT * FROM orders" + " WHERE idBill=" + "'" + id + "'";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            Orders bill;
            while (rs.next()) {
                bill = new Orders();
                Cart c = new Cart();
                Book b = new Book();
                b.setId(rs.getString("id"));
                b.setName(rs.getString("name"));
                b.setPrice(rs.getFloat("price"));
                User u = new User();
                u.setUser(rs.getString("user"));
                c.setQuantity(rs.getFloat("quantity"));
                c.setBook(b);
                c.setUser(u);
                bill.setCart(c);
                bill.setDate(rs.getString("date"));
                bill.setDiachi(rs.getString("address"));
                bill.setHinhthucthanhtoan(rs.getString("hinhthucthanhtoan"));
                bill.setIdbill(rs.getString("idbill"));
                bill.setSdt(rs.getString("phone"));
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }
    
    public int xoaBill(Orders b){
        getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM orders "+ "where idBill = ?";
        try {
            ps= con.prepareStatement(sql);
            ps.setString(1, b.getIdbill());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }
    }
    
    @Override
    public int addBill(Bill book) {
        getConnection();
        String sql = "INSERT INTO bill(idBill, user, address, phone,hinhthucthanhtoan,id,name,quantity,price,total,ngaythanhtoan) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, book.getOrder().getIdbill());
            ps.setString(2, book.getOrder().getCart().getUser().getUser());
            ps.setString(3, book.getOrder().getDiachi());
            ps.setString(4, book.getOrder().getSdt());
            ps.setString(5, book.getOrder().getHinhthucthanhtoan());
            ps.setString(6, book.getOrder().getCart().getBook().getId());
            ps.setString(7, book.getOrder().getCart().getBook().getName());
            ps.setFloat(8, book.getOrder().getCart().getQuantity());
            ps.setFloat(9, book.getOrder().getCart().getBook().getPrice());
            ps.setFloat(10,book.getOrder().getCart().getQuantity()*book.getOrder().getCart().getBook().getPrice());
            ps.setString(11, book.getNgaynhanhang());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
