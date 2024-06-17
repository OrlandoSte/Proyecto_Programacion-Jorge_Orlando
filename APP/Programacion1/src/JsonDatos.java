import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonDatos {

	private static Conexion conA = new Conexion();
	private Connection con = conA.abrirConexion();

	public JsonDatos(Conexion con) {
		this.conA = con;
	}

	public void exportarEventoAJson() {
		Map<String, List<Map<String, Object>>> eventoData = new HashMap<>();

		eventoData.put("personas", datosTabla("persona"));
		eventoData.put("corredores", obtenerDatosDeCorredores());
		eventoData.put("carreras", datosTabla("carrera"));
		eventoData.put("voluntarios", datosTabla("voluntario"));
		eventoData.put("trabajadores", datosTabla("trabajador"));
		eventoData.put("patrocinadores", datosTabla("patrocinador"));
		eventoData.put("aportaciones", datosTabla("aportacion"));
		eventoData.put("clubs", datosTabla("club"));
		eventoData.put("calificaciones", calificacionesCorredo());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(eventoData);

		try (FileWriter writer = new FileWriter("evento.json")) {
			writer.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<Map<String, Object>> datosTabla(String nombreTabla) {
		List<Map<String, Object>> tablaData = new ArrayList<>();

		String query = "SELECT * FROM " + nombreTabla;
		try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
			int columnCount = resultSet.getMetaData().getColumnCount();
			while (resultSet.next()) {
				Map<String, Object> row = new HashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = resultSet.getMetaData().getColumnName(i);
					Object columnValue = resultSet.getObject(i);
					row.put(columnName, columnValue);
				}
				tablaData.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tablaData;
	}

	private List<Map<String, Object>> obtenerDatosDeCorredores() {
		List<Map<String, Object>> corredoresData = new ArrayList<>();

		String query = "SELECT c.*, p.nombre, p.apellidos FROM corredor c JOIN persona p ON c.codigo_persona = p.codigo_persona";
		try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
			int columnCount = resultSet.getMetaData().getColumnCount();
			while (resultSet.next()) {
				Map<String, Object> row = new HashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = resultSet.getMetaData().getColumnName(i);
					Object columnValue = resultSet.getObject(i);
					row.put(columnName, columnValue);
				}
				corredoresData.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return corredoresData;
	}

	private List<Map<String, Object>> calificacionesCorredo() {
		List<Map<String, Object>> calificacionesData = new ArrayList<>();

		String query = "SELECT p.nombre, p.apellidos, c.* FROM corredor c JOIN persona p ON c.codigo_persona = p.codigo_persona ORDER BY c.tiempo ASC";
		try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
			int columnCount = resultSet.getMetaData().getColumnCount();
			while (resultSet.next()) {
				Map<String, Object> row = new HashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = resultSet.getMetaData().getColumnName(i);
					Object columnValue = resultSet.getObject(i);
					row.put(columnName, columnValue);
				}
				calificacionesData.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return calificacionesData;
	}
}
