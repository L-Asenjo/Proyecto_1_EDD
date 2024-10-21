/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1;

/**
 * Clase Grafo, para representar un grafo. En el caso del proyecto los vertices
 * son paradas o estaciones de una linea de transporte.
 */
public class Grafo {

    /**
     * La lista de vertices
     */
    private ArrayList<Vertice> vertices;

    /**
     * La lista de sucursales
     */
    private ArrayList<String> sucursales;
    /**
     * La distancia para la cobertura de cada sucursal
     */
    private int t;

    /**
     * El tipo de búsqueda a utilizar, puede ser BFS (por amplitud) o DFS (por
     * profundidad) @see TipoBusqueda
     */
    private TipoBusqueda tipoBusqueda;

    /**
     * Constructor de la clase
     */
    public Grafo() {
        this.vertices = new ArrayList<>();
        this.sucursales = new ArrayList<>();
        this.t = 0;
        this.tipoBusqueda = TipoBusqueda.BFS;
    }

    /**
     * Getter de t
     * 
     * @return El valor de t
     */
    public int getT() {
        return this.t;
    }

    /**
     * Setter de t
     * 
     * @param t El valor de t
     */
    public void setT(int t) {
        if (t < 0) {
            throw new IllegalArgumentException("El parámetro t no puede ser negativo");
        }
        this.t = t;
    }

    /**
     * Getter de tipoBusqueda
     * 
     * @return El valor de tipoBusqueda
     */
    public TipoBusqueda getTipoBusqueda() {
        return this.tipoBusqueda;
    }

    /**
     * Setter de tipoBusqueda
     * 
     * @param tipoBusqueda El valor de tipoBusqueda
     */
    public void setTipoBusqueda(TipoBusqueda tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    /**
     * Devuelve el tamaño de la lista de vertices
     * 
     * @return El tamaño de la lista de vertices
     */
    public int getVerticesSize() {
        return this.vertices.size();
    }

    /**
     * Devuelve el tamaño de la lista de sucursales
     * 
     * @return El tamaño de la lista de sucursales
     */

    public int getSucursalesSize() {
        return this.sucursales.size();
    }

    /**
     * Busca un vertice por su nombre
     * 
     * @param nombre El nombre del vertice
     * @return El vertice
     */
    public Vertice buscarVertice(String nombre) {
        if (nombre == null || nombre.length() == 0) {
            return null;
        }
        if (this.vertices.vacia()) {
            return null;
        }
        for (int i = 0; i < this.vertices.size(); i++) {
            Vertice vertice = this.vertices.get(i);
            if (vertice.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
                return vertice;
            }
        }
        return null;
    }

    /**
     * Busca un vertice, sirve para saber si el vertice ya fue creado o no.
     * 
     * @param vertice
     * @return El vertice
     */
    public Vertice buscarVertice(Vertice vertice) {
        if (vertice == null) {
            return null;
        }
        return this.buscarVertice(vertice.getNombre());
    }

    /**
     * Agrega un vertice al grafo usando su nombre
     * 
     * @param nombre El nombre del vertice
     * @return El vertice
     */
    public Vertice agregarVertice(String nombre) {
        if (nombre == null || nombre.length() == 0) {
            return null;
        }
        if (this.buscarVertice(nombre) != null) {
            return null;
        }
        Vertice vertice = new Vertice(nombre);
        this.vertices.agregar(vertice);
        return vertice;
    }

    /**
     * Retorna el vertice del indice dado.
     * 
     * @param index El indice
     * @return El vertice
     */
    public Vertice getVertice(int index) {
        if (index < 0 || index >= this.vertices.size()) {
            return null;
        }
        if (this.vertices.vacia()) {
            return null;
        }
        return this.vertices.get(index);
    }

    /**
     * Devuelve un arreglo con los nombres de los vertices
     * 
     * @return Los nombres de los vertices
     */
    public String[] getVertices() {
        if (this.vertices.vacia()) {
            return null;
        }
        String[] vertices = new String[this.vertices.size()];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = this.vertices.get(i).getNombre();
        }
        return vertices;
    }

