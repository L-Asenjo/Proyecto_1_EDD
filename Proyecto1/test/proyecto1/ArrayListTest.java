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

public class ArrayListTest {

    public ArrayListTest() {
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
     * Test of the constructor with no arguments, of class ArrayList.
     */
    @Test
    public void testArrayList() {
        ArrayList<Integer> lista = new ArrayList<>();
        assertNotNull(lista);
        assertEquals(0, lista.size());
        assertTrue(lista.vacia());
        assertEquals("List(0): //", lista.toString());
        assertEquals(1, lista.getTamArreglo());
        for (int i = 0; i < 10; i++) {
            lista.agregar(i);
        }
        assertEquals(10, lista.size());
        assertFalse(lista.vacia());
        assertEquals("List(10): 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> //", lista.toString());
        assertEquals(11, lista.getTamArreglo());
        lista.agregar(10);
        assertEquals("List(11): 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> //", lista.toString());
        assertEquals(11, lista.getTamArreglo());
        lista.agregar(11);
        assertEquals("List(12): 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11 -> //", lista.toString());
        assertEquals(16, lista.getTamArreglo());
        assertEquals(12, lista.size());
    }

    /**
     * Test of the constructor with 1 argument, of class ArrayList.
     */
    @Test
    public void testArrayListInt() {
        ArrayList<Integer> lista = new ArrayList<>(10);
        assertNotNull(lista);
        assertEquals(0, lista.size());
        assertTrue(lista.vacia());
        assertEquals("List(0): //", lista.toString());
        assertEquals(1, lista.getTamArreglo());
        for (int i = 0; i < 10; i++) {
            lista.agregar(i);
        }
        assertEquals(10, lista.size());
        assertFalse(lista.vacia());
        assertEquals("List(10): 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> //", lista.toString());
        assertEquals(11, lista.getTamArreglo());
        lista.agregar(10);
        assertEquals("List(11): 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> //", lista.toString());
        assertEquals(11, lista.getTamArreglo());
        lista.agregar(11);
        assertEquals("List(12): 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11 -> //", lista.toString());
        assertEquals(21, lista.getTamArreglo());
        assertEquals(12, lista.size());
    }

    /**
     * Test of the constructor with 2 arguments, of class ArrayList.
     */
    @Test
    public void testArrayListIntInt() {
        ArrayList<Integer> lista = new ArrayList<>(3, 7);
        assertNotNull(lista);
        assertEquals(0, lista.size());
        assertTrue(lista.vacia());
        assertEquals("List(0): //", lista.toString());
        assertEquals(3, lista.getTamArreglo());
        for (int i = 0; i < 7; i++) {
            lista.agregar(i);
        }
        assertEquals(7, lista.size());
        assertFalse(lista.vacia());
        assertEquals("List(7): 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> //", lista.toString());
        assertEquals(10, lista.getTamArreglo());
        lista.agregar(7);
        assertEquals("List(8): 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> //", lista.toString());
        assertEquals(10, lista.getTamArreglo());
        lista.agregar(8);
        assertEquals("List(9): 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> //", lista.toString());
        assertEquals(10, lista.getTamArreglo());
        lista.agregar(9);
        assertEquals("List(10): 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> //", lista.toString());
        assertEquals(10, lista.getTamArreglo());
        lista.agregar(10);
        assertEquals("List(11): 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> //", lista.toString());
        assertEquals(17, lista.getTamArreglo());
        assertEquals(11, lista.size());
    }

    /**
     * Test of insertar method, of class ArrayList.
     */
    @Test
    public void testInsertar() {
        ArrayList<Integer> lista = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lista.agregar(i);
        }
        assertEquals("List(10): 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> //", lista.toString());
        assertEquals(11, lista.getTamArreglo());
        assertEquals(10, lista.size());
        lista.insertar(0, 10);
        assertEquals("List(11): 10 -> 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> //", lista.toString());
        assertEquals(11, lista.getTamArreglo());
        assertEquals(11, lista.size());
        lista.insertar(11, 11);
        assertEquals("List(12): 10 -> 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 11 -> //", lista.toString());
        assertEquals(16, lista.getTamArreglo());
        assertEquals(12, lista.size());
        lista.insertar(12, 12);
        assertEquals("List(13): 10 -> 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 11 -> 12 -> //",
                lista.toString());
        assertEquals(16, lista.getTamArreglo());
        assertEquals(13, lista.size());
        assertFalse(lista.insertar(14, 14));
        assertEquals("List(13): 10 -> 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 11 -> 12 -> //",
                lista.toString());
        assertEquals(16, lista.getTamArreglo());
        assertEquals(13, lista.size());
    }

