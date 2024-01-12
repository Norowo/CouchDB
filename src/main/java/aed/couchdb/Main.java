package aed.couchdb;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.ViewQuery;
import org.ektorp.ViewResult;
import org.ektorp.ViewResult.Row;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

public class Main {

	public static void main(String[] args) throws Exception {

		try {
			// Establecer conexión a CouchDB
			HttpClient httpClient = new StdHttpClient.Builder().url("http://admin:admin@localhost:5984").build();
			CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
			System.out.println("Se ha conectado a la base de datos correctamente.");

			// Crear una conexión a la base de datos
			CouchDbConnector db = new StdCouchDbConnector("Alumnos", dbInstance);

			// Crear base de datos si no existe
			db.createDatabaseIfNotExists();

			// Crear un documento con nombre y apellido

			Alumno alumno = new Alumno("David", "Díaz");
			Alumno alumno2 = new Alumno("Hugo", "Gómez");
			Alumno alumno3 = new Alumno("Carlos", "Martínez");

			db.create(alumno);
			db.create(alumno2);
			db.create(alumno3);
			System.out.println("Registro creado correctamente.");

			// --------------------------------------------------------------------------------------------------------------------//

			// Realizar una consulta para obtener todos los documentos
			ViewQuery query = new ViewQuery().allDocs().includeDocs(true);
			ViewResult result = db.queryView(query);

			// Imprimir los resultados
			System.out.println("Registros en la base de datos:");

			for (ViewResult.Row row : result.getRows()) {
				Alumno alumnoConsultado = db.get(Alumno.class, row.getId());
				System.out.println(
						"Nombre: " + alumnoConsultado.getNombre() + ", Apellido: " + alumnoConsultado.getApellido());
			}

			// --------------------------------------------------------------------------------------------------------------------//

//			// Obtener el documento existente
//            Alumno alumnoExistente = db.get(Alumno.class, "ID_DEL_DOCUMENTO_A_ACTUALIZAR"); 
//
//            // Verificar si el documento existe
//            if (alumnoExistente != null) {
//                // Modificar el campo apellido
//                alumnoExistente.setApellido("Pérez");
//
//                // Actualizar el documento en la base de datos
//                db.update(alumnoExistente);
//                System.out.println("Registro actualizado correctamente.");
//            } else {
//                System.out.println("Documento no encontrado.");
//            }
			
			// ----------------------------------------------------------------------------------------------------------------------//
			
//			 // ID del documento que deseas borrar
//            String idDelDocumentoABorrar = "ID_DEL_DOCUMENTO_A_BORRAR";
//
//            // Obtener la revisión actual del documento
//            String revision = db.getCurrentRevision(idDelDocumentoABorrar);
//
//            // Borrar el documento de la base de datos
//            db.delete(idDelDocumentoABorrar, revision);
//            System.out.println("Registro borrado correctamente. _id: " + idDelDocumentoABorrar);


		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
