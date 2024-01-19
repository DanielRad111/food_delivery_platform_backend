package app.controller.rest;

import app.dto.ClientDTO;
import app.model.Client;
import app.service.ClientService;
import app.single_point_access.ServiceSinglePointAccess;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

// every request to http://localhost:8080/user will enter on this controller
@RequestMapping("/user")
public class ClientController {

    private ClientService clientService = ServiceSinglePointAccess.getClientService();

    // Map http://localhost:8080/user/all
    // Get - to take data from server
    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients() {

        // Return a Response which has a status and a body (data)
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll());
    }

    // {id} - is a value sent by url and is named path variable
    // {id} - will be taken from path - Path Variable
    // Attention - GET doesn't have a body
    @GetMapping("/id/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findById(id));
    }

    // Post - create data
    // RequestBody - is the data sent to server through request
    // For POST, PUT, DELETE we can send information both : Path Variable & RequestBody
    @PostMapping("/create")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.save(client));
    }

    // Put - update data
    // Put with path variable
    @PutMapping("/updateName/{id}/{name}")
    public ResponseEntity<Client> updateClientName(@PathVariable Integer id, @PathVariable String name) {
        Client client = clientService.findById(id);
        client.setName(name);
        Client clientUpdated = clientService.update(client);
        return ResponseEntity.status(HttpStatus.OK).body(clientUpdated);
    }

    @PutMapping("/update")
    public ResponseEntity<Client> update(@RequestBody Client client) {
        Client clientFromDB = clientService.findById(client.getId());
        clientFromDB.setName(clientFromDB.getName());
        clientFromDB.setPassword(client.getPassword());
        Client clientUpdated = clientService.update(clientFromDB);
        return ResponseEntity.status(HttpStatus.OK).body(clientUpdated);
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@RequestBody Integer id) {
        Client client = clientService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(clientService.delete(client));
    }

    @Operation(summary = "Get details (name, address, phone) from all users")
    @GetMapping("/details_all")
    public ResponseEntity<List<ClientDTO>> getAllClientDetails() {

        List<Client> clients = clientService.findAll();
        List<ClientDTO> clientDTOS = new ArrayList<>();

        for (Client client : clients) {
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setAddress(client.getAddress());
            clientDTO.setPhone(client.getPhone());
            clientDTO.setName(client.getName());

            clientDTOS.add(clientDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).body(clientDTOS);
    }

}
