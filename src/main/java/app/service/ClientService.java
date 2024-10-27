package app.service;

import app.model.Client;

import java.util.Date;
import java.util.List;

public interface ClientService {

    Client save(Client client);

    Client update(Client client);

    List<Client> findAll();

    Client findById(Integer id);

    boolean delete(Client client);

    Client login(String name, String password);
}
