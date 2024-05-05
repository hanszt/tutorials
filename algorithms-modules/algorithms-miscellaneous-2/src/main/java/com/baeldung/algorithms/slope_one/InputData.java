package com.baeldung.algorithms.slope_one;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.random.RandomGenerator;

public final class InputData {

    static final List<Item> items = List.of(
            new Item("Candy"),
            new Item("Drink"),
            new Item("Soda"),
            new Item("Popcorn"),
            new Item("Snacks")
    );

    public static Map<User, Map<Item, Double>> initializeData(int numberOfUsers, RandomGenerator random) {
        final Map<User, Map<Item, Double>> data = new HashMap<>();

        for (var i = 0; i < numberOfUsers; i++) {
            Map<Item, Double> newUser = new HashMap<>();
            Set<Item> newRecommendationSet = new HashSet<>();
            for (var j = 0; j < 3; j++) {
                newRecommendationSet.add(items.get(random.nextInt(items.size())));
            }
            for (var item : newRecommendationSet) {
                newUser.put(item, random.nextDouble());
            }
            data.put(new User("User " + i), newUser);
        }
        return data;
    }

}
