package ecoTrail_collection_exercise;

import java.util.*;

public class EcoTrailApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TrailUtil util = new TrailUtil();

        System.out.println("Enter the number of trails to be added");
        int n = Integer.parseInt(sc.nextLine());

        System.out.println("Enter trail details");
        for (int i = 0; i < n; i++) {
            String[] data = sc.nextLine().split(":");
            Trail t = new Trail(
                    data[0],
                    data[1],
                    data[2],
                    data[3],
                    Integer.parseInt(data[4])
            );
            util.addTrailRecord(t);
        }

        System.out.println("Enter the Trail Id to check hike status");
        String id = sc.nextLine();
        Trail trail = util.getTrailById(id);
        if (trail != null) {
            System.out.println(trail);
        } else {
            System.out.println("Trail Id " + id + " not found");
        }

        System.out.println("Most hiked trails are");
        util.getMostHikedTrails().forEach(System.out::println);

        System.out.println("Region-wise hike counts");
        util.getHikeCountByRegion()
                .forEach((k, v) -> System.out.println(k + ": " + v + " hikes"));

        System.out.println("Trails grouped by difficulty");
        util.groupTrailsByDifficulty().forEach((k, v) -> {
            System.out.println(k);
            v.forEach(System.out::println);
        });

        System.out.println("Enter Trail Id to update hikes and additional hikes");
        String[] upd = sc.nextLine().split(" ");
        if (util.updateHikeCount(upd[0], Integer.parseInt(upd[1]))) {
            System.out.println("Updated " + upd[0] + " by " + upd[1] + " hikes");
            System.out.println(util.getTrailById(upd[0]));
        }

        System.out.println("Filter trails by region and difficulty");
        String[] filter = sc.nextLine().split(" ");
        util.filterTrails(filter[0], filter[1]).forEach(System.out::println);

        System.out.println("Top " + 1 + " trails per region");
        util.getTopTrailsByRegion(1).forEach((k, v) -> {
            System.out.println(k);
            v.forEach(System.out::println);
        });

        System.out.println("Difficulty statistics");
        util.getDifficultyStats()
                .forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
