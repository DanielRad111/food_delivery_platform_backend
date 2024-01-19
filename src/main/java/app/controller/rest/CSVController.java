package app.controller.rest;

import app.model.Client;
import app.service.ClientService;
import app.single_point_access.ServiceSinglePointAccess;
import app.util.FileUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


// All imported/exported files are taken form resources directory ONLY
@RestController
@RequestMapping("/csv")
public class CSVController {

    private ClientService clientService = ServiceSinglePointAccess.getClientService();

    // TO DO
    // For project take in consideration that a csv file could have different order of columns
    // Do it for at least 2 entities - import and export
    // Take in consideration data validation or if some data already exists
    // Extract duplicate logic and improve it based on template below
    //
    // For demo - import at least 25 entities and export all entities
    //
    @PostMapping("/import_user")
    public ResponseEntity<Boolean> importUserFromCSV(@RequestBody String filename) {
        try {
            File file = FileUtil.getAndCreateFileFromResourcesDirectory(filename);

            // Read data in a buffer
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<Client> clients = new ArrayList<>();
            String line;
            boolean firstLine = true;
            String[] headers = null;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (firstLine) {
                    firstLine = false;
                    headers = values;
                    continue;
                }

                Client client = new Client();

                for (int i = 0; i < values.length; i++) {
                    if (i < headers.length) {
                        switch (headers[i].toLowerCase()) {
                            case "name":
                                client.setName(values[i]);
                                break;
                            case "password":
                                client.setPassword(values[i]);
                                break;
                            case "phone":
                                client.setPhone(values[i]);
                                break;
                            default:
                                break;
                        }
                    }
                }
                clients.add(client);
            }

            for (Client clientIterator : clients) {
                clientService.save(clientIterator);
            }

            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            // TO DO - treat exception case
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
            // TO DO - treat exception case
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
            // TO DO - treat exception case
        }
    }

    // You can send the order of fields that must appear in csv
    // Add a new parameter for header
    @PostMapping("/export_user")
    public ResponseEntity<Boolean> exportUserToCSV(@RequestBody  ExportRequest exportRequest) {

        try {
            File file = FileUtil.getAndCreateFileFromResourcesDirectory(exportRequest.getFilename());
            FileWriter fileWriter = new FileWriter(file);
            String header = "name,password\n";
            fileWriter.write(exportRequest.getHeader());


            List<Client> clients = clientService.findAll();
            for (Client clientIterator : clients) {
                StringBuilder clientDetails = new StringBuilder();
                String[] fields = exportRequest.getHeader().split(",");
                for(String field : fields){
                    switch (field.trim()){
                        case "name":
                            clientDetails.append(clientIterator.getName()).append(",");
                            break;
                        case "phone":
                            clientDetails.append(clientIterator.getPhone()).append(",");
                            break;
                        case "password":
                            clientDetails.append(clientIterator.getPassword()).append(",");
                            break;
                    }
                }
                clientDetails.deleteCharAt(clientDetails.length() - 1);
                clientDetails.append("\n");
                fileWriter.write(clientDetails.toString());
            }

            fileWriter.close();

            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (IOException ex) {

            // TO DO - treat exception case

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        } catch (URISyntaxException e) {

            // TO DO - treat exception case

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }
}
