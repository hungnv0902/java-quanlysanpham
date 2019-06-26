package controller;

import model.Product;
import service.ProductService;
import service.ProductServiceImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final String SAVE_DIRECTORY = "uploadDir";

        private ProductService productService = new ProductServiceImpl();

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }

            switch (action) {
                case "create":
                    createProduct(request, response);
                    break;

                case "edit":
                    updateProduct(request, response);
                    break;

                case "delete":
                    deleteProduct(request, response);
                    break;

                case "seach":
                    seachProduct(request, response);
                    break;

                default:
                    break;
            }
        }

        private String extractFileName(Part part) {// form-data; name="file"; filename="C:\file1.zip"
            // form-data; name="file"; filename="C:\Note\file2.zip"
            String contentDisp = part.getHeader("content-disposition");
            String[] items = contentDisp.split(";");
            for (String s : items) {
                if (s.trim().startsWith("filename")) {
                    // C:\file1.zip
                    // C:\Note\file2.zip
                    String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                    clientFileName = clientFileName.replace("\\", "/");
                    int i = clientFileName.lastIndexOf('/');
                    // file1.zip
                    // file2.zip
                    return clientFileName.substring(i + 1);
                }
            }
            return null;
        }

        private void seachProduct(HttpServletRequest request, HttpServletResponse response) {
            String name = request.getParameter("nameSeach");
            Product product = this.productService.findByName(name);
            RequestDispatcher dispatcher;
            if (product == null) {
                dispatcher = request.getRequestDispatcher("error-404.jsp");
            } else {
                request.setAttribute("product", product);
                dispatcher = request.getRequestDispatcher("product/viewSeach.jsp");
            }
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = this.productService.findById(id);
            RequestDispatcher dispatcher;
            if (product == null) {
                dispatcher = request.getRequestDispatcher("error-404.jsp");
            } else {
                this.productService.remove(id);
                try {
                    response.sendRedirect("/product");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            String fileName = "";
            String avatar = "";
            try {

                // Đường dẫn tuyệt đối tới thư mục gốc của web app.
                String appPath = request.getServletContext().getRealPath("");
                appPath = appPath.replace('\\', '/');


                // Thư mục để save file tải lên.
                String fullSavePath = null;
                if (appPath.endsWith("/")) {
                    fullSavePath = appPath + SAVE_DIRECTORY;
                } else {
                    fullSavePath = appPath + "/" + SAVE_DIRECTORY;
                }




                // Danh mục các phần đã upload lên (Có thể là nhiều file).
                for (Part part : request.getParts()) {
                    fileName = extractFileName(part);
                    if (fileName != null && fileName.length() > 0) {
                        String filePath = fullSavePath + File.separator + fileName;
                        System.out.println("Write attachment to file: " + filePath);

                        // Ghi vào file.
                        part.write(filePath);
                        avatar = fileName;
                        break;
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error: " + e.getMessage());

            }

            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String descreption = request.getParameter("descreption");
            float price = Float.parseFloat(request.getParameter("price"));
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            Product product = this.productService.findById(id);
            RequestDispatcher dispatcher;
            if (product == null) {
                dispatcher = request.getRequestDispatcher("error-404.jsp");
            } else {
                product.setName(name);
                product.setDescreption(descreption);
                product.setPrice(price);
                product.setStatus(status);
                product.setAvatar(avatar);
                this.productService.update(id, product);
                request.setAttribute("customer", product);
                request.setAttribute("message", "product information was updated");
                dispatcher = request.getRequestDispatcher("product/edit.jsp");
            }
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void createProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            String fileName = "";
            String avatar = "";
            try {

                // Đường dẫn tuyệt đối tới thư mục gốc của web app.
                String appPath = request.getServletContext().getRealPath("");
                appPath = appPath.replace('\\', '/');


                // Thư mục để save file tải lên.
                String fullSavePath = null;
                if (appPath.endsWith("/")) {
                    fullSavePath = appPath + SAVE_DIRECTORY;
                } else {
                    fullSavePath = appPath + "/" + SAVE_DIRECTORY;
                }


                // Tạo thư mục nếu nó không tồn tại.
                File fileSaveDir = new File(fullSavePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdir();
                }

                // Danh mục các phần đã upload lên (Có thể là nhiều file).
                for (Part part : request.getParts()) {
                    fileName = extractFileName(part);
                    if (fileName != null && fileName.length() > 0) {
                        String filePath = fullSavePath + File.separator + fileName;
                        System.out.println("Write attachment to file: " + filePath);

                        // Ghi vào file.
                        part.write(filePath);
                        avatar = fileName;
                        break;
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error: " + e.getMessage());

            }

            String name = request.getParameter("name");
            String descreption = request.getParameter("descreption");
            float price = Float.parseFloat(request.getParameter("price"));
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            int id = (int) (Math.random() * 10000);

            Product product = new Product(id, name, descreption, price, status, avatar);
            this.productService.save(product);
            RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
            request.setAttribute("message", "New product was created");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            switch (action) {
                case "create":
                    showCreateForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    showDeleteForm(request, response);
                    break;
                case "view":
                    viewProduct(request, response);
                    break;
                default:
                    listProducts(request, response);
                    break;
            }
        }


        private void viewProduct(HttpServletRequest request, HttpServletResponse response) {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = this.productService.findById(id);
            RequestDispatcher dispatcher;
            if (product == null) {
                dispatcher = request.getRequestDispatcher("error-404.jsp");
            } else {
                request.setAttribute("product", product);
                dispatcher = request.getRequestDispatcher("product/view.jsp");
            }
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = this.productService.findById(id);
            RequestDispatcher dispatcher;
            if (product == null) {
                dispatcher = request.getRequestDispatcher("error-404.jsp");
            } else {
                request.setAttribute("product", product);
                dispatcher = request.getRequestDispatcher("product/delete.jsp");
            }
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = this.productService.findById(id);
            RequestDispatcher dispatcher;
            if (product == null) {
                dispatcher = request.getRequestDispatcher("error-404.jsp");
            } else {
                request.setAttribute("product", product);
                dispatcher = request.getRequestDispatcher("product/edit.jsp");
            }
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void listProducts(HttpServletRequest request, HttpServletResponse response) {
            List<Product> product = this.productService.findAll();
            request.setAttribute("products", product);

            RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
}
