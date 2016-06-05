/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import flujos.read;
import flujos.write;
/**
 *
 * @author NaMYY
 */
public class expediente {
    private String exp = "\\documentclass[10pt,a4paper]{letter}\n" +
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
                        "{\\bf HISTORIAL MÉDICO\\\\}\n" +
                        "\\end{center}\n" +
                        "NOMBRE:\\ \\ \\ \\ \\ \\ \\ \\ <nombre>\\\\\n" +
                        "EDAD:\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ <edad>\\\\\n" +
                        "CURP:\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ <curp> \\\\\n" +
                        "DIRECCION:\\ \\ \\ \\ <direccion>\\\\\n" +
                        "--------------------------------------------------------------------------------------------------------- \\\\\n" +
                        "ENFERMEDADES CRONICAS: <cronicas>\\\\ \\\\\n" +
                        "ENFERMEDADES FAMILIARES: <familiares> \\\\ \\\\\n"
                        + "--------------------------------------------------------------------------------------------------------- \\\\\n"
                        + "-consultas-\n"
                        + "\\end{document}";
    private String consulta = "FECHA: <fecha>\\\\\n" +
                        "PESO: <peso>\\ \\ \\ \\ \\ \\ \\ \\ \\ PRESIÓN ARTERIAL: <presion>\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ ESTATURA: <estatura>\\\\\n" +
                        "OBSERVACIONES: <observaciones>\\\\\n" +
                        "DIAGNOSTICO: <diagnostico>\\\\\n" +
                        "--------------------------------------------------------------------------------------------------------- \\\\";
    
    public void crear(String nombre, String curp, String direccion, int edad, String cronicas, String familiares){
        String cumulo = "", aux = "-";
        write w = new write();
        read r = new read();
        
        w.abrir("expediente(" + curp + ").tex");
        r.abrir("expedientes.txt");
        
        while(!aux.equals("")){
            aux = r.leer();
            if(!aux.equals("")){
                cumulo += aux + "\n";
            }
        }
        exp = exp.replace("<nombre>",nombre);
        exp = exp.replace("<curp>",curp);
        exp = exp.replace("<direccion>",direccion);
        exp = exp.replace("<edad>",edad + "");
        exp = exp.replace("<cronicas>",cronicas);
        exp = exp.replace("<familiares>",familiares);
        cumulo += curp;
        
        w.escribir(exp);
        w.cerrar();
        r.cerrar();
        
        write w1 = new write();
        w1.abrir("expedientes.txt");
        w1.escribir(cumulo);
        w1.cerrar();
        
        try {
            Runtime.getRuntime().exec("exp.bat");
            Thread.sleep(6000);
            Runtime.getRuntime().exec("expa.bat");
        } catch (Exception ioe) {
            System.out.println("fallo un comando");
        }
    }
    
    public void compilar(){
        try {
            Runtime.getRuntime().exec("exp.bat");
            Thread.sleep(6000);
            Runtime.getRuntime().exec("expa.bat");
        } catch (Exception ioe) {
            System.out.println("fallo un comando");
        }
    }
}
