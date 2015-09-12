package uniandes.cupi2.numeroMvc.interfaz.notas;


public class ReproductorNota {
    private static boolean inicializado;

    private static javax.sound.midi.Synthesizer synth;

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
            private javax.sound.midi.MidiChannel channel;

            public void run() {
                synchronized(uniandes.cupi2.numeroMvc.interfaz.notas.ReproductorNota.synth) {
                    channel = uniandes.cupi2.numeroMvc.interfaz.notas.ReproductorNota.synth.getChannels()[canalT];
                    channel.noteOn(notaT, 100);
                    try {
                        java.lang.Thread.yield();
                        java.lang.Thread.sleep(300);
                    } catch (java.lang.InterruptedException e) {
                        e.printStackTrace();
                    }
                    channel.noteOff(notaT, 100);
                }
            }
        });
        t.run();
    }
}

