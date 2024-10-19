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

public class DatosRedArchivoTest {

    public DatosRedArchivoTest() {
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
     * Test of the constructor with no args, of class DatosRedArchivo.
     */
    @Test
    public void testDatosRedArchivo() {
        DatosRedArchivo datos = new DatosRedArchivo();

        assertEquals(null, datos.nombreArchivo);
    }

    /**
     * Test of the constructor with args, of class DatosRedArchivo.
     */
    @Test
    public void testDatosRedArchivo_String() {
        DatosRedArchivo datos = new DatosRedArchivo("./data/Caracas.json");
        assertEquals("./data/Caracas.json", datos.nombreArchivo);
    }

    /**
     * Test of setNombreArchivo method, of class DatosRedArchivo.
     */
    @Test
    public void testSetNombreArchivo() {
        DatosRedArchivo datos = new DatosRedArchivo();
        datos.setNombreArchivo("./data/Caracas.json");
        assertEquals("./data/Caracas.json", datos.nombreArchivo);
    }

    /**
     * Test of ObtenerDatos method, of class DatosRedArchivo.
     */
    @Test
    public void testObtenerDatos_String() {

        DatosRedArchivo datos = new DatosRedArchivo();
        datos.ObtenerDatos("./data/Caracas.json");
        String expectedString = "{\n" +
                "   \"Metro de Caracas\":[\n" +
                "      {\n" +
                "         \"Linea 1\":[\n" +
                "            \"Propatria\",\n" +
                "            \"Perez Bonalde\",\n" +
                "            \"Plaza Sucre\",\n" +
                "            \"Gato Negro\",\n" +
                "            \"Agua Salud\",\n" +
                "            \"Canno Amarillo\",\n" +
                "            {\"Capitolio\":\"El Silencio\"},\n" +
                "            \"La Hoyada\",\n" +
                "            \"Parque Carabobo\",\n" +
                "            \"Bellas Artes\",\n" +
                "            \"Colegio de Ingenieros\",\n" +
                "            {\"Plaza Venezuela\":\"Zona Rental\"},\n" +
                "            \"Sabana Grande\",\n" +
                "            \"Chacaito\",\n" +
                "            \"Chacao\",\n" +
                "            \"Altamira\",\n" +
                "            \"Miranda\",\n" +
                "            \"Los Dos Caminos\",\n" +
                "            \"Los Cortijos\",\n" +
                "            \"La California\",\n" +
                "            \"Petare\",\n" +
                "            \"Palo Verde\"\n" +
                "         ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"Linea 2\":[\n" +
                "            {\"El Silencio\":\"Capitolio\"},\n" +
                "            \"Capuchinos\",\n" +
                "            \"Maternidad\",\n" +
                "            \"Artigas\",\n" +
                "            \"La Paz\",\n" +
                "            \"La Yaguara\",\n" +
                "            \"Carapita\",\n" +
                "            \"Antimano\",\n" +
                "            \"Mamera\",\n" +
                "            \"Caricuao\",\n" +
                "            \"Zoologico\"\n" +
                "         ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"Linea 3\":[\n" +
                "            {\"Plaza Venezuela\":\"Zona Rental\"},\n" +
                "            \"Ciudad Universitaria\",\n" +
                "            \"Los Simbolos\",\n" +
                "            \"La Bandera\",\n" +
                "            \"El Valle\",\n" +
                "            \"Los Jardines\",\n" +
                "            \"Coche\",\n" +
                "            \"Mercado\",\n" +
                "            {\"La Rinconada\":\"Caracas\"}\n" +
                "         ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"Linea 4\":[\n" +
                "            \"Las Adjuntas\",\n" +
                "            \"Ruiz Pineda\",\n" +
                "            \"Mamera\",\n" +
                "            \"Antimano\",\n" +
                "            \"Carapita\",\n" +
                "            \"La Yaguara\",\n" +
                "            \"La Paz\",\n" +
                "            \"Maternidad\",\n" +
                "            \"Capuchinos\",\n" +
                "            \"Teatros\",\n" +
                "            \"Nuevo Circo\",\n" +
                "            \"Parque Central\",\n" +
                "            {\"Zona Rental\":\"Plaza Venezuela\"},\n" +
                "            \"Bello Monte\"\n" +
                "         ]\n" +
                "      }\n" +
                "   ]\n" +
                "}";
        String actualString = datos.ObtenerDatos("./data/Caracas.json");
        String[] expectedArray = expectedString.split("\n");
        String[] actualArray = actualString.split("\n");
        assertEquals(expectedArray.length, actualArray.length);
        for (int i = 0; i < expectedArray.length; i++) {
            assertEquals(expectedArray[i].strip(), actualArray[i].strip());
        }

    }

    /**
     * Test of ObtenerDatos method, of class DatosRedArchivo.
     */
    @Test
    public void testObtenerDatos_0args() {
        DatosRedArchivo datos = new DatosRedArchivo();
        datos.setNombreArchivo("./data/Caracas.json");
        datos.ObtenerDatos();
        String expectedString = "{\n" +
                "   \"Metro de Caracas\":[\n" +
                "      {\n" +
                "         \"Linea 1\":[\n" +
                "            \"Propatria\",\n" +
                "            \"Perez Bonalde\",\n" +
                "            \"Plaza Sucre\",\n" +
                "            \"Gato Negro\",\n" +
                "            \"Agua Salud\",\n" +
                "            \"Canno Amarillo\",\n" +
                "            {\"Capitolio\":\"El Silencio\"},\n" +
                "            \"La Hoyada\",\n" +
                "            \"Parque Carabobo\",\n" +
                "            \"Bellas Artes\",\n" +
                "            \"Colegio de Ingenieros\",\n" +
                "            {\"Plaza Venezuela\":\"Zona Rental\"},\n" +
                "            \"Sabana Grande\",\n" +
                "            \"Chacaito\",\n" +
                "            \"Chacao\",\n" +
                "            \"Altamira\",\n" +
                "            \"Miranda\",\n" +
                "            \"Los Dos Caminos\",\n" +
                "            \"Los Cortijos\",\n" +
                "            \"La California\",\n" +
                "            \"Petare\",\n" +
                "            \"Palo Verde\"\n" +
                "         ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"Linea 2\":[\n" +
                "            {\"El Silencio\":\"Capitolio\"},\n" +
                "            \"Capuchinos\",\n" +
                "            \"Maternidad\",\n" +
                "            \"Artigas\",\n" +
                "            \"La Paz\",\n" +
                "            \"La Yaguara\",\n" +
                "            \"Carapita\",\n" +
                "            \"Antimano\",\n" +
                "            \"Mamera\",\n" +
                "            \"Caricuao\",\n" +
                "            \"Zoologico\"\n" +
                "         ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"Linea 3\":[\n" +
                "            {\"Plaza Venezuela\":\"Zona Rental\"},\n" +
                "            \"Ciudad Universitaria\",\n" +
                "            \"Los Simbolos\",\n" +
                "            \"La Bandera\",\n" +
                "            \"El Valle\",\n" +
                "            \"Los Jardines\",\n" +
                "            \"Coche\",\n" +
                "            \"Mercado\",\n" +
                "            {\"La Rinconada\":\"Caracas\"}\n" +
                "         ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"Linea 4\":[\n" +
                "            \"Las Adjuntas\",\n" +
                "            \"Ruiz Pineda\",\n" +
                "            \"Mamera\",\n" +
                "            \"Antimano\",\n" +
                "            \"Carapita\",\n" +
                "            \"La Yaguara\",\n" +
                "            \"La Paz\",\n" +
                "            \"Maternidad\",\n" +
                "            \"Capuchinos\",\n" +
                "            \"Teatros\",\n" +
                "            \"Nuevo Circo\",\n" +
                "            \"Parque Central\",\n" +
                "            {\"Zona Rental\":\"Plaza Venezuela\"},\n" +
                "            \"Bello Monte\"\n" +
                "         ]\n" +
                "      }\n" +
                "   ]\n" +
                "}";
        String actualString = datos.ObtenerDatos("./data/Caracas.json");
        String[] expectedArray = expectedString.split("\n");
        String[] actualArray = actualString.split("\n");
        assertEquals(expectedArray.length, actualArray.length);
        for (int i = 0; i < expectedArray.length; i++) {
            assertEquals(expectedArray[i].strip(), actualArray[i].strip());
        }

    }

    /**
     * Test of main method, of class DatosRedArchivo.
     */
    @Test
    public void testMain() {

    }

}
