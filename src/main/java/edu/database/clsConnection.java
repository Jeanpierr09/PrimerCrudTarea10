package edu.database;

import com.sun.source.util.SourcePositions;

import java.sql.*;
import java.util.Scanner;
import java.util.SortedSet;

public class clsConnection {

    Scanner scanner = new Scanner(System.in);

    private Connection conn = null; //Establecemos la conexion

    private void conexion() throws SQLException {//se crea el metodo

        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud1", "root", "Pierr123"); //Aqui se establece la conexion a la base de datos
    }

    public void read() throws SQLException{

        conexion(); //Aqui establecemos conexion con la base de datos (de nuevo)

        String query = "select * from tablaprincipal"; //Aqui se crea una cadena de consulta SQL para seleccionar todos los registros de la tabla
        PreparedStatement ps = null; //Declaramos una variable de tipo PreparedStatement que utilizaremos para ejecutar la consulta
        ps = conn.prepareStatement(query); //Creamos un objeto utilizando la conexion conn y la consulta almacenada en la variable query

        ResultSet resultSet = ps.executeQuery(); // Esto recupera los datos de la base de datos y los almacena en resultSet.

        while (resultSet.next()){

            System.out.println(" id: " + resultSet.getString("id") +
                    " Nombre: " + resultSet.getString("nombre") +
                    " Apellido: " + resultSet.getString("apellido") +
                    " Fecha: " + resultSet.getString("fecha") +
                    " Sueldo: " + resultSet.getString("sueldo") +
                    " Sexo : " + resultSet.getString("sexo") +
                    " Edad : " + resultSet.getString("edad") +
                    " Longitud : " + resultSet.getString("longitud") +
                    " Latitud : " + resultSet.getString("latitud") +
                    " Comentarios: " + resultSet.getString("comentarios")
                    // esto es para obtener los valores de cada columna en la tabla "tablaprincipal". Luego, se imprimen estos valores en la consola utilizando System.out.println().

            );
        }

    }

    public void insert() throws SQLException{
        conexion(); //Aqui establecemos conexion con la base de datos (de nuevo)


        System.out.println("Ingrese su Nombre: "); //Aqui solicitamos al usuario que ingrese los datos correspondidos
        String nombre = scanner.nextLine(); //y se almacenan en la columna establecida con su nombre

        System.out.println("Ingrese su Apellido: ");
        String apellido = scanner.nextLine();

        System.out.println("Ingrese la Fecha: YYYY/MM/DD");
        String fecha = scanner.nextLine();

        System.out.println("Ingrese el Sueldo: ");
        String sueldo = scanner.nextLine();

        System.out.println("Ingrese su Sexo: (Masculino o Femenino)");
        String sexo = scanner.nextLine();

        System.out.println("Ingrese su Edad: ");
        String edad = scanner.nextLine();

        System.out.println("Ingrese la Longitud: ");
        String longitud = scanner.nextLine();

        System.out.println("Ingrese la Latitud: ");
        String latitud = scanner.nextLine();

        System.out.println("Ingrese los Comentarios: ");
        String comentarios = scanner.nextLine();


        //Aca abajo se crea una consulta de SQL para ingresar los datos en la tabla principal y se especifican las tablas y columnas con el Insert to
        // Luego, se utilizan marcadores de posición ? en la consulta SQL para los valores que se insertarán.
        String query = "Insert into tablaprincipal (nombre, apellido, fecha, sueldo, sexo, edad, longitud, latitud, comentarios) values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null; //Creamos un objeto de tipo PreparedStatement llamado ps para ejecutar la consulta de SQL
        ps = conn.prepareStatement(query);

        //Luego, se establecen los valores de los marcadores de posición utilizando los métodos setString() del objeto ps.
        //Cada setString() toma dos argumentos: el índice del marcador de posición (comenzando desde 1) y el valor que se debe insertar en ese lugar.
        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, fecha);
        ps.setString(4, sueldo);
        ps.setString(5, sexo);
        ps.setString(6, edad);
        ps.setString(7, longitud);
        ps.setString(8, latitud);
        ps.setString(9, comentarios);

