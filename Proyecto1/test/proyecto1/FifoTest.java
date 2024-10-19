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

public class FifoTest {

    public FifoTest() {
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
     * Test of the constructor, of class Fifo.
     */
    @Test
    public void testConstructor() {
        Fifo<Integer> cola = new Fifo<>();

        assertEquals(0, cola.size());
        assertTrue(cola.vacia());
        assertEquals("Fifo(0): //", cola.toString());
    }

    /**
     * Test of size method, of class Fifo.
     */
    @Test
    public void testSize() {
        Fifo<Integer> cola = new Fifo<>();

        assertEquals(0, cola.size());
        assertTrue(cola.vacia());
        assertEquals("Fifo(0): //", cola.toString());

        cola.encolar(1);

        assertEquals(1, cola.size());
        assertFalse(cola.vacia());
        assertEquals("Fifo(1): 1 <- //", cola.toString());
    }

    /**
     * Test of vacia method, of class Fifo.
     */
    @Test
    public void testVacia() {
        Fifo<Integer> cola = new Fifo<>();

        assertEquals(0, cola.size());
        assertTrue(cola.vacia());
        assertEquals("Fifo(0): //", cola.toString());

        cola.encolar(1);

        assertEquals(1, cola.size());
        assertFalse(cola.vacia());
        assertEquals("Fifo(1): 1 <- //", cola.toString());
    }

    /**
     * Test of encolar method, of class Fifo.
     */
    @Test
    public void testEncolar() {
        Fifo<Integer> cola = new Fifo<>();

        assertEquals(0, cola.size());
        assertTrue(cola.vacia());
        assertEquals("Fifo(0): //", cola.toString());

        assertTrue(cola.encolar(1));
        assertEquals(1, cola.size());
        assertFalse(cola.vacia());
        assertEquals("Fifo(1): 1 <- //", cola.toString());

        assertTrue(cola.encolar(2));
        assertEquals(2, cola.size());
        assertFalse(cola.vacia());
        assertEquals("Fifo(2): 1 <- 2 <- //", cola.toString());
    }

    /**
     * Test of desencolar method, of class Fifo.
     */
    @Test
    public void testDesencolar() {
        Fifo<Integer> cola = new Fifo<>();

        assertEquals(0, cola.size());
        assertTrue(cola.vacia());
        assertEquals("Fifo(0): //", cola.toString());
        assertNull(cola.desencolar());

        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);

        assertEquals(3, cola.size());
        assertFalse(cola.vacia());
        assertEquals("Fifo(3): 1 <- 2 <- 3 <- //", cola.toString());

        assertEquals(1, cola.desencolar());
        assertEquals(2, cola.size());
        assertFalse(cola.vacia());
        assertEquals("Fifo(2): 2 <- 3 <- //", cola.toString());

        assertEquals(2, cola.desencolar());
        assertEquals(1, cola.size());
        assertFalse(cola.vacia());
        assertEquals("Fifo(1): 3 <- //", cola.toString());

        assertEquals(3, cola.desencolar());
        assertEquals(0, cola.size());
        assertTrue(cola.vacia());
        assertEquals("Fifo(0): //", cola.toString());

        assertNull(cola.desencolar());
        assertEquals(0, cola.size());
        assertTrue(cola.vacia());
        assertEquals("Fifo(0): //", cola.toString());
    }

    /**
     * Test of frente method, of class Fifo.
     */
    @Test
    public void testFrente() {
        Fifo<Integer> cola = new Fifo<>();

        assertEquals(0, cola.size());
        assertTrue(cola.vacia());
        assertEquals("Fifo(0): //", cola.toString());
        assertNull(cola.frente());

        cola.encolar(1);
        cola.encolar(2);

        assertEquals(2, cola.size());
        assertFalse(cola.vacia());
        assertEquals("Fifo(2): 1 <- 2 <- //", cola.toString());

        assertEquals(1, cola.frente());
    }

    /**
     * Test of toString method, of class Fifo.
     */
    @Test
    public void testToString() {
        Fifo<Integer> cola = new Fifo<>();

        assertEquals(0, cola.size());
        assertTrue(cola.vacia());
        assertEquals("Fifo(0): //", cola.toString());

        cola.encolar(1);
        assertEquals(1, cola.size());
        assertFalse(cola.vacia());
        assertEquals("Fifo(1): 1 <- //", cola.toString());

        cola.encolar(2);
        assertEquals(2, cola.size());
        assertFalse(cola.vacia());
        assertEquals("Fifo(2): 1 <- 2 <- //", cola.toString());

        cola.encolar(3);
        assertEquals(3, cola.size());
        assertFalse(cola.vacia());
        assertEquals("Fifo(3): 1 <- 2 <- 3 <- //", cola.toString());
    }

}
