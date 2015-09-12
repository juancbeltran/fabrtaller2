package uniandes.cupi2.numeroMvc.interfaz.color;


public class VentanaColor extends javax.swing.JInternalFrame {
    private static final long serialVersionUID = 5107092146528207517L;

    private uniandes.cupi2.numeroMvc.interfaz.color.PanelColor panelColor;

    public VentanaColor(uniandes.cupi2.numeroMvc.mundo.Numero numero) {
        setSize(276, 150);
        setMaximizable(true);
        setClosable(true);
        setResizable(true);
        setPreferredSize(new java.awt.Dimension(276 , 150));
        setTitle("Vista-Controlador Color");
        panelColor = new uniandes.cupi2.numeroMvc.interfaz.color.PanelColor(numero);
        add(panelColor, java.awt.BorderLayout.CENTER);
    }
}

