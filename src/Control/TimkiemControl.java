/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Book;
import DBuntil.DB;
import DBuntil.DBInterface;
import View.BillFrm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author MyPC
 */
public class TimkiemControl {

    private BillFrm bill;
    private Book book;

    public TimkiemControl(BillFrm bill) {
        this.bill = bill;
        bill.timkiem(new Timkiem());
        bill.Huysua(new Huy());
        bill.sua(new Sua());
        bill.Xoa(new Xoa());
    }

    public class Timkiem implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String a = bill.getTen();
            DBInterface db = new DB();

            ArrayList<Book> SP = db.timkiemBook(a);
            if (SP.size() == 0) {
                bill.showMessage("Thông tin tìm kiếm chưa tồn tại");
            } else {
                bill.gettable().getDataVector().removeAllElements();
                for (Book b : SP) {
                    Vector vt = new Vector();
                    vt.add(b.getId());
                    vt.add(b.getName());
                    vt.add(b.getPrice());
                    vt.add(b.getAuthor());
                    bill.gettable().addRow(vt);
                }
                bill.get().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (e.getValueIsAdjusting() && bill.get().getSelectedRow() != -1) {
                            String id = bill.get().getValueAt(bill.get().getSelectedRow(), 0).toString();
                            String ten = bill.get().getValueAt(bill.get().getSelectedRow(), 1).toString();
                            String price = bill.get().getValueAt(bill.get().getSelectedRow(), 2).toString();
                            String author = bill.get().getValueAt(bill.get().getSelectedRow(), 3).toString();
                            bill.getjTextField6().setText(id);
                            bill.getjTextField6().setEditable(false);
                            bill.getjTextField7().setText(ten);
                            bill.getjTextField8().setText(price);
                            bill.getjTextField9().setText(author);
                        }
                    }
                });
            }
        }
    }

    public class Huy implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            bill.setHuysua();
        }

    }

    public class Sua implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            book = bill.getBookSua();
            if (book.getName().equals("") || book.getAuthor().equals("") || book.getPrice() == 0) {
                bill.showMessage("Thông tin không dược đẻ trống");
            } else {
                DBInterface db = new DB();
                db.suaBook(book);
                bill.showMessage("Sửa sách thành công");
            }
        }
    }

    public class Xoa implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            book = bill.getBookSua();
            DBInterface db = new DB();
            db.xoaBook(book);
            bill.showMessage("Xóa sách thành công");
        }
    }
}
