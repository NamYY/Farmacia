/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author NaMYY
 */
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JLabel; 
import javax.swing.JProgressBar;

public class reloj extends JLabel implements Runnable{ 

public String dia, mes, año, hora, minutos, segundos; 
public Calendar calendario = new GregorianCalendar();
public boolean segundero = false;
public int contador;
public JProgressBar barra;

Thread hilo; 

    /**
     *
     * @param x
     * @param y
     * @param p
     * @param p1
     */
    public reloj( int x, int y, int p, int p1) {
        setBounds(x, y, p, p1);
        segundero = false;
        contador = 0;
        setFont(new java.awt.Font("Cambri", 0, 36)); 
        hilo = new Thread(this); 
        hilo.start(); 
    }
    
    /**
     *
     * @param x
     * @param y
     * @param p
     * @param p1
     * @param barra
     */
    public reloj( int x, int y, int p, int p1, JProgressBar barra) {
        setBounds(x, y, p, p1);
        segundero = false;
        contador = 0;
        this.barra = barra;
        setFont(new java.awt.Font("Cambri", 0, 36)); 
        hilo = new Thread(this); 
        hilo.start(); 
    }

    public void actualiza() { 
        Date fechaHoraActual = new Date(); 
        calendario.setTime(fechaHoraActual); 

        hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY)); 
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE); 
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND); 
        dia = calendario.get(Calendar.DATE) > 9 ? "" + calendario.get(Calendar.DATE) : "0" + calendario.get(Calendar.DATE); 
        mes = calendario.get(Calendar.MONTH) > 9 ? "" + calendario.get(Calendar.MONTH) : "0" + calendario.get(Calendar.MONTH); 
        año = calendario.get(Calendar.YEAR) > 9 ? "" + calendario.get(Calendar.YEAR) : "0" + calendario.get(Calendar.YEAR); 
    }
    
    public void setSegundero(boolean set){
        segundero = set;
        contador = 0;
    }

    public void run( ){ 
        Thread ct = Thread.currentThread(); 
        while (ct == hilo) { 
        try { 
            actualiza(); 
            int mesE; 
            mesE = Integer.valueOf(mes) + 1;
            if(segundero){
                contador++;
                barra.setValue(contador);
            }
            setText(hora + ":" + minutos + ":" + segundos); 
            Thread.sleep(1000); 
        } catch (InterruptedException ex) { 
            System.out.println(ex.getMessage()); 
        } 

        } 
    }

}