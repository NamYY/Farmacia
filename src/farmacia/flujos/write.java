package flujos;

import java.io.FileNotFoundException;
import java.io.File;
import java.lang.SecurityException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class write{
	private Formatter salida;
	String arch;
	File arc;

	public void abrir(String archivo){
		try{
			arch = archivo;
			salida = new Formatter( archivo );
			arc = new File( archivo );
		}catch(SecurityException e){
			System.err.println("No tiene acceso al archivo");
			System.exit(1);
		}catch(FileNotFoundException e){
			System.err.println("Error al crear el archivo");
			System.exit(1);
		}
	}

	public void escribir(String s){
            try{
                    salida.format("%s\n", s);
            }catch(FormatterClosedException e){
                    System.err.println("Error al escribir en el archivo");
                    System.exit(1);
            }catch(NoSuchElementException e){
                    System.err.println("Entrada invalida");
            }
	}

	public void cerrar(){
		if(salida != null){
			salida.close();
		}
	}

	public void vacio(){
		escribir("");
		cerrar();
		abrir( arch );
	}

	public void borrar(){
		cerrar();
		if(!arc.delete()){
			System.out.println("El archivo no se pudo borrar");
		}
	}
}