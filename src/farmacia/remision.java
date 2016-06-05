/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;
import flujos.write;
import flujos.read;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author NaMYY
 */
public class remision {
    
    private String nota;
    private String remision;
    private String item;
    private String nota1;
    public String direccion;
    private String mPrevent;
    private String hPiece;
    private String receta;
    private String rItem;
    private String rItemf;
    
    public remision(){
        remision = "\\documentclass[10pt,a4paper]{letter}\n" +
                "\\usepackage[utf8]{inputenc}\n" +
                "\\usepackage{amsmath}\n" +
                "\\usepackage{amsfonts}\n" +
                "\\usepackage{amssymb}\n" +
                "\\usepackage{graphics,color}\n" +
                "\\usepackage{graphicx}\n" +
                "\\DeclareGraphicsExtensions{.bmp,.png,.pdf,.jpg, .jpeg}\n" +
                "\\title{\\bf ** CLINICA X **}\n" +
                "\\begin{document}\n" +
                "\\begin{center}\n" +
                "{\\scshape\\LARGE \\bf *** CLINICA X ***\\par}\n" +
                "\n" +
                "\\end{center}\n" +
                "\n" +
                "FECHA:\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ <fecha> \\\\\n" +
                "CONCEPTO DE PAGO:\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ <concepto> \\\\\n" +
                "CANTIDAD TOTAL:\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ <cantidad> \\\\\n" +
                "ATENDIDO POR:\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ <nombre> \\\\\n" +
                "GRACIAS POR SU COMPRA! \\\\ \\\\\n" +
                "\n" +
                " \n" +
                "\\end{document}";
        
        nota = "\\documentclass[10pt,a4paper]{letter}\n" +
                "\\usepackage[utf8]{inputenc}\n" +
                "\\usepackage{amsmath}\n" +
                "\\usepackage{amsfonts}\n" +
                "\\usepackage{amssymb}\n" +
                "\\usepackage{graphics,color}\n" +
                "\\usepackage{graphicx}\n" +
                "\\DeclareGraphicsExtensions{.bmp,.png,.pdf,.jpg, .jpeg}\n" +
                "\\title{\\bf CLINICA X}\n" +
                "\\begin{document}\n" +
                "\\begin{center}\n" +
                "{\\scshape\\LARGE CLINICA X\\par}\n" +
                "{Avenida Universidad 975, Col del Valle Sur,\\\\}\n" +
                "{C.P. 03100 Ciudad de M\'exico, D.F.}\n" +
                "\n" +
                "---------------------------------------------------------------\n" +
                "\\end{center}\n" +
                "\n" +
                "Fecha y hora: <fecha> <hora>\\\\\n" +
                "\n" +
                "Cantidad \\ \\ \\ \\ \\ \\ Medicamento \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\  Descripcion  \\ \\ \\ \\ \\ \\ \\ \\ \\ Precio \\\\\n" +
                "------------------------------------------------------------------------------------------------ \\\\\n" +
                "<compra>\n" +
                "------------------------------------------------------------------------------------------------ \\\\\n" +
                "Total	\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\  \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\  \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ <total> \\\\\n" +
                "Pago \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\  \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\  \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\  <pago> \\\\\n" +
                "Cambio	\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\  \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\  \\ \\ \\ \\ \\ \\ <cambio> \\\\\n" +
                "\n" +
                "Gracias por su compra!\n" +
                " \n" +
                "\\end{document}";
        item = "<cantidad> \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ <medicamento> \\ \\ \\ \\ \\ \\ \\ \\ \\ <descripcion> \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\$ <precio> \\\\\n";
        nota1 = "\\documentclass[10pt,a4paper]{letter}\n" +
                "\\usepackage[utf8]{inputenc}\n" +
                "\\usepackage{amsmath}\n" +
                "\\usepackage{amsfonts}\n" +
                "\\usepackage{amssymb}\n" +
                "\\usepackage{graphics,color}\n" +
                "\\usepackage{graphicx}\n" +
                "\\DeclareGraphicsExtensions{.bmp,.png,.pdf,.jpg, .jpeg}\n" +
                "\\title{\\bf CLINICA X}\n" +
                "\\begin{document}\n" +
                "\\begin{center}\n" +
                "{\\scshape\\LARGE CLINICA X\\par}\n" +
                "{Avenida Universidad 975, Col del Valle Sur,\\\\}\n" +
                "{C.P. 03100 Ciudad de M\'exico, D.F.}\\\\ \n" +
                "{No. <idRemision>}\\\\ \n" +
                "---------------------------------------------------------------\n" +
                "\\end{center}\n" +
                "\n" +
                "Fecha y hora: <fecha> <hora>\\\\\n" +
                "\n" +
                "Cantidad \\ \\ \\ \\ \\ \\ Medicamento \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\  Descripcion  \\ \\ \\ \\ \\ \\ \\ \\ \\ Precio \\\\\n" +
                "------------------------------------------------------------------------------------------------ \\\\\n" +
                "<compra>\n" +
                "------------------------------------------------------------------------------------------------ \\\\\n" +
                "Total	\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\  \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\  \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ <total> \\\\\n" +
                " \n" +
                "\\end{document}";
        mPrevent = "\\documentclass[10pt,b5paper]{letter}\n" +
                "\\usepackage[utf8]{inputenc}\n" +
                "\\usepackage{amsmath}\n" +
                "\\usepackage{amsfonts}\n" +
                "\\usepackage{amssymb}\n" +
                "\\usepackage{graphics,color}\n" +
                "\\usepackage{graphicx}\n" +
                "\\DeclareGraphicsExtensions{.bmp,.png,.pdf,.jpg, .jpeg}\n" +
                "\\title{\\bf CLINICA X}\n" +
                "\\begin{document}\n" +
                "\\begin{center}\n" +
                "{\\scshape\\LARGE \\bf CLINICA X\\par}\n" +
                "{\\bf MEDICINA PREVENTIVA\\\\}\n" +
                "\\end{center}\n" +
                "\n" +
                "NOMBRE:\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ <nombre> \\\\\n" +
                "CURP:\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ <curp> \\\\\n" +
                "PESO:\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ ----------------- \\\\\n" +
                "ESTATURA:\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ ----------------- \\\\\n" +
                "PRESION ARTERIAL:\\ \\ \\ \\ \\ \\ \\ \\ \\ ----------------- \\\\\n" +
                "\n" +
                "-----------------\\\\\n" +
                "FIRMA \n" +
                "\\end{document}";
        hPiece = "FECHA: \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ <fecha> \\\\\n" +
                "PESO: \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ <peso> \\\\\n" +
                "PRESIÓN ARTERIAL: \\ \\ \\ \\ \\ \\ <presion> \\\\\n" +
                "ESTATURA: \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ <estatura>\\\\\n" +
                "OBSERVACIONES:\\\\\n" +
                "<observaciones>\\\\\n" +
                "DIAGNOSTICO:\\\\\n" +
                "<diagnostico>\\\\\n" +
                "RECOMENDACIONES:\\\\\n" +
                "<recomendaciones>\\\\\n" +
                "--------------------------------------------------------------------------------------------------------- \\\\\n"+
                "-consultas-";
        receta = "\\documentclass[10pt,a4paper]{letter}\n" +
                "\\usepackage[utf8]{inputenc}\n" +
                "\\usepackage{amsmath}\n" +
                "\\usepackage{amsfonts}\n" +
                "\\usepackage{amssymb}\n" +
                "\\usepackage{graphics,color}\n" +
                "\\usepackage{graphicx}\n" +
                "\\usepackage{float}\n" +
                "\\usepackage[spanish,es-tabla]{babel}\n" +
                "\\usepackage{multirow}"+
                "\\DeclareGraphicsExtensions{.bmp,.png,.pdf,.jpg, .jpeg}\n" +
                "\\title{\\bf CLINICA X}\n" +
                "\\begin{document}\n" +
                "\\begin{center}\n" +
                "{\\scshape\\LARGE CLINICA X\\par}\n" +
                "{Avenida Universidad 975, Col del Valle Sur,\\\\}\n" +
                "{C.P. 03100 Ciudad de México, D.F.}\n" +
                "\n" +
                "---------------------------------------------------------------\n" +
                "\\end{center}\n" +
                "\n" +
                "Dr: <doctor>\\\\\n" +
                "Cédula: <cedula>\\\\\n" +
                "------------------------------------------------------------------------------------------------ \\\\ \\\\\n" +
                "Fecha: <fecha>\\\\\n" +
                "Nombre: <nombre>\\\\\n" +
                "CURP: <curp>\\\\\n" +
                "\n" +
                "------------------------------------------------------------------------------------------------ \\\\\n" +
                "\n" +
                "\n" +
                "\\begin{tabular}{| p{2.4cm}| p{2.4cm} | p{2.4cm} | p{2.4cm} |}\n" +
                "\\hline\n" +
                "\\multicolumn{4}{|c|}{Tratamiento} \\\\\n" +
                "\\hline\n" +
                "Medicamentos & Dosis & Frecuencia & Tiempo \\\\ \\hline\n" +
                "<item>\n" +
                "\\end{tabular} \\newline\n" +
                "\n" +
                "------------------------------------------------------------------------------------------------ \\\\ \\\\ \\\\\n" +
                "\n" +
                "\\bigskip\\ \\ \\  --------------------------\\\\\n" +
                "\\bigskip\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ FIRMA\n" +
                "\n" +
                " \n" +
                "\\end{document}";
        rItemf = "<medicamento> & <dosis> & <frecuencia> & <tiempo> \\\\ \\hline\n";
        rItem = "<medicamento> & <dosis> & <frecuencia> & <tiempo> \\\\ \\hline\n"
                + "<item>\n";
    }
    
