package com.example.fastfood.controller.category;

import com.example.fastfood.entity.Category;
import com.example.fastfood.model.CategoryModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditCategoryServlet extends HttpServlet {
    private CategoryModel model = new CategoryModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Category obj = model.findById(id);
        if (obj == null) {
            resp.setStatus(404);
            resp.getWriter().println("Not found");
        } else {
            req.setAttribute("obj", obj);
            req.getRequestDispatcher("/admin/category/edit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        Category obj = model.findById(id);
        if (obj == null) {
            resp.setStatus(404);
            resp.getWriter().println("Not found");
        } else {
            String CategoryName = req.getParameter("categoryName");
            int status = Integer.parseInt(req.getParameter("status"));
            obj.setId(id);
            obj.setCategoryName(CategoryName);
            obj.setStatus(status);

            model.update(id, obj);

            resp.sendRedirect("/category/list");

        }
    }
}
