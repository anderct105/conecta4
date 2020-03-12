package packDao;

public class pruebaBD {
    public static void main(String[] args){
        ConnectionManager conexion=new ConnectionManager();
        conexion.execSQL("insert into Partida (nombre, tiempo) values ('prueba',3000)");
    }



}
