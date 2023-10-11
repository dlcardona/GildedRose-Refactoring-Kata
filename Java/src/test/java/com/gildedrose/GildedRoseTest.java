package com.gildedrose;

import com.gildedrose.enums.Category;
import com.gildedrose.exception.ItemQualityGreaterThanFiftyException;
import com.gildedrose.exception.NegativeItemQualityException;
import com.gildedrose.exception.SulfurasItemInvalidQualityValueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.getItems()[0].getName());
    }

    @Test
    void agedBrieItemIncreasesQualityByOneTest() {
        Item[] items = new Item[] { new Item(Category.AGED_BRIE.getDescription(), 2, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.getItems()[0].getQuality());
        assertEquals(1, app.getItems()[0].getSellIn());
    }

    @Test
    void agedBrieItemIncreasesQualityByTwoTest() {
        Item[] items = new Item[] { new Item(Category.AGED_BRIE.getDescription(), -1, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.getItems()[0].getQuality());
        assertEquals(-2, app.getItems()[0].getSellIn());
    }

    @Test
    void sulfurasItemRemainsTheSame() {
        Item[] items = new Item[] { new Item(Category.SULFURAS.getDescription(), 10, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.getItems()[0].getQuality());
        assertEquals(10, app.getItems()[0].getSellIn());
    }

    @Test
    void backstageBassesItemIncreasesQualityByOneTest() {
        Item[] items = new Item[] { new Item(Category.BACKSTAGE_PASSES.getDescription(), 15, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.getItems()[0].getQuality());
        assertEquals(14, app.getItems()[0].getSellIn());
    }

    @Test
    void backstageBassesItemIncreasesQualityByTwoTest() {
        Item[] items = new Item[] { new Item(Category.BACKSTAGE_PASSES.getDescription(), 8, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.getItems()[0].getQuality());
        assertEquals(7, app.getItems()[0].getSellIn());
    }

    @Test
    void backstageBassesItemIncreasesQualityByThreeTest() {
        Item[] items = new Item[] { new Item(Category.BACKSTAGE_PASSES.getDescription(), 3, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.getItems()[0].getQuality());
        assertEquals(2, app.getItems()[0].getSellIn());
    }

    @Test
    void conjuredItemDecreasesQualityByTwo() {
        Item[] items = new Item[] { new Item(Category.CONJURED.getDescription(), 5, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.getItems()[0].getQuality());
        assertEquals(4, app.getItems()[0].getSellIn());
    }

    @Test
    void otherItemsDecreaseQualityByTwo() {
        Item[] items = new Item[] { new Item(Category.DEXTERITY_VEST.getDescription(), -1, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.getItems()[0].getQuality());
        assertEquals(-2, app.getItems()[0].getSellIn());
    }

    @Test
    void otherItemsDecreaseQualityByOne() {
        Item[] items = new Item[] { new Item(Category.ELIXIR_OF_THE_MONGOOSE.getDescription(), 3, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.getItems()[0].getQuality());
        assertEquals(2, app.getItems()[0].getSellIn());
    }

    @Test
    void itemQualityMustBePositiveTest() {
        Item[] items = new Item[] { new Item(Category.DEXTERITY_VEST.getDescription(), -1, -5) };
        GildedRose app = new GildedRose(items);
        assertThrows(NegativeItemQualityException.class, () -> app.updateQuality());
    }

    @Test
    void itemQualityShouldBeLessThanOrEqualToFiftyTest() {
        Item[] items = new Item[] { new Item(Category.DEXTERITY_VEST.getDescription(), -1, 90) };
        GildedRose app = new GildedRose(items);
        assertThrows(ItemQualityGreaterThanFiftyException.class, () -> app.updateQuality());
    }

    @Test
    void sulfurasQualityShouldAlwaysBeEightyTest() {
        Item[] items = new Item[] { new Item(Category.SULFURAS.getDescription(), -1, 90) };
        GildedRose app = new GildedRose(items);
        assertThrows(SulfurasItemInvalidQualityValueException.class, () -> app.updateQuality());
    }

    @Test
    void itemQualityCanNotGreterThanFiftyTest() {
        Item[] items = new Item[] { new Item(Category.AGED_BRIE.getDescription(), 5, 50) };
        GildedRose app = new GildedRose(items);
        assertEquals(50, app.getItems()[0].getQuality());
    }

    @Test
    void itemQualityCanNotBeNegativeTest() {
        Item[] items = new Item[] { new Item(Category.CONJURED.getDescription(), 5, 1) };
        GildedRose app = new GildedRose(items);
        assertEquals(1, app.getItems()[0].getQuality());
    }

}
