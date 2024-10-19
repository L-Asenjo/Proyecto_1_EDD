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

public class LineaTest {

    public LineaTest() {
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
     * Test of constructor method, of class Linea.
     */
    @Test
    public void LineaAL() {
        try {
            new Linea("");
            fail();
        } catch (IllegalArgumentException ex) {
            assertEquals("El nombre de la linea no puede estar vacío", ex.getMessage());
        }
        Linea linea = new Linea("Linea 1");
        assertEquals("Linea 1", linea.getNombre());
        assertTrue(linea.vacia());
        assertEquals(0, linea.size());
        assertEquals(1, linea.getTamArreglo());

    }

    /**
     * Test of getNombre method, of class Linea.
     */
    @Test
    public void testGetNombre() {
        Linea linea = new Linea("Linea 1");
        assertEquals("Linea 1", linea.getNombre());
    }

    /**
     * Test of setNombre method, of class Linea.
     */
    @Test
    public void testSetNombre() {
        Linea linea = new Linea("Linea 1");
        linea.setNombre("Linea 2");
        assertEquals("Linea 2", linea.getNombre());
    }

    /**
     * Test of getParadas method, of class Linea.
     */
    @Test
    public void testGetParadas() {
        Linea linea = new Linea("Linea 1");
        assertNull(linea.getParadas());

        Parada parada = new Parada("Parada 1");
        linea.agregarParada(parada);
        String[] paradas = linea.getParadas();
        assertEquals(1, paradas.length);
        assertEquals("Parada 1", paradas[0]);

        Parada parada2 = new Parada("Parada 2");
        linea.agregarParada(parada2);
        paradas = linea.getParadas();
        assertEquals(2, paradas.length);
        assertEquals("Parada 1", paradas[0]);
        assertEquals("Parada 2", paradas[1]);

        Parada parada3 = new Parada("Parada 3", "Parada alias 3");
        linea.agregarParada(parada3);
        paradas = linea.getParadas();
        assertEquals(3, paradas.length);
        assertEquals("Parada 1", paradas[0]);
        assertEquals("Parada 2", paradas[1]);
        assertEquals("Parada 3:Parada alias 3", paradas[2]);
    }

    /**
     * Test of agregarParada method, of class Linea.
     */
    @Test
    public void testAgregarParada() {

        Parada parada = new Parada("Parada 1");
        Linea linea = new Linea("Linea 1");
        assertTrue(linea.agregarParada(parada));
        assertEquals(1, linea.size());
        assertEquals("Parada 1", linea.get(0).getNombre());
        Parada parada2 = new Parada("Parada 2");
        assertTrue(linea.agregarParada(parada2));
        assertEquals(2, linea.size());
        assertEquals("Parada 1", linea.get(0).getNombre());
        assertEquals("Parada 2", linea.get(1).getNombre());

        Parada parada3 = new Parada("Parada 3");
        assertTrue(linea.agregarParada(parada3));
        assertEquals(3, linea.size());
        assertEquals("Parada 1", linea.get(0).getNombre());
        assertEquals("Parada 2", linea.get(1).getNombre());
        assertEquals("Parada 3", linea.get(2).getNombre());

        Parada parada4 = null;
        assertFalse(linea.agregarParada(parada4));
        assertEquals(3, linea.size());
        assertEquals("Parada 1", linea.get(0).getNombre());
        assertEquals("Parada 2", linea.get(1).getNombre());
        assertEquals("Parada 3", linea.get(2).getNombre());

        Parada parada5 = new Parada("Parada 1");
        assertFalse(linea.agregarParada(parada5));
        assertEquals(3, linea.size());
        assertEquals("Parada 1", linea.get(0).getNombre());
        assertEquals("Parada 2", linea.get(1).getNombre());
        assertEquals("Parada 3", linea.get(2).getNombre());
    }

    /**
     * Test of removerParada method, of class Linea.
     */
    @Test
    public void testRemoverParada() {

        Parada parada = new Parada("Parada 1");
        Linea linea = new Linea("Linea 1");
        linea.agregarParada(parada);
        assertEquals(1, linea.size());
        linea.removerParada(0);
        assertEquals(0, linea.size());

        Parada parada2 = new Parada("Parada 2");
        linea.agregarParada(parada);
        linea.agregarParada(parada2);
        linea.agregarParada(new Parada("Parada 3"));
        linea.agregarParada(new Parada("Parada 4"));

        assertEquals(4, linea.size());
        assertEquals(4, linea.getTamArreglo());

        linea.removerParada(0);
        assertEquals(3, linea.size());
        assertEquals(3, linea.getTamArreglo());

        assertEquals("Parada 2", linea.getParadas()[0]);
        assertEquals("Parada 3", linea.getParadas()[1]);
        assertEquals("Parada 4", linea.getParadas()[2]);

        linea.removerParada(2);
        assertEquals(2, linea.size());
        assertEquals(2, linea.getTamArreglo());
        assertEquals("Parada 2", linea.getParadas()[0]);
        assertEquals("Parada 3", linea.getParadas()[1]);

        linea.removerParada(1);
        assertEquals(1, linea.size());
        assertEquals(1, linea.getTamArreglo());
        assertEquals("Parada 2", linea.getParadas()[0]);

        linea.removerParada(0);
        assertEquals(0, linea.size());
        assertEquals(0, linea.getTamArreglo());
    }

    /**
     * Test of buscarParadaPorNombre method, of class Linea.
     */
    @Test
    public void testBuscarParadaPorNombre() {
        Linea linea = new Linea("Linea 1");
        linea.agregarParada(new Parada("Parada 1"));
        assertEquals(0, linea.buscarParadaPorNombre("Parada 1"));
        assertEquals(-1, linea.buscarParadaPorNombre("Parada 2"));

        linea.agregarParada(new Parada("Parada 2"));
        assertEquals(0, linea.buscarParadaPorNombre("Parada 1"));
        assertEquals(1, linea.buscarParadaPorNombre("Parada 2"));
    }

    /**
     * Test of buscarParadaPorAlias method, of class Linea.
     */
    @Test
    public void testBuscarParadaPorAlias() {
        Linea linea = new Linea("Linea 1");
        linea.agregarParada(new Parada("Parada 1", "Parada alias 1"));
        assertEquals(0, linea.buscarParadaPorAlias("Parada alias 1"));
        assertEquals(-1, linea.buscarParadaPorAlias("Parada 2"));

        linea.agregarParada(new Parada("Parada 3"));

        linea.agregarParada(new Parada("Parada 2", "Parada alias 2"));
        assertEquals(0, linea.buscarParadaPorAlias("Parada alias 1"));
        assertEquals(2, linea.buscarParadaPorAlias("Parada alias 2"));
    }

    /**
     * Test of vaciarLinea method, of class Linea.
     */
    @Test
    public void testVaciarLinea() {
        Linea linea = new Linea("Linea 1");
        linea.agregarParada(new Parada("Parada 1"));
        assertEquals(1, linea.size());
        linea.vaciarLinea();
        assertEquals(0, linea.size());
        assertEquals(0, linea.getTamArreglo());
    }

}