    public void escribir(String[][] compra, int vendedor, int compras, double pago){
        Date fecha = new Date();
        write w = new write();
        String fec, hora, comp = "", archivo;
        double total = 0;
        Calendar calendario = new GregorianCalendar();
        
        fec = fecha.getDate() + "-" + fecha.getMonth() + "-" + fecha.getYear();
        hora = calendario.get(Calendar.HOUR_OF_DAY) + "-" + calendario.get(Calendar.MINUTE) + "-" + calendario.get(Calendar.SECOND);
        archivo = "remision " + fec + "(" + hora + ")";
        w.abrir(archivo + ".tex");
        nota = nota.replace("<fecha>",fec);
        nota = nota.replace("<hora>",hora.replace("-",":"));
        for(int i = 0; i < compras; i++){
            comp += item.replace("<cantidad>",compra[i][0]).replace("<medicamento>", compra[i][1]).replace("<descripcion>",compra[i][2]).replace("<precio>",compra[i][3]);
            total += Double.parseDouble(compra[i][0]) * Double.parseDouble(compra[i][3]);
        }
        nota = nota.replace("<compra>", comp);
        nota = nota.replace("<total>",total+"");
        nota = nota.replace("<pago>", pago+"");
        nota = nota.replace("<cambio>", (pago - total) + "");
        w.escribir(nota);
        w.cerrar();
        try {
            Runtime.getRuntime().exec("res.bat");
        } catch (Exception ioe) {
        }
    }
    
