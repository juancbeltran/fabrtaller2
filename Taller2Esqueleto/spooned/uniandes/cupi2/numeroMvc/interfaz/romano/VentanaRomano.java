package uniandes.cupi2.numeroMvc.interfaz.romano;


public class VentanaRomano extends javax.swing.JInternalFrame {
    private static final long serialVersionUID = 3325218644134834205L;

    private uniandes.cupi2.numeroMvc.interfaz.romano.PanelRomano panelRomano;

    public VentanaRomano(uniandes.cupi2.numeroMvc.mundo.Numero numero) {
        setSize(276, 150);
        setMaximizable(true);
        setClosable(true);
        setResizable(true);
        setPreferredSize(new java.awt.Dimension(276 , 150));
        setTitle("Vista-Controlador Romano");
        panelRomano = new uniandes.cupi2.numeroMvc.interfaz.romano.PanelRomano(numero);
        add(panelRomano, java.awt.BorderLayout.CENTER);
    }
}

