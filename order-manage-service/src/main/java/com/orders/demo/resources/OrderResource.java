package com.orders.demo.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orders.demo.dataaccess.OrderDataAccess;
import com.orders.demo.datamanager.OrderDataManager;
import com.orders.demo.dto.OrderDTO;
import com.orders.demo.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderResource {
    @Autowired
    private OrderDataManager orderDataManager;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{orderId}")
    public Order getMovieInfo(@PathVariable("orderId") String movieId) {
     //   Order movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieSummary.class);
        return new Order();

    }

    @CrossOrigin
    @RequestMapping(value ="/get-all", headers="Content-Type=application/json", method = {RequestMethod.POST} )
    public ResponseEntity<?> getContentData(@ModelAttribute("model") ModelMap model, HttpServletRequest request, HttpServletResponse response , @RequestBody OrderDTO contentDTO) throws IOException {
        List<Order> orderDataList=  orderDataManager.getAllOrdersData();
        Gson gsonBuilder = new GsonBuilder().create();
        String jsonObj = gsonBuilder.toJson(orderDataList);
        return ResponseEntity.ok(jsonObj.toString());
    }

    @CrossOrigin
    @RequestMapping("/get-order")
    public ResponseEntity<?> getOrderById(@ModelAttribute("model") ModelMap model, HttpServletRequest request, HttpServletResponse response , @RequestBody OrderDTO orderDTO) {
        Order orderData=  orderDataManager.getOrderData(orderDTO.getId());
        Gson gsonBuilder = new GsonBuilder().create();
        String jsonObj = gsonBuilder.toJson(orderData);
        return ResponseEntity.ok(jsonObj.toString());
    }

    @CrossOrigin
    @RequestMapping("/add-order")
    public ResponseEntity<?> addOrder(@ModelAttribute("model") ModelMap model, HttpServletRequest request, HttpServletResponse response , @RequestBody OrderDTO orderDTO) {
        int result=  orderDataManager.addOrderData(orderDTO);
        return ResponseEntity.ok(result);
    }

}
