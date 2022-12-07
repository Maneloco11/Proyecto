/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;


/**
import java.awt.event.ActionListener;
import java.sql.ResultSet;
 *
 * @author solor
 */
public class Alumnos extends JFrame implements ActionListener
 
{
    PanelFondo contenedor;
    JTable tabla;
    JScrollPane barras;
    String titulos[]={"Nombre","Apellido Paterno","Apellido Materno","Usuario"};
    String datos[][];
    conexion c;
    ResultSet res;
    int i=0;
    JButton regresar;
    
    public Alumnos() 
    {
            contenedor=new PanelFondo("inicio.jpg");
            contenedor.setLayout(null);
            
            construir_tabla();
            setTitle("LISTA DE ALUMNOS");
            
            
            regresar=new JButton ("Regresar");
            regresar.addActionListener(this);
            regresar.setBounds(300, 350, 100, 20);
            
            contenedor.add(barras);
            contenedor.add(regresar);
            add(contenedor);
            
            setSize(700,500);
            setVisible(true);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
     
    void construir_tabla() 
    {
        try {
            i = 0;
            c = new conexion();
            res = c.consultar("SELECT nombre,apellido_parterno, apellido_materno, usuario FROM alumnos");
            System.out.println("Filas: " + c.filas);
            datos = new String[c.filas][4];
            /*for(int i=0;i<con.filas;i++){
               
            }*/

            while (res.next()) {
                datos[i][0] = res.getString("nombre");
                datos[i][1] = res.getString("apellido_parterno");
                datos[i][2] = res.getString("apellido_materno");
                datos[i][3] = res.getString("usuario");
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
            c.cerrar();
        }
    }

    public static void main(String[] args) 
    {
        Alumnos lol=new Alumnos();
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
