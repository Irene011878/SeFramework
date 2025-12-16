package com.irene_labs.Data;

import org.apache.commons.io.FileUtils;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;


public class DataReader {

    public List<HashMap<String, String>> getJsonDataToMap()throws IOException
    {
        //read json to string
        String jsonContent = FileUtils.readFileToString(
                new File(System.getProperty("user.dir") + "//src//test//java//com//irene_labs//Data//PurchaseOrder.json"),
                StandardCharsets.UTF_8);

        //Convert String to Hashmap (you need Jackson data bind para esta conversion)
        //creas objeto de mapper para que puedas leer el Json y tmbien le dices como quieres
        //que te devuelva la informacion del Json, en este caso en un hashmap de strings
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {

        });
        return data;

    }

}
