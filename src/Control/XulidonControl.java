/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Bill;
import Model.Orders;
import DBuntil.DB;
import DBuntil.DBInterface;
import View.SelectBillFrm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author MyPC
 */
public class XulidonControl {
    private SelectBillFrm frm;
    
    public XulidonControl(SelectBillFrm frm) {
        this.frm = frm;
        frm.Xacnhan(new Xacnhan());
        frm.Huy(new Huy());
    }
    public class Xacnhan implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = frm.getMa();
            DBInterface d = new DB();
            ArrayList<Orders> bills = d.selectBill(id);
            for(Orders or:bills){
                Bill bill = new Bill();
                bill.setOrder(or);
                bill.setNgaynhanhang(frm.getNgay());
                d.addBill(bill);
                d.xoaBill(or);
            }
            frm.showMessage("Thành công!");
            frm.setVisible(false);
        }
        
    }
    
    public class Huy implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = frm.getMa();
            DBInterface d = new DB();
            ArrayList<Orders> bills = d.selectBill(id);
            for(Orders or:bills){
                Bill bill = new Bill();
                bill.setOrder(or);
                bill.setNgaynhanhang(frm.getNgay());
                d.xoaBill(or);
            }
            frm.showMessage("Thành công!");
            frm.setVisible(false);
        }
    }
}
