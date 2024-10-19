/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package proyecto1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LifoTest {

    public LifoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of constructor, of class Lifo.
     */
    @Test
    public void testConstructor() {
        Lifo<Integer> pila = new Lifo<>();

        assertEquals(0, pila.size());
        assertTrue(pila.vacia());
    }

    /**
     * Test of size method, of class Lifo.
     */
    @Test
    public void testSize() {
        Lifo<Integer> pila = new Lifo<>();

        assertEquals(0, pila.size());
        assertTrue(pila.vacia());
        assertEquals("Lifo(0): //", pila.toString());

        pila.apilar(1);
        assertFalse(pila.vacia());
        assertEquals(1, pila.size());
        assertEquals("Lifo(1): 1 <- //", pila.toString());
    }

    /**
     * Test of vacia method, of class Lifo.
     */
    @Test
    public void testVacia() {

        Lifo<Integer> pila = new Lifo<>();
        assertTrue(pila.vacia());

        pila.apilar(1);
        assertFalse(pila.vacia());
    }

    /**
     * Test of apilar method, of class Lifo.
     */
    @Test
    public void testApilar() {
        Lifo<Integer> pila = new Lifo<>();
        pila.apilar(1);
        assertEquals(1, pila.size());
        assertEquals("Lifo(1): 1 <- //", pila.toString());

        pila.apilar(2);
        assertEquals(2, pila.size());
        assertEquals("Lifo(2): 2 <- 1 <- //", pila.toString());
    }

    /**
     * Test of desapilar method, of class Lifo.
     */
    @Test
    public void testDesapilar() {

        Lifo<Integer> pila = new Lifo<>();
        pila.apilar(1);
        pila.apilar(2);
        assertEquals(2, pila.size());
        assertEquals("Lifo(2): 2 <- 1 <- //", pila.toString());
        assertEquals(2, pila.desapilar());
        assertEquals(1, pila.size());
        assertEquals("Lifo(1): 1 <- //", pila.toString());
        assertEquals(1, pila.desapilar());
        assertEquals(0, pila.size());
        assertEquals("Lifo(0): //", pila.toString());
        assertNull(pila.desapilar());
        assertEquals(0, pila.size());
        assertTrue(pila.vacia());
        assertEquals("Lifo(0): //", pila.toString());
    }

    /**
     * Test of tope method, of class Lifo.
     */
    @Test
    public void testTope() {

        Lifo<Integer> pila = new Lifo<>();
        assertNull(pila.tope());
        pila.apilar(1);
        assertEquals(1, pila.tope());
        pila.apilar(2);
        assertEquals(2, pila.tope());
    }

    /**
     * Test of vaciar method, of class Lifo.
     */
    @Test
    public void testVaciar() {

        Lifo<Integer> pila = new Lifo<>();
        pila.apilar(1);
        pila.apilar(2);
        assertEquals(2, pila.size());
        assertEquals("Lifo(2): 2 <- 1 <- //", pila.toString());
        pila.vaciar();
        assertEquals(0, pila.size());
        assertEquals("Lifo(0): //", pila.toString());
        assertTrue(pila.vacia());
    }

    /**
     * Test of toString method, of class Lifo.
     */
    @Test
    public void testToString() {

        Lifo<Integer> pila = new Lifo<>();
        assertEquals("Lifo(0): //", pila.toString());
        pila.apilar(1);
        assertEquals("Lifo(1): 1 <- //", pila.toString());
        pila.apilar(2);
        assertEquals("Lifo(2): 2 <- 1 <- //", pila.toString());
    }

}
