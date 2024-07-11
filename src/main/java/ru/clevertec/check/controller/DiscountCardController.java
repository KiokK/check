package ru.clevertec.check.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.check.dto.request.DiscountCardModifDto;
import ru.clevertec.check.exception.EntityNotFoundException;
import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.service.DiscountCardService;
import ru.clevertec.check.service.impl.DiscountCardServiceImpl;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/discountcards")
public class DiscountCardController extends HttpServlet {

    private static final String ID = "id";

    private DiscountCardService discountCardService = new DiscountCardServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter(ID));
            response.setStatus(200);
            response.setContentType("application/json");
            response.getWriter().print(new Gson().toJson(discountCardService.findById(id)));

        } catch (NumberFormatException e) {
            response.setStatus(400);
        } catch (EntityNotFoundException e) {
            response.setStatus(404);
        } catch (InternalServerException e) {
            response.setStatus(500);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter(ID));
            String args = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            DiscountCardModifDto dto = new Gson().fromJson(args, DiscountCardModifDto.class);
            response.setStatus(201);
            response.setContentType("application/json");
            response.getWriter().print(new Gson().toJson(discountCardService.update(id, dto)));

        } catch (NumberFormatException e) {
            response.setStatus(400);
        } catch (InternalServerException e) {
            response.setStatus(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String args = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        try {
            DiscountCardModifDto dto = new Gson().fromJson(args, DiscountCardModifDto.class);
            response.setStatus(201);
            response.setContentType("application/json");
            response.getWriter().print(new Gson().toJson(discountCardService.create(dto)));

        } catch (NumberFormatException e) {
            response.setStatus(400);
        } catch (InternalServerException e) {
            response.setStatus(500);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter(ID));
            if (discountCardService.deleteById(id)) {
                response.setStatus(204);
            } else {
                response.setStatus(400);
            }

        } catch (NumberFormatException e) {
            response.setStatus(400);
        } catch (InternalServerException e) {
            response.setStatus(500);
        }
    }
}
