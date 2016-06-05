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
public class compilador {
    public static String consultorio(String consu){
        int i = 0;
        return nodo1(consu, i);
    }
    
    public static String inicio(String c){
        int i = 0; 
        String ux = inicio1(c, i);
        return ux.substring(0,ux.length() -1);
    }
    
    public static String fin(String c){
        int i = 0;
        return fin1( c, i);
    }
    
    private static String fin1(String c, int i){
        if(c.length() == i){
            return "";
        }else if(c.charAt(i) == '-'){
            i += 2;
            return fin2( c, i);
        }else{
            return fin1( c, ++i);
        }
    }
    
    private static String fin2( String c, int i){
        if(c.length() == i){
            return "";
        }else{
            return c.charAt(i) + fin2(c, ++i);
        }
    }
    
    private static String inicio1(String c, int i){
        if(c.length() == i){
            return "";
        }else if(c.charAt(i) == '|'){
            i += 2;
            return inicio2( c, i);
        }else{
            return inicio1(c, ++i);
        }
    }
    
    private static String inicio2(String c, int i){
        if(c.length() == i){
            return "";
        }else if(c.charAt(i) == '-'){
            return "";
        }else{
            return c.charAt(i) + inicio2(c, ++i);
        }
    }
    
    private static String nodo1(String c, int i){
        if(c.length() == i){
            return "";
        }else if(c.charAt(i) == '0'){
            return "0" + nodo2( c, ++i);
        }else if(c.charAt(i) == '1'){
            return "1" + nodo2( c, ++i);
        }else if(c.charAt(i) == '2'){
            return "2" + nodo2( c, ++i);
        }else if(c.charAt(i) == '3'){
            return "3" + nodo2( c, ++i);
        }else if(c.charAt(i) == '4'){
            return "4" + nodo2( c, ++i);
        }else if(c.charAt(i) == '5'){
            return "5" + nodo2( c, ++i);
        }else if(c.charAt(i) == '6'){
            return "6" + nodo2( c, ++i);
        }else if(c.charAt(i) == '7'){
            return "7" + nodo2( c, ++i);
        }else if(c.charAt(i) == '8'){
            return "8" + nodo2( c, ++i);
        }else if(c.charAt(i) == '9'){
            return "9" + nodo2( c, ++i);
        }else{
            return nodo1( c, ++i);
        }
    }
    
    private static String nodo2( String c, int i){
        if(c.charAt(i) == '0'){
            return "0" + nodo2( c, ++i);
        }else if(c.charAt(i) == '1'){
            return "1" + nodo2( c, ++i);
        }else if(c.charAt(i) == '2'){
            return "2" + nodo2( c, ++i);
        }else if(c.charAt(i) == '3'){
            return "3" + nodo2( c, ++i);
        }else if(c.charAt(i) == '4'){
            return "4" + nodo2( c, ++i);
        }else if(c.charAt(i) == '5'){
            return "5" + nodo2( c, ++i);
        }else if(c.charAt(i) == '6'){
            return "6" + nodo2( c, ++i);
        }else if(c.charAt(i) == '7'){
            return "7" + nodo2( c, ++i);
        }else if(c.charAt(i) == '8'){
            return "8" + nodo2( c, ++i);
        }else if(c.charAt(i) == '9'){
            return "9" + nodo2( c, ++i);
        }else{
            return "";
        }
    }
    
}
