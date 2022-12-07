
package proyecto;

/**
 *
 * @author Efren Barcenas
 */
import java.sql.*;

public class conexion {
    static String bd="proyecto";
    static String password;
    static String url="jdbc:mysql://localhost/"+bd;
    static String loing ="root";
    static boolean conectado;
    static Statement instruccion=null;
    static ResultSet resultado=null;
    static int filas=0;
    static String controlador="com.mysql.jdbc.Driver";
    static Connection conn;
    
    public conexion(){
        conn=null;
        try {
            Class.forName(controlador).newInstance();
            conn=DriverManager.getConnection(url,loing,"");
            instruccion=conn.createStatement();
            
            if(conn!=null){
                System.out.println("conexion a base de datos "+url+" ....ok");
                conectado=true;
            }
        } 
        catch (SQLException ex) {
            System.out.println("No ha sido posible conectarse a la base de datos");
            conectado=false;
        }
        catch (ClassNotFoundException es) {
             System.out.println(es);
             conectado=false;
        }
         catch (Exception e) {
             conectado=false;
        }
    }
    public void proceso_tabla(String sentencia)throws Exception{
        instruccion.executeUpdate(sentencia);
    }
    public static ResultSet consultar(String sentenciaSQL)
    {
        resultado=null;
        filas=0;
        ResultSet res=null;
        try
        {
            res=instruccion.executeQuery(sentenciaSQL);
            while(res.next())
              filas++;

            res=instruccion.executeQuery(sentenciaSQL);
            return res;
        }
        catch(SQLException m)
        {
            System.out.println("Error en la consulta"+m.getMessage());
        }
        return res;
    }
    public static int obtenerMaximo(){
        return filas;
    }
    
    public void cerrar(){
        try 
        {
            conn.close();
        } catch (SQLException e) 
        {
            System.err.println("Error al cerrar la sesion");
        }
    }
    public static void main(String args [])
    {
        conexion test=new conexion();
    }    
}
