package uniandes.cupi2.numeroMvc.interfaz;


public class InterfazNumeroMvc extends javax.swing.JFrame implements java.awt.event.ActionListener {
    private static final long serialVersionUID = 422461325927300786L;

    private static final java.lang.String NUEVO = "NUEVO";

    private static final java.lang.String SALIR = "SALIR";

    private static final java.lang.String VC_NUMERO = "Numero";

    private static final java.lang.String VC_ROMANO = "Romano";

    private static final java.lang.String VC_COLOR = "Color";

    private static final java.lang.String VC_NOTA = "Nota Musical";

    private static final java.lang.String VC_BINARIO = "Binario";

    private uniandes.cupi2.numeroMvc.mundo.Numero numero;

    private javax.swing.JDesktopPane desktop;

    private uniandes.cupi2.numeroMvc.interfaz.PanelExtension panelExtension;

    private javax.swing.JMenuBar barraMenu;

    private javax.swing.JMenu menu;

    private javax.swing.JMenuItem itemNuevo;

    private javax.swing.JMenuItem itemSalir;

    public InterfazNumeroMvc() {
        numero = new uniandes.cupi2.numeroMvc.mundo.Numero();
        setTitle("Numero - MVC");
        getContentPane().setLayout(new java.awt.BorderLayout());
        setSize(530, 530);
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        barraMenu = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu("VC");
        menu.setMnemonic(java.awt.event.KeyEvent.VK_D);
        barraMenu.add(menu);
        itemNuevo = new javax.swing.JMenuItem("Nuevo VC");
        itemNuevo.setMnemonic(java.awt.event.KeyEvent.VK_N);
        itemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.ActionEvent.ALT_MASK));
        itemNuevo.setActionCommand(NUEVO);
        itemNuevo.addActionListener(this);
        menu.add(itemNuevo);
        itemSalir = new javax.swing.JMenuItem("Salir");
        itemSalir.setMnemonic(java.awt.event.KeyEvent.VK_S);
        itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.ActionEvent.ALT_MASK));
        itemSalir.setActionCommand(SALIR);
        itemSalir.addActionListener(this);
        menu.add(itemSalir);
        setJMenuBar(barraMenu);
        panelExtension = new uniandes.cupi2.numeroMvc.interfaz.PanelExtension(this);
        getContentPane().add(panelExtension, java.awt.BorderLayout.SOUTH);
        desktop = new javax.swing.JDesktopPane();
        getContentPane().add(desktop, java.awt.BorderLayout.CENTER);
        desktop.setBackground(java.awt.Color.LIGHT_GRAY);
    }

    public void crearNuevaVentana() {
        javax.swing.JInternalFrame ventana = null;
        java.lang.String vc = ((java.lang.String)(javax.swing.JOptionPane.showInputDialog(this, "Ventana a crear", "Ventana", javax.swing.JOptionPane.QUESTION_MESSAGE, null, new java.lang.String[]{ VC_NUMERO , VC_ROMANO , VC_COLOR , VC_NOTA , VC_BINARIO }, null)));
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

    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (NUEVO.equals(e.getActionCommand()))
            crearNuevaVentana();
        else
            java.lang.System.exit(0);
        
    }

    public void reqFuncOpcion1() {
        java.lang.String resultado = numero.metodo1();
        javax.swing.JOptionPane.showMessageDialog(this, resultado, "Respuesta", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    public void reqFuncOpcion2() {
        java.lang.String resultado = numero.metodo2();
        javax.swing.JOptionPane.showMessageDialog(this, resultado, "Respuesta", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(java.lang.String[] args) {
        uniandes.cupi2.numeroMvc.interfaz.InterfazNumeroMvc interfaz = new uniandes.cupi2.numeroMvc.interfaz.InterfazNumeroMvc();
        interfaz.setVisible(true);
    }
}

