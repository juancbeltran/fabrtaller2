package uniandes.cupi2.numeroMvc.interfaz;


public class PanelExtension extends javax.swing.JPanel implements java.awt.event.ActionListener {
    private static final long serialVersionUID = -2712979860721352600L;

    private static final java.lang.String OPCION_1 = "OPCION_1";

    private static final java.lang.String OPCION_2 = "OPCION_2";

    private uniandes.cupi2.numeroMvc.interfaz.InterfazNumeroMvc principal;

    private javax.swing.JButton btnOpcion1;

    private javax.swing.JButton btnOpcion2;

    public PanelExtension(uniandes.cupi2.numeroMvc.interfaz.InterfazNumeroMvc ip) {
        principal = ip;
        setBorder(new javax.swing.border.TitledBorder("Opciones"));
        setLayout(new java.awt.GridLayout(1 , 2));
        btnOpcion1 = new javax.swing.JButton("Opci�n 1");
        btnOpcion1.setActionCommand(OPCION_1);
        btnOpcion1.addActionListener(this);
        add(btnOpcion1);
        btnOpcion2 = new javax.swing.JButton("Opci�n 2");
        btnOpcion2.setActionCommand(OPCION_2);
        btnOpcion2.addActionListener(this);
        add(btnOpcion2);
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (OPCION_1.equals(e.getActionCommand())) {
            principal.reqFuncOpcion1();
        } else if (OPCION_2.equals(e.getActionCommand())) {
            principal.reqFuncOpcion2();
        } 
    }
}