    /**
     * Agrega un adyacente a un vertice. Es decir agrega el vertice con su peso, a
     * la lista de adyacentes de un vertice dado.
     * Como este es un grafo sin dirección, se agrega en AMBAS direcciones es decir
     * del
     * Origen al Destino y del Destino al origen.
     * 
     * @param verticeOrigen  El vertice de origen.
     * @param verticeDestino El vertice de destino.
     * @param peso           El peso de la arista.
     * @return true si se pudo agregar el adyacente
     */
    public boolean agregarAdyacente(String verticeOrigen, String verticeDestino, int peso) {
        Vertice origen = this.buscarVertice(verticeOrigen);
        Vertice destino = this.buscarVertice(verticeDestino);
        if (origen == null || destino == null) {
            return false;
        }
        origen.agregarAdyacente(destino.getNombre(), peso);
        destino.agregarAdyacente(origen.getNombre(), peso);
        return true;
    }

    /**
     * Agrega un adyacente a un vertice. Es decir agrega el vertice con su peso, a
     * la lista de adyacentes de un vertice dado.
     * Como este es un grafo sin búsqueda, se agrega en AMBAS direcciones es decir
     * del Origen al Destino y del Destino al origen.
     * 
     * @param verticeOrigen  El vertice de origen.
     * @param verticeDestino El vertice de destino.
     * @param peso           El peso de la arista.
     * @return true si se pudo agregar el adyacente
     */
    public boolean agregarAdyacente(Vertice verticeOrigen, Vertice verticeDestino, int peso) {
        if (verticeOrigen == null || verticeDestino == null) {
            return false;
        }
        return this.agregarAdyacente(verticeOrigen.getNombre(), verticeDestino.getNombre(), peso);
    }

