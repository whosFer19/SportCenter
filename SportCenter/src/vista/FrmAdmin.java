package vista;
import modelo.Cancha;
import servicio.CanchaException;
import servicio.CanchaService;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
public class FrmAdmin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmAdmin.class.getName());

    private final CanchaService canchaService = new CanchaService();
    private DefaultTableModel modeloTabla;
    private int idSeleccionado = 0; // 0 = nueva cancha, >0 = editando una existente
    public FrmAdmin() {
        initComponents();
        setLocationRelativeTo(null);
        configurarCanchas();
    }
    private void configurarCanchas() {
        cmbDeporte.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{"Fútbol", "Vóley", "Básquet", "Tenis", "Pádel"}));
        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{Cancha.DISPONIBLE, Cancha.MANTENIMIENTO}));
 
        modeloTabla = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Deporte", "Precio/hora", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false; // la tabla es solo de lectura
            }
        };
        tblCanchas.setModel(modeloTabla);
        tblCanchas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
 
        // Al hacer clic en una fila, se pasan sus datos a los campos
        tblCanchas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblCanchas.getSelectedRow() >= 0) {
                cargarCamposDesdeTabla(tblCanchas.getSelectedRow());
            }
        });
 
        cargarTabla();
    }
 
    /** Consulta las canchas en la BD y las muestra en la tabla. */
    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        try {
            for (Cancha c : canchaService.listar()) {
                modeloTabla.addRow(new Object[]{
                    c.getIdCancha(), c.getNombre(), c.getDeporte(),
                    c.getPrecioHora(), c.getEstado()
                });
            }
        } catch (CanchaException ex) {
            mostrarError(ex.getMessage());
        }
    }
 
    /** Copia los datos de la fila seleccionada a los campos del formulario. */
    private void cargarCamposDesdeTabla(int fila) {
        idSeleccionado = (int) modeloTabla.getValueAt(fila, 0);
        txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
        cmbDeporte.setSelectedItem(modeloTabla.getValueAt(fila, 2).toString());
        txtPrecio.setText(modeloTabla.getValueAt(fila, 3).toString());
        cmbEstado.setSelectedItem(modeloTabla.getValueAt(fila, 4).toString());
        btnGuardar.setText("Actualizar");
    }
 
    /** Deja el formulario listo para registrar una cancha nueva. */
    private void limpiarCampos() {
        idSeleccionado = 0;
        txtNombre.setText("");
        txtPrecio.setText("");
        cmbDeporte.setSelectedIndex(0);
        cmbEstado.setSelectedItem(Cancha.DISPONIBLE);
        tblCanchas.clearSelection();
        btnGuardar.setText("Guardar");
        txtNombre.requestFocus();
    }
 
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        cmbDeporte = new javax.swing.JComboBox<>();
        cmbEstado = new javax.swing.JComboBox<>();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCanchas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Administrador");

        jLabel2.setText("Nombre");

        jLabel3.setText("Deporte");

        jLabel4.setText("Precio por hora (S/)");

        jLabel5.setText("Estado");

        cmbDeporte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----Seleccionar----", "Futbol", "Volley" }));

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----Seleccionar----", "DISPONIBLE", "MANTENIMIENTO" }));

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(this::btnNuevoActionPerformed);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        tblCanchas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre ", "Deporte ", "Precio/hora ", "Estado"
            }
        ));
        jScrollPane1.setViewportView(tblCanchas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombre)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addGap(69, 69, 69)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnEliminar))
                    .addComponent(cmbDeporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDeporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // 1. Convertir el precio (puede venir con letras o vacío)
        BigDecimal precio;
        try {
            precio = new BigDecimal(txtPrecio.getText().trim());
        } catch (NumberFormatException ex) {
            mostrarError("Ingrese un precio válido (ejemplo: 45.50).");
            return;
        }
 
        // 2. Armar el objeto Cancha con lo que hay en pantalla
        Cancha c = new Cancha(
                txtNombre.getText().trim(),
                cmbDeporte.getSelectedItem().toString().trim(),
                precio,
                cmbEstado.getSelectedItem().toString());
 
        // 3. Registrar o actualizar según el modo
        try {
            if (idSeleccionado == 0) {
                canchaService.registrar(c);
                JOptionPane.showMessageDialog(this, "Cancha registrada correctamente.");
            } else {
                c.setIdCancha(idSeleccionado);
                canchaService.modificar(c);
                JOptionPane.showMessageDialog(this, "Cancha actualizada correctamente.");
            }
            cargarTabla();
            limpiarCampos();
        } catch (CanchaException ex) {
            mostrarError(ex.getMessage());
        }
    
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (idSeleccionado == 0) {
            mostrarError("Seleccione una cancha de la tabla.");
            return;
        }
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Seguro que desea eliminar la cancha seleccionada?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            canchaService.eliminar(idSeleccionado);
            cargarTabla();
            limpiarCampos();
        } catch (CanchaException ex) {
            mostrarError(ex.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmAdmin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cmbDeporte;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCanchas;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
