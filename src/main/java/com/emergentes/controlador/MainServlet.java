
package com.emergentes.controlador;

import com.emergentes.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;


@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {


  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            String op = request.getParameter("op");
            Persona objper = new Persona();
            int id, pos;
            
            HttpSession ses = request.getSession();
            ArrayList<Persona> lista = (ArrayList<Persona>) ses.getAttribute("listaper");
           
            switch(op) {
                case "nuevo":
                    //envair un objeto vacio para ingresar datos
                    request.setAttribute("miobjper", objper);
                    request.getRequestDispatcher("editar.jsp").forward(request, response);
                    break;
                case "editar":
                    //modifiacr obteniendo el Id
                    id = Integer.parseInt(request.getParameter("id"));
                    pos = buscarPorIndice(request, id);
                    //ponerle valorea
                    objper = lista.get(pos);
                    //para rellenar
                    request.setAttribute("miobjper", objper);
                    request.getRequestDispatcher("editar.jsp").forward(request, response);
                    break;
                case "eliminar":
                    //Eliminar obteniendo el id de la persona
                    id = Integer.parseInt(request.getParameter("id"));
                    //buscamosn la posisciom
                    pos = buscarPorIndice(request, id);
                    
                    if(pos >= 0){
                        lista.remove(pos);
                    }
                    request.setAttribute("listaper", lista);
                    response.sendRedirect("index.jsp");
                    break;
                    default:    
                
            }
    }

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        HttpSession ses = request.getSession();
        ArrayList<Persona> lista = (ArrayList<Persona>) ses.getAttribute("listaper");
        Persona objper = new Persona();
        objper.setId(id);
        objper.setNombres(request.getParameter("nombres"));
        objper.setApellidos(request.getParameter("apellidos"));
        objper.setEdad(Integer.parseInt(request.getParameter("edad")));
        objper.setTareas(request.getParameter("tareas"));
        if(id == 0){
            //nuevo registro
            int idNuevo = obtenerId(request);
            objper.setId(idNuevo);
            lista.add(objper);
        }else{
            //posiscion
            int pos = buscarPorIndice(request, id);
            lista.set(pos, objper);
        }
        request.setAttribute("listaper", lista);
        response.sendRedirect("index.jsp");
    }

    private int buscarPorIndice(HttpServletRequest request, int id) {
        HttpSession ses = request.getSession();
        ArrayList<Persona> lista = (ArrayList<Persona>) ses.getAttribute("listaper");
        
        int pos = -1;
        
        if(lista != null){
            for (Persona ele : lista) {
                ++pos;
                if (ele.getId() == id) {
                    break;
                }
            }
        }
        return pos;
    }

    private int obtenerId(HttpServletRequest request) {
        HttpSession ses = request.getSession();
        ArrayList<Persona> lista = (ArrayList<Persona>) ses.getAttribute("listaper");
        
        //allamos en la lista
        int idn = 0;
        for (Persona ele : lista) {
            idn = ele.getId();
        }
        return idn + 1;
    }

}
