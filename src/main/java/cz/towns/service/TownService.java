package cz.towns.service;

import cz.towns.model.Town;
import cz.towns.model.TownPart;
import cz.towns.repositories.TownPartsRepository;
import cz.towns.repositories.TownsRepository;

import java.util.List;

public class TownService {

    private final TownsRepository townsRepository;
    private final TownPartsRepository townPartsRepository;

    public TownService() {
        this.townsRepository = new TownsRepository();
        this.townPartsRepository = new TownPartsRepository();
    }

    public void addTowns(List<Town> towns) {
        townsRepository.addTowns(towns);
    }

    public void addTownParts(List<TownPart> towns) {
        townPartsRepository.addTownParts(towns);
    }
}