package org.sid.customerservice.dtos;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class CustomerRequestDTO {

    private String name;
    private String email;
}
