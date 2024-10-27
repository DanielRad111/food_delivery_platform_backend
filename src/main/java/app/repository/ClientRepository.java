package app.repository;

import app.model.Client;

public interface ClientRepository extends CRUDRepository<Client, Integer> {
    Client findByName(String name);
    Client findByNameAndPassword(String name, String password);
}
