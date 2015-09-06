package uniandes.cupi2.numeroMvc.interfaz;


/** 
 * Esta es la ventana principal de la aplicaci�n.
 */
public class InterfazNumeroMvc extends javax.swing.JFrame implements java.awt.event.ActionListener {
    /** 
     * Constante de serializaci�n.
     */
    private static final long serialVersionUID = 422461325927300786L;
    
    /** 
     * Constante para la acci�n del bot�n de nueva vista.
     */
    private static final java.lang.String NUEVO = "NUEVO";
    
    /** 
     * Constante para la acci�n del bot�n de salir.
     */
    private static final java.lang.String SALIR = "SALIR";
    
    /** 
     * Constante para la acci�n del bot�n de nueva ventana de formato decimal.
     */
    private static final java.lang.String VC_NUMERO = "Numero";
    
    /** 
     * Constante para la acci�n del bot�n de nueva ventana de formato romano.
     */
    private static final java.lang.String VC_ROMANO = "Romano";
    
    /** 
     * Constante para la acci�n del bot�n de nueva ventana de formato color.
     */
    private static final java.lang.String VC_COLOR = "Color";
    
    /** 
     * Constante para la acci�n del bot�n de nueva ventana de formato musical.
     */
    private static final java.lang.String VC_NOTA = "Nota Musical";
    
    /** 
     * Constante para la acci�n del bot�n de nueva ventana de formato binario.
     */
    private static final java.lang.String VC_BINARIO = "Binario";
    
    /** 
     * Modelo: N�mero a visualizar - controlar.
     */
    private uniandes.cupi2.numeroMvc.mundo.Numero numero;
    
    /** 
     * Desktop donde se muestran las ventanas adicionales.
     */
    private javax.swing.JDesktopPane desktop;
    
    /** 
     * Panel con las extensiones.
     */
    private uniandes.cupi2.numeroMvc.interfaz.PanelExtension panelExtension;
    
    /** 
     * Barra de men�.
     */
    private javax.swing.JMenuBar barraMenu;
    
    /** 
     * El men� VC.
     */
    private javax.swing.JMenu menu;
    
    /** 
     * �tem nuevo.
     */
    private javax.swing.JMenuItem itemNuevo;
    
    /** 
     * �tem salir.
     */
    private javax.swing.JMenuItem itemSalir;
    
    /** 
     * Constructor de la ventana principal de la interfaz.
     */
    public InterfazNumeroMvc() {
        numero = new uniandes.cupi2.numeroMvc.mundo.Numero();
        setTitle("Numero - MVC");
        getContentPane().setLayout(new java.awt.BorderLayout());
        setSize(530 ,530);
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        barraMenu = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu("VC");
        menu.setMnemonic(java.awt.event.KeyEvent.VK_D);
        barraMenu.add(menu);
        itemNuevo = new javax.swing.JMenuItem("Nuevo VC");
        itemNuevo.setMnemonic(java.awt.event.KeyEvent.VK_N);
        itemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N ,java.awt.event.ActionEvent.ALT_MASK));
        itemNuevo.setActionCommand(NUEVO);
        itemNuevo.addActionListener(this);
        menu.add(itemNuevo);
        itemSalir = new javax.swing.JMenuItem("Salir");
        itemSalir.setMnemonic(java.awt.event.KeyEvent.VK_S);
        itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S ,java.awt.event.ActionEvent.ALT_MASK));
        itemSalir.setActionCommand(SALIR);
        itemSalir.addActionListener(this);
        menu.add(itemSalir);
        setJMenuBar(barraMenu);
        panelExtension = new uniandes.cupi2.numeroMvc.interfaz.PanelExtension(this);
        getContentPane().add(panelExtension ,java.awt.BorderLayout.SOUTH);
        desktop = new javax.swing.JDesktopPane();
        getContentPane().add(desktop ,java.awt.BorderLayout.CENTER);
        desktop.setBackground(java.awt.Color.LIGHT_GRAY);
    }
    
    /** 
     * Crea una nueva ventana (vista-controlador) del n�mero (modelo).
     */
    public void crearNuevaVentana() {
        javax.swing.JInternalFrame ventana = null;
        java.lang.String vc = ((java.lang.String)(javax.swing.JOptionPane.showInputDialog(this ,"Ventana a crear" ,"Ventana" ,javax.swing.JOptionPane.QUESTION_MESSAGE ,null ,new java.lang.String[]{ VC_NUMERO , VC_ROMANO , VC_COLOR , VC_NOTA , VC_BINARIO } ,null)));
        if (vc != null) {
            if (VC_NUMERO.equals(vc))
                ventana = new uniandes.cupi2.numeroMvc.interfaz.numero.VentanaNumero(numero);
            else if (VC_ROMANO.equals(vc))
                ventana = new uniandes.cupi2.numeroMvc.interfaz.romano.VentanaRomano(numero);
            else if (VC_NOTA.equals(vc))
                ventana = new uniandes.cupi2.numeroMvc.interfaz.notas.VentanaNotas(numero);
            else if (VC_BINARIO.equals(vc))
                ventana = new uniandes.cupi2.numeroMvc.interfaz.binario.VentanaBinario(numero);
            else
                ventana = new uniandes.cupi2.numeroMvc.interfaz.color.VentanaColor(numero);
            
            desktop.add(ventana);
            ventana.setVisible(true);
        } 
    }
    
    /** 
     * Manejo de los eventos de los botones.
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (NUEVO.equals(e.getActionCommand()))
            crearNuevaVentana();
        else
            java.lang.System.exit(0);
        
    }
    
    /** 
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1() {
        java.lang.String resultado = numero.metodo1();
        javax.swing.JOptionPane.showMessageDialog(this ,resultado ,"Respuesta" ,javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
    
    /** 
     * M�todo para la extensi�n 2
     */
    public void reqFuncOpcion2() {
        java.lang.String resultado = numero.metodo2();
        javax.swing.JOptionPane.showMessageDialog(this ,resultado ,"Respuesta" ,javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
    
    /** 
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
     * @param args Argumentos para la ejecuci�n de la aplicaci�n. En este caso no se requieren
     */
    public static void main(java.lang.String[] args) {
        uniandes.cupi2.numeroMvc.interfaz.InterfazNumeroMvc interfaz = new uniandes.cupi2.numeroMvc.interfaz.InterfazNumeroMvc();
        interfaz.setVisible(true);
    }
    
}

