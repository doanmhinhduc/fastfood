package com.example.fastfood.controller.fastfood;

import com.example.fastfood.entity.fastfood;
import com.example.fastfood.model.CategoryModel;
import com.example.fastfood.model.FastFoodModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateFoodServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryModel model = new CategoryModel();
        req.setAttribute("listCate", model.findAll());
        req.getRequestDispatcher("/admin/food/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
//        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        String description = req.getParameter("description");
        String thumbnail = req.getParameter("thumbnail");
        double price = Double.parseDouble(req.getParameter("price"));
//        Timestamp createdAt = Timestamp.valueOf(("createdAt"));
//        Timestamp updatedAt = Timestamp.valueOf(("updatedAt"));
        int status = Integer.parseInt(req.getParameter("status"));
        FastFoodModel model = new FastFoodModel();
        fastfood obj = new fastfood(name,categoryId,description,thumbnail,price,null,null,status);
        if (!obj.isvalid()) {
            req.setAttribute("errors", obj.getErrors());
            req.getRequestDispatcher("/admin/food/form.jsp").forward(req, resp);
            return;
        }
        model.save(obj);

        resp.sendRedirect("/food/list");
    }
}
