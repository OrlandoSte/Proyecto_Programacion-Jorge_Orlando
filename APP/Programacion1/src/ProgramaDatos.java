import com.google.gson.Gson;

public class ProgramaDatos {

	public static void main(String[] args) {
		Datos datos = new Datos();
		
		
		
		
		
		datos.setCODIGO(1);
		/*datos.setDNI(var2);
		datos.setNOMBRE(var3);
		datos.setAPELLIDOS(var4);
		datos.setSEXO(var5);
		datos.setFECHANAC(var6);
		datos.setDIRECCION(var7);
		datos.setCODIGOPOSTAL(var8);
		datos.setLOCALIDAD(var9);
		datos.setPROVINCIA(var10);
		datos.setTELEFONO(var11);
		datos.setClub(var111);
		datos.setDorsal(var112);
		datos.setTiempo(var113);
		datos.setCodigoCarrera(var114);*/
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(datos);
		System.out.println(jsonString);

	}

}
