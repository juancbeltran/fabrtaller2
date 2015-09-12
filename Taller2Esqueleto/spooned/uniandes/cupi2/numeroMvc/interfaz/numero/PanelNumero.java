package uniandes.cupi2.numeroMvc.interfaz.numero;


public class PanelNumero extends javax.swing.JPanel implements java.awt.event.ActionListener , java.util.Observer {
    private static final long serialVersionUID = 8638090313032173271L;

    private static final java.lang.String CAMBIAR = "Cambiar";

    private uniandes.cupi2.numeroMvc.mundo.Numero numero;

    private javax.swing.JLabel etiquetaNumero;

    private javax.swing.JButton botonCambiar;

    private javax.swing.JTextField textoNumero;

    public PanelNumero(uniandes.cupi2.numeroMvc.mundo.Numero num) {
        numero = num;
        setLayout(new java.awt.BorderLayout());
        setSize(261, 106);
        etiquetaNumero = new javax.swing.JLabel();
        etiquetaNumero.setText("###");
        etiquetaNumero.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        etiquetaNumero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaNumero.setForeground(new java.awt.Color(128 , 130 , 159));
        etiquetaNumero.setFont(new java.awt.Font("Tahoma" , java.awt.Font.BOLD , 24));
        botonCambiar = new javax.swing.JButton();
        botonCambiar.setText(CAMBIAR);
        botonCambiar.setActionCommand(CAMBIAR);
        botonCambiar.addActionListener(this);
        textoNumero = new javax.swing.JTextField();
        javax.swing.JPanel panelControl = new javax.swing.JPanel();
        panelControl.setLayout(new java.awt.BorderLayout());
        panelControl.add(botonCambiar, java.awt.BorderLayout.EAST);
        panelControl.add(textoNumero, java.awt.BorderLayout.CENTER);
        add(etiquetaNumero, java.awt.BorderLayout.CENTER);
        add(panelControl, java.awt.BorderLayout.SOUTH);
        numero.addObserver(this);
        etiquetaNumero.setText(java.lang.Integer.toString(numero.darNumero()));
    }

    public void removeNotify() {
        numero.deleteObserver(this);
    }

    public void modificarNumero() {
        try {
            int nuevoNumero = java.lang.Integer.parseInt(textoNumero.getText());
            numero.cambiarNumero(nuevoNumero);
        } catch (java.lang.NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "El n�mero especificado es inv�lido", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public void update(java.util.Observable sender, java.lang.Object num) {
        java.lang.Integer numero = ((java.lang.Integer)(num));
        textoNumero.setText("");
        etiquetaNumero.setText(numero.toString());
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        java.lang.String comando = e.getActionCommand();
        if (CAMBIAR.equals(comando))
            modificarNumero();
        
    }
}

