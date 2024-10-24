/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto1;

import java.lang.reflect.Array;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

public class VentanaManejadorGrafo extends javax.swing.JFrame {
    /**
     * Algunas constantes que nos permiten definir como se ven los nodos del grafo
     * dependiendo de su status.
     */
    // Example
    // "size: 8px; stroke-mode: plain; stroke-color: green; stroke-width: 3px;
    // fill-color: rgba(0,0,0,0);"
    private final String attributesFormat = "size: <size>px; stroke-mode: plain; stroke-color: <color>; stroke-width: <width>px; fill-color: <fill-color>;";
    private final String[] attributesTags = { "<size>", "<color>", "<width>", "<fill-color>" };
    private final String[] normal = { "8", "green", "3", "rgba(0,0,0,0)" };
    private final String[] sucursal = { "16", "green", "3", "rgba(0,0,0,0)" };
    private final String[] faltantes = { "8", "red", "3", "rgba(0,0,0,0)" };
    private final String[] conCoberturaTotal = { "8", "green", "3", "rgba(0,0,0,0)" };
    private final String[] seleccionada = { "14", "gray", "3", "rgba(0,0,0,0)" };
    private final String[] sucursalSeleccionada = { "20", "gray", "3", "rgba(0,0,0,0)" };
    private final String[] sucursalEnCoberturaSeleccionada = { "16", "gray", "3", "rgba(0,0,0,0)" };
    private final String[] coberturaSeleccionada = { "8", "gray", "3", "rgba(0,0,0,0)" };
    private final String[] recomendadas = { "16", "red", "3", "rgba(0,0,0,0)" };

    /**
     * Referencias a las clases principal, ventana principal y ventana grafo
     */
    private Proyecto1 mainClass;
    private VentanaPrincipal ventanaPrincipal;
    private VentanaGrafo ventanaGrafo;

    /**
     * Referencias a los objetos grafo y gsGraph, para hacer más corta la referencia
     * a estos.
     */
    private Graph gsGraph;
    private Grafo grafo;
    /**
     * Referencia al viewer de graphstream, crea el view que se utiliza en la
     * ventanaGrafo.
     */
    private Viewer viewer;
    /**
     * Si hay una parada seleccionada o no.
     */
    private String nombreParadaSeleccionada = null;

    /**
     * Conjuntos que permite manejar los atributos visuales de cada nodo, y así
     * poder tener una diferencia visual entre nodos en coberturas, sucursales, etc.
     */
    private Set<String> coberturaTotal;
    private Set<String> verticesFaltantes;
    private Set<String> coberturaParada;
    private Set<String> sucursales;
    private Set<String> sucursalesRecomendadas;

    /**
     * Creates new form VentanaManejadorVentanaGrafo
     */
    public VentanaManejadorGrafo(Proyecto1 mainClass, VentanaPrincipal ventanaPrincipal) {
        this.mainClass = mainClass;
        this.ventanaPrincipal = ventanaPrincipal;
        this.gsGraph = this.mainClass.red.getGsGraph();
        this.grafo = this.mainClass.red.getGrafo();
        setLocationRelativeTo(ventanaPrincipal);
        setTitle("Manejador Del Grafo");
        grafo.setT(1);
        this.grafo.setTipoBusqueda(TipoBusqueda.BFS);
        this.grafo.asignarCoberturas();
        this.updateSets();
        this.updateGsGraph();
        this.initComponents();
        this.setUpGraphView();
        this.setUpSpinner();
        this.setUpListParadas();
        this.setUpComboBoxTipoBusqueda();
    }

    public VentanaManejadorGrafo() {
        initComponents();
    }

    /**
     * Método para ordenar una lista de strings, usado para desplegar listas o
     * text areas con información ordenada.
     *
     * @param opciones el array de strings a ordenar
     * @return el array de strings ordenado
     */
    private static String[] ordenarOpciones(String[] opciones) {
        ArrayList<String> listaOpciones = new ArrayList<>(opciones);
        ArrayList<String> listaOrdenada = new ArrayList<>();
        while (listaOpciones.size() > 0) {
            String minValue = null;
            Integer minIndex = null;
            for (int i = 0; i < listaOpciones.size(); i++) {
                if (minValue == null || listaOpciones.get(i).compareToIgnoreCase(minValue) < 0) {
                    minValue = listaOpciones.get(i);
                    minIndex = i;
                }
            }
            listaOrdenada.agregar(minValue);
            listaOpciones.remover(minIndex);
        }
        String[] listaOrdenadaArray = new String[listaOrdenada.size()];
        for (int i = 0; i < listaOrdenada.size(); i++) {
            listaOrdenadaArray[i] = listaOrdenada.get(i);
        }
        return listaOrdenadaArray;
    }

