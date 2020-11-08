package com.orders.demo.datamanagers;

import com.orders.demo.dataaccess.UserDataAccess;
import com.orders.demo.dto.ItemDTO;
import com.orders.demo.dto.OrderDTO;
import com.orders.demo.dto.UserDTO;
import com.orders.demo.models.Movie;
import com.orders.demo.models.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author IsuruP
 */
@Component
public class UserDataManager {
    @Autowired
    private UserDataAccess userDataAccess;

    public int addUserData(UserDTO userDTO) {
        return userDataAccess.addUserData(userDTO);
    }

//    public String updateUser(UserDTO userDTO) {
//        RestTemplate restTemplate = new RestTemplate();
//        Map<String, String> moduleParams = new HashMap<String,String>();
//        moduleParams.put("id",Long.toString(orderDTO.getId()));
//        moduleParams.put("userId",Long.toString(orderDTO.getUserId()));
//        moduleParams.put("address",orderDTO.getAddress());
//        moduleParams.put("prize", Long.toString(orderDTO.getTotalPrize()));
//        moduleParams.put("status",orderDTO.getStatus());
//        String actionStatus = restTemplate.postForObject("http://localhost:8083/add-order", moduleParams,String.class);
//        return actionStatus;
//    }

    public List<SysUser> getAllUsers() {
        return userDataAccess.getAllUserData();
    }


//    public SysUser getUserByUserId(UserDTO userDTO) {
//        return userDataAccess.getUserData(userDTO.getUserName());
//    }



    // Handle orders

    public String addOrder(OrderDTO orderDTO) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> moduleParams = new HashMap<String,String>();
        moduleParams.put("Id",Long.toString(orderDTO.getId()));
        moduleParams.put("totalPrize",Long.toString(orderDTO.getTotalPrize()));
        moduleParams.put("itemId",Long.toString(orderDTO.getItemId()));
        moduleParams.put("quantity", Long.toString(orderDTO.getQuantity()));
        moduleParams.put("status",orderDTO.getStatus());
        moduleParams.put("address",orderDTO.getAddress());
        moduleParams.put("userId",Long.toString(orderDTO.getUserId()));
        String actionStatus = restTemplate.postForObject("http://localhost:8082/order/add-order", moduleParams,String.class);
        return actionStatus;
    }

    public String upDateOrder(OrderDTO orderDTO) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> moduleParams = new HashMap<String,String>();
        moduleParams.put("id",Long.toString(orderDTO.getId()));
        moduleParams.put("userId",Long.toString(orderDTO.getUserId()));
        moduleParams.put("address",orderDTO.getAddress());
        moduleParams.put("prize", Long.toString(orderDTO.getTotalPrize()));
        moduleParams.put("status",orderDTO.getStatus());
        String actionStatus = restTemplate.postForObject("http://localhost:8082/order/add-order", moduleParams,String.class);
        return actionStatus;
    }

    public List<OrderDTO> getAllOrders() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> moduleParams = new HashMap<String,String>();
        String orders = restTemplate.getForObject("http://localhost:8082/order/get-all" , String.class);

        return null;
    }

    public OrderDTO getOrderByUserId(OrderDTO orderDTO) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> moduleParams = new HashMap<String,String>();
        moduleParams.put("id",Long.toString(orderDTO.getUserId()));
        String order = restTemplate.postForObject("http://localhost:8082/order/add-order", moduleParams,String.class);
        return null;
    }

    public OrderDTO getOrderById(OrderDTO orderDTO) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> moduleParams = new HashMap<String,String>();
        moduleParams.put("id",Long.toString(orderDTO.getId()));
        String actionStatus = restTemplate.postForObject("http://localhost:8082/order/get-order", moduleParams,String.class);
        return null;
    }


    // Handle Items

    public String addItem(ItemDTO itemDTO) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> moduleParams = new HashMap<String,String>();
        moduleParams.put("Id",Long.toString(itemDTO.getId()));
        moduleParams.put("itemDescription",itemDTO.getItemDescription());
        moduleParams.put("price",Double.toString(itemDTO.getPrice()));
        moduleParams.put("itemName", itemDTO.getItemName());
        String actionStatus = restTemplate.postForObject("http://localhost:8083/item/add-item", moduleParams,String.class);
        return actionStatus;
    }

//    public String upDateItem(OrderDTO orderDTO) {
//        RestTemplate restTemplate = new RestTemplate();
//        Map<String, String> moduleParams = new HashMap<String,String>();
//        moduleParams.put("id",Long.toString(orderDTO.getId()));
//        moduleParams.put("userId",Long.toString(orderDTO.getUserId()));
//        moduleParams.put("address",orderDTO.getAddress());
//        moduleParams.put("prize", Long.toString(orderDTO.getTotalPrize()));
//        moduleParams.put("status",orderDTO.getStatus());
//        String actionStatus = restTemplate.postForObject("http://localhost:8083/add-order", moduleParams,String.class);
//        return actionStatus;
//    }

    public List<ItemDTO> getAllItems() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> moduleParams = new HashMap<String,String>();
        String items = restTemplate.getForObject("http://item-manager-module/item/get-all" , String.class);

        return null;
    }


    public ItemDTO getItemById(ItemDTO itemDTO) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> moduleParams = new HashMap<String,String>();
        moduleParams.put("id",Long.toString(itemDTO.getId()));
        String actionStatus = restTemplate.postForObject("http://localhost:8083/item/get-item", moduleParams,String.class);
        return null;
    }

    public SysUser getUserData(String userName) {
        return userDataAccess.getUserData(userName);
    }

}


