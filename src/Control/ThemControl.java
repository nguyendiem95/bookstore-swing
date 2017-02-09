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

/**
 *
 * @author MyPC
 */
public class ThemControl {
    private BillFrm bill;
    private Book book;
    public ThemControl(BillFrm bill) {
        this.bill = bill;
        bill.them(new ThemListenner());
        bill.huy(new HuyListenner());
    }
    public class ThemListenner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            book = bill.getBook();
            DBInterface db = new DB();
            if(book.getId().equals("")||book.getName().equals("")||book.getAuthor().equals("")||book.getPrice()==0){
                bill.showMessage("Không được để trống thông tin");
            }else if(book.getPrice()<=0){
                bill.showMessage("Giá sách phải lớn hơn 0");
            }else if(db.checkBook(book.getId())){
                bill.showMessage("Trùng mã");
            }else{
                db.addBook(book);
                bill.showMessage("Thêm sách thành công");
            }
        }
    }
    
    public class HuyListenner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            bill.setHuy();
        }
        
    }

    
}
