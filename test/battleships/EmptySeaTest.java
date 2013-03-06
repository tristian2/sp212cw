package batteships;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class EmptySeaTest {
    private EmptySea es;
    private Ocean oc;

    @Before
    public void setUp() {
        es = new EmptySeaImpl();
        oc = new OceanImpl();
    }

    @After
    public void tearDown() {
        es = null;
        oc = null;
    }

    @Test
    public void testConstructor() {
        assertEquals(1, es.getLength());
    }

    @Test
    public void testShootAt() {
        assertFalse(es.shootAt(0, 0));
        assertFalse(es.shootAt(5, 5));
    }

    @Test
    public void testIsSunk() {
        assertFalse(es.isSunk());
    }

    @Test
    public void testToString() {
        assertEquals(".", es.toString());
    }

    @Test
    public void testOkToPlaceShipAt() {
        assertFalse(es.okToPlaceShipAt(0, 0, false, oc));
        assertFalse(es.okToPlaceShipAt(0, 0, true, oc));
    }
}
