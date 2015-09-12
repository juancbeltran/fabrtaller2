package uniandes.cupi2.numeroMvc.interfaz.notas;


public class PanelNotas extends javax.swing.JPanel implements java.awt.event.ActionListener , java.util.Observer {
    private static final long serialVersionUID = 8169100775679594866L;

    private static final java.lang.String CAMBIAR = "Seleccionar Nota";

    private static final java.lang.String TOCAR = "Tocar";

    private uniandes.cupi2.numeroMvc.mundo.Numero numero;

    private javax.swing.JLabel etiquetaNumero;

    private javax.swing.JButton botonCambiar;

    private javax.swing.JButton botonTocar;

    public PanelNotas(uniandes.cupi2.numeroMvc.mundo.Numero num) {
        numero = num;
        setLayout(new java.awt.BorderLayout());
        setSize(261, 106);
        etiquetaNumero = new javax.swing.JLabel();
        etiquetaNumero.setText("###");
        etiquetaNumero.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        etiquetaNumero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaNumero.setForeground(new java.awt.Color(128 , 130 , 159));
        etiquetaNumero.setFont(new java.awt.Font("Tahoma" , java.awt.Font.BOLD , 12));
        botonCambiar = new javax.swing.JButton();
        botonCambiar.setText(CAMBIAR);
        botonCambiar.setActionCommand(CAMBIAR);
        botonCambiar.addActionListener(this);
        botonTocar = new javax.swing.JButton();
        botonTocar.setText(TOCAR);
        botonTocar.setActionCommand(TOCAR);
        botonTocar.addActionListener(this);
        javax.swing.JPanel panelControl = new javax.swing.JPanel();
        panelControl.setLayout(new java.awt.BorderLayout());
        panelControl.add(botonCambiar, java.awt.BorderLayout.CENTER);
        panelControl.add(botonTocar, java.awt.BorderLayout.EAST);
        add(etiquetaNumero, java.awt.BorderLayout.CENTER);
        add(panelControl, java.awt.BorderLayout.SOUTH);
        numero.addObserver(this);
        etiquetaNumero.setText((((("Frecuencia Musical: " + (darNota(numero.darNumero()))) + " (") + (numero.darNumero())) + ")"));
    }

    public void removeNotify() {
        numero.deleteObserver(this);
    }

    public void cambiarNota() {
        java.lang.Object[] notas = new java.lang.Object[]{ "Do" , "Re" , "Mi" , "Fa" , "Sol" , "La" , "Si" };
        java.lang.String nota = ((java.lang.String)(javax.swing.JOptionPane.showInputDialog(this, "Nota", "Nota", javax.swing.JOptionPane.QUESTION_MESSAGE, null, notas, "Do")));
        if (nota != null) {
            int numeroNota = 0;
            if ("Do".equals(nota))
                numeroNota = 50;
            else if ("Re".equals(nota))
                numeroNota = 52;
            else if ("Mi".equals(nota))
                numeroNota = 54;
            else if ("Fa".equals(nota))
                numeroNota = 55;
            else if ("Sol".equals(nota))
                numeroNota = 57;
            else if ("La".equals(nota))
                numeroNota = 59;
            else if ("Si".equals(nota))
                numeroNota = 61;
            
            numero.cambiarNumero(numeroNota);
        } 
    }

    public void tocarNota() {
        uniandes.cupi2.numeroMvc.interfaz.notas.ReproductorNota.tocarNota(darNota(numero.darNumero()), 0);
    }

    public int darNota(int numero) {
        int nota = (numero % 100) + 10;
        return nota;
    }

    public void update(java.util.Observable sender, java.lang.Object num) {
        java.lang.Integer numero = ((java.lang.Integer)(num));
        etiquetaNumero.setText((((("Frecuencia Musical: " + (darNota(numero.intValue()))) + " (") + (numero.intValue())) + ")"));
        tocarNota();
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        java.lang.String comando = e.getActionCommand();
        if (CAMBIAR.equals(comando))
            cambiarNota();
        
    }
}

