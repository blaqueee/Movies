package com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = Paths.get("./movies.json");

    public static Catalog readFile(){
        String json = "";

        try {
            json = Files.readString(PATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return GSON.fromJson(json, Catalog.class);
    }
}
