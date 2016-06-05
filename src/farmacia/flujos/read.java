package flujos;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class read{

	private static Scanner entrada;

	public static void abrir( String archivo ){
		try{
			entrada = new Scanner( new File( archivo ));
		}catch( FileNotFoundException e){
			System.err.println("Error al abrir el archivo");
			System.exit(1);
		}
	}

	public static String leer(){
		try{
			if(entrada.hasNext()){
				return entrada.nextLine();
			}else{
				return "";
			}
		}catch( NoSuchElementException e){
			System.err.println("El archivo no esta bien formado");
			entrada.close();
			System.exit(1);
		}catch( IllegalStateException e){
			System.err.println("Error al leer el archivo");
			System.exit(1);
		}
		return "";
	}

	public void cerrar(){
		if(entrada != null){
			entrada.close();
		}
	}
}