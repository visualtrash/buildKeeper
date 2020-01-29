package com.nick.dao.repositories;

import com.google.gson.Gson;
import com.nick.dao.entities.Build;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BuildRepository implements BKRepository<Build> {
    private List<Build> buildList = new ArrayList<>();
    private File file = new File("\\BuildKeeper\\saveData\\build.json");

    public static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.US_ASCII);
    }

    public Optional get(String name) {
        for (Build build : buildList) {
            if (build.getName().equals(name)) {
                return Optional.of(build);
            }
        }
        return Optional.empty();
    }

    public List getAll() {
        return buildList;
    }

    public Optional<Build> get(UUID id) {
        for (Build build : buildList) {
            if (build.getId().equals(id)) {
                return Optional.of(build);
            }
        }
        return Optional.empty();
    }


    public void update(Build buildToUpdate) throws Exception {
        Optional<Build> optionalBuild = get(buildToUpdate.getId());

        if (optionalBuild.isPresent()) {
            Build build1 = optionalBuild.get();
            build1.setName(buildToUpdate.getName());
            build1.setHero(buildToUpdate.getHero());
            build1.setAbility(buildToUpdate.getAbility());
            build1.setItems(buildToUpdate.getItems());
            build1.setRune(buildToUpdate.getRune());
        } else System.out.println("build not found(null)");

    }

    //add build in List
    public void add(Build build) throws Exception {
        buildList.add(build);
        update(build);
    }

    public void persist() throws IOException {
        // create space on disk for save
        Gson gson = new Gson();
        String buildListJson = gson.toJson(buildList);

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        bufferedWriter.write(buildListJson);
        bufferedWriter.close();
    }

    public void delete(Build build) throws Exception {
        // флаг, был ли найден build в списке
        boolean buildWasFounded = false;

        for (Build currentBuild : buildList)
            if (currentBuild.getId().equals(build.getId())) {
                buildWasFounded = true;

                buildList.remove(currentBuild);
                update(build);
                break;
            }
        if (!buildWasFounded) {
            throw new Exception("Cannot find the build");
        }
    }

    //load from disk
    public List<Build> load() {
        if (file.exists()) {
            String stringJson = "";

            try {
                stringJson = readFile(file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }

            Gson gson = new Gson();
            buildList = gson.fromJson(stringJson, List.class);
        }
        return buildList;
    }
}