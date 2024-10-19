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

public class RedTest {

    private final Path TEST_FILE = Paths.get(".\\data\\prueba.json");

    public RedTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        try {
            if (Files.exists(TEST_FILE)) {
                Files.delete(TEST_FILE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        try {
            if (Files.exists(TEST_FILE)) {
                Files.delete(TEST_FILE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test of the constructor method with 1 arg, of class Red.
     */
    @Test
    public void testRedALWith1Args() {
        Red red = new Red(".\\data\\Caracas.json");
        assertNull(red.getNombre());
        assertEquals(".\\data\\Caracas.json", red.getNombreArchivo());

    }

    /**
     * Test of the constructor method no args, of class Red.
     */
    @Test
    public void testRedALNoArgs() {
        Red red = new Red();
        assertNull(red.getNombre());
        assertNull(red.getNombreArchivo());
    }

    /**
     * Test of getNombre method, of class Red.
     */
    @Test
    public void testGetNombre() {
        Red red = new Red();

        red.setNombre("Caracas");
        assertEquals("Caracas", red.getNombre());

        try {
            red.setNombre(null);
            fail("Se esperaba una excepción");
        } catch (IllegalArgumentException e) {
            assertEquals("El nombre de la red no puede estar vacío", e.getMessage());
        }

        try {
            red.setNombre("");
            fail("Se esperaba una excepción");
        } catch (IllegalArgumentException e) {
            assertEquals("El nombre de la red no puede estar vacío", e.getMessage());
        }
    }

    /**
     * Test of setNombre method, of class Red.
     */
    @Test
    public void testSetNombre() {
        Red red = new Red();

        red.setNombre("Caracas");
        assertEquals("Caracas", red.getNombre());

        try {
            red.setNombre(null);
            fail("Se esperaba una excepción");
        } catch (IllegalArgumentException e) {
            assertEquals("El nombre de la red no puede estar vacío", e.getMessage());
        }

        try {
            red.setNombre("");
            fail("Se esperaba una excepción");
        } catch (IllegalArgumentException e) {
            assertEquals("El nombre de la red no puede estar vacío", e.getMessage());
        }
    }

    /**
     * Test of getNombreArchivo method, of class Red.
     */
    @Test
    public void testGetNombreArchivo() {

        Red red = new Red();
        assertNull(red.getNombreArchivo());
        red.setNombreArchivo(".\\data\\Caracas.json");
        assertEquals(".\\data\\Caracas.json", red.getNombreArchivo());

        try {
            red.setNombreArchivo(null);
            fail("Se esperaba una excepción");
        } catch (IllegalArgumentException e) {
            assertEquals("El nombre del archivo no puede estar vacío", e.getMessage());
        }

        try {
            red.setNombreArchivo("");
            fail("Se esperaba una excepción");
        } catch (IllegalArgumentException e) {
            assertEquals("El nombre del archivo no puede estar vacío", e.getMessage());
        }
    }

    /**
     * Test of setNombreArchivo method, of class Red.
     */
    @Test
    public void testSetNombreArchivo() {
        Red red = new Red();
        assertNull(red.getNombreArchivo());
        red.setNombreArchivo(".\\data\\Caracas.json");
        assertEquals(".\\data\\Caracas.json", red.getNombreArchivo());

        try {
            red.setNombreArchivo(null);
            fail("Se esperaba una excepción");
        } catch (IllegalArgumentException e) {
            assertEquals("El nombre del archivo no puede estar vacío", e.getMessage());
        }

        try {
            red.setNombreArchivo("");
            fail("Se esperaba una excepción");
        } catch (IllegalArgumentException e) {
            assertEquals("El nombre del archivo no puede estar vacío", e.getMessage());
        }
    }

    /**
     * Test of getNombresLineas method, of class Red.
     */
    @Test
    public void testGetNombresLineas() {
        Red red = new Red();
        red.setNombre("prueba");
        Linea linea = new Linea("Linea 1");
        linea.agregarParada(new Parada("parada1"));
        linea.agregarParada(new Parada("parada2"));
        red.agregarLinea(linea);

        Linea linea2 = new Linea("Linea 2");
        linea2.agregarParada(new Parada("parada3"));
        linea2.agregarParada(new Parada("parada4"));
        red.agregarLinea(linea2);

        assertEquals("Linea 1", red.getNombresLineas()[0]);
        assertEquals("Linea 2", red.getNombresLineas()[1]);

        String[] lineas = red.getNombresLineas();
        assertEquals("Linea 1", lineas[0]);
        assertEquals("Linea 2", lineas[1]);

    }

    /**
     * Test of getParadasLinea method, of class Red.
     */
    @Test
    public void testGetParadasLinea() {

        Red red = new Red();

        red.setNombre("prueba");
        Linea linea = new Linea("Linea 1");
        linea.agregarParada(new Parada("parada1"));
        linea.agregarParada(new Parada("parada2"));
        red.agregarLinea(linea);

        Linea linea2 = new Linea("Linea 2");
        linea2.agregarParada(new Parada("parada3"));
        linea2.agregarParada(new Parada("parada4"));
        red.agregarLinea(linea2);

        assertEquals("parada1", red.getParadasLinea(0)[0]);
        assertEquals("parada2", red.getParadasLinea(0)[1]);
        assertEquals("parada3", red.getParadasLinea(1)[0]);
        assertEquals("parada4", red.getParadasLinea(1)[1]);

        assertEquals(2, red.getParadasLinea(0).length);
        assertEquals(2, red.getParadasLinea(1).length);

        red.get(0).agregarParada(new Parada("parada5"));
        assertEquals(3, red.getParadasLinea(0).length);
        assertEquals("parada5", red.getParadasLinea(0)[2]);
    }

    /**
     * Test of agregarLinea method with Linea linea arg, of class Red.
     */
    @Test
    public void testAgregarLineaWithLineaArg() {
        Red red = new Red("red1");

        Linea linea = new Linea("Linea 1");
        assertTrue(red.agregarLinea(linea));

        Linea linea2 = new Linea("Linea 2");
        assertTrue(red.agregarLinea(linea2));

        assertEquals("Linea 1", red.getNombresLineas()[0]);
        assertEquals("Linea 2", red.getNombresLineas()[1]);
        assertEquals(2, red.size());

    }

    /**
     * Test of agregarParada method with String lineaName, of class Red.
     */
    @Test
    public void testAgregarParadaWithStringLineaName() {
        Red red = new Red("red1");
        assertTrue(red.agregarLinea("Linea 1"));
        assertTrue(red.agregarLinea("Linea 2"));
        assertEquals(2, red.size());
        assertEquals("Linea 1", red.getNombresLineas()[0]);
        assertEquals("Linea 2", red.getNombresLineas()[1]);
    }

    /**
     * Test of agregarParada method, of class Red.
     */
    @Test
    public void testAgregarParada() {
        Red red = new Red("red1");
        assertTrue(red.agregarParada("Linea 1", "parada1"));
        assertTrue(red.agregarParada("Linea 1", "parada2"));
        assertTrue(red.agregarParada("Linea 2", "parada3:alias1"));
        assertTrue(red.agregarParada("Linea 2", "parada4"));

        assertEquals("parada1", red.getParadasLinea(0)[0]);
        assertEquals("parada2", red.getParadasLinea(0)[1]);
        assertEquals("parada3:alias1", red.getParadasLinea(1)[0]);
        assertEquals("parada4", red.getParadasLinea(1)[1]);

        assertEquals("Linea 1", red.get(0).getNombre());
        assertEquals("Linea 2", red.get(1).getNombre());

        assertFalse(red.agregarParada("linea 1", "parada1"));
        assertEquals("parada1", red.getParadasLinea(0)[0]);
        assertEquals("parada2", red.getParadasLinea(0)[1]);
        assertEquals("parada3:alias1", red.getParadasLinea(1)[0]);
        assertEquals("parada4", red.getParadasLinea(1)[1]);

        assertEquals("Linea 1", red.get(0).getNombre());
        assertEquals("Linea 2", red.get(1).getNombre());

        assertFalse(red.agregarParada("Linea 3", ""));
        assertEquals(-1, red.buscarLineaPorNombre("Linea 3"));

    }

    /**
     * Test of agregarParadas method, of class Red.
     */
    @Test
    public void testAgregarParadas() {
        Red red = new Red("red1");
        String[] paradas = { "parada1", "\"{\"parada2\"}\"", "\"{\"parada3\":\"alias3\"}\"", "{parada4:alias4}" };
        assertTrue(red.agregarParadas("Linea 1", paradas));
        assertEquals(1, red.size());
        assertEquals(4, red.get(0).getParadas().length);
        assertEquals("Linea 1", red.getNombresLineas()[0]);
        assertEquals("parada1", red.getParadasLinea(0)[0]);
        assertEquals("parada2", red.getParadasLinea(0)[1]);
        assertEquals("parada3:alias3", red.getParadasLinea(0)[2]);
        assertEquals("parada4:alias4", red.getParadasLinea(0)[3]);

        String[] paradas2 = { "parada5", "\"{\"parada6\"}\"", "", "{parada8:alias8}" };
        assertFalse(red.agregarParadas("Linea 2", paradas2));
        assertEquals(1, red.size());
        assertEquals(4, red.get(0).getParadas().length);
        assertEquals("Linea 1", red.getNombresLineas()[0]);
        assertEquals("parada1", red.getParadasLinea(0)[0]);
        assertEquals("parada2", red.getParadasLinea(0)[1]);
        assertEquals("parada3:alias3", red.getParadasLinea(0)[2]);
        assertEquals("parada4:alias4", red.getParadasLinea(0)[3]);

        assertFalse(red.agregarParadas("Linea 3", paradas2));
        assertEquals(1, red.size());
        assertEquals(4, red.get(0).getParadas().length);
        assertEquals("Linea 1", red.getNombresLineas()[0]);
        assertEquals("parada1", red.getParadasLinea(0)[0]);
        assertEquals("parada2", red.getParadasLinea(0)[1]);
        assertEquals("parada3:alias3", red.getParadasLinea(0)[2]);
        assertEquals("parada4:alias4", red.getParadasLinea(0)[3]);
        assertEquals(-1, red.buscarLineaPorNombre("Linea 3"));

    }

    /**
     * Test of removerLinea method, of class Red.
     */
    @Test
    public void testRemoverLinea() {
        Red red = new Red("red1");

        Linea linea = new Linea("Linea 1");
        red.agregarLinea(linea);

        Linea linea2 = new Linea("Linea 2");
        red.agregarLinea(linea2);

        Linea linea3 = new Linea("Linea 3");
        red.agregarLinea(linea3);

        linea.agregarParada(new Parada("parada1"));
        linea.agregarParada(new Parada("parada2"));

        linea2.agregarParada(new Parada("parada3"));
        linea2.agregarParada(new Parada("parada4"));

        linea3.agregarParada(new Parada("parada5"));
        linea3.agregarParada(new Parada("parada6"));

        red.removerLinea(1);
        assertEquals(2, red.size());
        assertEquals("Linea 1", red.getNombresLineas()[0]);
        assertEquals("Linea 3", red.getNombresLineas()[1]);

        red.removerLinea(0);
        assertEquals(1, red.size());
        assertEquals("Linea 3", red.getNombresLineas()[0]);

        red.removerLinea(0);
        assertEquals(0, red.size());

    }

    /**
     * Test of buscarLineaPorNombre method, of class Red.
     */
    @Test
    public void testBuscarLineaPorNombre() {
        Red red = new Red("red1");

        Linea linea = new Linea("Linea 1");
        red.agregarLinea(linea);

        Linea linea2 = new Linea("Linea 2");
        red.agregarLinea(linea2);

        Linea linea3 = new Linea("Linea 3");
        red.agregarLinea(linea3);

        assertEquals(0, red.buscarLineaPorNombre("Linea 1"));
        assertEquals(1, red.buscarLineaPorNombre("Linea 2"));
        assertEquals(2, red.buscarLineaPorNombre("Linea 3"));

        assertEquals(-1, red.buscarLineaPorNombre("Linea 4"));
    }

    /**
     * Test of vaciarRed method, of class Red.
     */
    @Test
    public void testVaciarRed() {
        Red red = new Red();

        Linea linea = new Linea("Linea 1");
        red.agregarLinea(linea);

        Linea linea2 = new Linea("Linea 2");
        red.agregarLinea(linea2);

        Linea linea3 = new Linea("Linea 3");
        red.agregarLinea(linea3);

        linea.agregarParada(new Parada("parada1"));
        linea.agregarParada(new Parada("parada2"));

        linea2.agregarParada(new Parada("parada3"));
        linea2.agregarParada(new Parada("parada4"));

        linea3.agregarParada(new Parada("parada5"));
        linea3.agregarParada(new Parada("parada6"));

        assertEquals(3, red.size());

        red.vaciarRed();
        assertEquals(0, red.size());
        assertEquals(1, red.getTamArreglo());
    }

    /**
     * Test Cargar Archivo method, of class Red.
     */

    @Test
    public void testCargarArchivo() {
        Red red = new Red();

        assertFalse(red.cargarArchivo());

        red.setNombreArchivo(".\\data\\Caracas.json");
        red.cargarArchivo();

        assertEquals(red.getNombre(), "Metro de Caracas");
        String[] expectedNombresLineas = { "Linea 1", "Linea 2", "Linea 3", "Linea 4" };
        String[] actualNombresLineas = red.getNombresLineas();
        for (int i = 0; i < 4; i++) {
            assertEquals(expectedNombresLineas[i], actualNombresLineas[i]);
        }
        assertEquals("Propatria", red.getParadasLinea(0)[0]);

    }

    /**
     * Test of toString method, of class Red.
     */
    @Test
    public void testToString() {

        Red red = new Red("./data/Caracas.json");
        red.cargarArchivo();
        String expectedString = "Red: Metro de Caracas\n" +
                "    Linea: Linea 1\n" +
                "        Propatria\n" +
                "        Perez Bonalde\n" +
                "        Plaza Sucre\n" +
                "        Gato Negro\n" +
                "        Agua Salud\n" +
                "        Canno Amarillo\n" +
                "        Capitolio:El Silencio\n" +
                "        La Hoyada\n" +
                "        Parque Carabobo\n" +
                "        Bellas Artes\n" +
                "        Colegio de Ingenieros\n" +
                "        Plaza Venezuela:Zona Rental\n" +
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
                "    Linea: Linea 2\n" +
                "        El Silencio:Capitolio\n" +
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
                "    Linea: Linea 3\n" +
                "        Plaza Venezuela:Zona Rental\n" +
                "        Ciudad Universitaria\n" +
                "        Los Simbolos\n" +
                "        La Bandera\n" +
                "        El Valle\n" +
                "        Los Jardines\n" +
                "        Coche\n" +
                "        Mercado\n" +
                "        La Rinconada:Caracas}\n" +
                "    Linea: Linea 4\n" +
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
                "        Zona Rental:Plaza Venezuela\n" +
                "        Bello Monte";
        String[] expectedArray = expectedString.split("\n");
        String[] actualArray = red.toString().split("\n");
        assertEquals(expectedArray.length, actualArray.length);
        for (int i = 0; i < expectedArray.length; i++) {
            assertEquals(expectedArray[i], actualArray[i]);
        }
    }

    /**
     * Test of charge a Red and then charge another Red.
     */
    @Test
    public void testReChargeRed() {
        Red red = new Red();
        red.cargarArchivo("./data/Caracas.json");
        String expectedString = "Red: Metro de Caracas\n" +
                "    Linea: Linea 1\n" +
                "        Propatria\n" +
                "        Perez Bonalde\n" +
                "        Plaza Sucre\n" +
                "        Gato Negro\n" +
                "        Agua Salud\n" +
                "        Canno Amarillo\n" +
                "        Capitolio:El Silencio\n" +
                "        La Hoyada\n" +
                "        Parque Carabobo\n" +
                "        Bellas Artes\n" +
                "        Colegio de Ingenieros\n" +
                "        Plaza Venezuela:Zona Rental\n" +
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
                "    Linea: Linea 2\n" +
                "        El Silencio:Capitolio\n" +
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
                "    Linea: Linea 3\n" +
                "        Plaza Venezuela:Zona Rental\n" +
                "        Ciudad Universitaria\n" +
                "        Los Simbolos\n" +
                "        La Bandera\n" +
                "        El Valle\n" +
                "        Los Jardines\n" +
                "        Coche\n" +
                "        Mercado\n" +
                "        La Rinconada:Caracas}\n" +
                "    Linea: Linea 4\n" +
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
                "        Zona Rental:Plaza Venezuela\n" +
                "        Bello Monte";
        String[] expectedArray = expectedString.split("\n");
        String[] actualArray = red.toString().split("\n");
        assertEquals(expectedArray.length, actualArray.length);
        for (int i = 0; i < expectedArray.length; i++) {
            assertEquals(expectedArray[i], actualArray[i]);
        }

        red.cargarArchivo("./data/Bogota.json");
        expectedString = "Red: Transmilenio\n" +
                "    Linea: Av. Caracas\n" +
                "        Terminal\n" +
                "        Calle 187\n" +
                "        Portal del Norte\n" +
                "        Toberin\n" +
                "        Cardio Infantil\n" +
                "        Mazuren\n" +
                "        Calle 146\n" +
                "        Calle 142\n" +
                "        Alcala\n" +
                "        Prado\n" +
                "        Calle 127\n" +
                "        Pepe Sierra\n" +
                "        Calle 106\n" +
                "        Calle 100\n" +
                "        Virrey\n" +
                "        Calle 85\n" +
                "        Heroes\n" +
                "        Calle 76\n" +
                "        Calle 72\n" +
                "        Flores\n" +
                "        Calle 63\n" +
                "        Calle 57\n" +
                "        Marty\n" +
                "        Calle 452\n" +
                "        Av. 39\n" +
                "        Profamilia\n" +
                "        Calle 26\n" +
                "        Calle 22\n" +
                "        Calle 19\n" +
                "        Av. Jimenez\n" +
                "        Tercer Milenio\n" +
                "        Bicentenario\n" +
                "        Hospital\n" +
                "        Hortua\n" +
                "        Narinno\n" +
                "        Fucha\n" +
                "        Restrepo\n" +
                "        Olaya\n" +
                "        Quiroga\n" +
                "        Calle 40 Sur\n" +
                "        Santa Lucia\n" +
                "        Socorro\n" +
                "        Consuelo\n" +
                "        Molinos\n" +
                "        Portal de Usme         ]\n" +
                "    Linea: Av. Caracas Sur\n" +
                "        Santa Lucia\n" +
                "        Biblioteca\n" +
                "        Parque\n" +
                "        Portal del Tunal         ]\n" +
                "    Linea: Av. Suba\n" +
                "        Portal de Suba\n" +
                "        La Campinna\n" +
                "        Suba TV 91\n" +
                "        21 Angeles\n" +
                "        Gratamira\n" +
                "        Suba Av. Boyaca\n" +
                "        Niza Calle 127\n" +
                "        Humedal Cordoba\n" +
                "        Shaio\n" +
                "        Puentelargo\n" +
                "        Suba Calle 100\n" +
                "        Suba Calle 95\n" +
                "        Rionegro\n" +
                "        San Martin\n" +
                "        NQS Calle 75\n" +
                "        Av. Chile\n" +
                "        Simon Bolivar\n" +
                "        Coliseo\n" +
                "        Campin U Antonio Narinno\n" +
                "        U Nacional\n" +
                "        Av. El Dorado\n" +
                "        CAD\n" +
                "        Paloquemao\n" +
                "        Ricaurte\n" +
                "        Comuneros\n" +
                "        Guatoque Veraguas\n" +
                "        Tygua San Jose\n" +
                "        Bicentenario         ]\n" +
                "    Linea: NQS Central\n" +
                "        Calle 100\n" +
                "        La Castellana\n" +
                "        NQS Calle 75\n" +
                "        Esc. Militar         ]\n" +
                "    Linea: Calle 80\n" +
                "        Portal de la 80\n" +
                "        Quirigua\n" +
                "        Carrera 90\n" +
                "        Avenida Cali\n" +
                "        Granja Carrera 77\n" +
                "        Minuto de Dios\n" +
                "        Boyaca\n" +
                "        Ferias\n" +
                "        Avenida 68\n" +
                "        Carrera 53\n" +
                "        Carrera 47\n" +
                "        Esc. Militar\n" +
                "        Polo\n" +
                "        Heroes         ]\n" +
                "    Linea: Av. Las Americas\n" +
                "        Portal de las Americas\n" +
                "        Patio Bonito\n" +
                "        Biblioteca Tintal\n" +
                "        Transversal 86\n" +
                "        Mandalay\n" +
                "        Mundo Aventura\n" +
                "        Marsella\n" +
                "        Pradera\n" +
                "        Americas Carrera 53A\n" +
                "        Puente Aranda\n" +
                "        Carrera 43\n" +
                "        Zona Industrial\n" +
                "        CDS Carrera 32\n" +
                "        Ricaurte\n" +
                "        San Facson Carrera 22\n" +
                "        De la Sabana\n" +
                "        Av Jimenez\n" +
                "        San Victorino\n" +
                "        Museo del Oro\n" +
                "        Las Aguas:Universidades}\n" +
                "    Linea: Calle 26\n" +
                "        Universidades:Las Aguas\n" +
                "        Calle 26\n" +
                "        Centro Memoria\n" +
                "        Plaza de la Democracia\n" +
                "        Ciudad Universitaria\n" +
                "        Corferias\n" +
                "        Quinta Paredes\n" +
                "        Gobernación\n" +
                "        CAN\n" +
                "        Salitre El Greco\n" +
                "        El Tiempo Maloka\n" +
                "        Av Rojas\n" +
                "        Normandia\n" +
                "        Modelia\n" +
                "        Portal el Dorado         ]\n" +
                "    Linea: Av. NQS Sur\n" +
                "        Comuneros\n" +
                "        Santa Isabel\n" +
                "        SENA\n" +
                "        NQS Calle 30 Sur\n" +
                "        NQS Calle 38 A Sur\n" +
                "        General Santander\n" +
                "        Alqueria\n" +
                "        Venecia\n" +
                "        Sevillana\n" +
                "        Magdalena\n" +
                "        Perdomo\n" +
                "        Portal del Sur\n" +
                "        Bosa\n" +
                "        La Despensa\n" +
                "        Leon XIII\n" +
                "        Terreros Hospital C.V.\n" +
                "        San Mateo         ]\n" +
                "    Linea: Carrera 10\n" +
                "        Portal 20 de Julio\n" +
                "        Country Sur\n" +
                "        Av. 1ro de Mayo\n" +
                "        Ciudad Jardin UAN\n" +
                "        Policarpa\n" +
                "        Hospitales\n" +
                "        Bicentenario\n" +
                "        San Victorino\n" +
                "        Las Nieves\n" +
                "        San Diego\n" +
                "        Museo Nacional";
        expectedArray = expectedString.split("\n");
        actualArray = red.toString().split("\n");
        assertEquals(expectedArray.length, actualArray.length);
        for (int i = 0; i < expectedArray.length; i++) {
            assertEquals(expectedArray[i], actualArray[i]);
        }
    }

    /**
     * Test of main method, of class Red.
     */
    @Test
    public void testMain() {

    }

}
