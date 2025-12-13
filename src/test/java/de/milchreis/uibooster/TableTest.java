package de.milchreis.uibooster;

import de.milchreis.uibooster.model.Weights;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TableTest {

    @Test
    @DisplayName("create table with weighted width")
    void createTableWithWeightedWidth() {
        new UiBooster().showTable(
            new String[][]{
                {"Jimmy Johnson", "35", "Zombieland"},
                {"Danny Durango", "23", "Hangover"},
                {"Larry Berry", "54", ""}
            },
            List.of("Name", "Age", "Favorite movie"),
            "Favorite movies",
            Weights.LARGE_SMALL_SMALL);
    }
}
