/*
 * EspinozaCastroJavierFernando
21630220
Programa que tiene una interfaz en el que se puede ingresar un AFD y comprobar las cadenas validas para ese automata
 */
package er_comprobacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Javier
 */
public class Interfaz extends javax.swing.JFrame {

    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
    }
    static String estado = "";
    static String[] abc;
    static int numSim = 0;
    static List<Nodo> ListaNodos = new ArrayList<>();
    static List<Nodo> NodosCierra = new ArrayList<>();
    static int numEstados = 0;
    static String cadena = "";
    static String dec = "";
    static boolean op = true;

    public static void IngresaAlfabeto() {

        numSim = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el numero de simbolos de tu alfabeto"));

        abc = new String[numSim];

        for (int i = 0; i < numSim; i++) {
            System.out.println();//ingresa los simbolos
            abc[i] = JOptionPane.showInputDialog("Ingresa el dato " + (i + 1) + " del alfabeto");//guarda en arreglo ya definido

        }
    }

    public static void CrearNodos() {
        numEstados = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el numero de estados"));
        IngresaAlfabeto();

        //Con este for creo los nodos con los nombres de las letras en orden alfabetico
        for (int x = 0; x < numEstados; x++) {
            char letra = (char) ('A' + x);
            estado = String.valueOf(letra);
            Nodo nodo = new Nodo(estado);
            ListaNodos.add(nodo);

        }

    }

    public static void ConectarNodos() {
        String Cierra = "";
        boolean check = false;

        for (Nodo nodo : ListaNodos) { //este for recorre toda la lista de los estados de la A hasta el que sea el utimo
            while (!check) {//este whle sirve para que el usuario solo pueda ingresar SI o NO
                Cierra = JOptionPane.showInputDialog("¿El estado " + nodo.getEstado() + " tiene cerradura de Clean? SI/NO");//esto es para saber si tiene cerradura el estado en el que va recorriendo el for
                if (Cierra.equalsIgnoreCase("SI") || Cierra.equalsIgnoreCase("NO")) {
                    check = true;
                } else {
                    JOptionPane.showMessageDialog(null, "DEBES ELEGIR SI/NO!");
                }
            }

            for (int i = 0; i < numSim; i++) {

                String estadoDestino = JOptionPane.showInputDialog("Ingresa el estado al que va " + nodo.getEstado() + " con el simbolo: " + abc[i]);  //aqui se recibe a que nodo se conecta el nodo 

                if (Cierra.equalsIgnoreCase("SI")) {//verificamos,  si se ingreso que tiene cerradura el estado, se agrega a la lista de nodos con cerradura 
                    NodosCierra.add(nodo);
                }

                Nodo nodoDestino = null;//Solo se utiliza nododestino como una variable temporal
                for (Nodo origen : ListaNodos) {//se recorre la lista de nodos para buscar el estado a donde se hara la conexion
                    if (estadoDestino.equalsIgnoreCase(origen.getEstado())) {
                        nodoDestino = origen;
                        /*se guarda en "nodoDestino" el nodo guardado en "origen" cuando se encuentre en la lista
                        de nodos principal*/
                        break;//se rompe el recorrido de la lista principal, pues ya se encontro el nodo que se queria
                    }
                }
                if (nodoDestino != null) {//si se encontro el nodo que se queria se le agrega en el hashMap del nodo origen con el simbolo correspondiente
                    nodo.agregarConexion(abc[i], nodoDestino);
                } else {//si no, verifica si es un asterisco que nos indica que no tiene conexiones con simbolo correspondiente
                    if (estadoDestino.equalsIgnoreCase("*")) {
                        JOptionPane.showMessageDialog(null, "Agregaste conexion en vacio");
                    } else {
                        JOptionPane.showMessageDialog(null, "El estado de destino ingresado no existe");
                    }
                }
            }
            check = false;
        }
    }

    public static boolean ValidarCadena() {

        cadena = JOptionPane.showInputDialog("Ingresa la cadena a validar");

        ArrayList<String> caracteres = new ArrayList<>(); //se crea una lista para guardar el numero de caracteres que tenga la cadena

        //En este for se separan y se guarda caracter por caracter
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            caracteres.add(String.valueOf(caracter));
        }

        Nodo Temporal = ListaNodos.get(0);//Usado guardar en temporal el nodo A ( para posicionarnos en el Nodo A)
        //for que recorre toda la lista de caracteres
        for (String letra : caracteres) {

            if (!Temporal.getConexiones().containsKey(letra)) {
                return false;
            } else {
                Temporal = Temporal.getConexiones().get(letra);
            }
        }
        return NodosCierra.contains(Temporal);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        IngresarProcesos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Texto = new javax.swing.JTextArea();
        IngresaCadena = new javax.swing.JToggleButton();
        FIn = new javax.swing.JButton();
        Reset = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(883, 643));

        IngresarProcesos.setText("Ingresar procesos");
        IngresarProcesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IngresarProcesosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("BIENVENIDO");

        jLabel2.setText("En este programa se ingresa el AFD de tu expresion regular y despues puedes ingresar cadenas de texto para validarlas");

        Texto.setColumns(20);
        Texto.setRows(5);
        jScrollPane1.setViewportView(Texto);

        IngresaCadena.setText("Ingresar cadena");
        IngresaCadena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IngresaCadenaActionPerformed(evt);
            }
        });

        FIn.setText("Terminar");
        FIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FInActionPerformed(evt);
            }
        });

        Reset.setText("Reset");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });

        jLabel3.setText("El numero de estados ingresado se enumeran desde la A a la Z dependiendo el numero de destados.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(288, 288, 288))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(9, 9, 9))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(IngresarProcesos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(IngresaCadena, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FIn)
                        .addGap(32, 32, 32)
                        .addComponent(Reset)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IngresarProcesos)
                    .addComponent(IngresaCadena)
                    .addComponent(Reset)
                    .addComponent(FIn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IngresarProcesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IngresarProcesosActionPerformed
        Texto.setText("");
        CrearNodos();
        ConectarNodos();
        // Imprimir información de cada nodo
        for (Nodo nodo : ListaNodos) {
            Texto.setText(Texto.getText() + "Nodo: " + nodo.getEstado());
            Texto.setText(Texto.getText() + ("\n"));
            Texto.setText(Texto.getText() + ("Conxiones"));
            Texto.setText(Texto.getText() + ("\n"));

            // Iterar a través de las conexiones y mostrarlas
            for (Map.Entry<String, Nodo> conexion : nodo.getConexiones().entrySet()) {
                Texto.setText(Texto.getText() + "  Símbolo: " + conexion.getKey() + ", Destino: " + conexion.getValue().getEstado());
                Texto.setText(Texto.getText() + "\n");
            }

            Texto.setText(Texto.getText() + "\n"); // Separador entre nodos
        }
    }//GEN-LAST:event_IngresarProcesosActionPerformed

    private void IngresaCadenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IngresaCadenaActionPerformed
        if (ValidarCadena()) {
            JOptionPane.showMessageDialog(null, "MATCHED");
        } else {
            JOptionPane.showMessageDialog(null, "NO MATCHED");
        }
    }//GEN-LAST:event_IngresaCadenaActionPerformed

    private void FInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FInActionPerformed
        System.exit(0);
    }//GEN-LAST:event_FInActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        numEstados = 0;
        String cadena = "";
        String dec = "";
        boolean op = true;
        estado = "";
        numSim = 0;
        Texto.setText("");

    }//GEN-LAST:event_ResetActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton FIn;
    private javax.swing.JToggleButton IngresaCadena;
    private javax.swing.JButton IngresarProcesos;
    private javax.swing.JButton Reset;
    private javax.swing.JTextArea Texto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
