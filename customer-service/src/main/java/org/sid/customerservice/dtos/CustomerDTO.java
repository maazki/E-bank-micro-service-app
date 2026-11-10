package org.sid.customerservice.dtos;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
}