    /**
     * Test of agregar method, of class ArrayList.
     */
    @Test
    public void testAgregar() {
        ArrayList<Integer> lista = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lista.agregar(i);
        }
        assertEquals("List(10): 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> //", lista.toString());
        assertEquals(11, lista.getTamArreglo());
        assertEquals(10, lista.size());
        lista.agregar(10);
        assertEquals("List(11): 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> //", lista.toString());
        assertEquals(11, lista.getTamArreglo());
    }

    /**
     * Test of get method, of class ArrayList.
     */
    @Test
    public void testGet() {
        ArrayList<Integer> lista = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lista.agregar(i);
        }
        assertEquals("List(10): 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> //", lista.toString());
        assertEquals(11, lista.getTamArreglo());
        assertEquals(10, lista.size());
        assertEquals(0, lista.get(0));
        assertEquals(1, lista.get(1));
        assertEquals(2, lista.get(2));
        assertEquals(3, lista.get(3));
        assertEquals(4, lista.get(4));
        assertEquals(5, lista.get(5));
        assertEquals(6, lista.get(6));
        assertEquals(7, lista.get(7));
        assertEquals(8, lista.get(8));
        assertEquals(9, lista.get(9));
    }

    /**
     * Test of set method, of class ArrayList.
     */
    @Test
    public void testSet() {
        ArrayList<Integer> lista = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lista.agregar(i);
        }
        assertEquals("List(10): 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> //", lista.toString());
        assertEquals(11, lista.getTamArreglo());
        assertEquals(10, lista.size());
        for (int i = 0; i < 10; i++) {
            lista.set(i, lista.get(i) + 10);
        }
        assertEquals("List(10): 10 -> 11 -> 12 -> 13 -> 14 -> 15 -> 16 -> 17 -> 18 -> 19 -> //", lista.toString());
        assertEquals(11, lista.getTamArreglo());
        assertEquals(10, lista.size());
    }

    /**
     * Test of compactar method, of class ArrayList.
     */
    @Test
    public void testCompactar() {
        ArrayList<Integer> lista = new ArrayList<>();
        lista.agregar(1);
        lista.agregar(2);
        assertEquals("List(2): 1 -> 2 -> //", lista.toString());
        assertEquals(6, lista.getTamArreglo());
        lista.compactar();
        assertEquals("List(2): 1 -> 2 -> //", lista.toString());
        assertEquals(2, lista.getTamArreglo());
    }

    /**
     * Test of remover method, of class ArrayList.
     */
    @Test
    public void testRemover() {
        ArrayList<Integer> lista = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lista.agregar(i);
        }
        assertEquals("List(10): 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> //", lista.toString());
        assertEquals(11, lista.getTamArreglo());
        assertEquals(10, lista.size());
        assertEquals(0, lista.remover(0));
        assertEquals("List(9): 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> //", lista.toString());
        assertEquals(11, lista.getTamArreglo());
        assertEquals(9, lista.size());
        assertEquals(9, lista.remover(8));
        assertEquals("List(8): 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> //", lista.toString());
        assertEquals(11, lista.getTamArreglo());
        assertEquals(8, lista.size());
        assertEquals(8, lista.remover(7));
        assertEquals("List(7): 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> //", lista.toString());
        assertEquals(11, lista.getTamArreglo());
        assertEquals(7, lista.size());
        assertEquals(7, lista.remover(6));
        assertEquals("List(6): 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> //", lista.toString());
        assertEquals(11, lista.getTamArreglo());
        assertEquals(6, lista.size());
        assertEquals(6, lista.remover(5));
        assertEquals("List(5): 1 -> 2 -> 3 -> 4 -> 5 -> //", lista.toString());
        assertEquals(5, lista.getTamArreglo());
        assertEquals(5, lista.size());
        assertEquals(5, lista.remover(4));
        assertEquals("List(4): 1 -> 2 -> 3 -> 4 -> //", lista.toString());
        assertEquals(5, lista.getTamArreglo());
        assertEquals(4, lista.size());
        assertEquals(4, lista.remover(3));
        assertEquals("List(3): 1 -> 2 -> 3 -> //", lista.toString());
        assertEquals(5, lista.getTamArreglo());
        assertEquals(3, lista.size());
        assertEquals(3, lista.remover(2));
        assertEquals("List(2): 1 -> 2 -> //", lista.toString());
        assertEquals(5, lista.getTamArreglo());
        assertEquals(2, lista.size());
        assertEquals(2, lista.remover(1));
        assertEquals("List(1): 1 -> //", lista.toString());
        assertEquals(5, lista.getTamArreglo());
        assertEquals(1, lista.size());
        assertEquals(1, lista.remover(0));
        assertEquals("List(0): //", lista.toString());
        assertEquals(5, lista.getTamArreglo());
        assertEquals(0, lista.size());
    }

    /**
     * Test of vaciar method, of class ArrayList.
     */
    @Test
    public void testVaciar() {
        ArrayList<Integer> lista = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lista.agregar(i);
        }
        assertEquals("List(10): 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> //", lista.toString());
        assertEquals(11, lista.getTamArreglo());
        assertEquals(10, lista.size());
        lista.vaciar();
        assertEquals("List(0): //", lista.toString());
        assertEquals(1, lista.getTamArreglo());
        assertEquals(0, lista.size());
    }

}
