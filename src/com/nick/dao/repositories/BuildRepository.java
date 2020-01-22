package com.nick.dao.repositories;

import com.nick.dao.entities.Build;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BuildRepository implements BKRepository<Build> {
    private List<Build> buildList;

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
        for (Build buildOfList : buildList) {
            System.out.println(buildOfList.getName());
        }
        return buildList;
    }

    //add build in List
    public void save(Build build) {
        buildList.add(build);
    }

    //save build on disk
    @SuppressWarnings("Duplicates")
    public void update(Build build) {
        StringBuilder result = new StringBuilder();

        for (Build currentBuild : buildList) {
            String buildLine =
                    currentBuild.getName() + "<|>" + currentBuild.getHero() + "<|>"
                            + currentBuild.getItems() + "<|>" + currentBuild.getAbility() + "<|>"
                            + currentBuild.getRune() + "<|>" + currentBuild.getId();
            result.append(buildLine).append("\n");
        }

        File file = new File("C://BKrep", "BuildRep.txt");

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(result.toString());
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
