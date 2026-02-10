package ecoTrail_collection_exercise;

public class Trail {
    private String trailId;
    private String name;
    private String region;
    private String difficulty;
    private int hikeCount;

    public Trail() {
    }

    public Trail(String trailId, String name, String region, String difficulty, int hikeCount) {
        this.trailId = trailId;
        this.name = name;
        this.region = region;
        this.difficulty = difficulty;
        this.hikeCount = hikeCount;
    }

    public String getTrailId() {
        return trailId;
    }

    public void setTrailId(String trailId) {
        this.trailId = trailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getHikeCount() {
        return hikeCount;
    }

    public void setHikeCount(int hikeCount) {
        this.hikeCount = hikeCount;
    }

    @Override
    public String toString() {
        return trailId + " | " + name + " | " + region + " | " +
               difficulty + " | " + hikeCount + " hikes";
    }
}
