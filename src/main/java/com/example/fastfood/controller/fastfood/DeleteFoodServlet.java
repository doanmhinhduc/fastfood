package com.example.fastfood.controller.fastfood;

import com.example.fastfood.entity.fastfood;
import com.example.fastfood.model.FastFoodModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteFoodServlet extends HttpServlet {
    private FastFoodModel model = new FastFoodModel();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        fastfood obj = model.findById(id);
        if (obj == null) {
            resp.setStatus(404);
            resp.getWriter().println("Not found");
        } else {
            model.deleteVip(id);
            resp.sendRedirect("/food/list");
        }
    }
}
