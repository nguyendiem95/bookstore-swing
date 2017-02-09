/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Orders;
import DBuntil.DB;
import DBuntil.DBInterface;
import View.BillFrm;
import View.SelectBillFrm;
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
public class SelectCartControl {
    private Orders bill;
    private BillFrm frm;
    private SelectBillFrm s;
    
    public SelectCartControl(BillFrm frm) {
        this.frm = frm;
        frm.hienthi(new Hienthi());
    }
    public SelectCartControl(SelectBillFrm s) {
        this.s = s;
    }
    public class Hienthi implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            DBInterface db = new DB();
            ArrayList<Orders> bills= db.selectBills();
            frm.gettable2().getDataVector().removeAllElements();
            for(Orders b: bills){
                Vector vts = new Vector();
                vts.add(b.getIdbill());
                vts.add(b.getCart().getUser().getUser());
                vts.add(b.getDate());
                frm.gettable2().addRow(vts);
            }
            frm.getjTable2().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (e.getValueIsAdjusting() && frm.getjTable2().getSelectedRow() != -1) {
                        s = new SelectBillFrm();
                        SelectCartControl controller = new SelectCartControl(s);
                        XulidonControl c = new XulidonControl(s);
                        s.setVisible(true);
                        //frm.setVisible(false);
                        float tong =0;
                        String id = frm.getjTable2().getValueAt(frm.getjTable2().getSelectedRow(), 0).toString();
                        ArrayList<Orders> bills = db.selectBill(id);
                        for(Orders b : bills){
                            s.getjTextField1().setText(b.getIdbill());
                            s.getjTextField2().setText(b.getHinhthucthanhtoan());
                            s.getjTextField3().setText(b.getCart().getUser().getUser());
                            s.getjTextField4().setText(b.getSdt());
                            s.getjTextField5().setText(b.getDiachi());
                            Vector v = new Vector();
                            v.add(b.getCart().getBook().getId());
                            v.add(b.getCart().getBook().getName());
                            v.add(b.getCart().getQuantity());
                            v.add(b.getCart().getBook().getPrice());
                            v.add(b.getCart().getBook().getPrice()*b.getCart().getQuantity());
                            tong+= b.getCart().getBook().getPrice()*b.getCart().getQuantity();
                            s.gettable().addRow(v);
                            }
                        String a = Float.toString(tong);
                        s.getjTextField7().setText(a);
                        }
                }
            });
        }
        
    }
}
