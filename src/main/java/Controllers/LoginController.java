package Controllers;

import Classes.Admin;
import Functions.AdminFunc;
import Views.LoginView;
import Views.ManagerView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private AdminFunc adminFunc;
    private LoginView loginView;
    private ManagerView managerView;
    
    public LoginController(LoginView view) {
        this.loginView = view;
        this.adminFunc = new AdminFunc();
        view.addLoginListener(new LoginListener());
    }
    
    public void showLoginView() {
        loginView.setVisible(true);
    }
    
    /**
     * Lớp LoginListener chứa cài đặt cho sự kiện click button "Login"
     * 
     * @author viettuts.vn
     */
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Admin admin = loginView.getAdmin();
            if (adminFunc.checkAdmin(admin)) {
                // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
                managerView = new ManagerView();
                ManagerController managerController = new ManagerController(managerView);
                managerController.showManagerView();
                loginView.setVisible(false);
            } else {
                loginView.showMessage("username hoặc password không đúng.");
            }
        }
    }
}
