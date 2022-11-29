
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.google.gson.*;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		
		System.out.println("Programa de mensajería - Escribe acción \n"+
						   "1 - Enviar mensaje \n"+
				           "2 - Leer mensajes \n"+
						   "3 - Lista de receptores");
		
		Scanner sc = new Scanner(System.in);
		
		int opcion = sc.nextInt();
		
		while(opcion>3 || opcion <1) {
			
			System.err.println("Error, elige una opción correcta.");
			
			opcion = sc.nextInt();
			
		}
		
		String jsonString = "";
		
		Connection conn;
		
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","");
		
		Statement statement = conn.createStatement();
		
		String emisor, receptor, texto;
		
		switch(opcion) {
		
		case 1:
			
			sc.nextLine();
				
			System.out.println("Introduce el nombre del emisor: ");
				emisor = sc.nextLine();
			System.out.println("Introduce el nombre del receptor: ");
				receptor = sc.nextLine();
			System.out.println("Introduce el mensaje: ");
				texto = sc.nextLine();
			
			Mensaje msg = new Mensaje(new Timestamp(System.currentTimeMillis()).toString(),emisor, receptor, texto);
			
			GsonBuilder builder = new GsonBuilder();
			
			builder.setPrettyPrinting();
			
			Gson gson = builder.create();
			
			jsonString = gson.toJson(msg);
			
			System.out.println("JSON String: "+jsonString);				
			
			try {
				
				Mensaje msg2 = new Mensaje(new Timestamp(System.currentTimeMillis()).toString(),emisor, receptor, texto);
				
				String insertar = "insert into describemensajesjson (receptor, mensaje) values ('"+msg2.getReceptor()+"', '"+jsonString+"');";
				
				statement.executeUpdate("use mydb;");
				
				statement.executeUpdate(insertar);
				
				statement.close();
				
				conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case 2:

			sc.nextLine();
			
			System.out.println("Introduce el nombre del emisor cuyos mensajes quieres buscar: ");
			
			String nombre = sc.nextLine();
			
			try {
				
				GsonBuilder builder2 = new GsonBuilder();
				
				builder2.setPrettyPrinting();
				
				Gson gson2 = builder2.create();
				
				statement.executeUpdate("use mydb;");								
				
				String consulta = "select * from describemensajesjson where receptor like '"+nombre+"';";
				
				ResultSet rs = statement.executeQuery(consulta);
				
				ArrayList <Mensaje> listaMensajes = new ArrayList<>();
				
				while(rs.next()) {
					System.out.println(rs.getString(3));
					
					String msgBD = rs.getString(3);
					
					Mensaje mensajeBD = gson2.fromJson(rs.getString(3), Mensaje.class);
					
					listaMensajes.add(mensajeBD);
				}
				
				System.out.println("Mensajes de "+nombre);
				
				for (Mensaje mensaje : listaMensajes) {
					System.out.println(mensaje.toString());
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			break;
		case 3:
			
			String consulta2 = "select distinct receptor from describemensajesjson;";
			
			ResultSet rs;
			try {
				
				statement.execute("use mydb;");
				
				rs = statement.executeQuery(consulta2);
								
				while(rs.next()) {
					
					System.out.println(rs.getString(1));
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			break;
		
		}
		
	}

}
