package persistence;


import model.Item;
import model.Inventory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {

    // model for code based on JsonSerializationDemo

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads order from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Inventory read() throws IOException {
        String jsonData = readFile(this.source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseInventory(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: parses order from JSON item and returns it
    private Inventory parseInventory(JSONObject jsonObject) {
        String name = jsonObject.getString("inventoryName");
        Inventory inventory = new Inventory(name);
        addItemsInInventory(inventory, jsonObject);
        return inventory;
    }


    // MODIFIES: ord
    // EFFECTS: parses items from JSON object and adds them to order
    private void addItemsInInventory(Inventory ord, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("itemsInInventory");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(ord, nextItem);
        }
    }

    // MODIFIES: ord
    // EFFECTS: parses item from JSON object and adds it to order
    private void addItem(Inventory ord, JSONObject jsonObject) {
        String name = jsonObject.getString("itemName");
        int price = jsonObject.getInt("itemPrice");

        Item item = new Item(name, price);
        ord.addToInventory(item);
    }

}
