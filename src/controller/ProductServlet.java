package controller;

import model.Product;
import service.ProductService;
import service.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
    public class ProductServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null) {
            action = "";
        }

        switch (action) {
            case "create" :
                createProduct(request, response);
                break;

            case "edit" :
                updateProduct(request, response);
                break;

            case "delete" :
                deleteProduct(request, response);
                break;

            case "seach" :
                seachProduct(request, response);
                break;

            default:
                break;
        }
    }

    private void seachProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("nameSeach");
        Product product = this.productService.findByName(name);
        RequestDispatcher dispatcher;
        if(product == null){
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
        if(product == null){
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
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String descreption = request.getParameter("descreption");
        float price = Float.parseFloat(request.getParameter("price"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        String avatar = request.getParameter("avatar");
        Product product = this.productService.findById(id);
        RequestDispatcher dispatcher;
        if(product == null){
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
        String name = request.getParameter("name");
        String descreption = request.getParameter("descreption");
        float price = Float.parseFloat(request.getParameter("price"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        String avatar = request.getParameter("avatar");
        int id = (int)(Math.random() * 10000);

        Product product = new Product(id, name,descreption, price,status,avatar);
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
        if(action == null){
            action = "";
        }
        switch (action){
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
        if(product == null){
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
        if(product == null){
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
        if(product == null){
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