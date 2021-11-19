package fileuploadtest;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStream;

@WebServlet(name = "FileUploadServlet", urlPatterns = { "/upload" })

@MultipartConfig(
	fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
	maxFileSize = 1024 * 1024 * 10,      // 10 MB
	maxRequestSize = 1024 * 1024 * 100   // 100 MB
)

public class FileUploadServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		
		StringBuilder fileContent = new StringBuilder("");
		
		
		try {
			for(Part part : request.getParts()) {
				String name = part.getName();
				printWriter.write("Name: " + name + "\n");
				InputStream input = part.getInputStream();
				
				int c = input.read();
				while(c != -1) {
					fileContent.append((char)c);
					c = input.read();
				}
				
				printWriter.write(fileContent.toString());
		}
			
		}
		finally {
			printWriter.close();
		}
	}
}
