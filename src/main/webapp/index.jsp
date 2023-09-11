<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.Persona"%>
<%
    if(session.getAttribute("listaper") == null){
        ArrayList<Persona> lisaux = new ArrayList<Persona>();
        session.setAttribute("listaper", lisaux);
    }
    ArrayList<Persona> lista = (ArrayList<Persona>) session.getAttribute("listaper");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de registros</h1>
        <p>Nombre: Sadam Jose Quispe Chino</p>
        <a href="MainServlet?op=nuevo">Nuevo registro</a>
        <table border='1'>
            <tr>
                <th>Id</th>
                <th>Nombres</th>
                <th>Apellidos</th>
                <th>Edad</th>
                <th>Tareas</th>
                <th>Completado</th>
                <th></th>
                <th></th>
            </tr>
            <%
                if (lista != null) {
                        for (Persona item : lista) {
                               
            %>
            <tr>
                <td><%= item.getId() %></td>
                <td><%= item.getNombres() %></td>
                <td><%= item.getApellidos() %></td>
                <td><%= item.getEdad() %></td>
                <td><%= item.getTareas() %></td>
                <td>
                    <input type="checkbox" checked="checked">
                </td>
                <td>
                    <a href="MainServlet?op=editar&id=<%= item.getId() %>">Editar</a>
                </td>
                <td>
                    <a href="MainServlet?op=eliminar&id=<%= item.getId() %>"
                       onclick="return(confirm('Esta seguro que quiere eliminar'))"
                       >Eliminar</a>
                </td>
            </tr>
            <%
                  }
                } 
            %>
        </table>
    </body>
</html>
