package org.example;

import java.sql.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Connection conexion = conectar();
        consulta(conexion);
        borrar(conexion);
        consulta(conexion);
        desconectar(conexion);

    }

    public static Connection conectar(){

        Connection conexion;
        String host = "jdbc:mariadb://localhost:3307/";
        String usuario = "root";
        String psw = "";
        String bd = "instituto";

        try {
            conexion = DriverManager.getConnection(host+bd,usuario,psw);
            System.out.println("Conexión realizada con éxito.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return conexion;
    }

    public static void desconectar(Connection conexion){

        try {
            conexion.close();
            System.out.println("Conexión finalizada.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static void consulta(Connection conexion){

        String query = "SELECT * FROM estudiante";

        Statement stmt;
        ResultSet resultado;

        try {
            stmt = conexion.createStatement();
            resultado = stmt.executeQuery(query);

            while (resultado.next()){
                int nia = resultado.getInt("nia");
                String nombre = resultado.getString("nombre");
                LocalDate fecha_nacimiento = resultado.getDate("fecha_nacimiento").toLocalDate();
                System.out.println("NIA: " + nia + " - Nombre : " + nombre + " - Fecha nacimiento: " + fecha_nacimiento);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static void insertar(Connection conexion){

        String query = "INSERT INTO estudiante (nia,nombre,fecha_nacimiento) VALUES ('78956412','Juan','2000-01-01')";

        Statement stmt;

        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Fila insertada.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static void modificar(Connection conexion){

        String query = "UPDATE estudiante SET nombre = 'Patri' WHERE nia = '12345678'";

        Statement stmt;

        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Fila modificada.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static void borrar (Connection conexion){

        String query = "DELETE FROM estudiante WHERE nombre = 'Patri'";

        Statement stmt;

        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Fila eliminada.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }


}