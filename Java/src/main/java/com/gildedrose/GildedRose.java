package com.gildedrose;

import com.gildedrose.enums.Category;
import com.gildedrose.exception.ItemQualityGreaterThanFiftyException;
import com.gildedrose.exception.NegativeItemQualityException;
import com.gildedrose.exception.SulfurasItemInvalidQualityValueException;

class GildedRose {
    private Item[] items;

    private static final int BY_ONE = 1;
    private static final int BY_TWO = 2;
    private static final int BY_THREE = 3;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for(Item item : items) {
            if (item.getQuality() < 0) {
                throw new NegativeItemQualityException("The quality of an item must be positive");
            }

            if (item.getQuality() > 50 && (!item.getName().equals(Category.SULFURAS.getDescription()))) {
                throw new ItemQualityGreaterThanFiftyException("The quality of an item must be at most 50");
            }

            if (item.getName().equals(Category.SULFURAS.getDescription()) && (item.getQuality() != 80)) {
                throw new SulfurasItemInvalidQualityValueException("Sulfuras is a legendary item and as such its Quality is 80 and it never alters");
            }

            switch (Category.valueOfDescription(item.getName())) {
                case AGED_BRIE:
                    item.setQuality(manageAgedBrieItem(item));
                    break;
                case SULFURAS:
                    break;
                case BACKSTAGE_PASSES:
                    item.setQuality(manageBackstagePassesItem(item));
                    break;
                case CONJURED:
                    item.setQuality(decreaseQuality(BY_TWO, item));
                    break;
                default:
                    item.setQuality(manageRegularItems(item));
            }


            if (!item.getName().equals(Category.SULFURAS.getDescription())) {
                item.setSellIn(decreaseSellByDate(item.getSellIn()));
            }
        }
    }

    private int manageRegularItems(Item item) {
        return (item.getSellIn() >= 0) ? decreaseQuality(BY_ONE, item) : decreaseQuality(BY_TWO, item);
    }

    private int manageAgedBrieItem(Item item) {
        return (item.getSellIn() >= 0) ? increaseQuality(BY_ONE, item) : increaseQuality(BY_TWO, item);
    }

    private int manageBackstagePassesItem(Item item) {
        int newQuality = 0;

        if (item.getSellIn() > 0 && (item.getSellIn() <= 5)) {
            newQuality = increaseQuality(BY_THREE, item);
        } else if (item.getSellIn() > 5 && (item.getSellIn() <= 10)) {
            newQuality = increaseQuality(BY_TWO, item);
        } else if (item.getSellIn() > 10) {
            newQuality = increaseQuality(BY_ONE, item);
        }

        return newQuality;
    }

    private int increaseQuality(int amount, Item item) {
        return  (isQualityStillLessThanOrEqualToFifty(amount, item)) ? item.getQuality() + amount : item.getQuality();
    }

    private int decreaseQuality(int amount, Item item) {
        return  (isQualityStillPositive(amount, item)) ? item.getQuality() - amount : item.getQuality();
    }

    private int decreaseSellByDate(int currentSellByDate) {
        return currentSellByDate - 1;
    }

    private boolean isQualityStillPositive(int amount, Item item) {
        return  (item.getQuality() - amount >= 0);
    }

    private boolean isQualityStillLessThanOrEqualToFifty(int amount, Item item) {
        return ((item.getQuality() + amount) <= 50);
    }

    public Item[] getItems() {
        return items;
    }
}
