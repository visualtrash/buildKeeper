package com.nick.dao.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepository<T> {

    private List<T> data = new ArrayList<>();

    public static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.US_ASCII);
    }

    abstract String getSaveFileName();

    public void persist() throws IOException {
        // create space on disk for save
        Gson gson = new Gson();
        String buildListJson = gson.toJson(data);

        FileOutputStream fileOutputStream = new FileOutputStream("\\BuildKeeper\\saveData\\" + getSaveFileName() + ".json");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        bufferedWriter.write(buildListJson);
        bufferedWriter.close();
    }

    //load from disk
    public void load() throws IOException {
        File file = new File("\\BuildKeeper\\saveData\\" + getSaveFileName() + ".json");

        if (file.exists()) {
            String stringJson = "";
            stringJson = readFile(file.getPath());

            Gson gson = new Gson();

            Type collectionType = new TypeToken<List<T>>() {
            }.getType();
            data = gson.fromJson(stringJson, collectionType);
        }
    }
}
