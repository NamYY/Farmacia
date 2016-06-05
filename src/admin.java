/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import farmacia.conectar;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
/**
 *
 * @author NaMYY
 */
public class admin extends javax.swing.JPanel {
    private DefaultListModel consultorios = new DefaultListModel();
    private DefaultListModel medicos = new DefaultListModel();
    private DefaultListModel cDisponibles = new DefaultListModel();
    private DefaultListModel mDisponibles = new DefaultListModel();
    private DefaultTableModel asignaciones = new DefaultTableModel();
    private DefaultTableModel medicamentos = new DefaultTableModel();
    private final JFrame anfitrion;
    private final JFrame actual;
    private final JFrame fram = new JFrame();
    private edicion ed;
    
    /**
     * Creates new form admin
     * @param anfitrion
     * @param actual
     */
    public admin(JFrame anfitrion, JFrame actual) {
        this.actual = actual;
        this.anfitrion = anfitrion;
        
        ed = new edicion(this, fram);
        fram.setSize(407, 463);
        fram.add(ed);
        ed.setSize(387, 374);
        ed.setVisible(true);
        fram.setVisible(false);
        fram.setResizable(false);
        
        asignaciones.addColumn("Nombre");
        asignaciones.addColumn("Consultorio");
        asignaciones.addColumn("Hora de entrada");
        asignaciones.addColumn("Hora de salida");
        
        medicamentos.addColumn("Codigo");
        medicamentos.addColumn("Nombre");
        medicamentos.addColumn("Tipo");
        medicamentos.addColumn("Presentacion");
        medicamentos.addColumn("Precio");
        medicamentos.addColumn("Stock");
        medicamentos.addColumn("Contenido");
        
        initComponents();
        
        jTable1.setModel(asignaciones);
        jList2.setModel(cDisponibles);
        jList3.setModel(consultorios);
        jList1.setModel(mDisponibles);
        jList4.setModel(medicos);
        jTable2.setModel(medicamentos);
        
        actualizar();
    }
    
