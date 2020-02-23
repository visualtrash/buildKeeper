package com.nick.dal.repositories;

import com.google.gson.Gson;
import com.nick.dal.entities.BKEntity;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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

    abstract String getSaveFileName();
    abstract Type getRepositoryEntityListType();

    public void persist() throws IOException {
        String buildListJson = new Gson().toJson(data);

        if (!Paths.get("saveData").toFile().exists())
            Files.createDirectory(Paths.get("saveData"));

        Path path = Paths.get("saveData\\" + getSaveFileName() + ".json");
        if (!path.toFile().exists())
            Files.createFile(path);

        FileOutputStream fileOutputStream = new FileOutputStream(path.toFile());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream))) {
            bufferedWriter.write(buildListJson);
        }
    }
    private static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.US_ASCII);
    }

    public void load() {
        File file = Paths.get("saveData\\" + getSaveFileName() + ".json").toFile();

        if (file.exists()) {
            String stringJson = "";
            try {
                stringJson = readFile(file.getPath());

                data = new Gson().fromJson(stringJson, getRepositoryEntityListType());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<T> getAll(){return data;}
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
