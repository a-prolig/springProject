package org.task.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.task.clientStorage.ClientProduct;
import org.task.clientStorage.ClientProductService;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class ClientProductController {
    private final ClientProductService clientProductService;

    public ClientProductController(ClientProductService clientProductService) {
        this.clientProductService = clientProductService;
    }

    @GetMapping("/getAllProducts")
    public List<ClientProduct> getAllProducts() {
        return clientProductService.findAll();
    }

    @GetMapping("/getProduct")
    public ClientProduct getProduct(@RequestParam int id) {
        return clientProductService.getProduct(id);
    }

    @GetMapping("/getUserProduct")
    public List<ClientProduct> getUserProduct(@RequestParam int userId) {
        return clientProductService.findProductByUserId(userId);
    }

    @PostMapping("/saveProduct")
    public void saveProduct(@RequestBody List<ClientProduct> clientProducts) {
        clientProductService.saveProduct(clientProducts);
    }
}
