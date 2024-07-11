package ru.clevertec.check.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.check.exception.EntityNotFoundException;
import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.repository.ProductRepository;
import ru.clevertec.check.service.ProductService;
import ru.clevertec.check.service.impl.ProductServiceImpl;

import java.io.IOException;

@WebServlet("/products")
public class ProductController extends HttpServlet {

    private ProductService productService = new ProductServiceImpl(new ProductRepository());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            response.setStatus(200);
            response.setContentType("application/json");
            response.getWriter().print(new Gson().toJson(productService.findById(id)));

        } catch (NumberFormatException e) {
            response.setStatus(400);
        } catch (EntityNotFoundException e) {
            response.setStatus(404);
        } catch (InternalServerException e) {
            response.setStatus(500);
        }
    }
}