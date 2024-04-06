package controller;

import Classes.User;
import Dao.UserDao;
import View.LoginView;
import View.Quanly;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginController {
    private UserDao userDao;
    private LoginView loginView;
    private Quanly quanly;
    
    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserDao();
        view.addLoginListener((ActionListener) new LoginListener());
    }
    
    public void showLoginView() {
        loginView.setVisible(true);
    }
    
    /**
     * Lớp LoginListener 
     * chứa cài đặt cho sự kiện click button "Login"
     * 
     * @author viettuts.vn
     */
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userDao.checkUser(user)) {
                // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
                quanly = new Quanly();
                
                QuanlyController quanlycontroller = new QuanlyController(quanly);
                quanlycontroller.showQuanly();
                loginView.setVisible(false);
            } else {
                loginView.showMessage("username hoặc password không đúng.");
            }
        }
    }
}
