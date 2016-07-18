package org.openmrs.module.nuform.web;

import org.openmrs.util.OpenmrsUtil;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

/**
 * Original file from openmrs-module-patientimage
 * Modified by beapen on 25/05/16.
 */
public class NuformImageServlet extends HttpServlet {

    // Constants ----------------------------------------------------------------------------------
    private static final int DEFAULT_BUFFER_SIZE = 102400; // 100KB.

    // Properties ---------------------------------------------------------------------------------
    private String imagePath;

    //Separator
    private String sep = File.separator;

    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void init() throws ServletException {
        this.imagePath = OpenmrsUtil.getApplicationDataDirectory() + sep + "nuform" + sep;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Image data serialized as a String
        String requestedImage = request.getParameter("image");

        if (requestedImage == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        File image = new File(imagePath, URLDecoder.decode(requestedImage, "UTF-8"));
        if (!image.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();
        String contentType = mimetypesFileTypeMap.getContentType(image);

        /*String contentType = getServletContext().getMimeType(image.getName());
        if (contentType == null || !contentType.startsWith("image")) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }*/

        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(image.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + image.getName() + "\"");

        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            input = new BufferedInputStream(new FileInputStream(image), DEFAULT_BUFFER_SIZE);
            output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        } finally {
            close(output);
            close(input);
        }
    }
}