    /**
     * Busca una sucursal por su nombre, en la lista de sucursales
     * 
     * @param nombre el nombre de la sucursal
     * @return el indice de la sucursal o -1 si no se encuentra
     */
    public int buscarSucursal(String nombre) {
        if (this.vertices.vacia() || this.sucursales.vacia()) {
            return -1;
        }
        for (int i = 0; i < this.sucursales.size(); i++) {
            String sucursal = this.sucursales.get(i);
            if (sucursal.toLowerCase().equals(nombre.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Agrega una sucursal a la lista de sucursales
     * 
     * @param nombre el nombre de la sucursal
     * @return true si se agrego o false si ya existe
     */
    public boolean agregarSucursal(String nombre) {
        if (vertices.vacia()) {
            return false;
        }
        if (this.buscarVertice(nombre) == null) {
            return false;
        }
        if (this.buscarSucursal(nombre) != -1) {
            return false;
        }
        this.sucursales.agregar(nombre);
        return true;
    }

    /**
     * Elimina una sucursal de la lista de sucursales
     * 
     * @param nombre el nombre de la sucursal
     * @return true si se elimino o false si no existe
     */
    public boolean eliminarSucursal(String nombre) {
        if (vertices.vacia()) {
            return false;
        }
        int index = this.buscarSucursal(nombre);
        if (index == -1) {
            return false;
        }
        this.sucursales.remover(index);
        return true;
    }

    /**
     * Devuelve un arreglo con los nombres de las sucursales
     * 
     * @return Los nombres de las sucursales
     */
    public String[] getSucursales() {
        if (this.vertices.vacia()) {
            return null;
        }
        String[] aux = new String[this.sucursales.size()];
        for (int i = 0; i < this.sucursales.size(); i++) {
            aux[i] = this.sucursales.get(i);
        }
        return aux;
    }

    /**
     * Agrega un arreglo de sucursales a la lista de sucursales
     * 
     * @param sucursales el arreglo de sucursales
     * @return true si se agregaron o false sen caso contrario
     */
    public boolean setSucursales(String[] sucursales) {
        if (vertices.vacia()) {
            return false;
        }
        for (int i = 0; i < sucursales.length; i++) {
            this.agregarSucursal(sucursales[i]);
        }
        return true;
    }

    /**
     * Vacia la lista de sucursales
     */
    public void vaciarSucursales() {
        this.sucursales.vaciar();
    }

    /**
     * Recorre el grafo por amplitud y devuelve un arreglo con los vertices
     * visitados.
     * Recordar que el objeto Visitado, tiene la distancia al vertice inicial.
     * Puede usar T o no, ya que sin usar T, nos permite verificar si el grafo es
     * conexo.
     * En el recorrido por amplitud se usa una cola para visitar los adyacentes
     * del vertice que se esta visitando.
     * 
     * @param nombreVertice El nombre del vertice en que empieza el recorrido
     * @param usarT         True si se usa T, false si no
     * @return El arreglo con los vertices visitados
     */
    public Visitado[] recorrerPorAmplitud(String nombreVertice, boolean usarT) {
        if (usarT) {
            // verificando que t no sea negativo o cero
            if (this.t < 0) {
                throw new IllegalArgumentException("El parámetro t no puede ser negativo");
            } else if (this.t == 0) {
                throw new IllegalArgumentException(
                        "El parámetro t no puede ser 0 para usar t en un recorrido por amplitud");
            }
        }
        // se crea la lista de visitados y la cola para el manejo de los adyacentes a
        // visitar
        ArrayList<Visitado> visitados = new ArrayList<>();
        Fifo<Visitado> cola = new Fifo<>();
        // verifico que el vertice exista
        Vertice vertice = this.buscarVertice(nombreVertice);
        if (vertice == null) {
            throw new IllegalArgumentException("El vertice no existe");
        }
        // se encola para iniciar el recorrido
        cola.encolar(new Visitado(vertice.getNombre(), 0));
        // mientras la cola no este vacía se recorre el grafo
        while (!cola.vacia()) {
            // se saca el primer elemento de la cola
            // es el vertice que se esta visitando.
            Visitado visitado = cola.desencolar();
            // solo verificando que el vertice exista, y necesito el vertice
            // para poder conseguir sus adyacentes
            vertice = this.buscarVertice(visitado.nombre);
            if (vertice == null) {
                throw new NullPointerException("El vertice no existe");
            }
            // se verifica si se usa t o si el recorrido es completo
            if (usarT) {
                if (visitado.distancia > this.t) {
                    // si llegué aquí, superé la distancia t.
                    continue;
                }
            }
            boolean visitadoEncontrado = false;
            boolean saltarAdyacentes = false;
            // se verifica si ya fue visitado
            for (int i = 0; i < visitados.size(); i++) {
                if (visitado.nombre.toLowerCase().equals(visitados.get(i).nombre.toLowerCase())) {
                    // Si este algoritmo no tuviera pesos, o distancias, que es lo mismo,
                    // solo se ignoraría el vertice porque ya fue visitado anteriormente.
                    // Pero con los pesos, puede pasar que un camino usado incluya paradas con
                    // transferencias cuyos pesos son 0 y la distancia puede ser menor.
                    // Si es así, se actualiza la distancia del nodo visitado.
                    if (visitado.distancia < visitados.get(i).distancia) {
                        visitados.get(i).distancia = visitado.distancia;
                    } else {
                        // si no es más corta la distancia, se ignora los adyacentes
                        // porque ya fue visitado
                        saltarAdyacentes = true;
                    }
                    visitadoEncontrado = true;
                    break;
                }
            }
            if (visitadoEncontrado && saltarAdyacentes) {
                // si el vertice ya fue visitado y su distancia no es menor, se ignoran sus
                // adyacentes
                continue;
            }
            // finalmente se agrega a la lista de visitados. Si no fue visitado.
            if (!visitadoEncontrado) {
                visitados.agregar(visitado);
            }
            // se encolan sus adyacentes
            Adyacente[] adyacentes = vertice.getAdyacentes();
            if (adyacentes == null) {
                // esto sería un problema, ya que el vertice no tiene adyacentes
                // el vertice esta aislado y el grafo no sería conexo.
                throw new NullPointerException("El vertice no tiene adyacentes");
            }
            for (int i = 0; i < adyacentes.length; i++) {
                // aquí se agrega el adyacente al final de la cola, con su distancia al vertice
                // inicial.
                cola.encolar(new Visitado(adyacentes[i].nombre, visitado.distancia + adyacentes[i].peso));
            }
        }
        // copiamos la lista de visitados a un arreglo para retornarla.
        Visitado[] aux = new Visitado[visitados.size()];
        for (int i = 0; i < visitados.size(); i++) {
            aux[i] = visitados.get(i);
        }
        return aux;
    }

    /**
     * Recorre el grafo por profundidad y devuelve un arreglo con los vertices
     * visitados.
     * Recordar que el objeto Visitado, tiene la distancia al vertice inicial.
     * Puede usar T o no, ya que sin usar T, nos permite verificar si el grafo es
     * conexo.
     * En el recorrido por profundidad se usa una pila para visitar los adyacentes
     * del vertice que se esta visitando. Y al apilarlos se agregan inversamente
     * a como fueron creados. (Esto último por decisión del programador).
     * 
     * @param nombreVertice el nombre del vertice inicial
     * @param usarT         si se usa T o no
     * @return el arreglo de visitados
     */
    public Visitado[] recorrerPorProfundidad(String nombreVertice, boolean usarT) {
        if (usarT) {
            // verificando que t no sea negativo o cero
            if (this.t < 0) {
                throw new IllegalArgumentException("El parámetro t no puede ser negativo");
            } else if (this.t == 0) {
                throw new IllegalArgumentException(
                        "El parámetro t no puede ser 0 para usar t en un recorrido por amplitud");
            }
        }
        // se crea la lista de visitados y la pila para el manejo de los adyacentes a
        // visitar
        ArrayList<Visitado> visitados = new ArrayList<>();
        Lifo<Visitado> pila = new Lifo<>();
        // verifico que el vertice exista
        Vertice vertice = this.buscarVertice(nombreVertice);
        if (vertice == null) {
            throw new IllegalArgumentException("El vertice no existe");
        }
        // se apila para iniciar el recorrido
        pila.apilar(new Visitado(vertice.getNombre(), 0));
        // mientras la pila no este vacía se recorre el grafo
        while (!pila.vacia()) {
            // se saca el elemento del tope de la pila
            // es el vertice que se esta visitando.
            Visitado visitado = pila.desapilar();
            // solo verificando que el vertice exista, y necesito el vertice
            // para poder conseguir sus adyacentes
            vertice = this.buscarVertice(visitado.nombre);
            if (vertice == null) {
                throw new NullPointerException("El vertice no existe");
            }
            // se verifica si se usa t o si el recorrido es completo
            if (usarT) {
                if (visitado.distancia > this.t) {
                    // si llegué aquí, superé la distancia t.
                    continue;
                }
            }
            boolean visitadoEncontrado = false;
            boolean saltarAdyacentes = false;
            // se verifica si ya fue visitado
            for (int i = 0; i < visitados.size(); i++) {
                if (visitado.nombre.toLowerCase().equals(visitados.get(i).nombre.toLowerCase())) {
                    // Si este algoritmo no tuviera pesos, o distancias, que es lo mismo,
                    // solo se ignoraría el vertice porque ya fue visitado anteriormente.
                    // Pero con los pesos, puede pasar que un camino usado incluya paradas con
                    // transferencias cuyos pesos son 0 y la distancia puede ser menor.
                    // Si es así, se actualiza la distancia del nodo visitado.
                    if (visitado.distancia < visitados.get(i).distancia) {
                        visitados.get(i).distancia = visitado.distancia;
                    } else {
                        // si no es más corta la distancia, se ignora los adyacentes
                        // porque ya fue visitado
                        saltarAdyacentes = true;
                    }
                    visitadoEncontrado = true;
                    break;
                }
            }
            if (visitadoEncontrado && saltarAdyacentes) {
                // si el vertice ya fue visitado y su distancia no es menor, se ignoran sus
                // adyacentes
                continue;
            }
            // finalmente se agrega a la lista de visitados. Si no fue visitado.
            if (!visitadoEncontrado) {
                visitados.agregar(visitado);
            }
            // se apilan todos los adyacentes del vertice
            Adyacente[] adyacentes = vertice.getAdyacentes();
            if (adyacentes == null) {
                // esto sería un problema, ya que el vertice no tiene adyacentes
                // el vertice esta aislado y el grafo no sería conexo.
                throw new NullPointerException("El vertice no tiene adyacentes");
            }
            // se recorren todos los adyacentes del vertice, pero en orden inverso, para que
            // el
            // ultimo de la pila, sea el primero, y así seguimos el orden (si es que existe)
            // de los vertices, como se crearon. Esto es solo para poder hacer test y
            // correrlo manualmente para ver si hace sentido.
            for (int i = adyacentes.length - 1; i >= 0; i--) {
                // se apila el adyacente en el tope de la pila, con su distancia al vertice
                // inicial
                pila.apilar(new Visitado(adyacentes[i].nombre, visitado.distancia + adyacentes[i].peso));
            }
        }
        // copiamos la lista de visitados a un arreglo para retornarla.
        Visitado[] aux = new Visitado[visitados.size()];
        for (int i = 0; i < visitados.size(); i++) {
            aux[i] = visitados.get(i);
        }
        return aux;
    }

    /**
     * Método para convertir un arreglo de visitados en una cadena de texto. Usado
     * sobre todo
     * para hacer pruebas.
     * 
     * @param visitados El arreglo de visitados
     * @return La cadena de texto
     */
    public static String visitadosToString(Visitado[] visitados) {
        String txt = "";
        for (int i = 0; i < visitados.length; i++) {
            if (i > 0) {
                txt += ", ";
            }
            txt += visitados[i].toString();
        }
        return txt;
    }

    /**
     * Determina si el grafo es conexo o no. Usado para hacer pruebas.
     * 
     * @return true si es conexo, false si no
     */
    public boolean esConexo() {
        String nombrePrimerVertice = vertices.get(0).getNombre();
        Visitado[] visitados = null;
        if (this.tipoBusqueda == TipoBusqueda.BFS) {
            visitados = recorrerPorAmplitud(nombrePrimerVertice, false);
        } else {
            visitados = recorrerPorProfundidad(nombrePrimerVertice, false);
        }
        if (visitados == null) {
            return false;
        }
        if (visitados.length == vertices.size()) {
            return true;
        }
        return false;
    }

    /**
     * Asigna la cobertura de un vertice, agregándole los visitados a su lista de
     * cobertura.
     * 
     * @param nombreVertice
     */
    public void asignarCobertura(String nombreVertice) {
        if (this.t <= 0) {
            throw new IllegalArgumentException("El parámetro t no puede ser negativo o cero");
        }
        Vertice vertice = this.buscarVertice(nombreVertice);
        if (vertice == null) {
            throw new NullPointerException("El vertice " + nombreVertice + " no existe");
        }
        Visitado[] visitados = null;
        if (this.tipoBusqueda == TipoBusqueda.BFS) {
            visitados = this.recorrerPorAmplitud(vertice.getNombre(), true);
        } else {
            visitados = this.recorrerPorProfundidad(vertice.getNombre(), true);
        }
        if (visitados == null) {
            throw new NullPointerException("El vertice " + vertice.getNombre() + " no tiene adyacentes");
        }
        for (int i = 0; i < visitados.length; i++) {
            vertice.agregarVisitado(visitados[i]);
        }
    }

    /**
     * Calcula la cobertura de todos los vertices y se las asigna.
     */
    public void asignarCoberturas() {
        if (this.t <= 0) {
            throw new IllegalArgumentException("El parámetro t no puede ser negativo o cero");
        }
        for (int i = 0; i < this.vertices.size(); i++) {
            Vertice vertice = this.vertices.get(i);
            Visitado[] visitados = null;
            if (this.tipoBusqueda == TipoBusqueda.BFS) {
                visitados = this.recorrerPorAmplitud(vertice.getNombre(), true);
            } else {
                visitados = this.recorrerPorProfundidad(vertice.getNombre(), true);
            }
            if (visitados == null) {
                throw new NullPointerException("El vertice " + vertice.getNombre() + " no tiene adyacentes");
            }
            for (int j = 0; j < visitados.length; j++) {
                vertice.agregarVisitado(visitados[j]);
            }
        }
    }

    /**
     * Calcula la cobertura total de todas las sucursales, en la lista, y la
     * devuelve como un arreglo de nombres de vertices. Se usa la clase Set
     * (conjunto), para que sea más fácil agregar nombres de vertices y que no se
     * dupliquen. @see Set
     * 
     * @return un arreglo de nombres de vertices que están cubiertos por todas las
     *         sucursales
     */
    public String[] getCoberturaTotal() {
        this.asignarCoberturas();
        Set<String> coberturaTotal = new Set<>();
        for (int i = 0; i < this.sucursales.size(); i++) {
            String nombreSucursal = this.sucursales.get(i);
            Vertice vertice = this.buscarVertice(nombreSucursal);
            Visitado[] cobertura = vertice.getCobertura();
            for (int j = 0; j < cobertura.length; j++) {
                coberturaTotal.agregar(cobertura[j].nombre);
            }
        }
        String[] aux = new String[coberturaTotal.size()];
        for (int i = 0; i < aux.length; i++) {
            aux[i] = coberturaTotal.pop();
        }
        return aux;
    }

    /**
     * Determina si con las sucursales de la lista de sucursales hay cobertura
     * total. Se usa la clase Set (conjunto). @see Set
     * 
     * @return
     */
    public boolean hayCoberturaTotal() {
        Set<String> coberturaTotal = new Set<>(this.getCoberturaTotal());
        Set<String> vertices = new Set<>(this.getVertices());
        Set<String> verticesFaltantes = vertices.diferencia(coberturaTotal);
        if (verticesFaltantes.vacio()) {
            return true;
        }
        return false;
    }

    /**
     * Método recursivo que calcula las mejores sucursales. Es privado y es llamado
     * por recomendar sucursales.
     * 
     * @param sucursales  la lista de sucursales
     * @param verticesSet el conjunto de vertices totales
     * @return una lista de string con las sucursales
     */
    private String[] calcularSucursales(ArrayList<String> sucursales, Set<String> verticesSet) {

        // Verifico que tenga en this.sucursales las sucursales que me pasaron.
        this.sucursales.vaciar();
        for (int i = 0; i < sucursales.size(); i++) {
            this.sucursales.agregar(sucursales.get(i));
        }

        // y calculo las coberturas de todos los vertices.
        this.asignarCoberturas();

        // creo un conjunto de nombres de vertices que tienen cobertura.
        Set<String> coberturaTotalSet = new Set<>(this.getCoberturaTotal());

        // Les resto el conjunto de vertices totales que me pasaron (me los pasaron)
        // para no tener que hacer un nuevo conjunto cada vez, ese conjunto no se
        // modifica.
        // asi que obtengo los vertices que faltan por cobertura.
        Set<String> verticesFaltantesSet = verticesSet.diferencia(coberturaTotalSet);

        // Este es el caso base de la recursion. Si no tengo vertices faltantes,
        // devuelvo las sucursales.
        if (verticesFaltantesSet.vacio()) {
            String[] aux = new String[sucursales.size()];
            for (int i = 0; i < aux.length; i++) {
                aux[i] = sucursales.get(i);
            }
            return aux;
        }

        // Tengo que tomar uno por uno, así que lo transformo en un array de strings.
        ArrayList<String> verticesFaltantesList = verticesFaltantesSet.toList();

        // Y necesito guardar las sucursales que me devuelven las llamadas a
        // calcularSucursales.
        String[][] sucursalesCalculadasArray = new String[verticesFaltantesList.size()][];

        // Voy vertice por vertice faltante, y lo convierto en sucursal, para ver que
        // pasa!!
        for (int i = 0; i < verticesFaltantesList.size(); i++) {
            ArrayList<String> sucursalesRecomendadas = new ArrayList<>(sucursales);
            // Le agrego a la sucursales, el vertice faltante
            sucursalesRecomendadas.agregar(verticesFaltantesList.get(i));
            // veo que me devuelve el método recursivo
            String[] sucursalesCalculadas = calcularSucursales(sucursalesRecomendadas, verticesSet);
            // y lo agrego al arreglo, para ver cuantas sucursales me devuelven.
            sucursalesCalculadasArray[i] = sucursalesCalculadas;
        }

        // calculo la menor cantidad de sucursales.... y esa es la que devuelvo.
        Integer min_value = null;
        Integer min_index = null;
        for (int i = 0; i < sucursalesCalculadasArray.length; i++) {
            if (min_value == null || sucursalesCalculadasArray[i].length < min_value) {
                min_value = sucursalesCalculadasArray[i].length;
                min_index = i;
            }
        }
        return sucursalesCalculadasArray[min_index];
    }

    /**
     * Devuelve un arreglo con las sucursales que se pueden recomendar.
     * 
     * @return arreglo con las sucursales reco
     */
    public String[] recomendarSucursales() {

        // Necesito guardar las sucursales originales para no perderlas
        // y poder volver al estado inicial
        ArrayList<String> sucursalesBkup = new ArrayList<>();
        for (int i = 0; i < this.sucursales.size(); i++) {
            sucursalesBkup.agregar(this.sucursales.get(i));
        }

        // Conjunto de todos los vertices para poder restarles el conjunto
        // de vertices de la cobertura y calcular que vertices faltan.
        Set<String> verticesSet = new Set<>(this.getVertices());

        // Calculo la cobertura total actual.
        this.asignarCoberturas();

        // conjunto de la cobertura total actual
        Set<String> coberturaTotalSet = new Set<>(this.getCoberturaTotal());

        // Si al conjunto de los vertices totales le resto el conjunto de vertices de
        // la cobertura total, obtengo los vertices faltantes
        Set<String> verticesFaltantesSet = verticesSet.diferencia(coberturaTotalSet);

        // Si es vacío, no hay sucursales que recomendar.
        if (verticesFaltantesSet.vacio()) {
            return new String[0];
        }

        ArrayList<String> sucursalesIniciales = new ArrayList<>(this.sucursales);
        // Hago la llamada al método recursivo que calcula las mejores sucursales
        String[] sucursalesRecomendadasTotales = this.calcularSucursales(sucursalesIniciales, verticesSet);

        // Vuelvo a colocar todo como antes de hacer el análisis.
        this.sucursales.vaciar();
        for (int i = 0; i < sucursalesBkup.size(); i++) {
            this.sucursales.agregar(sucursalesBkup.get(i));
        }
        this.asignarCoberturas();

        // Utilizo conjunto para quitar las sucursales actuales a las sucursales
        // recomendadas totales
        // así solo dejo las nuevas.
        Set<String> sucursalesRecomendadasTotalesSet = new Set<>(sucursalesRecomendadasTotales);
        Set<String> sucursalesActualesSet = new Set<>(this.sucursales);
        Set<String> sucursalesRecomendadasSet = sucursalesRecomendadasTotalesSet.diferencia(sucursalesActualesSet);

        // creo el arreglo de string que voy a devolver.
        String[] sucursalesRecomendadas = new String[sucursalesRecomendadasSet.size()];
        int tam = sucursalesRecomendadasSet.size();
        for (int i = 0; i < tam; i++) {
            sucursalesRecomendadas[i] = sucursalesRecomendadasSet.pop();
        }
        // Devuelvo las mejores sucursales
        return sucursalesRecomendadas;
    }

    /**
     * Vacia el grafo completo. Usado para cargar un grafo nuevo.
     */
    public void vaciar() {
        for (int i = 0; i < this.vertices.size(); i++) {
            this.vertices.get(i).vaciar();
        }
        this.vertices.vaciar();
        this.vaciarSucursales();
        this.t = 0;
        this.tipoBusqueda = TipoBusqueda.BFS;
    }

    /**
     * Devuelve una representación en cadena del grafo.
     * Util para hacer pruebas.
     */
    public String toString() {
        String txt = "";
        for (int i = 0; i < this.vertices.size(); i++) {
            txt += this.vertices.get(i).toString() + "\n";
            Adyacente[] adyacentes = this.vertices.get(i).getAdyacentes();
            for (int j = 0; j < adyacentes.length; j++) {
                txt += "\t" + adyacentes[j].toString();
            }
            txt += "\n";
        }
        return txt;
    }

    /**
     * Solo para hacer unas pruebas.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        for (char letra = 'A'; letra <= 'Z'; letra++) {
            grafo.agregarVertice(String.valueOf(letra));
        }
        grafo.agregarAdyacente("A", "B", 1);
        grafo.agregarAdyacente("B", "C", 1);
        grafo.agregarAdyacente("C", "D", 1);
        grafo.agregarAdyacente("D", "E", 1);
        grafo.agregarAdyacente("E", "F", 1);

        grafo.agregarAdyacente("G", "H", 1);
        grafo.agregarAdyacente("H", "E", 1);
        grafo.agregarAdyacente("E", "I", 1);
        grafo.agregarAdyacente("I", "J", 1);
        grafo.agregarAdyacente("J", "K", 1);
        grafo.agregarAdyacente("K", "L", 1);

        grafo.agregarAdyacente("M", "N", 1);
        grafo.agregarAdyacente("N", "O", 1);
        grafo.agregarAdyacente("O", "L", 1);
        grafo.agregarAdyacente("L", "P", 1);
        grafo.agregarAdyacente("P", "Q", 1);

        grafo.agregarAdyacente("J", "R", 0);
        grafo.agregarAdyacente("R", "S", 1);
        grafo.agregarAdyacente("S", "T", 1);

        grafo.agregarAdyacente("U", "S", 1);
        grafo.agregarAdyacente("S", "V", 1);
        grafo.agregarAdyacente("V", "Q", 0);
        grafo.agregarAdyacente("Q", "W", 1);
        grafo.agregarAdyacente("W", "X", 1);

        grafo.agregarAdyacente("V", "Y", 1);
        grafo.agregarAdyacente("Y", "Z", 1);
        // System.out.println(grafo.toString());
        // Visitado[] visitados = grafo.recorrerPorAmplitud("A", false);
        // grafo.setT(3);
        // Visitado[] visitados = grafo.recorrerPorAmplitud("E", true);
        // String nodosVisitados = Grafo.visitadosToString(visitados);
        // System.out.println(nodosVisitados);

        // Visitado[] visitados = grafo.recorrerPorProfundidad("A", false);
        grafo.setT(3);
        // Visitado[] visitados = grafo.recorrerPorProfundidad("E", true);
        // String nodosVisitados = Grafo.visitadosToString(visitados);
        // System.out.println(nodosVisitados);

        // TipoBusqueda búsqueda = TipoBusqueda.DFS;
        // System.out.println("El tipo de la busqueda es:" + busqueda.toString());
        grafo.asignarCoberturas();
        System.out.println(grafo.toString());
        grafo.setTipoBusqueda(TipoBusqueda.DFS);
        grafo.agregarSucursal("J");
        grafo.agregarSucursal("A");
        String[] sucursales = grafo.recomendarSucursales();
        System.out.println("Las sucursales recomendadas son: ");
        for (int i = 0; i < sucursales.length; i++) {
            System.out.print(sucursales[i]);
            if (i < sucursales.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();

    }

}