    public void escribir(String[][] compra, int vendedor, int compras, int idRemision){
        Date fecha = new Date();
        write w = new write();
        String fec, hora, comp = "", archivo;
        double total = 0;
        Calendar calendario = new GregorianCalendar();
        
        fec = fecha.getDate() + "-" + fecha.getMonth() + "-" + fecha.getYear();
        hora = calendario.get(Calendar.HOUR_OF_DAY) + "-" + calendario.get(Calendar.MINUTE) + "-" + calendario.get(Calendar.SECOND);
        archivo = "nota" + fec + "(" + hora + ")";
        direccion = archivo + ".pdf";
        w.abrir(archivo + ".tex");
        nota1 = nota1.replace("<idRemision>", idRemision + "");
        nota1 = nota1.replace("<fecha>",fec);
        nota1 = nota1.replace("<hora>",hora.replace("-",":"));
        for(int i = 0; i < compras; i++){
            comp += item.replace("<cantidad>",compra[i][0]).replace("<medicamento>", compra[i][1]).replace("<descripcion>",compra[i][2]).replace("<precio>",compra[i][3]);
            total += Double.parseDouble(compra[i][0]) * Double.parseDouble(compra[i][3]);
        }
        nota1 = nota1.replace("<compra>", comp);
        nota1 = nota1.replace("<total>",total+"");
        w.escribir(nota1);
        w.cerrar();
        try {
            Runtime.getRuntime().exec("not.bat");
        } catch (Exception ioe) {
        }
    }
    
    public void rem(String fecha, String nombre, String concepto, double cantidad){
        String nom = null;
        remision = remision.replace("<fecha>", fecha);
        remision = remision.replace("<nombre>", nombre);
        remision = remision.replace("<concepto>", concepto);
        remision = remision.replace("<cantidad>", cantidad+ "");
        try{
            Connection cnn = (new conectar()).conect();
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery("select max(idCompra) as maximo from compras");
            rs.next();
            nom = "remision("+rs.getString("maximo")+")";
            rs.close();
            stmt.close();
            cnn.close();
        }catch(Exception e){
            
        }
        write w = new write();
        w.abrir(nom + ".tex");
        w.escribir(remision);
        w.cerrar();
        try {
            Runtime.getRuntime().exec("rem.bat");
        } catch (Exception ioe) {
            
        }
    }
    
