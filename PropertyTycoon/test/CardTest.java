/**
 *
 * @author Oliver(134730)
 */

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class CardTest {

    private Card a;
    private Card b;
    private Card c;

    @Test
    public void testCardCreation() {
        assertNotEquals(null, a.getDescription());
        assertNotEquals(null, b.getDescription());
        assertNotEquals(null, c.getDescription());
        assertNotEquals(null, a.getAction());
        assertNotEquals(null, b.getAction());
        assertNotEquals(null, c.getAction());
        assertNotEquals(null, a.getValue());
        assertNotEquals(null, b.getValue());
        assertNotEquals(null, c.getValue());
    }

    @Test
    public void testCardDescription() {
        assertEquals("Bank pays you divided of £50", a.getDescription());
        assertEquals("Mega late night taxi bill pay £50",b.getDescription());
        assertEquals("Get out of jail free",c.getDescription());
    }

    @Test
    public void testCardAction() {
        assertEquals("get",a.getAction());
        assertEquals("pay",b.getAction());
        assertEquals("multipass",c.getAction());
    }

    @Test
    public void testCardValue() {
        assertEquals(50,a.getValue());
        assertEquals(50,b.getValue());
        assertEquals(1,c.getValue());
    }

    @BeforeEach
    public void init() {
        a = new Card("Bank pays you divided of £50","get",50);
        b = new Card("Mega late night taxi bill pay £50","pay",50);
        c = new Card("Get out of jail free","multipass",1);
    }

    @AfterEach
    public void endTests() {
        a = null;
        b = null;
        c = null;
    }
}