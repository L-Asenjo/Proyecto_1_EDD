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

import java.lang.reflect.Array;

public class SetTest {

    public SetTest() {
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
     * Test of the constructor method, of class Set.
     */
    @Test
    public void testSet1() {
        Set<Integer> conjunto = new Set<>();
        assertNotNull(conjunto);
        assertEquals(0, conjunto.size());
        assertTrue(conjunto.vacio());
        assertEquals("Set(0): {}", conjunto.toString());
    }

    /**
     * Test of the constructor method with an IList as an argument, of class Set.
     */
    @Test
    public void testSet2() {

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arrayList.agregar(i);
        }
        Set<Integer> conjunto = new Set<>(arrayList);
        assertEquals("Set(5): {0, 1, 2, 3, 4}", conjunto.toString());
    }

    /**
     * Test of the constructor method with an array as an argument, of class Set.
     */
    @Test
    public void testSet3() {
        Integer[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        Set<Integer> conjunto = new Set<>(values);
        assertEquals("Set(10): {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}", conjunto.toString());
    }

    /**
     * Test of the constructor method with a Set as an argument, of class Set.
     */
    @Test
    public void testSet4() {
        Set<Integer> conjunto1 = new Set<>();
        for (int i = 0; i < 5; i++) {
            conjunto1.agregar(i);
        }
        Set<Integer> conjunto2 = new Set<>(conjunto1);
        assertEquals("Set(5): {0, 1, 2, 3, 4}", conjunto2.toString());
    }

    /**
     * Test of size method, of class Set.
     */
    @Test
    public void testSize() {
        Set<Integer> conjunto = new Set<>();
        assertEquals(0, conjunto.size());
        assertTrue(conjunto.vacio());

        conjunto.agregar(1);
        assertEquals(1, conjunto.size());
        assertFalse(conjunto.vacio());
        assertEquals("Set(1): {1}", conjunto.toString());
    }

    /**
     * Test of vacío method, of class Set.
     */
    @Test
    public void testVacio() {
        Set<Integer> conjunto = new Set<>();
        assertEquals(0, conjunto.size());
        assertTrue(conjunto.vacio());

        conjunto.agregar(1);
        assertEquals(1, conjunto.size());
        assertFalse(conjunto.vacio());
        assertEquals("Set(1): {1}", conjunto.toString());
    }

    /**
     * Test of contiene method, of class Set.
     */
    @Test
    public void testContiene() {
        Set<Integer> conjunto = new Set<>();
        assertEquals(0, conjunto.size());
        assertTrue(conjunto.vacio());

        conjunto.agregar(1);
        assertEquals(1, conjunto.size());
        assertFalse(conjunto.vacio());
        assertEquals("Set(1): {1}", conjunto.toString());

        assertTrue(conjunto.contiene(1));
        assertFalse(conjunto.contiene(2));
        conjunto.agregar(2);
        assertTrue(conjunto.contiene(2));
        assertEquals("Set(2): {1, 2}", conjunto.toString());
    }

    /**
     * Test of agregar method with one value, of class Set.
     */
    @Test
    public void testAgregar1() {
        Set<Integer> conjunto = new Set<>();
        assertEquals(0, conjunto.size());
        assertTrue(conjunto.vacio());

        assertTrue(conjunto.agregar(1));
        assertEquals(1, conjunto.size());
        assertFalse(conjunto.vacio());
        assertEquals("Set(1): {1}", conjunto.toString());

        assertTrue(conjunto.contiene(1));
        assertFalse(conjunto.contiene(2));
        assertTrue(conjunto.agregar(2));
        assertTrue(conjunto.contiene(2));
        assertEquals("Set(2): {1, 2}", conjunto.toString());

        assertFalse(conjunto.agregar(1));
        assertEquals("Set(2): {1, 2}", conjunto.toString());
    }

    /**
     * Test of agregar with an array of values, of class Set.
     */
    @Test
    public void testAgregar2() {
        Set<Integer> conjunto = new Set<>();
        Integer[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        conjunto.agregar(values);
        assertEquals("Set(10): {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}", conjunto.toString());
    }

    /**
     * Test of agregar with an IList of values, of class Set.
     */
    @Test
    public void testAgregar3() {
        Set<Integer> conjunto = new Set<>();
        IList<Integer> values = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            values.agregar(i);
        }
        conjunto.agregar(values);
        assertEquals("Set(10): {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}", conjunto.toString());
    }

    /**
     * Test of agregar with an ISet of values, of class Set.
     */
    @Test
    public void testAgregar4() {
        Set<Integer> conjunto = new Set<>();
        ISet<Integer> values = new Set<>();
        for (int i = 0; i < 10; i++) {
            values.agregar(i);
        }
        conjunto.agregar(values);
        assertEquals("Set(10): {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}", conjunto.toString());
    }

    /**
     * Test of remover method, of class Set.
     */
    @Test
    public void testRemover() {
        Set<Integer> conjunto = new Set<>();
        for (int i = 0; i < 10; i++) {
            conjunto.agregar(i);
        }
        assertEquals(10, conjunto.size());
        assertFalse(conjunto.vacio());
        assertEquals("Set(10): {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}", conjunto.toString());

        assertTrue(conjunto.remover(0));
        assertEquals("Set(9): {1, 2, 3, 4, 5, 6, 7, 8, 9}", conjunto.toString());

        assertTrue(conjunto.remover(9));
        assertEquals("Set(8): {1, 2, 3, 4, 5, 6, 7, 8}", conjunto.toString());

        assertTrue(conjunto.remover(2));
        assertEquals("Set(7): {1, 3, 4, 5, 6, 7, 8}", conjunto.toString());

        assertFalse(conjunto.remover(2));
        assertEquals("Set(7): {1, 3, 4, 5, 6, 7, 8}", conjunto.toString());

        assertTrue(conjunto.remover(1));
        assertEquals("Set(6): {3, 4, 5, 6, 7, 8}", conjunto.toString());

        assertTrue(conjunto.remover(6));
        assertEquals("Set(5): {3, 4, 5, 7, 8}", conjunto.toString());

        assertTrue(conjunto.remover(3));
        assertEquals("Set(4): {4, 5, 7, 8}", conjunto.toString());

        assertTrue(conjunto.remover(4));
        assertEquals("Set(3): {5, 7, 8}", conjunto.toString());

        assertTrue(conjunto.remover(5));
        assertEquals("Set(2): {7, 8}", conjunto.toString());

        assertTrue(conjunto.remover(7));
        assertEquals("Set(1): {8}", conjunto.toString());

        assertTrue(conjunto.remover(8));
        assertEquals("Set(0): {}", conjunto.toString());

        assertFalse(conjunto.remover(8));
        assertEquals("Set(0): {}", conjunto.toString());
    }

    /**
     * Test of pop method, of class Set.
     */
    @Test
    public void testPop() {

        Set<Integer> conjunto = new Set<>();

        for (int i = 0; i < 5; i++) {
            conjunto.agregar(i);
        }
        assertEquals("Set(5): {0, 1, 2, 3, 4}", conjunto.toString());
        assertEquals(0, conjunto.pop());
        assertEquals("Set(4): {1, 2, 3, 4}", conjunto.toString());
        assertEquals(1, conjunto.pop());
        assertEquals("Set(3): {2, 3, 4}", conjunto.toString());
        assertEquals(2, conjunto.pop());
        assertEquals("Set(2): {3, 4}", conjunto.toString());
        assertEquals(3, conjunto.pop());
        assertEquals("Set(1): {4}", conjunto.toString());
        assertEquals(4, conjunto.pop());
        assertEquals("Set(0): {}", conjunto.toString());
        assertEquals(null, conjunto.pop());
        assertEquals("Set(0): {}", conjunto.toString());
    }

    /**
     * Test of union method, of class Set.
     */
    @Test
    public void testUnion() {
        Set<Integer> conjunto1 = new Set<>();
        Set<Integer> conjunto2 = new Set<>();
        Set<Integer> conjunto3 = conjunto1.union(conjunto2);
        assertEquals("Set(0): {}", conjunto3.toString());
        conjunto2.agregar(1);
        conjunto3 = conjunto1.union(conjunto2);
        assertEquals("Set(1): {1}", conjunto3.toString());
        conjunto3 = conjunto2.union(conjunto1);
        assertEquals("Set(1): {1}", conjunto3.toString());

        conjunto1.agregar(1);
        conjunto1.agregar(2);
        conjunto2.agregar(2);
        conjunto3 = conjunto2.union(conjunto1);
        assertEquals("Set(2): {1, 2}", conjunto3.toString());
        conjunto2.agregar(3);
        conjunto3 = conjunto2.union(conjunto1);
        assertEquals("Set(3): {1, 2, 3}", conjunto3.toString());
    }

    /**
     * Test of interseccion method, of class Set.
     */
    @Test
    public void testInterseccion() {

        Set<Integer> conjunto1 = new Set<>();
        Set<Integer> conjunto2 = new Set<>();
        Set<Integer> conjunto3 = conjunto1.interseccion(conjunto2);
        assertEquals("Set(0): {}", conjunto3.toString());
        conjunto2.agregar(1);
        conjunto3 = conjunto1.interseccion(conjunto2);
        assertEquals("Set(0): {}", conjunto3.toString());
        conjunto1.agregar(2);
        conjunto2.agregar(2);
        conjunto3 = conjunto2.interseccion(conjunto1);
        assertEquals("Set(1): {2}", conjunto3.toString());
        conjunto2.agregar(3);
        conjunto3 = conjunto2.interseccion(conjunto1);
        assertEquals("Set(1): {2}", conjunto3.toString());
        conjunto1.agregar(3);
        conjunto3 = conjunto2.interseccion(conjunto1);
        assertEquals("Set(2): {2, 3}", conjunto3.toString());
    }

    /**
     * Test of diferencia method, of class Set.
     */
    @Test
    public void testDiferencia() {
        Set<Integer> conjunto1 = new Set<>();
        Set<Integer> conjunto2 = new Set<>();
        Set<Integer> conjunto3 = conjunto1.diferencia(conjunto2);
        for (int i = 0; i < 5; i++) {
            conjunto1.agregar(i);
        }
        for (int i = 3; i < 7; i++) {
            conjunto2.agregar(i);
        }
        conjunto3 = conjunto1.diferencia(conjunto2);
        assertEquals("Set(3): {0, 1, 2}", conjunto3.toString());
        conjunto3 = conjunto2.diferencia(conjunto1);
        assertEquals("Set(2): {5, 6}", conjunto3.toString());
    }

    /**
     * Test of diferenciaSimetrica method, of class Set.
     */
    @Test
    public void testDiferenciaSimetrica() {

        Set<Integer> conjunto1 = new Set<>();
        Set<Integer> conjunto2 = new Set<>();

        for (int i = 0; i < 5; i++) {
            conjunto1.agregar(i);
        }
        for (int i = 3; i < 7; i++) {
            conjunto2.agregar(i);
        }
        Set<Integer> conjunto3 = conjunto1.diferenciaSimetrica(conjunto2);
        assertEquals("Set(5): {0, 1, 2, 5, 6}", conjunto3.toString());
        conjunto3 = conjunto2.diferenciaSimetrica(conjunto1);
        assertEquals("Set(5): {5, 6, 0, 1, 2}", conjunto3.toString());
    }

    /**
     * Test of equals method, of class Set.
     */
    @Test
    public void testEquals() {
        Set<Integer> conjunto1 = new Set<>();
        Set<Integer> conjunto2 = new Set<>();
        assertTrue(conjunto1.equals(conjunto2));
        assertTrue(conjunto2.equals(conjunto1));

        for (int i = 0; i < 5; i++) {
            conjunto1.agregar(i);
            conjunto2.agregar(i);
        }
        assertTrue(conjunto1.equals(conjunto2));
        assertTrue(conjunto2.equals(conjunto1));
        conjunto2.agregar(5);
        assertFalse(conjunto1.equals(conjunto2));
        assertFalse(conjunto2.equals(conjunto1));
    }

    /**
     * Test of toArray method, of class Set.
     */
    @Test
    public void testToArray() {
        Set<Integer> conjunto = new Set<>();
        for (int i = 0; i < 5; i++) {
            conjunto.agregar(i);
        }
        assertEquals("Set(5): {0, 1, 2, 3, 4}", conjunto.toString());

        ArrayList<Integer> arrayList = conjunto.toList();

        assertEquals(5, arrayList.size());
        for (int i = 0; i < 5; i++) {
            assertEquals(i, arrayList.get(i));
        }
    }

}
