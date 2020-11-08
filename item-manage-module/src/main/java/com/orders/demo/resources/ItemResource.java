package com.orders.demo.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orders.demo.dao.HibernateDAOFactory;
import com.orders.demo.datamanager.ItemDataManager;
import com.orders.demo.dto.ItemDTO;
import com.orders.demo.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemResource {

    @Autowired
    private ItemDataManager itemDataManager;

    @RequestMapping("/{item}")
    public Item getItemById(@PathVariable("item") String itemId) {
        return new Item();
    }

    @CrossOrigin
    @RequestMapping("/get-all")
    public ResponseEntity<?> getAllItems() {
        List<Item> contentData=  itemDataManager.getAllItemData();
        Gson gsonBuilder = new GsonBuilder().create();
        String jsonObj = gsonBuilder.toJson(contentData);
        return ResponseEntity.ok(jsonObj.toString());
    }

    @CrossOrigin
    @RequestMapping(value ="/get-item", headers="Content-Type=application/json", method = {RequestMethod.POST} )
    public ResponseEntity<?> getContentData(@ModelAttribute("model") ModelMap model, HttpServletRequest request, HttpServletResponse response , @RequestBody ItemDTO itemDTO) throws IOException {
        Item contentData=  itemDataManager.getItemData(itemDTO.getId());
        Gson gsonBuilder = new GsonBuilder().create();
        String jsonObj = gsonBuilder.toJson(contentData);
        return ResponseEntity.ok(jsonObj.toString());
    }

    @CrossOrigin
    @RequestMapping("/add-item")
    public ResponseEntity<?> addOrder(@ModelAttribute("model") ModelMap model, HttpServletRequest request, HttpServletResponse response , @RequestBody ItemDTO itemDTO) {
        int result=  itemDataManager.addItemData(itemDTO);
        return ResponseEntity.ok(result);
    }

}
