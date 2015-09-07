package uniandes.cupi2.numeroMvc.interfaz.color;


public class PanelColor extends javax.swing.JPanel implements java.awt.event.ActionListener , java.util.Observer {
    private static final long serialVersionUID = 164393480331818959L;

    private static final java.lang.String SELECCIONAR_COLOR = "Seleccionar Color";

    private uniandes.cupi2.numeroMvc.mundo.Numero numero;

    private javax.swing.JLabel etiquetaNumero;

    private javax.swing.JButton botonCambiar;

    public PanelColor(uniandes.cupi2.numeroMvc.mundo.Numero num) {
        numero = num;
        setLayout(new java.awt.BorderLayout());
        setSize(261, 106);
        etiquetaNumero = new javax.swing.JLabel();
        etiquetaNumero.setText("");
        etiquetaNumero.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        etiquetaNumero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaNumero.setForeground(new java.awt.Color(128 , 130 , 159));
        etiquetaNumero.setFont(new java.awt.Font("Tahoma" , java.awt.Font.BOLD , 24));
        etiquetaNumero.setOpaque(true);
        botonCambiar = new javax.swing.JButton();
        botonCambiar.setText(SELECCIONAR_COLOR);
        botonCambiar.setActionCommand(SELECCIONAR_COLOR);
        botonCambiar.addActionListener(this);
        javax.swing.JPanel panelControl = new javax.swing.JPanel();
        panelControl.setLayout(new java.awt.BorderLayout());
        panelControl.add(botonCambiar, java.awt.BorderLayout.CENTER);
        add(etiquetaNumero, java.awt.BorderLayout.CENTER);
        add(panelControl, java.awt.BorderLayout.SOUTH);
        numero.addObserver(this);
        etiquetaNumero.setBackground(new java.awt.Color(numero.darNumero()));
    }

    public void removeNotify() {
        numero.deleteObserver(this);
    }

    public void cambiarColor() {
        java.awt.Color nuevoColor = javax.swing.JColorChooser.showDialog(this, "Seleccione el color", etiquetaNumero.getBackground());
        if (nuevoColor != null)
            numero.cambiarNumero((16777216 + (nuevoColor.getRGB())));
        
    }

    public void update(java.util.Observable sender, java.lang.Object num) {
        java.lang.Integer numero = ((java.lang.Integer)(num));
        etiquetaNumero.setBackground(new java.awt.Color(numero.intValue()));
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        java.lang.String comando = e.getActionCommand();
        if (SELECCIONAR_COLOR.equals(comando))
            cambiarColor();
        
    }
}

