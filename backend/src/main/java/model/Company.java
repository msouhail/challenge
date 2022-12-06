package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Company {
    private Long id;
    private String name;
    private double balance;

}
