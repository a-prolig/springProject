package org.task.clientStorage;


import org.task.config.AppConfig;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientProductDao {
    public List<ClientProduct> getAll() {
        String SQL_QUERY = "select * from clientproducts";
        List<ClientProduct> clientProducts = new ArrayList<>();
        try (Connection con = getDBConnection();
             PreparedStatement pst = con.prepareStatement( SQL_QUERY );
             ResultSet rs = pst.executeQuery()) {
            while (rs.next() ) {
                ClientProduct clientProduct = new ClientProduct();
                clientProduct.setId(rs.getInt("id"));
                clientProduct.setAccountNumber(rs.getString("accountNumber"));
                clientProduct.setBalance(rs.getString("balance"));
                clientProduct.setProductType(ProductType.valueOf(rs.getString("productType")));
                clientProduct.setUserId(rs.getLong("userid"));
                clientProducts.add(clientProduct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientProducts;
    }

    public ClientProduct getProduct(int id) {
        String SQL_QUERY = "select * from clientproducts where clientproducts.id=?";
        List<ClientProduct> clientProducts = getAll();
        ClientProduct clientProduct = new ClientProduct();
        try (Connection con = getDBConnection();
             PreparedStatement pst = con.prepareStatement(SQL_QUERY)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next() ) {
                    int productId = rs.getInt("id");
                    clientProduct = clientProducts.stream().filter(product -> product.id == productId).findFirst().orElse(clientProduct);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientProduct;
    }

    public List<ClientProduct> getProductByUserId(long userid) {
        String SQL_QUERY = "select * from clientproducts where clientproducts.userid=?";
        List<ClientProduct> clientProducts = getAll();
        List<ClientProduct> result = new ArrayList<>();
        try (Connection con = getDBConnection();
             PreparedStatement pst = con.prepareStatement(SQL_QUERY)) {
            pst.setLong(1, userid);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next() ) {
                    int userId = (int) rs.getLong("userid");
                    result = clientProducts.stream().filter(product -> product.userId == userId).collect(Collectors.toList());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void save(List<ClientProduct> clientProducts) {
        String SQL_QUERY = "insert into clientproducts (id, accountnumber, balance, producttype, userid) values (?, ?, ?, ?, ?)";
        try (Connection con = getDBConnection();
            PreparedStatement pst = con.prepareStatement(SQL_QUERY)) {
            clientProducts.forEach(product -> {
                try {
                    pst.setLong(1, product.id);
                    pst.setString(2, product.accountNumber);
                    pst.setString(3, product.balance);
                    pst.setString(4, String.valueOf(product.productType));
                    pst.setLong(5, product.userId);
                    pst.executeUpdate();
                    System.out.println("Database has been update");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Connection getDBConnection() throws SQLException {
        AppConfig appConfig = new AppConfig();
        return appConfig.getDataSource().getConnection();
    }
}
