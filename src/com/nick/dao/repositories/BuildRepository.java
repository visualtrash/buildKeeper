package com.nick.dao.repositories;

import com.nick.dao.entities.Build;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BuildRepository extends AbstractRepository<Build> {
    public Optional<Build> get(String name) {
        for (Build build : data) {
            if (build.getName().equals(name)) {
                return Optional.of(build);
            }
        }
        return Optional.empty();
    }

    public List getAll() {
        return data;
    }

    public Optional<Build> get(UUID id) {
        for (Build build : data) {
            if (build.getId().equals(id)) {
                return Optional.of(build);
            }
        }
        return Optional.empty();
    }


    public void update(Build buildToUpdate) {
        Optional<Build> optionalBuild = get(buildToUpdate.getId());

        if (optionalBuild.isPresent()) {
            Build build1 = optionalBuild.get();
            build1.setName(buildToUpdate.getName());
            build1.setHero(buildToUpdate.getHero());
            build1.setAbilities(buildToUpdate.getAbilities());
            build1.setItems(buildToUpdate.getItems());
            build1.setRune(buildToUpdate.getRune());
        } else System.out.println("build not found(null)");

    }

    //add build in List
    public void add(Build build) {
        data.add(build);
        update(build);
    }

    public void delete(Build build) throws Exception {
        // флаг, был ли найден build в списке
        boolean buildWasFounded = false;

        for (Build currentBuild : data)
            if (currentBuild.getId().equals(build.getId())) {
                buildWasFounded = true;

                data.remove(currentBuild);
                update(build);
                break;
            }
        if (!buildWasFounded) {
            throw new Exception("Cannot find the build");
        }
    }

    public String getSaveFileName() {
        return "builds";
    }
}