/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DBuntil.DB;
import DBuntil.DBInterface;
import Model.Nhanvien;
import View.BillFrm;
import View.DangNhap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author MyPC
 */
public class DangnhapControl {
    private Nhanvien user;
    private DangNhap dangnhap;

    public DangnhapControl(DangNhap dangnhap) {
        this.dangnhap = dangnhap;
        dangnhap.dangnhap(new DangnhapListenner());
    }
    public class DangnhapListenner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                user = dangnhap.getUser();
                DBInterface db = new DB();
                if(user.getUser().equals("")|| user.getPass().equals("")){
                    dangnhap.showMessage("Không được để trống thông tin");
                }else if (db.checkUser(user)) {
                    dangnhap.showMessage("Đăng nhập thành công");
                    BillFrm view = new BillFrm();
                    ThemControl controller = new ThemControl(view);
                    TimkiemControl c = new TimkiemControl(view);
                    SelectCartControl s = new SelectCartControl(view);
                    view.setVisible(true);
                    dangnhap.setVisible(false);
                } else {
                    dangnhap.showMessage("Tên đnag nhập hoặc mật khầu không đúng");
                }
            } catch (Exception ex) {
                dangnhap.showMessage(ex.getStackTrace().toString());
            }
        }
        
    }
}
