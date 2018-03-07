package de.dhbw.mosbach.se.inf16.usowan.game;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BoardRegionTest {
    @Test
    public void testIntersection() {
        BoardRegion r1 = new BoardRegion(new Vector2(0, 0), new Vector2(2,2));
        BoardRegion r2 = new BoardRegion(new Vector2(1, 1), new Vector2(2,2));

        assertThat(r1.intersects(r2), is(true));
        assertThat(r2.intersects(r1), is(true));
    }

    @Test
    public void testEdgeToEdge() {
        BoardRegion r1 = new BoardRegion(new Vector2(0, 0), new Vector2(2, 2));
        BoardRegion r2 = new BoardRegion(new Vector2(2, 2), new Vector2(2, 2));

        assertThat(r1.intersects(r2), is(false));
        assertThat(r2.intersects(r1), is(false));
    }

    @Test
    public void testPointInRegion() {
        BoardRegion region = new BoardRegion(new Vector2(3,5), new Vector2(4, 8));
        Vector2 p1 = new Vector2(3, 5);
        Vector2 p2 = new Vector2(5, 5);
        Vector2 p3 = new Vector2(5, 13);
        Vector2 p4 = new Vector2(8, 6);
        Vector2 p5 = new Vector2(7, 13);

        assertThat(region.pointInRegion(p1), is(true));
        assertThat(region.pointInRegion(p2), is(true));
        assertThat(region.pointInRegion(p3), is(false));
        assertThat(region.pointInRegion(p4), is(false));
        assertThat(region.pointInRegion(p5), is(false));

    }
}