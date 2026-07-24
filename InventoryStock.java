package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.ProductDAO;
import dao.CategoryDAO;
import dao.SupplierDAO;
import java.util.List;
import model.Product;
import model.Category;
import model.Supplier;
import model.User;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;
    private SupplierDAO supplierDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
        supplierDAO = new SupplierDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("id"));
            productDAO.deleteProduct(productId);
            response.sendRedirect("ProductServlet?action=list");
            
        } else if ("edit".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("id"));
            Product existingProduct = productDAO.getProductById(productId);
            
            // Fetch categories and suppliers for edit dropdowns
            List<Category> categoryList = categoryDAO.getAllCategories();
            List<Supplier> supplierList = supplierDAO.getAllSuppliers();
            
            request.setAttribute("product", existingProduct);
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("supplierList", supplierList);
            request.getRequestDispatcher("edit-product.jsp").forward(request, response);
            
        } else if ("new".equals(action)) {
    // Dedicated route to display the Add Product form with dropdown lists
    List<Category> categoryList = categoryDAO.getAllCategories();
    List<Supplier> supplierList = supplierDAO.getAllSuppliers();

    // These exact names must match what your JSP is looking for!
    request.setAttribute("categoryList", categoryList);
    request.setAttribute("supplierList", supplierList);

    request.getRequestDispatcher("add-product.jsp").forward(request, response);
} else {
            // Default action: list products with pagination
            int page = 1;
            int recordsPerPage = 10;
            
            if (request.getParameter("page") != null) {
                try {
                    page = Integer.parseInt(request.getParameter("page"));
                } catch (NumberFormatException e) {
                    page = 1;
                }
            }
            
            int offset = (page - 1) * recordsPerPage;
            
            List<Product> productList = productDAO.getProductsByPage(recordsPerPage, offset);
            int totalRecords = productDAO.getTotalProductCount();
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

            request.setAttribute("productList", productList);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages == 0 ? 1 : totalPages);

            HttpSession session = request.getSession(false);
            User user = (session != null) ? (User) session.getAttribute("user") : null;

            if (user != null && "Customer".equalsIgnoreCase(user.getRole())) {
                request.getRequestDispatcher("customer-product-list.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("product-list.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            String productName = request.getParameter("productName");
            double productPrice = Double.parseDouble(request.getParameter("productPrice"));
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            int supplierId = Integer.parseInt(request.getParameter("supplierId"));
            
            Product newProduct = new Product();
            newProduct.setProductName(productName);
            newProduct.setProductPrice(productPrice);
            newProduct.setCategoryId(categoryId);
            newProduct.setSupplierId(supplierId);
            
            boolean isInserted = productDAO.addProduct(newProduct);
            
            if (isInserted) {
                response.sendRedirect("ProductServlet?action=list");
            } else {
                request.setAttribute("errorMessage", "Failed to add product.");
                // Repopulate options so the form reloads correctly on failure
                request.setAttribute("categoryList", categoryDAO.getAllCategories());
                request.setAttribute("supplierList", supplierDAO.getAllSuppliers());
                request.getRequestDispatcher("add-product.jsp").forward(request, response);
            }
            
        } else if ("update".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            String productName = request.getParameter("productName");
            double productPrice = Double.parseDouble(request.getParameter("productPrice"));
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            int supplierId = Integer.parseInt(request.getParameter("supplierId"));

            Product product = new Product();
            product.setProductId(productId);
            product.setProductName(productName);
            product.setProductPrice(productPrice);
            product.setCategoryId(categoryId);
            product.setSupplierId(supplierId);
            
            productDAO.updateProduct(product);
            response.sendRedirect("ProductServlet?action=list");
        }
    }
}