    public void prevent(String nombre, String curp){
        String nom = "mPrev";
        mPrevent = mPrevent.replace("<nombre>", nombre);
        mPrevent = mPrevent.replace("<curp>", curp);
        write w = new write();
        w.abrir(nom + ".tex");
        w.escribir(mPrevent);
        w.cerrar();
        try {
            Runtime.getRuntime().exec("prev.bat");
        } catch (Exception ioe) {
            
        }
    }
    
    public void addExp( int idCita, double peso, double estatura, double presion, String observaciones, String diagnostico, String recomendaciones){
        String curp = "";
        String adicion = "", aux = "algo";
        String fecha = "";
        try{
            Connection cnn = (new conectar()).conect();
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery("select p.curp, curdate() as fecha from cita c, paciente p where p.idPaciente = c.idPaciente and c.idCita = " + idCita);
            rs.next();
            curp = rs.getString("curp");
            fecha = rs.getString("fecha");
            rs.close();
            stmt.close();
            cnn.close();
        }catch(Exception e){
            
        }
        try {
            String cmd[] = {"naux.bat", curp};
            Runtime.getRuntime().exec(cmd);
            Thread.sleep(1000);
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
        read r = new read();
        r.abrir("expediente("+curp+").tex");
        while(!aux.equals("")){
            aux = r.leer();
            if(!aux.equals("")){
                adicion += aux + "\n";
            }
        }
        r.cerrar();
        write w = new write();
        w.abrir("expediente("+curp+").tex");

        hPiece = hPiece.replace("<fecha>", fecha);
        hPiece = hPiece.replace("<peso>",peso + "");
        hPiece = hPiece.replace("<presion>",presion + "");
        hPiece = hPiece.replace("<estatura>",estatura + "");
        hPiece = hPiece.replace("<observaciones>",observaciones);
        hPiece = hPiece.replace("<diagnostico>",diagnostico);
        hPiece = hPiece.replace("<recomendaciones>",recomendaciones);
        adicion = adicion.replace("-consultas-", hPiece);
        
        w.escribir(adicion);
        w.cerrar();
        
    }
    
    public void compilar(){
        try{
            Runtime.getRuntime().exec("exp.bat");
        }catch(Exception e){

        }
    }
    
    public void receta( int idCita, String[][] med, int altura){
        String curp = "";
        String nombre = "";
        String doctor = "";
        String cedula = "";
        String fecha = "";
        try{
            Connection cnn = (new conectar()).conect();
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery("select concat(p.nombre, ' ', p.apellidos) as nombre ,p.curp, curdate() as fecha, concat(m.nombre, ' ', m.apellidos) as doctor, m.cedula from cita c, paciente p, medico m, horario h where p.idPaciente = c.idPaciente and h.idHorario = c.idHorario and h.idMedico = m.idMedico and c.idCita = " + idCita);
            rs.next();
            curp = rs.getString("curp");
            nombre = rs.getString("nombre");
            doctor = rs.getString("doctor");
            cedula = rs.getString("cedula");
            fecha = rs.getString("fecha");
            rs.close();
            stmt.close();
            cnn.close();
        }catch(Exception e){
            
        }
        receta = receta.replace("<nombre>", nombre);
        receta = receta.replace("<curp>", curp);
        receta = receta.replace("<cedula>", cedula);
        receta = receta.replace("<doctor>", doctor);
        receta = receta.replace("<fecha>", fecha);
        for(int i = 0; i < altura; i++){
            if(i + 1 == altura){
                receta = receta.replace("<item>", rItemf.replace("<medicamento>", med[i][0]).replace("<dosis>", med[i][1]).replace("<frecuencia>", med[i][2]).replace("<tiempo>", med[i][3]));
            }else{
                receta = receta.replace("<item>", rItem.replace("<medicamento>", med[i][0]).replace("<dosis>", med[i][1]).replace("<frecuencia>", med[i][2]).replace("<tiempo>", med[i][3]));
            }
        }
        write w = new write();
        w.abrir("receta.tex");
        w.escribir(receta);
        w.cerrar();
        try {
            Runtime.getRuntime().exec("rec.bat");
            Thread.sleep(1000);
        } catch (Exception ioe) {

        }
    }
}
