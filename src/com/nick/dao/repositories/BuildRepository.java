package com.nick.dao.repositories;

import com.google.gson.Gson;
import com.nick.dao.entities.Build;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BuildRepository implements BKRepository<Build> {
    private List<Build> buildList = new ArrayList<>();

    public Optional<Build> get(UUID id) throws Exception {
        for (Build build : buildList) {
            if (build.getId().equals(id)) {
                return Optional.of(build);
            }
        }
        return Optional.empty();
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

    //add build in List
    public void add(Build build) {
        buildList.add(build);
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

    public void delete(Build build) throws Exception {
        // флаг, был ли найден build в списке
        boolean buildWasFounded = false;

        for (Build currentBuild : buildList)
            if (currentBuild.getId().equals(build.getId())) {
                buildWasFounded = true;

                buildList.remove(currentBuild);
                break;
            }
        if (!buildWasFounded) {
            throw new Exception("Cannot find the build");
        }
    }

    public void persist() throws IOException {
        // create space on disk for save
        Path filePath = Files.createDirectories(Paths.get("C:\\BuildKeeper\\saveData"));
        File file = new File(filePath + "\\build.json");

        Gson gson = new Gson();
        String buildListJson = gson.toJson(buildList);

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        bufferedWriter.write(buildListJson);
        bufferedWriter.close();
    }

    // TODO: 28.01.2020
    //load from disk
    public void load(String json) throws IOException {
        Gson gson = new Gson();

        Build build = gson.fromJson(json, Build.class);
    }
}
