package com.baeldung.algorithms.quadtree;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class QuadTreeSearchUnitTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuadTreeSearchUnitTest.class);

    private static QuadTree quadTree;

    @BeforeAll
    public static void setUp() {
        var area = new Region(0, 0, 400, 400);
        quadTree = new QuadTree(area);

        var points = new float[][] { { 21, 25 }, { 55, 53 }, { 70, 318 }, { 98, 302 },
            { 49, 229 }, { 135, 229 }, { 224, 292 }, { 206, 321 }, { 197, 258 }, { 245, 238 } };

        for (var i = 0; i < points.length; i++) {
            var point = new Point(points[i][0], points[i][1]);
            quadTree.addPoint(point);
        }
        LOGGER.debug("\n" + quadTree.printTree(""));
        LOGGER.debug("==============================================");
    }

    @Test
    void givenQuadTree_whenSearchingForRange_thenReturn1MatchingItem() {
        var searchArea = new Region(200, 200, 250, 250);
        var result = quadTree.search(searchArea, null, "");

        LOGGER.debug(result.toString());
        LOGGER.debug(quadTree.printSearchTraversePath());

        assertEquals(1, result.size());
        assertArrayEquals(new float[] { 245, 238 },
            new float[]{result.get(0).getX(), result.get(0).getY() }, 0);
    }

    @Test
    void givenQuadTree_whenSearchingForRange_thenReturn2MatchingItems() {
        var searchArea = new Region(0, 0, 100, 100);
        var result = quadTree.search(searchArea, null, "");

        LOGGER.debug(result.toString());
        LOGGER.debug(quadTree.printSearchTraversePath());

        assertEquals(2, result.size());
        assertArrayEquals(new float[] { 21, 25 },
            new float[]{result.get(0).getX(), result.get(0).getY() }, 0);
        assertArrayEquals(new float[] { 55, 53 },
            new float[]{result.get(1).getX(), result.get(1).getY() }, 0);

    }
}
