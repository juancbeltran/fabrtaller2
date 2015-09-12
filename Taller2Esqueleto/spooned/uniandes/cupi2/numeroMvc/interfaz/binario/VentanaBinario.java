package uniandes.cupi2.numeroMvc.interfaz.binario;


public class VentanaBinario extends javax.swing.JInternalFrame {
    private static final long serialVersionUID = -2975409965024688727L;

    private uniandes.cupi2.numeroMvc.interfaz.binario.PanelBinario panelBinario;

    public VentanaBinario(uniandes.cupi2.numeroMvc.mundo.Numero numero) {
        setSize(276, 150);
        setMaximizable(true);
        setClosable(true);
        setResizable(true);
        setPreferredSize(new java.awt.Dimension(276 , 150));
        setTitle("Vista-Controlador Binario");
        panelBinario = new uniandes.cupi2.numeroMvc.interfaz.binario.PanelBinario(numero);
        add(panelBinario, java.awt.BorderLayout.CENTER);
    }
}

