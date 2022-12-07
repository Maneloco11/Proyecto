/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

/**
 *
 * @author solor
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class Resultados extends JFrame implements ActionListener
 
{
    PanelFondo contenedor;
    JTable tabla;
    JScrollPane barras;
    String titulos[]={"Nombre","Apellido Paterno","Apellido Materno"};
    String datos[][];
    conexion con;
    ResultSet res;
    int i=0;
    JButton regresar;
    
    public Resultados() 
    {
           
        contenedor=new PanelFondo("inicio.jpg");
            contenedor.setLayout(null);
            
            construir_tabla();
            setTitle("LISTA DE RESPUESTAS");
            
            contenedor.add(barras);
            add(contenedor);
            
            regresar=new JButton ("Regresar");
            regresar.setBounds(300, 350, 100, 20);
            regresar.addActionListener(this);
            
            contenedor.add(regresar);
            
            setSize(700,500);
            setVisible(true);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
     
    void construir_tabla() {
        try {
            i = 0;
            con = new conexion();
            res = conexion.consultar("SELECT alumnos.nombre, alumnos.apellido_parterno, "
                    + "alumnos.apellido_materno FROM alumnos;");
            System.out.println("Filas: " + conexion.filas);
            datos = new String[conexion.filas][3];
            /*for(int i=0;i<con.filas;i++){
               
            }*/

            while (res.next()) {
                datos[i][0] = res.getString("nombre");
                datos[i][1] = res.getString("apellido_parterno");
                datos[i][2] = res.getString("apellido_materno");
                //datos[i][3] = res.getString("respuestas");
                i++;
            }
            tabla = new JTable(datos, titulos);
            barras = new JScrollPane(tabla);
            barras.setBounds(50, 10, 580, 100);
            
            
            
        } 
       
            
        catch (Exception e) 
        {
            System.err.println("error" + e);

        } 
        finally 
        {
            con.cerrar();
        }
    }
    
    public static void main(String[] args) {
        Resultados lol=new Resultados();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==regresar)
        {
            this.setVisible(false);
            new Orientador().setVisible(true);
        }
    }
}   

