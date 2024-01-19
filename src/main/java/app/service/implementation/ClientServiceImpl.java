package app.service.implementation;

import app.model.*;
import app.repository.ClientRepository;
import app.repository.OrderRepository;
import app.service.ClientService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository = RepositorySinglePointAccess.getUserRepository();
    private OrderRepository orderRepository = RepositorySinglePointAccess.getOrderRepository();

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        return clientRepository.update(client);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Integer id) {
        return clientRepository.findById(id);
    }

    @Override
    public boolean delete(Client client) {
        return clientRepository.delete(client);
    }

    @Override
    public Client login(String name, String password) {
        return clientRepository.findByNameAndPassword(name, password);
    }
}


