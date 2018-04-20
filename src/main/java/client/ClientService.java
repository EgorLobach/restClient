package client;

import model.Directory;
import model.Item;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;

public class ClientService {
    private String host = "http://localhost:8800/";

    private ClientConfig config = new ClientConfig();
    private Client client = ClientBuilder.newClient(config);
    private WebTarget target = client.target(host);

    public List<Item> getItems() throws JAXBException {
        String response = target.path("rest").
                path("service").
                path("getItems").
                request().
                accept(MediaType.APPLICATION_XML).
                get(String.class);

        JAXBContext context = JAXBContext.newInstance(Directory.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader stringReader = new StringReader(response);
        Directory directory = (Directory) unmarshaller.unmarshal(stringReader);

        return directory.getItems();
    }
    public void deleteItem(String itemName){
        Response response = target.
                path("rest").
                path("service").
                path("deleteItem").
                path(itemName).
                request().
                delete();

    }
    public void addItem(Item item){
        Response response = target.
                path("rest").
                path("service").
                path("addItem").
                request().
                post(Entity.entity(item, MediaType.APPLICATION_XML), Response.class);
    }
    public void updateItem(Item item){
        Response response = target.
                path("rest").
                path("service").
                path("updateItem").
                request().
                put(Entity.entity(item, MediaType.APPLICATION_XML), Response.class);
    }
}
