package app.dto;

import app.model.Address;
import lombok.Data;

@Data
public class ClientDTO {

    private String name;
    private Address address;
    private String phone;
}
