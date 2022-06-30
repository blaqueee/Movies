package com.Reader;

import com.Catalog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
