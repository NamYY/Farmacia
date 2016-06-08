
import com.qoppa.pdf.PDFException;
import com.qoppa.pdf.PDFPermissionException;
import com.qoppa.pdf.PrintSettings;
import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import farmacia.conectar;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import farmacia.remision;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import com.qoppa.pdfViewer.PDFViewerBean;
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NaMYY
 */
public class farmacia extends javax.swing.JPanel {
    private int idRemision;
    private final JFrame anfitrion;
    private final JFrame actual;
    public int idVendedor;
    private String modeloTipo[];
    private String modeloPresentacion[];
    private DefaultTableModel modeloInventario = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
                return false;
        }
    };
    private DefaultTableModel modeloEntrada = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
                return false;
        }
    };
    private DefaultTableModel modeloCirculacion = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
                return false;
        }
    };
    private DefaultTableModel modeloCompra = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
                return false;
        }
    };
    private DefaultTableModel modeloComp = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
                return false;
        }
    };
    private ArrayList<compra> pendiente = new ArrayList();
    private DefaultTableModel modeloPendiente = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
                return false;
        }
    };
    private DefaultListModel pendientes = new DefaultListModel();
    private PDFViewerBean nota = new PDFViewerBean();
    private DefaultTableModel modeloPagos = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
                return false;
        }
    };
    /**
     * Creates new form farmacia
     * @param anfitrion
     * @param actual
     * @param id
     */
    public farmacia(JFrame anfitrion, JFrame actual, int id) {
        idRemision = 0;
        initComponents();
        String nom, apell;
        this.anfitrion = anfitrion;
        this.actual = actual;
        idVendedor = id;
        jTextField7.setText("0");
        try{
            conectar cn = new conectar();
            Connection cnn = cn.conect();
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Vendedor where idVendedor = " + id);
            if(rs.next()){
                nom = rs.getString("Nombres");
                apell = rs.getString("Apellidos");
                jLabel2.setText(nom + " " + apell);
            }else{
                JOptionPane.showMessageDialog(actual, "Hay un problema con tus datos de usuario, inicia sesion de nuevo");
                actual.setVisible(false);
                anfitrion.setVisible(true);
            }
            rs = stmt.executeQuery("select max(idPresentacion) as idPresentacion from catalogoPresentacion");
            if(rs.next()){
                modeloPresentacion = new String[rs.getInt("idPresentacion") + 1];
                guiones(modeloPresentacion);
                rs = stmt.executeQuery("select * from catalogoPresentacion order by idPresentacion");
                while(rs.next()){
                    modeloPresentacion[rs.getInt("idPresentacion")] = rs.getString("Nombre");
                }
                jComboBox2.setModel(new DefaultComboBoxModel(modeloPresentacion));
            }
            rs = stmt.executeQuery("select max(idTipo) as idTipo from catalogoTipo");
            if(rs.next()){
                modeloTipo = new String[rs.getInt("idTipo") + 1];
                guiones(modeloTipo);
                rs = stmt.executeQuery("select * from catalogoTipo order by idTipo");
                while(rs.next()){
                    modeloTipo[rs.getInt("idTipo")] = rs.getString("Nombre");
                }
                jComboBox1.setModel(new DefaultComboBoxModel(modeloTipo));
                jComboBox3.setModel(new DefaultComboBoxModel(modeloTipo));
            }
            rs.close();
            stmt.close();
            cnn.close();
        }catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(anfitrion, "Ocurrio un error al cargar los datos del programa, notifica a sistemas");
            actual.setVisible(false);
            anfitrion.setVisible(true);
        }
        
        modeloInventario.addColumn("Codigo");
        modeloInventario.addColumn("Nombre");
        modeloInventario.addColumn("Descripcion");
        modeloInventario.addColumn("Cantidad");
        modeloInventario.addColumn("Tipo");
        modeloInventario.addColumn("Presentacion");
        modeloInventario.addColumn("Precio");
        modeloInventario.addColumn("Stock");
        modeloInventario.addColumn("Contenido");
        modeloCirculacion.addColumn("Codigo");
        modeloCirculacion.addColumn("Nombre");
        modeloCirculacion.addColumn("Descripcion");
        modeloCirculacion.addColumn("Cantidad");
        modeloCirculacion.addColumn("Tipo");
        modeloCirculacion.addColumn("Presentacion");
        modeloCirculacion.addColumn("Precio");
        modeloCirculacion.addColumn("Stock");
        modeloCirculacion.addColumn("Contenido");
        modeloCompra.addColumn("Codigo");
        modeloCompra.addColumn("Nombre");
        modeloCompra.addColumn("Descripcion");
        modeloCompra.addColumn("Cantidad");
        modeloCompra.addColumn("Tipo");
        modeloCompra.addColumn("Presentacion");
        modeloCompra.addColumn("Precio");
        modeloCompra.addColumn("Stock");
        modeloCompra.addColumn("Contenido");
        modeloComp.addColumn("Codigo");
        modeloComp.addColumn("Nombre");
        modeloComp.addColumn("Descripcion");
        modeloComp.addColumn("Cantidad");
        modeloComp.addColumn("Tipo");
        modeloComp.addColumn("Presentacion");
        modeloComp.addColumn("Contenido");
        modeloComp.addColumn("Precio");
        modeloComp.addColumn("Unidades");
        modeloEntrada.addColumn("Codigo");
        modeloEntrada.addColumn("Nombre");
        modeloEntrada.addColumn("Descripcion");
        modeloEntrada.addColumn("Cantidad");
        modeloEntrada.addColumn("Tipo");
        modeloEntrada.addColumn("Presentacion");
        modeloEntrada.addColumn("Precio");
        modeloEntrada.addColumn("Stock");
        modeloEntrada.addColumn("Contenido");
        jTable1.setModel(modeloInventario);
        jTable2.setModel(modeloEntrada);
        jTable4.setModel(modeloCompra);
        jTable5.setModel(modeloComp);
        
        actualiza();
        
        jList1.setModel(pendientes);
        jTable3.setModel(modeloPagos);
        modeloPagos.addColumn("Cantidad");
        modeloPagos.addColumn("Medicamento");
        modeloPagos.addColumn("Descripcion");
        modeloPagos.addColumn("Precio");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jSpinner1 = new javax.swing.JSpinner();
        jComboBox2 = new javax.swing.JComboBox();
        jSpinner2 = new javax.swing.JSpinner();
        jComboBox3 = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jSpinner4 = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton13 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jSpinner5 = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton6 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jButton1.setText("Cerrar Sesion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 18)); // NOI18N
        jLabel1.setText("Farmacia");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton10.setText("Borrar seleccion");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Inventario", jPanel2);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Nuevo medicamento");

        jLabel4.setText("Nombre:");

        jLabel5.setText("Costo:");

        jLabel6.setText("Cantidad:");

        jLabel7.setText("Presentación:");

        jLabel8.setText("Contenido neto:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setText("Registrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel9.setText("Descripción:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel10.setText("Unidades (stock):");

        jLabel11.setText("No es necesario registrar una cantidad inicial de productos");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Entrada medicamento");

        jButton4.setText("Buscar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jLabel13.setText("Entradas:");

        jButton5.setText("Ingresar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel15.setText("Tipo:");

        jButton8.setText("Borrar seleccion");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(235, 235, 235))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                    .addComponent(jSpinner1)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jComboBox3, 0, 65, Short.MAX_VALUE))
                                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jSpinner3))
                                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 15, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(78, 78, 78)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(42, 42, 42)
                        .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton4)
                                .addComponent(jButton8))
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jButton5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Entradas", jPanel3);

        jLabel16.setText("Medicamento");

        jButton11.setText("Buscar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable4);

        jButton13.setText("Agregar");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel17.setText("Lista de compra");

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable5);

        jLabel18.setText("Total:");

        jTextField7.setEnabled(false);

        jButton14.setText("Borrar todo");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Quitar");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Imprimir nota");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Borrar seleccion");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton17))
                    .addComponent(jSeparator3)
                    .addComponent(jScrollPane6)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addGap(48, 48, 48)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton13))
                            .addComponent(jButton16, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11)
                    .addComponent(jButton17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14)
                    .addComponent(jButton15)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton16)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ventas", jPanel4);

        jLabel14.setText("Pagos pendientes");

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jList1);

        jButton6.setText("Pago realizado");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jTable3);

        jButton7.setText("Medicamentos entregados");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton9.setText("Pago cancelado");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton12.setText("Cancelar entrega");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel19.setText("Compra numero: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton9)
                            .addGap(18, 18, 18)
                            .addComponent(jButton6))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton12)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7)
                            .addComponent(jButton12))))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pagos pendientes", jPanel1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Usuario");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(39, 39, 39)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jLabel2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );
    }// </editor-fold>//GEN-END:initComponents
    // funcional
    
    private void vacio(DefaultTableModel modelo){
        int cuantos = modelo.getRowCount();
        for(int i = 0; i < cuantos; i++){
            modelo.removeRow(0);
        }
    }
    
    private void actualiza(){
        try{
            vacio(modeloInventario);
            vacio(modeloEntrada);
            vacio(modeloCompra);
            conectar c = new conectar();
            Connection cnn = c.conect();
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery("Select p.idProducto, p.nombre, p.descripcion, p.cantidad, t.nombre as tipo, c.nombre as presentacion, p.precio, p.stock, p.contenido from producto p, catalogoTipo t, catalogoPresentacion c where p.idTipo = t.idTipo and p.idPresentacion = c.idPresentacion order by p.idProducto");
            while(rs.next()){
                modeloInventario.addRow(new String[]{rs.getString("idProducto"),rs.getString("Nombre"),rs.getString("Descripcion"),rs.getString("Cantidad"),rs.getString("Tipo"),rs.getString("Presentacion"),rs.getString("Precio"),rs.getString("Stock"),rs.getString("Contenido")});
                modeloEntrada.addRow(new String[]{rs.getString("idProducto"),rs.getString("Nombre"),rs.getString("Descripcion"),rs.getString("Cantidad"),rs.getString("Tipo"),rs.getString("Presentacion"),rs.getString("Precio"),rs.getString("Stock"),rs.getString("Contenido")});
                modeloCompra.addRow(new String[]{rs.getString("idProducto"),rs.getString("Nombre"),rs.getString("Descripcion"),rs.getString("Cantidad"),rs.getString("Tipo"),rs.getString("Presentacion"),rs.getString("Precio"),rs.getString("Stock"),rs.getString("Contenido")});
            }
            rs.close();
            stmt.close();
            cnn.close();
        }catch(Exception e){
            
        }
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        anfitrion.setVisible(true);
        actual.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        jTextField3.setText("");
        jTable2.setModel(modeloEntrada);
    }//GEN-LAST:event_jButton8ActionPerformed
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        DefaultTableModel aux = new DefaultTableModel();
        String[] mod;
        boolean coinsidencia = false;
        String busqueda = jTextField3.getText();
        if(!busqueda.equals("")){
            if(busqueda.length() >= 3){
                aux.addColumn("Codigo");
                aux.addColumn("Nombre");
                aux.addColumn("Descripcion");
                aux.addColumn("Cantidad");
                aux.addColumn("Tipo");
                aux.addColumn("Presentacion");
                aux.addColumn("Precio");
                aux.addColumn("Stock");
                aux.addColumn("Contenido");
                int row = modeloEntrada.getRowCount();
                int column = modeloEntrada.getColumnCount();
                mod = new String[column + 1];
                System.out.println(modeloEntrada.getValueAt(0, 0));
                for(int j = 0; j < row; j++){
                    for(int i = 0; i < column; i++){
                        if(modeloEntrada.getValueAt( j, i).toString().contains(busqueda)){
                            coinsidencia = true;
                            for(int k = 0; k < column; k++){
                                mod[k] = modeloEntrada.getValueAt( j, k).toString();
                            }
                            aux.addRow(mod);
                        }
                    }
                }
                if(coinsidencia){
                    jTable2.setModel(aux);
                }else{
                    jTextField3.setText("");
                    JOptionPane.showMessageDialog(actual, "No se encontraron coinsidencias");
                }
            }else{
                JOptionPane.showMessageDialog(actual, "Ingesa mas de tres caracteres de busqueda");
            }
        }else{
            JOptionPane.showMessageDialog(actual,"No has escrito una busqueda");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String mensage = "";
        String nombre, descripcion;
        double costo;
        int cantidad, unidades, contenido, tipo, presentacion, referencia;
        nombre = jTextField1.getText();
        descripcion = jTextArea1.getText();
        cantidad = Integer.parseInt(jSpinner1.getValue().toString());
        tipo = jComboBox1.getSelectedIndex();
        costo = Double.parseDouble(jTextField5.getText());
        presentacion = jComboBox2.getSelectedIndex();
        unidades = Integer.parseInt(jSpinner2.getValue().toString());
        contenido = Integer.parseInt(jSpinner3.getValue().toString());
        referencia = jComboBox3.getSelectedIndex();
        // validaciones
        if(nombre.length() >= 70){
            mensage += "El nombre del medicamento es demasiado largo\n";
            jTextField1.setText(nombre.substring(0,69));
        }
        if(descripcion.length() >= 150){
            mensage += "La descripcion del producto excede la longitud establecida\n";
            jTextArea1.setText(descripcion.substring(0,149));
        }
        if(cantidad <= 0){
            mensage += "La cantidad no puede ser menor o igual a cero\n";
            jSpinner1.setValue(1);
        }
        if(tipo == 0){
            mensage += "Selecciona un tipo\n";
        }
        if(presentacion == 0){
            mensage += "Selecciona una presentacion\n";
        }
        if(unidades < 0){
            mensage += "Las unidades iniciales no pueden ser menores que cero";
            jSpinner2.setValue(0);
        }
        if(contenido <= 0){
            mensage += "El contenido no puede ser menor o igual a cero";
            jSpinner3.setValue(1);
        }
        if(referencia != tipo){
            referencia = tipo;
            jComboBox3.setSelectedIndex(referencia);
        }
        if(mensage.equals("")){
            try{
                conectar c = new conectar();
                Connection cnn = c.conect();
                Statement stmt = cnn.createStatement();
                System.out.print("select idProducto from producto where nombre = '" + nombre + "' and cantidad  = " + cantidad + " and tipo = " + tipo + " and presentacion = " + presentacion + " and costo = "+ costo + " and contenido = " + contenido);
                ResultSet rs = stmt.executeQuery("select idProducto from producto where nombre = '" + nombre + "' and cantidad  = " + cantidad + " and idtipo = " + tipo + " and idpresentacion = " + presentacion + " and precio = "+ costo + " and contenido = " + contenido);
                if(rs.next()){
                    JOptionPane.showMessageDialog(this, "Un producto identico ya esta registrado");
                }else{
                    String query = "insert into producto values(default,'"+nombre+"','"+descripcion+"',"+cantidad+","+tipo+","+presentacion+","+costo+","+unidades+","+contenido+")";
                    stmt.execute(query);
                    rs = stmt.executeQuery("select max(idProducto) as id from producto");
                    rs.next();
                    JOptionPane.showMessageDialog(actual, "Medicamento registrado");
                    modeloInventario.addRow(new String[]{rs.getString("id"),nombre,descripcion,cantidad+"",jComboBox1.getSelectedItem().toString(),jComboBox2.getSelectedItem().toString(),costo + "",unidades + "",jSpinner2.getValue().toString()});
                    modeloCirculacion.addRow(new String[]{rs.getString("id"),nombre,descripcion,cantidad+"",jComboBox1.getSelectedItem().toString(),jComboBox2.getSelectedItem().toString(),costo + "",unidades + "",jSpinner2.getValue().toString()});
                    modeloEntrada.addRow(new String[]{rs.getString("id"),nombre,descripcion,cantidad+"",jComboBox1.getSelectedItem().toString(),jComboBox2.getSelectedItem().toString(),costo + "",unidades + "",jSpinner2.getValue().toString()});
                    jTextField1.setText("");
                    jTextArea1.setText("");
                    jSpinner1.setValue(0);
                    jComboBox1.setSelectedIndex(0);
                    jTextField5.setText("");
                    jComboBox2.setSelectedIndex(0);
                    jSpinner2.setValue(0);
                    jSpinner3.setValue(0);
                    jComboBox3.setSelectedIndex(0);
                }
                rs.close();
                stmt.close();
                cnn.close();
            }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(actual, "Error al guardar el producto, verifique en inventario");
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(actual, mensage);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DefaultTableModel aux2 = new DefaultTableModel();
        String[] mod;
        boolean coinsidencia = false;
        String busqueda = jTextField2.getText();
        if(!busqueda.equals("")){
            if(busqueda.length() >= 3){
                aux2.addColumn("Codigo");
                aux2.addColumn("Nombre");
                aux2.addColumn("Descripcion");
                aux2.addColumn("Cantidad");
                aux2.addColumn("Tipo");
                aux2.addColumn("Presentacion");
                aux2.addColumn("Precio");
                aux2.addColumn("Stock");
                aux2.addColumn("Contenido");
                int row = modeloInventario.getRowCount();
                int column = modeloInventario.getColumnCount();
                mod = new String[column + 1];
                for(int j = 0; j < row; j++){
                    for(int i = 0; i < column; i++){
                        if(modeloInventario.getValueAt( j, i).toString().contains(busqueda)){
                            coinsidencia = true;
                            for(int k = 0; k < column; k++){
                                mod[k] = modeloInventario.getValueAt( j, k).toString();
                            }
                            aux2.addRow(mod);
                        }
                    }
                }
                if(coinsidencia){
                    jTable1.setModel(aux2);
                }else{
                    jTextField2.setText("");
                    JOptionPane.showMessageDialog(actual, "No se encontraron coinsidencias");
                }
            }else{
                JOptionPane.showMessageDialog(actual, "Selecciona tres o mas caracteres de busqueda");
            }
        }else{
            JOptionPane.showMessageDialog(actual, "No has escrito una busqueda");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        jTextField2.setText("");
        jTable1.setModel(modeloInventario);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try{
            String aux = jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString();
            int entrada = Integer.parseInt(jSpinner4.getValue().toString());
            int indice = Integer.parseInt(aux);
            if(entrada > 0){
                conectar c = new conectar();
                Connection cnn = c.conect();
                Statement stmt = cnn.createStatement();
                stmt.execute("update producto set stock = (stock + "+entrada+") where idProducto = "+indice);
                stmt.close();
                cnn.close();
                jSpinner4.setValue(0);
                JOptionPane.showMessageDialog(actual,"Medicamentos ingresados");
                actualiza();
            }else{
                JOptionPane.showMessageDialog(actual, "No puedes tener entradas negativas ni menores que cero");
                jSpinner4.setValue("0");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(actual,"No has seleccionado un medicamento");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        DefaultTableModel aux2 = new DefaultTableModel();
        String[] mod;
        boolean coinsidencia = false;
        String busqueda = jTextField6.getText();
        if(!busqueda.equals("")){
            if(busqueda.length() >= 3){
                aux2.addColumn("Codigo");
                aux2.addColumn("Nombre");
                aux2.addColumn("Descripcion");
                aux2.addColumn("Cantidad");
                aux2.addColumn("Tipo");
                aux2.addColumn("Presentacion");
                aux2.addColumn("Precio");
                aux2.addColumn("Stock");
                aux2.addColumn("Contenido");
                int row = modeloCompra.getRowCount();
                int column = modeloCompra.getColumnCount();
                mod = new String[column + 1];
                for(int j = 0; j < row; j++){
                    for(int i = 0; i < column; i++){
                        if(modeloCompra.getValueAt( j, i).toString().contains(busqueda)){
                            coinsidencia = true;
                            for(int k = 0; k < column; k++){
                                mod[k] = modeloCompra.getValueAt( j, k).toString();
                            }
                            aux2.addRow(mod);
                        }
                    }
                }
                if(coinsidencia){
                    jTable4.setModel(aux2);
                }else{
                    jTextField6.setText("");
                    JOptionPane.showMessageDialog(actual, "No se encontraron coinsidencias");
                }
            }else{
                JOptionPane.showMessageDialog(actual, "Selecciona tres o mas caracteres de busqueda");
            }
        }else{
            JOptionPane.showMessageDialog(actual, "No has escrito una busqueda");
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        jTable4.setModel(modeloCompra);
        jTextField6.setText("");
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        int indice;
        int unidades;
        int stock;
        try{
            stock = Integer.parseInt(jTable4.getValueAt(jTable4.getSelectedRow(), 7).toString());
            indice = Integer.parseInt(jTable4.getValueAt(jTable4.getSelectedRow(), 0).toString());
            unidades = Integer.parseInt(jSpinner5.getValue().toString());
            if(unidades <= stock){
                modeloComp.addRow(new String[]{jTable4.getValueAt(jTable4.getSelectedRow(),0).toString(),jTable4.getValueAt(jTable4.getSelectedRow(),1).toString(),jTable4.getValueAt(jTable4.getSelectedRow(),2).toString(),jTable4.getValueAt(jTable4.getSelectedRow(),3).toString(),jTable4.getValueAt(jTable4.getSelectedRow(),4).toString(),jTable4.getValueAt(jTable4.getSelectedRow(),5).toString(),jTable4.getValueAt(jTable4.getSelectedRow(),8).toString(),jTable4.getValueAt(jTable4.getSelectedRow(),6).toString(), unidades + ""});
                jTextField7.setText(Double.parseDouble(jTextField7.getText()) + (unidades * Double.parseDouble(jTable4.getValueAt(jTable4.getSelectedRow(),6).toString())) + "");
                jTable4.setValueAt((stock - unidades) + "",jTable4.getSelectedRow(), 7);
            }else{
                JOptionPane.showMessageDialog(this, "No hay suficientes unidades");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(actual,"No has seleccionado un medicamento");
        }
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        int filas = modeloComp.getRowCount();
        int unidades, codigo;
        System.out.println(filas);
        for(int i = 0; i < filas; i++){
            unidades = Integer.parseInt(jTable5.getValueAt(0, 8).toString());
            codigo = Integer.parseInt(jTable5.getValueAt(0, 0).toString());
            for(int j = 0; j < jTable4.getRowCount(); j++){
                if(Integer.parseInt(jTable4.getValueAt(j, 0).toString()) == codigo){
                    jTable4.setValueAt((Integer.parseInt(jTable4.getValueAt(j, 7).toString()) + unidades) + "", j, 7);
                }
            }
            modeloComp.removeRow(0);
        }
        jTextField7.setText("0");
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        jTextField7.setText(Double.parseDouble(jTextField7.getText()) - (Integer.parseInt(jTable5.getValueAt(jTable5.getSelectedRow(),7).toString()) * Double.parseDouble(jTable5.getValueAt(jTable5.getSelectedRow(), 8).toString())) + "");
        int unidades = Integer.parseInt(jTable5.getValueAt(jTable5.getSelectedRow(), 8).toString());
        int codigo = Integer.parseInt(jTable5.getValueAt(jTable5.getSelectedRow(), 0).toString());
        for(int i = 0; i < jTable4.getRowCount(); i++){
            if(Integer.parseInt(jTable4.getValueAt(i, 0).toString()) == codigo){
                jTable4.setValueAt((Integer.parseInt(jTable4.getValueAt(i, 7).toString()) + unidades) + "", i, 7);
            }
        }
        modeloComp.removeRow(jTable5.getSelectedRow());
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        int filas = jTable5.getRowCount();
        String impresion;
        String presentacion = (idRemision) + "- ";
        double total = 0;
        String[][] compra = new String[filas][5];
        for(int i = 0; i < filas; i++){
            compra[i][0] = jTable5.getValueAt(i,8).toString();// unidades
            compra[i][1] = jTable5.getValueAt(i,1).toString();// medicamento
            presentacion += jTable5.getValueAt(i,1).toString() + ", ";
            compra[i][2] = jTable5.getValueAt(i,2).toString();// descripcion
            compra[i][3] = jTable5.getValueAt(i,7).toString();// precio
            compra[i][4] = jTable5.getValueAt(i,0).toString();// codigo
            total += Double.parseDouble(compra[i][0]) * Double.parseDouble(compra[i][3]);
        }
        compra com = new compra(compra, idRemision, filas);
        pendiente.add(com);
        remision r = new remision();
        r.escribir(compra, idVendedor, filas, idRemision);
        idRemision++;
        jTextField7.setText("0");
        for(int i = 0; i < filas; i++){
            modeloComp.removeRow(0);
        }
        pendientes.addElement(presentacion);
        impresion = "C:\\Users\\NaMYY\\Desktop\\Farmacia\\Notas\\" + r.direccion;
        imprimir(impresion);
        try{
            boolean puede = false;
            conectar c = new conectar();
            Connection cnn = c.conect();
            Statement stmt = cnn.createStatement();
            ResultSet rs = null;
            for(int i = 0; i < filas; i++){
            rs = stmt.executeQuery("select stock >= "+compra[i][0]+" as puede from producto where idProducto = "+compra[i][4]);
                if(rs.next() && rs.getString("puede").equals("1")){
                    stmt.execute("insert into ventas values("+compra[i][4]+","+idVendedor+",curdate(),"+compra[i][0]+")");
                    stmt.execute("update producto set stock = stock - "+compra[i][0]+" where idProducto = "+compra[i][4]);
                }
            }
            actualiza();
            rs.close();
            stmt.close();
            cnn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(actual, "Error al realizar la compra");
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(!jList1.isSelectionEmpty()){
            String selected = jList1.getSelectedValue().toString();
            if(modeloPagos.getRowCount() > 0){
                int med = modeloPagos.getRowCount();
                for(int i = 0; i < med; i++){
                    modeloPagos.removeRow(0);
                }
            }
            int num;
            if(selected == null){
                JOptionPane.showMessageDialog(this, "No has seleccionado una compra");
            }else{
                num = Integer.parseInt(selected.split("-")[0]);
                for(int i = 0; i < pendiente.size(); i++){
                    if(pendiente.get(i).idCompra == num){
                        for(int j = 0; j < pendiente.get(i).filas; j++){
                            modeloPagos.addRow(pendiente.get(i).comp[j]);
                        }
                    }
                }
                jLabel19.setText("Compra numero:" + num);
            }
        }else{
            JOptionPane.showMessageDialog(this, "No has seleccionado una compra");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(modeloPagos.getRowCount() > 0){
            int med = modeloPagos.getRowCount();
            for(int i = 0; i < med; i++){
                modeloPagos.removeRow(0);
            }
            int numero = Integer.parseInt(jLabel19.getText().split(":")[1]);
            jLabel19.setText("Compra numero:");
            for(int i = 0; i < pendientes.getSize(); i++){
                if(Integer.parseInt(pendientes.getElementAt(i).toString().split("-")[0]) == numero){
                    pendientes.removeElementAt(i);
                }
            }
            for(int i = 0; i < pendiente.size(); i++){
                if(pendiente.get(i).idCompra == numero){
                    pendiente.remove(i);
                }
            }
            JOptionPane.showMessageDialog(this, "Entrega realizada");
        }else{
            JOptionPane.showMessageDialog(this, "No hay medicamentos a entregar");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if(modeloPagos.getRowCount() > 0){
            int med = modeloPagos.getRowCount();
            for(int i = 0; i < med; i++){
                modeloPagos.removeRow(0);
            }
            JOptionPane.showMessageDialog(this, "Entrega cancelada");
        }else{
            JOptionPane.showMessageDialog(this, "No hay compra activa");
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        String selected = jList1.getSelectedValue().toString();
        String[][] back = null;
        if(selected == null){
            JOptionPane.showMessageDialog(this, "No has seleccionado una compra");
        }else{
            int num = Integer.parseInt(selected.split("-")[0]);
            int filas = 0;
            pendientes.removeElement(selected);
            for(int i = 0; i < pendiente.size(); i++){
                if(pendiente.get(i).idCompra == num){
                    back = pendiente.get(i).comp;
                    filas = pendiente.get(i).filas;
                }
            }
            try{
                Connection cnn = (new conectar()).conect();
                Statement stmt = cnn.createStatement();
                for(int i = 0; i < filas; i++){
                    stmt.execute("update producto set stock = stock + "+back[i][0]+" where idProducto = " + back[i][4]);
                }
                stmt.close();
                cnn.close();
            }catch(Exception e){
                
            }
            JOptionPane.showMessageDialog(this, "Compra cancelada");
            if(modeloPagos.getRowCount() > 0){
                int med = modeloPagos.getRowCount();
                for(int i = 0; i < med; i++){
                    modeloPagos.removeRow(0);
                }
            }
            actualiza();
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JSpinner jSpinner5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables

    private void guiones(String[] modelo) {
        for(int i = 0; i < modelo.length; i++){
            modelo[i] = "-";
        }
    }
    
    private void imprimir(String impresion){
        try{
            Thread.sleep(1000);
            nota.loadPDF(impresion);
            PrintSettings p = new PrintSettings();
            p.m_AutoRotate = true;
            p.m_CenterInPage = true;
            p.m_ExpandToMargins = true;
            p.m_PrintAnnotations = true;
            p.m_QoppaDialog = true;
            p.m_ShrinkToMargins = true;
            nota.print(p);
        } catch (PDFException | PrinterException | InterruptedException ex) {
            imprimir(impresion);
        }
    }
}
