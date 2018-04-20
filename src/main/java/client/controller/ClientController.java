package client.controller;


import client.ClientService;
import model.Item;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

public class ClientController{

    private ClientService service = new ClientService();

    public ClientController() {

    }

    public void addItem(Item item){
        service.addItem(item);
    }


    public void updateItem(Item item){
        service.updateItem(item);
    }


    public void deleteItem(String name){
        service.deleteItem(name);
    }


    public List<Item> getItems(){
        List<Item> items = new ArrayList<>();
        try {
            items = service.getItems();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return items;
    }
}