    public void actualizar(){
        int ref = medicamentos.getRowCount();
        for(int i = 0; i < ref; i++){
            medicamentos.removeRow(0);
        }
        ref = asignaciones.getRowCount();
        for(int i = 0; i < ref; i++){
            asignaciones.removeRow(0);
        }
        cDisponibles.removeAllElements();
        mDisponibles.removeAllElements();
        consultorios.removeAllElements();
        medicos.removeAllElements();
        try{
            Connection cnn = (new conectar()).conect();
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery("select distinct concat(m.nombre, ' ', m.apellidos) as nombre, (select c.numero from consultorio c where c.idConsultorio = h.idConsultorio) as numero, (select min(inicio) from horario g where g.idMedico = h.idMedico and g.idConsultorio = h.idConsultorio) as inicio, (select max(fin) from horario g where g.idMedico = h.idMedico and g.idConsultorio = h.idConsultorio) as fin from horario h, medico m where h.idMedico = m.idMedico");
            String row[] = new String[4];
            while(rs.next()){
                row[0] = rs.getString("nombre");
                row[1] = rs.getString("numero");
                row[2] = rs.getString("inicio");
                row[3] = rs.getString("fin");
                asignaciones.addRow(row);
            }
            rs.close();
            stmt.close();
            cnn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            String raw[] = new String[7];
            Connection cnn = (new conectar()).conect();
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery("select distinct h.idConsultorio, c.numero from horario h, consultorio c where h.idMedico = 0 and h.idConsultorio = c.idConsultorio");
            while(rs.next()){
                cDisponibles.addElement("Consultorio " + rs.getString("numero"));
            }
            rs = stmt.executeQuery("select * from consultorio");
            while(rs.next()){
                consultorios.addElement("Consultorio " + rs.getString("numero"));
            }
            rs = stmt.executeQuery("select nombre, apellidos from medico");
            while(rs.next()){
                medicos.addElement(rs.getString("nombre") + " " + rs.getString("apellidos"));
            }
            for(int i = 0; i < medicos.getSize(); i++){
                rs = stmt.executeQuery("select count(h.idMedico) < 28 as disponible from medico m, horario h where h.idMedico = m.idMedico and concat(m.nombre, ' ', m.apellidos) = '"+medicos.getElementAt(i).toString()+"'");
                rs.next();
                if(rs.getBoolean("disponible")){
                    mDisponibles.addElement(medicos.getElementAt(i));
                }
            }
            rs = stmt.executeQuery("select p.idProducto as id, p.nombre, ct.nombre as tipo, cp.nombre as presentacion, p.precio, p.stock, p.contenido from producto p, catalogoPresentacion cp, catalogotipo ct where p.idPresentacion = cp.idPresentacion and p.idTipo = ct.idTipo");
            while(rs.next()){
                raw[0] = rs.getString("id");
                raw[1] = rs.getString("nombre");
                raw[2] = rs.getString("tipo");
                raw[3] = rs.getString("presentacion");
                raw[4] = rs.getString("precio");
                raw[5] = rs.getString("stock");
                raw[6] = rs.getString("contenido");
                medicamentos.addRow(raw);
            }
            rs = stmt.executeQuery("select * from configuracion");
            if(rs.next()){
                jTextField6.setText(rs.getDouble("consulta") + "");
                jTextField7.setText(rs.getDouble("vacunas") + "");
            }
            rs.close();
            stmt.close();
            cnn.close();
        }catch(Exception e){
            
        }
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
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        jLabel15 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList();
        jButton9 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();

        jButton1.setText("Cerrar sesion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Administracion");

        jLabel3.setText("Asignaciones actuales");

        jTable1.setModel(asignaciones);
        jScrollPane1.setViewportView(jTable1);

        jButton4.setText("Borrar asignacion");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 611, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addGap(161, 161, 161))
        );

        jTabbedPane1.addTab("Consultorios - Medicos", jPanel1);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jList1);

        jLabel5.setText("Medicos sin asignar");

        jLabel6.setText("Consultorios sin asignar");

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jList2FocusGained(evt);
            }
        });
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jList2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jList2PropertyChange(evt);
            }
        });
        jList2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jList2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jList2KeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(jList2);

        jLabel7.setText("Horario a cubrir");

        jButton2.setText("Crear asignacion");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Matutino | 06:00:00 - 13:00:00", "Vespertino | 13:00:00 - 20:00:00" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(110, 110, 110)
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Asignacion", jPanel2);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Nuevo medico");

        jLabel2.setText("Nombre:");

        jLabel10.setText("Especialidad:");

        jLabel11.setText("Cedula:");

        jLabel12.setText("Apellidos:");

        jLabel13.setText("Contraseña:");

        jButton6.setText("Registrar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Nuevo consultorio");

        jButton7.setText("Crear Consultorio");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jList3.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jList3);

        jLabel15.setText("Consultorios");

        jButton8.setText("Eliminar seleccionado");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel16.setText("Medicos");

        jList4.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(jList4);

        jButton9.setText("Eliminar seleccionado");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7)
                        .addGap(72, 72, 72)
                        .addComponent(jButton8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton6)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel13))
                                    .addGap(35, 35, 35)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField1)
                                        .addComponent(jTextField2)
                                        .addComponent(jTextField3)
                                        .addComponent(jTextField4)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel16)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton9))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel12)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel10)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel11)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel13)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jButton7)
                                            .addComponent(jLabel14)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton8)))
                                .addGap(0, 28, Short.MAX_VALUE))
                            .addComponent(jSeparator3))
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("Contratacion y recursos", jPanel3);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(jTable2);

        jButton5.setText("Editar seleccionado");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton10.setText("Eliminar seleccionado");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton10)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton10))
                .addGap(119, 119, 119))
        );

        jTabbedPane1.addTab("Medicamentos", jPanel4);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Costo de las consultas");

        jLabel8.setText("$");

        jButton3.setText("Guardar configuracion");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Costo de las vacunas");

        jLabel9.setText("$");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel17))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 506, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Configuracion", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        actual.setVisible(false);
        anfitrion.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int numero;
        try{
            numero = Integer.parseInt(jList3.getSelectedValue().toString().substring(12));
            if(numero >= 1){
                Connection cnn = (new conectar()).conect();
                Statement stmt = cnn.createStatement();
                stmt.execute("delete from horario where horario.idConsultorio = (select consultorio.idConsultorio from consultorio where consultorio.numero = " + numero + ")");
                stmt.execute("delete from consultorio where numero = " + numero );
                cDisponibles.removeElement(jList3.getSelectedValue());
                consultorios.removeElement(jList3.getSelectedValue());
                JOptionPane.showMessageDialog(this,"Consultorio eliminado");
                stmt.close();
                cnn.close();
            }else{
                JOptionPane.showMessageDialog(this, "No has seleccionado un consultrio a eliminar");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Ocurrio un error al borrar el consultorio");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Double consulta, vacunas;
        try{
            consulta = Double.parseDouble(jTextField6.getText());
            vacunas = Double.parseDouble(jTextField7.getText());
            Connection cnn = (new conectar()).conect();
            Statement stmt = cnn.createStatement();
            stmt.execute("update configuracion set consulta = " + consulta + ", vacunas = " + vacunas + " where idConfig = 0");
            JOptionPane.showMessageDialog(this, "Precios actualizados");
            stmt.close();
            cnn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "No has escrito un formato de precio correcto");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if(jTable2.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this,"No has seleccionado un medicamento");
        }else{
            try{
                Connection cnn = (new conectar()).conect();
                Statement stmt = cnn.createStatement();
                stmt.execute("delete from producto where idProducto = " + medicamentos.getValueAt(jTable2.getSelectedRow(), 0));
                JOptionPane.showMessageDialog(this,"Producto eliminado");
                stmt.close();
                cnn.close();
                actualizar();
            }catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(jTable2.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this,"No has seleccionado un medicamento");
        }else{
            ed.ref.setText("-");
            ed.ref.setText(medicamentos.getValueAt(jTable2.getSelectedRow(),0).toString());
            fram.setVisible(true);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if(jList4.isSelectionEmpty()){
            JOptionPane.showMessageDialog(this, "No has seleccionado un medico a despedir");
        }else{
            try{
                String nombre = jList4.getSelectedValue().toString();
                Connection cnn = (new conectar()).conect();
                Statement stmt = cnn.createStatement();
                stmt.execute("update horario set idMedico = 0 where idMedico = (select idMedico from medico where concat(nombre,' ',apelidos) = '"+nombre+"')");
                stmt.execute("delete from medico where idMedico = (select idMedico from medico where concat(nombre,' ',apelidos) = '"+nombre+"')");
                stmt.close();
                cnn.close();
                JOptionPane.showMessageDialog(this,"Medico Despedido");
                actualizar();
            }catch(SQLException | HeadlessException e){
                
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try{
            String num;
            Connection cnn = (new conectar()).conect();
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery("select (max(numero) + 1) num from consultorio");
            rs.next();
            num = rs.getString("num");
            stmt.execute("insert into consultorio values(default,"+num+")");
            rs = stmt.executeQuery("select max(idConsultorio) as num from consultorio");
            rs.next();
            num = rs.getString("num");
            for(int i = 0; i < 28; i++){
                stmt.execute("insert into horario values(default, 0, "+num+", '"+(6 + (int)((30 * i)/60))+":"+((i * 30) % 60)+"', '"+(6 + (int)((30 * (i + 1))/60))+":"+(((i + 1) * 30) % 60)+"')");
            }
            rs.close();
            stmt.close();
            cnn.close();
            JOptionPane.showMessageDialog(this,"Consultorio creado");
            actualizar();
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String nombre, apellidos, especialidad, cedula, contraseña;
        nombre = jTextField1.getText();
        apellidos = jTextField1.getText();
        especialidad = jTextField1.getText();
        cedula = jTextField1.getText();
        contraseña = jTextField1.getText();
        if(nombre.equals("")||apellidos.equals("")||especialidad.equals("")||cedula.equals("")||contraseña.equals("")){
            JOptionPane.showMessageDialog(this, "No has llenado todos los campos");
        }else{
            try{
                Connection cnn = (new conectar()).conect();
                Statement stmt = cnn.createStatement();
                stmt.execute("insert into medico values(default, '"+nombre+"', '"+apellidos+"','"+especialidad+"','"+cedula+"','"+nombre+"','"+contraseña+"')");
                stmt.close();
                cnn.close();
                JOptionPane.showMessageDialog(this, "Medico registrado");
                actualizar();
            }catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(!jList1.isSelectionEmpty() && !jList2.isSelectionEmpty()){
            String doctor = jList1.getSelectedValue().toString();
            String consultorio = jList2.getSelectedValue().toString();
            String horario = jComboBox3.getSelectedItem().toString();
            if(horario.charAt(0) == 'M'){
                try{
                    Connection cnn = (new conectar()).conect();
                    Statement stmt = cnn.createStatement();
                    ResultSet rs = stmt.executeQuery("select idConsultorio from horario where idMedico = (select idMedico from medico where concat(nombre, ' ', apellidos)) = '"+doctor+"' and inicio = '06:00:00'");
                    if(rs.next()){
                        JOptionPane.showMessageDialog(this, "El medico ya esta ocupado en ese horario");
                    }else{
                        for(int i = 0; i < 14; i++){
                            stmt.execute("update horario set idMedico = (select idMedico from medico where concat(nombre, ' ', apellidos) = '"+doctor+"') where idConsultorio = (select idConsultorio from consultorio where concat('Consultorio ', numero) = '"+consultorio+"') and inicio = '"+(6 + (int)((30 * i)/60))+":"+((i * 30) % 60)+":00'");
                        }
                    }
                    rs.close();
                    stmt.close();
                    cnn.close();
                    JOptionPane.showMessageDialog(this, "Medico asignado");
                    actualizar();
                }catch(Exception e){
                    
                }
            }else if(horario.charAt(0) == 'V'){
                try{
                    Connection cnn = (new conectar()).conect();
                    Statement stmt = cnn.createStatement();
                    ResultSet rs = stmt.executeQuery("select idConsultorio from horario where idMedico = (select idMedico from medico where concat(nombre, ' ', apellidos)) = '"+doctor+"' and inicio = '13:00:00'");
                    if(rs.next()){
                        JOptionPane.showMessageDialog(this, "El medico ya esta ocupado en ese horario");
                    }else{
                        for(int i = 14; i < 28; i++){
                            stmt.execute("update horario set idMedico = (select idMedico from medico where concat(nombre, ' ', apellidos) = '"+doctor+"') where idConsultorio = (select idConsultorio from consultorio where concat('Consultorio ', numero) = '"+consultorio+"') and inicio = '"+(6 + (int)((30 * i)/60))+":"+((i * 30) % 60)+":00'");
                        }
                    }
                    rs.close();
                    stmt.close();
                    cnn.close();
                    JOptionPane.showMessageDialog(this, "Medico asignado");
                    actualizar();
                }catch(Exception e){
                    
                }
            }else{
                JOptionPane.showMessageDialog(this, "No hay horarios disponibles");
            }
        }else{
            JOptionPane.showMessageDialog(this, "No has seleccionado al medico y consultorio");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jList2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jList2FocusGained
        calcular();
    }//GEN-LAST:event_jList2FocusGained

    private void jList2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList2KeyTyped
        calcular();
    }//GEN-LAST:event_jList2KeyTyped

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
        calcular();
    }//GEN-LAST:event_jList2MouseClicked

    private void jList2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jList2PropertyChange
        calcular();
    }//GEN-LAST:event_jList2PropertyChange

    private void jList2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList2KeyPressed
        calcular();
    }//GEN-LAST:event_jList2KeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(jTable1.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "No has seleccionado una asignacion");
        }else{
            try{
                String nombre, consultorio, inicio, fin;
                int ini = 0, fn = 0;
                nombre = asignaciones.getValueAt(jTable1.getSelectedRow(),0).toString();
                consultorio = asignaciones.getValueAt(jTable1.getSelectedRow(),1).toString();
                inicio = asignaciones.getValueAt(jTable1.getSelectedRow(),2).toString();
                fin = asignaciones.getValueAt(jTable1.getSelectedRow(),3).toString();
                Connection cnn = (new conectar()).conect();
                Statement stmt = cnn.createStatement();
                ResultSet rs = stmt.executeQuery("select idHorario from horario where idMedico = (select idMedico from medico where concat(nombre, ' ', apellidos) = '"+nombre+"') and idConsultorio = (select idConsultorio from consultorio where numero = "+consultorio+") and inicio = '"+inicio+"'");
                if(rs.next()){
                    ini = rs.getInt("idHorario");
                }
                rs = stmt.executeQuery("select idHorario from horario where idMedico = (select idMedico from medico where concat(nombre, ' ', apellidos) = '"+nombre+"') and idConsultorio = (select idConsultorio from consultorio where numero = "+consultorio+") and fin = '"+fin+"'");
                if(rs.next()){
                    fn = rs.getInt("idHorario");
                }
                stmt.execute("update horario set idMedico = 0 where idHorario >= "+ini+" and idHorario <= "+fn+"");
                JOptionPane.showMessageDialog(this, "Asignacion eliminada");
                actualizar();
            }catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void calcular(){
        boolean ninguno = true;
        if(!jList2.isSelectionEmpty()){
            try{
                jComboBox3.removeAllItems();
                Connection cnn = (new conectar()).conect();
                Statement stmt = cnn.createStatement();
                ResultSet rs = stmt.executeQuery("select idMedico = 0 as conf from horario h, consultorio c where c.idConsultorio = h.idConsultorio and concat('Consultorio ', c.numero) = '"+jList2.getSelectedValue().toString()+"' and h.inicio = '06:00:00'");
                rs.next();
                if(rs.getBoolean("conf")){
                    jComboBox3.addItem("Matutino | 6:00 - 13:00");
                    ninguno = false;
                }
                rs = stmt.executeQuery("select idMedico = 0 as conf from horario h, consultorio c where c.idConsultorio = h.idConsultorio and concat('Consultorio ', c.numero) = '"+jList2.getSelectedValue().toString()+"' and h.inicio = '13:00:00'");
                rs.next();
                if(rs.getBoolean("conf")){
                    ninguno = false;
                    jComboBox3.addItem("Vespertino | 13:00 - 20:00");
                }
                if(ninguno){
                    jComboBox3.addItem("No hay horarios disponibles");
                }
            }catch(Exception e){
                
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JList jList3;
    private javax.swing.JList jList4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
