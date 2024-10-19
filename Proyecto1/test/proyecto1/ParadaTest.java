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

public class ParadaTest {

    public ParadaTest() {
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
     * Test of the constructor with 2 args method, of class Parada.
     */
    @Test
    public void testConstructor2Args() {
        Parada parada = new Parada("nombre", "alias");
        assertEquals("nombre", parada.getNombre());
        assertEquals("alias", parada.getAlias());
        assertEquals("nombre:alias", parada.toString());

        try {
            new Parada(null);
            fail();
        } catch (IllegalArgumentException ex) {
            assertEquals("El nombre de la parada no puede estar vacío.", ex.getMessage());
        }

        try {
            new Parada("");
            fail();
        } catch (IllegalArgumentException ex) {
            assertEquals("El nombre de la parada no puede estar vacío.", ex.getMessage());
        }
    }

    /**
     * Test of the constructor with 1 args method, of class Parada.
     */
    @Test
    public void testConstructor1Args() {
        Parada parada = new Parada("nombre");
        assertEquals("nombre", parada.getNombre());
        assertNull(parada.getAlias());
        assertEquals("nombre", parada.toString());
    }

    /**
     * Test of getNombre method, of class Parada.
     */
    @Test
    public void testGetNombre() {
        Parada parada = new Parada("nombre");
        assertEquals("nombre", parada.getNombre());
    }

    /**
     * Test of setNombre method, of class Parada.
     */
    @Test
    public void testSetNombre() {
        Parada parada = new Parada("nombre");
        parada.setNombre("nombre2");
        assertEquals("nombre2", parada.getNombre());
    }

    /**
     * Test of getAlias method, of class Parada.
     */
    @Test
    public void testGetAlias() {
        Parada parada = new Parada("nombre", "alias");
        assertEquals("alias", parada.getAlias());
    }

    /**
     * Test of setAlias method, of class Parada.
     */
    @Test
    public void testSetAlias() {
        Parada parada = new Parada("nombre");
        parada.setAlias("alias");
        assertEquals("alias", parada.getAlias());
    }

    /**
     * Test of toString method, of class Parada.
     */
    @Test
    public void testToString() {
        Parada parada = new Parada("nombre", "alias");
        assertEquals("nombre:alias", parada.toString());
    }

}
