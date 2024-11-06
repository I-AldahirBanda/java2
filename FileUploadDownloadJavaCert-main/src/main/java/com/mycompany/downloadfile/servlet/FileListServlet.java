package com.mycompany.downloadfile.servlet;

import java.io.File;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/list-files")
public class FileListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Especifica una ruta absoluta para un directorio persistente
    private static final String UPLOAD_DIR = "C:/wildfly-uploads";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            response.getWriter().println("No se ha encontrado el directorio de subida.");
            return;
        }

        File[] files = uploadDir.listFiles();
        if (files == null || files.length == 0) {
            response.getWriter().println("No hay archivos subidos.");
            return;
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        
        response.getWriter().println("<html><head><title>Lista de Archivos</title></head><body>");
        response.getWriter().println("<h1>Archivos Subidos</h1>");
        response.getWriter().println("<ul>");
        
        for (File file : files) {
            response.getWriter().println("<li><a href=\"download?file=" + file.getName() + "\" target=\"_blank\">" + file.getName() + "</a></li>");
        }
        
        response.getWriter().println("</ul>");
        response.getWriter().println("</body></html>");
    }
}
