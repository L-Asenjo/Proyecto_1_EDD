/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package proyecto1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JsonRedParserTest {

    public JsonRedParserTest() {
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
     * Test of getNombreRed method, of class JsonRedParser.
     */
    @Test
    public void testGetNombreRed() {

    }

    /**
     * Test of getNombresLineas method, of class JsonRedParser.
     */
    @Test
    public void testGetNombresLineas() {

    }

    /**
     * Test of getNombresParadas method, of class JsonRedParser.
     */
    @Test
    public void testGetNombresParadas() {

    }

    /**
     * Test of setJson method, of class JsonRedParser.
     */
    @Test
    public void testSetJson() {

    }

    /**
     * Test of stripTotal method, of class JsonRedParser.
     */
    @Test
    public void testStripTotal() {

    }

    /**
     * Test of obtenerRed method, of class JsonRedParser.
     */
    @Test
    public void testObtenerRed() {

    }

    /**
     * Test of depurarLineas method, of class JsonRedParser.
     */
    @Test
    public void testDepurarLineas() {

    }

    /**
     * Test of obtenerLineas method, of class JsonRedParser.
     */
    @Test
    public void testObtenerLineas() {

    }

    /**
     * Test of parse method, of class JsonRedParser.
     */
    @Test
    public void testParse_0args() {

    }

    /**
     * Test of parse method, of class JsonRedParser.
     */
    @Test
    public void testParse_String() {
        String nombreArchivo = "./data/Caracas.json";
        Path path = Paths.get(nombreArchivo);
        String json = null;
        try {
            json = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(json);
        JsonRedParser parser = new JsonRedParser();
        parser.parse(json);
        String expectedString = "Red: Metro de Caracas\n" +
                "    Linea 1\n" +
                "        Propatria\n" +
                "        Perez Bonalde\n" +
                "        Plaza Sucre\n" +
                "        Gato Negro\n" +
                "        Agua Salud\n" +
                "        Canno Amarillo\n" +
                "        {Capitolio:El Silencio}\n" +
                "        La Hoyada\n" +
                "        Parque Carabobo\n" +
                "        Bellas Artes\n" +
                "        Colegio de Ingenieros\n" +
                "        {Plaza Venezuela:Zona Rental}\n" +
                "        Sabana Grande\n" +
                "        Chacaito\n" +
                "        Chacao\n" +
                "        Altamira\n" +
                "        Miranda\n" +
                "        Los Dos Caminos\n" +
                "        Los Cortijos\n" +
                "        La California\n" +
                "        Petare\n" +
                "        Palo Verde         ]\n" +
                "    Linea 2\n" +
                "        {El Silencio:Capitolio}\n" +
                "        Capuchinos\n" +
                "        Maternidad\n" +
                "        Artigas\n" +
                "        La Paz\n" +
                "        La Yaguara\n" +
                "        Carapita\n" +
                "        Antimano\n" +
                "        Mamera\n" +
                "        Caricuao\n" +
                "        Zoologico         ]\n" +
                "    Linea 3\n" +
                "        {Plaza Venezuela:Zona Rental}\n" +
                "        Ciudad Universitaria\n" +
                "        Los Simbolos\n" +
                "        La Bandera\n" +
                "        El Valle\n" +
                "        Los Jardines\n" +
                "        Coche\n" +
                "        Mercado\n" +
                "        {La Rinconada:Caracas}         ]\n" +
                "    Linea 4\n" +
                "        Las Adjuntas\n" +
                "        Ruiz Pineda\n" +
                "        Mamera\n" +
                "        Antimano\n" +
                "        Carapita\n" +
                "        La Yaguara\n" +
                "        La Paz\n" +
                "        Maternidad\n" +
                "        Capuchinos\n" +
                "        Teatros\n" +
                "        Nuevo Circo\n" +
                "        Parque Central\n" +
                "        {Zona Rental:Plaza Venezuela}\n" +
                "        Bello Monte";
        String[] expectedArray = expectedString.split("\n");
        String[] actualArray = parser.toString().split("\n");
        assertEquals(expectedArray.length, actualArray.length);
        for (int i = 0; i < expectedArray.length; i++) {
            assertEquals(expectedArray[i], actualArray[i]);
        }
    }

    /**
     * Test of toString method, of class JsonRedParser.
     */
    @Test
    public void testToString() {

    }

    /**
     * Test of main method, of class JsonRedParser.
     */
    @Test
    public void testMain() {

    }

}
