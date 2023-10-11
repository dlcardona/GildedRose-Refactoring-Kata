package com.gildedrose.enums;

import java.util.HashMap;
import java.util.Map;

public enum Category {

    AGED_BRIE("Aged Brie"),
    SULFURAS("Sulfuras, Hand of Ragnaros"),
    BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert"),
    CONJURED("Conjured Mana Cake"),
    DEXTERITY_VEST("+5 Dexterity Vest"),
    ELIXIR_OF_THE_MONGOOSE("Elixir of the Mongoose"),
    FOO("foo");

    private static final Map<String, Category> BY_DESCRIPTION = new HashMap<>();

    static {
        for (Category category: values()) {
            BY_DESCRIPTION.put(category.description, category);
        }
    }

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public static Category valueOfDescription(String description) {
        return BY_DESCRIPTION.get(description);
    }

    public String getDescription() {
        return description;
    }
}
