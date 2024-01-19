package app.controller.gui;

import app.model.Client;
import app.service.ClientService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.view.ClientDetailsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientDetailsController {

    private ClientDetailsView clientDetailsView;

    private ClientService clientService = ServiceSinglePointAccess.getClientService();


    public void startLogic(Client client) {
        clientDetailsView = new ClientDetailsView();
        GUIFrameSinglePointAccess.changePanel(clientDetailsView.getMainPanel(), "Welcome " + client.getName());

        clientDetailsView.getNameValue().setText(client.getName());
        clientDetailsView.getStreetValue().setText(client.getAddress().getStreet());
        clientDetailsView.getCityValue().setText(client.getAddress().getCity());

        clientDetailsView.getLogOutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginController loginController = new LoginController();
                loginController.startLogic();
            }
        });
    }
}
