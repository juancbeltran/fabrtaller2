package uniandes.cupi2.numeroMvc.interfaz.notas;


/** 
 * Clase que permite tocar una nota.
 */
public class ReproductorNota {
    /** 
     * Guarda la informaci�n de inicializaci�n del reproductor.
     */
    private static boolean inicializado;
    
    /** 
     * Es el sintetizador con el que se reproducen las notas.
     */
    private static javax.sound.midi.Synthesizer synth;
    
    /** 
     * Toca la nota indicada, las notas van de 0 a 120 siendo 60 el Do central, 61 Do#, etc
     * @param nota - valor num�rico de la nota a reproducir.
     * @param canal - el instrumento con el que se quiere tocar la nota. Van de 0 a 15. 0, es el piano.
     */
    public static void tocarNota(int nota, int canal) {
        if (!(uniandes.cupi2.numeroMvc.interfaz.notas.ReproductorNota.inicializado)) {
            uniandes.cupi2.numeroMvc.interfaz.notas.ReproductorNota.inicializado = true;
            try {
                uniandes.cupi2.numeroMvc.interfaz.notas.ReproductorNota.synth = javax.sound.midi.MidiSystem.getSynthesizer();
                uniandes.cupi2.numeroMvc.interfaz.notas.ReproductorNota.synth.open();
            } catch (javax.sound.midi.MidiUnavailableException e) {
                e.printStackTrace();
            }
        } 
        final int notaT = nota;
        final int canalT = canal;
        java.lang.Thread t = new java.lang.Thread(new java.lang.Runnable() {
            /** 
             * Es el canal que se usa para reproducir las notas.
             */
            private javax.sound.midi.MidiChannel channel;
            
            public void run() {
                synchronized(uniandes.cupi2.numeroMvc.interfaz.notas.ReproductorNota.synth) {
                    channel = uniandes.cupi2.numeroMvc.interfaz.notas.ReproductorNota.synth.getChannels()[canalT];
                    channel.noteOn(notaT ,100);
                    try {
                        java.lang.Thread.yield();
                        java.lang.Thread.sleep(300);
                    } catch (java.lang.InterruptedException e) {
                        e.printStackTrace();
                    }
                    channel.noteOff(notaT ,100);
                }
            }
            
        });
        t.run();
    }
    
}

