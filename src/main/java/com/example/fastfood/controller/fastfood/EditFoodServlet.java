package com.example.fastfood.controller.fastfood;

import com.example.fastfood.entity.fastfood;
import com.example.fastfood.model.FastFoodModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditFoodServlet extends HttpServlet {
    private FastFoodModel model = new FastFoodModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        fastfood obj = model.findById(id);
        if (obj == null) {
            resp.setStatus(404);
            resp.getWriter().println("Not found");
        } else {
            req.setAttribute("obj", obj);
            req.getRequestDispatcher("/admin/food/edit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        fastfood obj = model.findById(id);
        if (obj == null) {
            resp.setStatus(404);
            resp.getWriter().println("Not found");
        } else {
            String name = req.getParameter("name");
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            String description = req.getParameter("description");
            String thumbnail = req.getParameter("thumbnail");
            double price = Double.parseDouble(req.getParameter("price"));
//            Timestamp createdAt = Timestamp.valueOf(("createdAt"));
//            Timestamp updatedAt = Timestamp.valueOf(("updatedAt"));
            int status = Integer.parseInt(req.getParameter("status"));
            obj.setName(name);
            obj.setCategoryId(categoryId);
            obj.setDescription(description);
            obj.setThumbnail(thumbnail);
            obj.setPrice(price);
            obj.setStatus(status);
            obj.setUpdatedAt(null);

            model.update(id, obj);

            resp.sendRedirect("/food/list");

        }
    }
}