    /**
     * Permite generar un string de atributos visuales de un nodo, dependiendo
     * de que tipo de nodo es.
     * 
     * @param attributes el array de atributos visuales
     * @return
     */
    private String getNodeAttributes(String[] attributes) {
        String attributesString = this.attributesFormat;
        for (int i = 0; i < attributes.length; i++) {
            attributesString = attributesString.replace(attributesTags[i], attributes[i]);
        }
        return attributesString;
    }

    /**
     * Actualiza los nodos dependiendo de a que conjunto pertenecen.
     */
    private void updateGsGraph() {
        if (sucursales.vacio()) {
            String formatoNormal = this.getNodeAttributes(this.normal);
            for (int i = 0; i < this.gsGraph.getNodeCount(); i++) {
                this.gsGraph.getNode(i).setAttribute("ui.style", formatoNormal);
            }
        }
        if (!verticesFaltantes.vacio() && !sucursales.vacio()) {
            String formatoFaltantes = this.getNodeAttributes(this.faltantes);
            ArrayList<String> verticesFaltantesArrayList = verticesFaltantes.toList();
            for (int i = 0; i < verticesFaltantesArrayList.size(); i++) {
                Node node = this.gsGraph.getNode(verticesFaltantesArrayList.get(i));
                node.setAttribute("ui.style", formatoFaltantes);
            }
        }
        Set<String> soloCoberturaTotal = coberturaTotal.diferencia(sucursales).diferencia(coberturaParada);
        if (!soloCoberturaTotal.vacio()) {
            String formatoCoberturaTotal = this.getNodeAttributes(this.conCoberturaTotal);
            ArrayList<String> soloCoberturaTotalArrayList = soloCoberturaTotal.toList();
            for (int i = 0; i < soloCoberturaTotalArrayList.size(); i++) {
                Node node = this.gsGraph.getNode(soloCoberturaTotalArrayList.get(i));
                node.setAttribute("ui.style", formatoCoberturaTotal);
            }
        }
        if (this.nombreParadaSeleccionada == null) {
            String formatoSucursal = this.getNodeAttributes(this.sucursal);
            ArrayList<String> sucursalesArrayList = sucursales.toList();
            for (int i = 0; i < sucursalesArrayList.size(); i++) {
                Node node = this.gsGraph.getNode(sucursalesArrayList.get(i));
                node.setAttribute("ui.style", formatoSucursal);
            }
        }
        Set<String> soloSucursalesNoEnCobertura = sucursales.diferencia(coberturaParada);
        if (!soloSucursalesNoEnCobertura.vacio()) {
            String formatoSucursalesNoEnCobertura = this.getNodeAttributes(this.sucursal);
            ArrayList<String> soloSucursalesNoEnCoberturaArrayList = soloSucursalesNoEnCobertura.toList();
            for (int i = 0; i < soloSucursalesNoEnCoberturaArrayList.size(); i++) {
                Node node = this.gsGraph.getNode(soloSucursalesNoEnCoberturaArrayList.get(i));
                node.setAttribute("ui.style", formatoSucursalesNoEnCobertura);
            }
        }
        Set<String> soloCoberturaParadaNoSucursales = coberturaParada.diferencia(sucursales);
        if (!soloCoberturaParadaNoSucursales.vacio()) {
            String formatoCoberturaParadaNoSucursales = this.getNodeAttributes(this.coberturaSeleccionada);
            ArrayList<String> soloCoberturaParadaNoSucursalesArrayList = soloCoberturaParadaNoSucursales.toList();
            for (int i = 0; i < soloCoberturaParadaNoSucursalesArrayList.size(); i++) {
                Node node = this.gsGraph.getNode(soloCoberturaParadaNoSucursalesArrayList.get(i));
                node.setAttribute("ui.style", formatoCoberturaParadaNoSucursales);
            }
        }
        Set<String> sucursalesEnCobertura = sucursales.interseccion(coberturaParada);
        if (!sucursalesEnCobertura.vacio()) {
            String formatoSucursalesEnCobertura = this.getNodeAttributes(this.sucursalEnCoberturaSeleccionada);
            ArrayList<String> sucursalesEnCoberturaArrayList = sucursalesEnCobertura.toList();
            for (int i = 0; i < sucursalesEnCoberturaArrayList.size(); i++) {
                Node node = this.gsGraph.getNode(sucursalesEnCoberturaArrayList.get(i));
                node.setAttribute("ui.style", formatoSucursalesEnCobertura);
            }
        }

        if (!sucursalesRecomendadas.vacio()) {
            String formatSucursalesRecomendadas = this.getNodeAttributes(this.recomendadas);
            ArrayList<String> sucursalesRecomendadasArrayList = this.sucursalesRecomendadas.toList();
            for (int i = 0; i < sucursalesRecomendadasArrayList.size(); i++) {
                Node node = this.gsGraph.getNode(sucursalesRecomendadasArrayList.get(i));
                node.setAttribute("ui.style", formatSucursalesRecomendadas);
            }
        }
        if (this.nombreParadaSeleccionada == null) {
            return;
        }
        if (!sucursales.vacio() && sucursales.contiene(this.nombreParadaSeleccionada)) {
            String formatoSucursalSeleccionada = this.getNodeAttributes(this.sucursalSeleccionada);
            Node node = this.gsGraph.getNode(this.nombreParadaSeleccionada);
            node.setAttribute("ui.style", formatoSucursalSeleccionada);
            return;
        }
        String formatoSeleccionada = this.getNodeAttributes(this.seleccionada);
        Node node = this.gsGraph.getNode(this.nombreParadaSeleccionada);
        node.setAttribute("ui.style", formatoSeleccionada);

    }

