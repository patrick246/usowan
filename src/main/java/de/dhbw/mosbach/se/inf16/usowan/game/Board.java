package de.dhbw.mosbach.se.inf16.usowan.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private List<BoardRegion> regions = new ArrayList<>();
    private Vector2 size;

    public Board(Vector2 size, BoardRegion... boardRegions) {
        regions.addAll(Arrays.asList(boardRegions));
        if(!checkRegionsValid()) {
            throw new IllegalStateException("Regions cannot intersect");
        }
        this.size = size;
    }

    public void toggle(Vector2 location) {
        getField(location).toggle();
    }

    public Field getField(Vector2 location) {
        BoardRegion region = regions
                .stream()
                .filter(r -> r.pointInRegion(location))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Field not found" + location));
        return region.getField(location);
    }

    public Vector2 getSize() {
        return size;
    }

    public boolean isValid() {
        boolean regionsValid = regions.stream().allMatch(BoardRegion::isRegionValidLiars);
        boolean blackFieldsValid = regions.stream().allMatch(BoardRegion::isRegionValidBlacks);
        return regionsValid && blackFieldsValid;
    }

    public Field[] getFields() {
        Field[] fields = new Field[size.getX() * size.getY()];
        for(int y = 0; y < size.getY(); y++) {
            for(int x = 0; x < size.getX(); x++) {
                fields[y * size.getX() + x] = getField(new Vector2(x, y));
            }
        }
        return fields;
    }

    public List<BoardRegion> getRegions() {
        return this.regions;
    }

    private boolean checkRegionsValid() {
        for(int i = 0; i < regions.size(); i++) {
            for(int j = i; j < regions.size(); j++) {
                if(regions.get(i) == regions.get(j))
                    continue;
                if(regions.get(i).intersects(regions.get(j)))
                    return false;
            }
        }
        return true;
    }
}
