package ru.clevertec.check.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.check.dto.request.PurchaseDto;
import ru.clevertec.check.dto.request.PurchaseServletDto;
import ru.clevertec.check.dto.response.CheckDto;
import ru.clevertec.check.exception.BadRequestException;
import ru.clevertec.check.exception.EntityNotFoundException;
import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.exception.NotEnoughMoneyException;
import ru.clevertec.check.factory.CheckFactory;
import ru.clevertec.check.mapper.PurchaseDtoMapper;
import ru.clevertec.check.util.printer.factory.CsvCheckPrinter;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/")
public class CheckController extends HttpServlet {

    private CsvCheckPrinter csvCheckPrinter = new CsvCheckPrinter();
    private CheckFactory checkFactory = new CheckFactory();
    private PurchaseDtoMapper purchaseDtoMapper = new PurchaseDtoMapper();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String args = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        try {
            PurchaseServletDto req = new Gson().fromJson(args, PurchaseServletDto.class);
            PurchaseDto dto = purchaseDtoMapper.toPurchaseDto(req);
            CheckDto checkDto = checkFactory.createCheck(dto);

            response.setStatus(200);
            response.setContentType("text/html");
            response.getWriter().print(csvCheckPrinter.printString(checkDto));

        } catch (NotEnoughMoneyException | BadRequestException e) {
            response.setStatus(400);
        } catch (EntityNotFoundException e) {
            response.setStatus(404);
        } catch (InternalServerException e) {
            response.setStatus(500);
        }
    }
}