    /**
     * Actualiza los distintos conjuntos, de forma que luego se pueda saber que
     * atributos visuales va a tener cada nodo.
     */
    private void updateSets() {
        Set<String> vertices = new Set<>(this.grafo.getVertices());
        Set<String> sucursales = new Set<>(this.grafo.getSucursales());
        Set<String> coberturaTotal = new Set<>(this.grafo.getCoberturaTotal());
        Set<String> verticesFaltantes = vertices.diferencia(coberturaTotal);
        Set<String> coberturaParada = null;
        if (this.nombreParadaSeleccionada != null) {
            Vertice vertice = this.grafo.buscarVertice(this.nombreParadaSeleccionada);
            coberturaParada = new Set<>(vertice.getNombresCobertura());
        } else {
            coberturaParada = new Set<>();
        }
        this.sucursales = sucursales;
        this.coberturaTotal = coberturaTotal;
        this.verticesFaltantes = verticesFaltantes;
        this.coberturaParada = coberturaParada;
        this.sucursalesRecomendadas = new Set<>(this.grafo.recomendarSucursales());
    }

    /**
     * Crea la vista del grafo en GraphStream, y la pasa a la ventana ventanaGrafo,
     * para que lo despliegue.
     */
    private void setUpGraphView() {
        System.setProperty("org.graphstream.ui", "swing");
        viewer = new SwingViewer(gsGraph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        View view = viewer.addDefaultView(false);
        VentanaGrafo ventanaGrafo = new VentanaGrafo(view, this.mainClass.red.getNombre(), ventanaPrincipal, this);
        this.ventanaGrafo = ventanaGrafo;
        ventanaGrafo.setVisible(true);
        tBtnAutoLayout.setSelected(true);
        tBtnAutoLayout.setText("On");
    }

    /**
     * Sets up the spinner.
     */
    private void setUpSpinner() {
        SpinnerNumberModel model = new SpinnerNumberModel(grafo.getT(), 1, grafo.getVerticesSize(), 1);
        spinnerT.setModel(model);
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinnerT.getEditor();
        editor.getTextField().setEditable(false);
    }

    /**
     * Sets up the list of paradas.
     */
    private void setUpListParadas() {
        String[] paradas = grafo.getVertices();
        String[] paradasOrdenadas = VentanaManejadorGrafo.ordenarOpciones(paradas);
        listParadas.setListData(paradasOrdenadas);
        jLabelParadas.setText("Paradas: " + this.grafo.getVerticesSize());
    }

    /**
     * Sets up the combo box for the type of search.
     */
    private void setUpComboBoxTipoBusqueda() {
        comboBoxTipoBusqueda.addItem("BFS");
        comboBoxTipoBusqueda.addItem("DFS");
        comboBoxTipoBusqueda.setSelectedIndex(0);

    }

    /**
     * Revisa la recomendación de sucursales y si la cobertura es
     * total
     */
    public void revisarRecomendacion() {
        if (this.grafo.hayCoberturaTotal()) {
            JOptionPane.showMessageDialog(this, "Hay Cobertura Total!");
            textAreaSucursalesRecomendadas.setText("Hay Cobertura Total!");
            listParadas.clearSelection();
            textParadaSeleccionada.setText("");
            this.nombreParadaSeleccionada = null;
            textAreaCobertura.setText("");
            return;
        }
        if (this.grafo.getSucursalesSize() > 0) {
            String[] sucursalesRecomendadas = this.grafo.recomendarSucursales();
            String[] sucursalesRecomendadasOrdenadas = ordenarOpciones(sucursalesRecomendadas);
            textAreaSucursalesRecomendadas.setText("");
            for (String sucursal : sucursalesRecomendadasOrdenadas) {
                textAreaSucursalesRecomendadas.append(sucursal + "\n");
            }
            textAreaSucursalesRecomendadas.setCaretPosition(0);
        } else {
            textAreaSucursalesRecomendadas.setText("");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tBtnAutoLayout = new javax.swing.JToggleButton();
        spinnerT = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        scrollPaneSeleccionarParada = new javax.swing.JScrollPane();
        listParadas = new javax.swing.JList<>();
        jLabelParadas = new javax.swing.JLabel();
        textParadaSeleccionada = new javax.swing.JTextField();
        scrollPaneCobertura = new javax.swing.JScrollPane();
        textAreaCobertura = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tBtnSucursal = new javax.swing.JToggleButton();
        scrollPaneSucursales = new javax.swing.JScrollPane();
        textAreaSucursales = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        scrollPanelSucursalesRecomendadas = new javax.swing.JScrollPane();
        textAreaSucursalesRecomendadas = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        comboBoxTipoBusqueda = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(650, 710));
        setResizable(false);
        setSize(new java.awt.Dimension(750, 710));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Grafo auto organizado:");

        tBtnAutoLayout.setText("On");
        tBtnAutoLayout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tBtnAutoLayoutActionPerformed(evt);
            }
        });

        spinnerT.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerTStateChanged(evt);
            }
        });

        jLabel2.setText("Valor de T:");

        listParadas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listParadas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listParadasValueChanged(evt);
            }
        });
        scrollPaneSeleccionarParada.setViewportView(listParadas);

        jLabelParadas.setText("Paradas:");

        textParadaSeleccionada.setEditable(false);
        textParadaSeleccionada.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        textAreaCobertura.setEditable(false);
        textAreaCobertura.setColumns(20);
        textAreaCobertura.setRows(5);
        scrollPaneCobertura.setViewportView(textAreaCobertura);

        jLabel4.setText("Cobertura:");

        jLabel5.setText("Sucursal:");

        tBtnSucursal.setText("No");
        tBtnSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tBtnSucursalActionPerformed(evt);
            }
        });

        textAreaSucursales.setColumns(20);
        textAreaSucursales.setRows(5);
        scrollPaneSucursales.setViewportView(textAreaSucursales);

        jLabel6.setText("Sucursales:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel7.setText("Formato: Parada(distancia en t)");

        textAreaSucursalesRecomendadas.setEditable(false);
        textAreaSucursalesRecomendadas.setColumns(20);
        textAreaSucursalesRecomendadas.setRows(5);
        scrollPanelSucursalesRecomendadas.setViewportView(textAreaSucursalesRecomendadas);

        jLabel8.setText("Recomendadas:");

        comboBoxTipoBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTipoBusquedaActionPerformed(evt);
            }
        });

        jLabel9.setText("Tipo Busqueda:");

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabelParadas)
                                                                .addGap(0, 326, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                                        false)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel5)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(tBtnSucursal)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        Short.MAX_VALUE)
                                                                                .addComponent(btnLimpiar))
                                                                        .addComponent(scrollPaneCobertura,
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel4)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(textParadaSeleccionada))
                                                                        .addComponent(scrollPaneSeleccionarParada,
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                290, Short.MAX_VALUE))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        80, Short.MAX_VALUE)))
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(jLabel8)
                                                        .addComponent(jLabel6)
                                                        .addComponent(scrollPaneSucursales)
                                                        .addComponent(scrollPanelSucursalesRecomendadas,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 306,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(jLabel1)
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(tBtnAutoLayout))
                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                        layout.createSequentialGroup()
                                                                                .addComponent(jLabel2)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(spinnerT,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                        layout.createSequentialGroup()
                                                                                .addComponent(jLabel9)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(comboBoxTipoBusqueda,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(18, 18, 18)))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel1)
                                                        .addComponent(tBtnAutoLayout))
                                                .addGap(17, 17, 17)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(spinnerT, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel2))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(comboBoxTipoBusqueda,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel9))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(scrollPaneSucursales,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 252,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(scrollPanelSucursalesRecomendadas,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 205,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabelParadas)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(scrollPaneSeleccionarParada,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 257,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel5)
                                                        .addComponent(tBtnSucursal)
                                                        .addComponent(btnLimpiar))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel4)
                                                        .addComponent(textParadaSeleccionada,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(scrollPaneCobertura,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 283,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addContainerGap(32, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Manejador del evento de presionar el botón limpiar, que limpia la parada
     * seleccionada, así como la cobertura de esta parada.
     * 
     * @param evt
     */
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLimpiarActionPerformed
        listParadas.clearSelection();
        textParadaSeleccionada.setText("");
        textAreaCobertura.setText("");
        tBtnSucursal.setEnabled(false);
        this.nombreParadaSeleccionada = null;
        this.updateSets();
        this.updateGsGraph();
        return;
    }// GEN-LAST:event_btnLimpiarActionPerformed

    /**
     * Manejador del evento de seleccionar una parada en el JList.
     * 
     * @param evt
     */
    private void listParadasValueChanged(javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_listParadasValueChanged
        String nombreParadaSeleccionada = listParadas.getSelectedValue();
        if ((nombreParadaSeleccionada == null) || (nombreParadaSeleccionada.equals(""))) {
            textParadaSeleccionada.setText("");
            textAreaCobertura.setText("");
            tBtnSucursal.setEnabled(false);
            this.nombreParadaSeleccionada = null;
            this.updateSets();
            this.updateGsGraph();
            return;
        }
        if (nombreParadaSeleccionada != this.nombreParadaSeleccionada) {
            this.nombreParadaSeleccionada = nombreParadaSeleccionada;
            int indexSucursal = this.grafo.buscarSucursal(nombreParadaSeleccionada);
            if (indexSucursal == -1) {
                tBtnSucursal.setSelected(false);
                tBtnSucursal.setText("No");
            } else {
                tBtnSucursal.setSelected(true);
                tBtnSucursal.setText("Si");
            }
            textParadaSeleccionada.setText(nombreParadaSeleccionada);
            tBtnSucursal.setEnabled(true);
            Vertice vertice = grafo.buscarVertice(nombreParadaSeleccionada);
            Visitado[] cobertura = vertice.getCobertura();
            textAreaCobertura.setText("");
            for (Visitado visitado : cobertura) {
                textAreaCobertura.append(visitado.toString() + "\n");
            }
            textAreaCobertura.setCaretPosition(0);
            this.updateSets();
            this.updateGsGraph();
        }
    }// GEN-LAST:event_listParadasValueChanged

    /**
     * Manejador del evento de presionar el botón de asignar/remover a la parada
     * seleccionada como sucursal.
     * 
     * @param evt
     */
    private void tBtnSucursalActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tBtnSucursalActionPerformed
        if (tBtnSucursal.isSelected()) {
            tBtnSucursal.setText("Si");
            this.grafo.agregarSucursal(textParadaSeleccionada.getText());
        } else {
            tBtnSucursal.setText("No");
            this.grafo.removerSucursal(textParadaSeleccionada.getText());
        }
        textAreaSucursales.setText("");
        String[] sucursales = grafo.getSucursales();
        String[] sucursalesOrdenadas = ordenarOpciones(sucursales);
        for (String sucursal : sucursalesOrdenadas) {
            textAreaSucursales.append(sucursal + "\n");
        }
        textAreaSucursales.setCaretPosition(0);
        this.revisarRecomendacion();
        this.updateSets();
        this.updateGsGraph();
    }// GEN-LAST:event_tBtnSucursalActionPerformed

    /**
     * Manejador del selector del valor de T.
     * 
     * @param evt
     */
    private void spinnerTStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinnerTStateChanged
        if ((int) spinnerT.getValue() != this.grafo.getT()) {
            this.grafo.setT((int) spinnerT.getValue());
            this.grafo.asignarCoberturas();
            if (this.nombreParadaSeleccionada != null) {
                Vertice vertice = grafo.buscarVertice(nombreParadaSeleccionada);
                Visitado[] cobertura = vertice.getCobertura();
                textAreaCobertura.setText("");
                for (Visitado visitado : cobertura) {
                    textAreaCobertura.append(visitado.toString() + "\n");
                }
                textAreaCobertura.setCaretPosition(0);
            }
            this.revisarRecomendacion();
            this.updateSets();
            this.updateGsGraph();
        }
    }// GEN-LAST:event_spinnerTStateChanged

    /**
     * Manejador de la selección del tipo de busqueda.
     * 
     * @param evt
     */
    private void comboBoxTipoBusquedaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_comboBoxTipoBusquedaActionPerformed
        if ((String) comboBoxTipoBusqueda.getSelectedItem() == null) {
            return;
        }
        if (comboBoxTipoBusqueda.getSelectedItem() == "BFS") {
            this.grafo.setTipoBusqueda(TipoBusqueda.BFS);
        } else {
            this.grafo.setTipoBusqueda(TipoBusqueda.DFS);
        }
        this.grafo.asignarCoberturas();
        if (this.nombreParadaSeleccionada != null) {
            Vertice vertice = grafo.buscarVertice(nombreParadaSeleccionada);
            Visitado[] cobertura = vertice.getCobertura();
            textAreaCobertura.setText("");
            for (Visitado visitado : cobertura) {
                textAreaCobertura.append(visitado.toString() + "\n");
            }
            textAreaCobertura.setCaretPosition(0);
        }
        this.revisarRecomendacion();
        this.updateSets();
        this.updateGsGraph();
    }// GEN-LAST:event_comboBoxTipoBusquedaActionPerformed

    /**
     * Si la ventana se esta cerrando, vuelve visible a la ventana principal.
     * 
     * @param evt
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowClosing
        this.ventanaGrafo.dispose();
        this.ventanaPrincipal.setVisible(true);
    }// GEN-LAST:event_formWindowClosing

    /**
     * Manejador el toogleButton que permite que el grafo se organice
     * automáticamente o no.
     * 
     * @param evt
     */
    private void tBtnAutoLayoutActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tBtnAutoLayoutActionPerformed
        if (tBtnAutoLayout.isSelected()) {
            tBtnAutoLayout.setText("On");
            this.viewer.enableAutoLayout();
        } else {
            tBtnAutoLayout.setText("Off");
            this.viewer.disableAutoLayout();
        }
    }// GEN-LAST:event_tBtnAutoLayoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaManejadorGrafo.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaManejadorGrafo.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaManejadorGrafo.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaManejadorGrafo.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaManejadorGrafo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> comboBoxTipoBusqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelParadas;
    private javax.swing.JList<String> listParadas;
    private javax.swing.JScrollPane scrollPaneCobertura;
    private javax.swing.JScrollPane scrollPaneSeleccionarParada;
    private javax.swing.JScrollPane scrollPaneSucursales;
    private javax.swing.JScrollPane scrollPanelSucursalesRecomendadas;
    private javax.swing.JSpinner spinnerT;
    private javax.swing.JToggleButton tBtnAutoLayout;
    private javax.swing.JToggleButton tBtnSucursal;
    private javax.swing.JTextArea textAreaCobertura;
    private javax.swing.JTextArea textAreaSucursales;
    private javax.swing.JTextArea textAreaSucursalesRecomendadas;
    private javax.swing.JTextField textParadaSeleccionada;
    // End of variables declaration//GEN-END:variables
}
