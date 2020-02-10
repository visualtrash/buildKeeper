package com.nick.dao.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nick.dao.entities.BKEntity;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractRepository<T extends BKEntity> {
    List<T> data;

    AbstractRepository() {
        data = new ArrayList<>();
        load();
    }

    public static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.US_ASCII);
    }

    abstract String getSaveFileName();

    public void persist() throws IOException {
        // create space on disk for save
        String buildListJson = new Gson().toJson(data);

        File file = new File("\\BuildKeeper\\saveData\\" + getSaveFileName() + ".json");

        if (!file.exists()) {
            boolean mkdirs = file.getParentFile().mkdirs();
            boolean newFile = file.createNewFile();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        bufferedWriter.write(buildListJson);
        bufferedWriter.close();
    }

    public List<T> getAll() {
        return data;
    }

    //load from disk
    public void load() {
        File file = new File("\\BuildKeeper\\saveData\\" + getSaveFileName() + ".json");

        if (file.exists()) {
            String stringJson = "";
            try {
                stringJson = readFile(file.getPath());
                Gson gson = new Gson();

                Type collectionType = new TypeToken<List<T>>() {
                }.getType();
                data = gson.fromJson(stringJson, collectionType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void delete(T entity) throws Exception {
        // флаг, был ли найден hero в списке
        boolean wasFounded = false;

        for (T current : data)
            if (current.getId().equals(entity.getId())) {
                wasFounded = true;

                data.remove(current);
                persist();
                break;
            }
        if (!wasFounded) {
            throw new Exception("Cannot find the item");
        }
    }

    public void deleteById(UUID id) throws Exception {
        // флаг, был ли найден entity в списке
        boolean wasFounded = false;
        for (T currentEntity : data)
            if (currentEntity.getId().equals(id)) {
                wasFounded = true;

                data.remove(currentEntity);
                persist();
                break;
            }
        if (!wasFounded) {
            throw new Exception("Cannot find the entity with id " + id);
        }
    }

    public void deleteByName(String name) throws Exception {
        // флаг, был ли найден entity в списке
        boolean wasFounded = false;
        for (T currentEntity : data)
            if (currentEntity.getName().equals(name)) {
                wasFounded = true;

                data.remove(currentEntity);
                persist();
                break;
            }
        if (!wasFounded) {
            throw new Exception("Cannot find the entity with name " + name);
        }
    }
}
