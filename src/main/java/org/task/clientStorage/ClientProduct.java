package org.task.clientStorage;

import lombok.Data;

@Data
public class ClientProduct {
    public int id;
    public String accountNumber;
    public String balance;
    public ProductType productType;
    public Long userId;
}
