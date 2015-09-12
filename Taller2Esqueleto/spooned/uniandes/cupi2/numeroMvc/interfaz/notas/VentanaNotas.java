package uniandes.cupi2.numeroMvc.interfaz.notas;


public class VentanaNotas extends javax.swing.JInternalFrame {
    private static final long serialVersionUID = 6571393258666895672L;

    private uniandes.cupi2.numeroMvc.interfaz.notas.PanelNotas panelNotas;

    public VentanaNotas(uniandes.cupi2.numeroMvc.mundo.Numero numero) {
        setSize(276, 150);
        setMaximizable(true);
        setClosable(true);
        setResizable(true);
        setPreferredSize(new java.awt.Dimension(276 , 150));
        setTitle("Vista-Controlador Notas Musicales");
        panelNotas = new uniandes.cupi2.numeroMvc.interfaz.notas.PanelNotas(numero);
        add(panelNotas, java.awt.BorderLayout.CENTER);
    }
}

