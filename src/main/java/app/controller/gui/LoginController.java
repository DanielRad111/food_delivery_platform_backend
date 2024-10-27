package app.controller.gui;

import app.model.Client;
import app.service.ClientService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

    private LoginView loginView;

    private ClientService clientService = ServiceSinglePointAccess.getClientService();

    public void startLogic() {
        loginView = new LoginView();
        GUIFrameSinglePointAccess.changePanel(loginView.getLoginPanel(), "Login");

        loginView.getLogInButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = loginView.getTextFieldName().getText();
                String password = new String(loginView.getPasswordField().getPassword());

                Client client = clientService.login(name, password);
                if (client != null) {
                    ClientDetailsController clientDetailsController = new ClientDetailsController();
                    clientDetailsController.startLogic(client);
                } else {
                    GUIFrameSinglePointAccess.showDialogMessage("Invalid username or password");
                }
            }
        });
    }
}