        ps.executeUpdate();
// el executeUpdate para ejecutar la consulta SQL y agregar un nuevo registro a la tabla "tablaprincipal" con los valores ingresados por el usuario.
    }

    public void delete() throws SQLException{
        conexion();

        System.out.println("SELECCIONE QUE ID DESEA ELIMINAR");
        int num_id = scanner.nextInt();

        String query = "Delete from tablaprincipal Where id = ?"; //Aqui se crea una consulta de MySQL para eliminar un registro de la tabla
        //en funcion del valor id que se ingreso por el usuario
        PreparedStatement ps = null;
        ps = conn.prepareStatement(query);//aqui se crea el objetivo ps para ejecutar la consulta
        ps.setInt(1, num_id);//Se establece el valor de la posicion marcado con ? en la consulta, donde numid es el valor que se usa para eliminar
        //y el 1 es el indice del marcador de posicion.
        ps.executeUpdate(); // y esto  para ejecutar la consulta SQL y eliminar el registro de la tabla "tablaprincipal"
        // que coincide con el valor de "id" ingresado por el usuario.

    }

    public void update()throws SQLException{
        conexion();

        System.out.println("\nSELECCIONE EL ID");
        int num_id = scanner.nextInt();
        //aqui se le pide al usuario el numero de ID que tiene el usuario para poder seleccionarlo y editarlo

        System.out.println("SELECCIONE EL NUMERO DE DATO QUE DESEA CAMBIAR");
        System.out.println("1. Nombre");
        System.out.println("2. Apellido");
        System.out.println("3. Fecha");
        System.out.println("4. Sueldo");
        System.out.println("5. Sexo"); //Este es el numero que se coloca con respecto al dato que se quiere actualizar
        System.out.println("6. Edad");
        System.out.println("7. Longitud");
        System.out.println("8. Latitud");
        System.out.println("9. Comentarios");
        int num = scanner.nextInt();
        scanner.nextLine();

        String query = "";
        String up = "";

        switch(num){ //Utilizamos un switch para saber que caso se eligio y poder modificar esa columna
            case 1:
                query = "Update tablaprincipal Set nombre = ? where id = ?";
                System.out.println("Escriba el nuevo nombre:");
                up = scanner.nextLine();
                break;

            case 2:
                query = "Update tablaprincipal Set apellido = ? where id = ?";
                System.out.println("Escriba el nuevo Apellido:");
                up = scanner.nextLine();
                break;

            case 3:
                query = "Update tablaprincipal Set fecha = ? where id = ?";
                System.out.println("Escriba la nueva Fecha:");
                up = scanner.nextLine();
                break;

            case 4:
                query = "Update tablaprincipal Set sueldo = ? where id = ?";
                System.out.println("Escriba el nuevo Sueldo:");
                up = scanner.nextLine();
                break;

            case 5:
                query = "Update tablaprincipal Set sexo = ? where id = ?";
                System.out.println("Escriba el nuevo Sexo:");
                up = scanner.nextLine();
                break;

            case 6:
                query = "Update tablaprincipal Set edad = ? where id = ?";
                System.out.println("Escriba la nueva Edad:");
                up = scanner.nextLine();
                break;

            case 7:
                query = "Update tablaprincipal Set longitud = ? where id = ?";
                System.out.println("Escriba la nueva Longitud:");
                up = scanner.nextLine();
                break;

            case 8:
                query = "Update tablaprincpal Set latitud = ? where id = ?";
                System.out.println("Escriba la nueva Latitud:");
                up = scanner.nextLine();
                break;

            case 9:
                query = "Update tablaprincipal Set cometarios = ? where id = ?";
                System.out.println("Escriba el nuevo Comentario:");
                up = scanner.nextLine();
                break;
        }

        PreparedStatement ps = null;
        ps = conn.prepareStatement(query);
        ps.setString(1, up);
        ps.setInt(2, num_id);
        ps.executeUpdate();

        //Se crea un objeto PreparedStatement llamado ps para ejecutar la consulta SQL. Luego, se establece el
        // valor del marcador de posición ? en la consulta utilizando ps.setString(1, up) para el valor actualizado
        // (up contiene el nuevo valor ingresado por el usuario) y ps.setInt(2, num_id) para el ID del registro que se
        // debe actualizar (num_id contiene el ID ingresado por el usuario). Finalmente, se utiliza ps.executeUpdate()
        // para ejecutar la consulta SQL y realizar la actualización en la base de datos.
    }

}
