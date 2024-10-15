package org.task.clientStorage;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientProductService {
    private final ClientProductDao clientProductDao;
    public ClientProductService(ClientProductDao clientProductDao){
        this.clientProductDao = clientProductDao;
    }

    public ClientProduct getProduct(int id) {
        return clientProductDao.getProduct(id);
    }

    public List<ClientProduct> findAll() {
        return clientProductDao.getAll();
    }

    public List<ClientProduct> findProductByUserId(long userId) {
        return clientProductDao.getProductByUserId(userId);
    }

    public void saveProduct(List<ClientProduct> clientProducts) {
        clientProductDao.save(clientProducts);
    }

}
