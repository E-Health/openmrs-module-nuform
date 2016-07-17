package org.openmrs.module.nuform.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.openmrs.module.nuform.NuformConstants;
import org.openmrs.util.OpenmrsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Servlet implementation class DermUploadServlet
 */
public class NuformUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    //private static final String DATA_DIRECTORY = "data";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Separator
        String sep = File.separator;


        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        // Ref Stackoverflow 4112686
        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8");


        if (!isMultipart) {
            response.getWriter().write(NuformConstants.ERROR);
            return;
        }

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Sets the size threshold beyond which files are written directly to
        // disk.
        factory.setSizeThreshold(MAX_MEMORY_SIZE);

        // Sets the directory used to temporarily store files that are larger
        // than the configured size threshold. We use temporary directory for
        // java
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // constructs the folder where uploaded file will be stored
        String uploadFolder = OpenmrsUtil.getApplicationDataDirectory() +
                sep + "nuform" + sep;

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(MAX_REQUEST_SIZE);

        try {
            // Parse the request
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            // Random prefix
            Random random = new Random();
            String prefix = "nuform_" + Integer.toString(random.nextInt(999)) + "_";
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadFolder + File.separator + prefix + fileName;
                    File uploadedFile = new File(filePath);
                    System.out.println(filePath);
                    // saves the file to upload directory
                    item.write(uploadedFile);
                }
            }

            // displays referrer page after upload finished
            // Ref Stackoverflow 4112686
            response.getWriter().write(NuformConstants.SUCCESS);
            //getServletContext().getRequestDispatcher("/done.jsp").forward(request, response);

        } catch (FileUploadException ex) {
            response.getWriter().write(NuformConstants.ERROR);
            throw new ServletException(ex);
        } catch (Exception ex) {
            response.getWriter().write(NuformConstants.ERROR);
            throw new ServletException(ex);
        }

    }

}