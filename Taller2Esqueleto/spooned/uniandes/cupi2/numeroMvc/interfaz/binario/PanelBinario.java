package uniandes.cupi2.numeroMvc.interfaz.binario;


public class PanelBinario extends javax.swing.JPanel implements java.awt.event.ActionListener , java.util.Observer {
    private static final long serialVersionUID = -2486744718378309246L;

    private static final java.lang.String CAMBIAR = "Cambiar";

    private uniandes.cupi2.numeroMvc.mundo.Numero numero;

    private javax.swing.JLabel etiquetaNumero;

    private javax.swing.JButton botonCambiar;

    private javax.swing.JTextField textoNumeroBinario;

    public PanelBinario(uniandes.cupi2.numeroMvc.mundo.Numero num) {
        numero = num;
        setLayout(new java.awt.BorderLayout());
        setSize(261, 106);
        etiquetaNumero = new javax.swing.JLabel();
        etiquetaNumero.setText("###");
        etiquetaNumero.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        etiquetaNumero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaNumero.setForeground(new java.awt.Color(0 , 94 , 169));
        etiquetaNumero.setFont(new java.awt.Font("Courier New" , java.awt.Font.PLAIN , 18));
        botonCambiar = new javax.swing.JButton();
        botonCambiar.setText(CAMBIAR);
        botonCambiar.setActionCommand(CAMBIAR);
        botonCambiar.addActionListener(this);
        textoNumeroBinario = new javax.swing.JTextField();
        javax.swing.JPanel panelControl = new javax.swing.JPanel();
        panelControl.setLayout(new java.awt.BorderLayout());
        panelControl.add(botonCambiar, java.awt.BorderLayout.EAST);
        panelControl.add(textoNumeroBinario, java.awt.BorderLayout.CENTER);
        add(etiquetaNumero, java.awt.BorderLayout.CENTER);
        add(panelControl, java.awt.BorderLayout.SOUTH);
        numero.addObserver(this);
        etiquetaNumero.setText(java.lang.Integer.toBinaryString(numero.darNumero()));
    }

    public void removeNotify() {
        numero.deleteObserver(this);
    }

    public void modificarNumero() {
        try {
            int nuevoNumero = java.lang.Integer.parseInt(textoNumeroBinario.getText(), 2);
            numero.cambiarNumero(nuevoNumero);
        } catch (java.lang.NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "El n�mero en binario especificado es inv�lido", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public void update(java.util.Observable sender, java.lang.Object num) {
        java.lang.Integer numero = ((java.lang.Integer)(num));
        textoNumeroBinario.setText("");
        etiquetaNumero.setText(java.lang.Integer.toBinaryString(numero.intValue()));
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        java.lang.String comando = e.getActionCommand();
        if (CAMBIAR.equals(comando))
            modificarNumero();
        
    }
}

