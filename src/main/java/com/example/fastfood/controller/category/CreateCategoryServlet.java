package com.example.fastfood.controller.category;

import com.example.fastfood.entity.Category;
import com.example.fastfood.model.CategoryModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/category/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        String categoryName = req.getParameter("categoryName");
        int status = Integer.parseInt(req.getParameter("status"));
        CategoryModel model = new CategoryModel();
        Category obj = new Category();
        obj.setCategoryName(categoryName);
        obj.setStatus(status);
        if (!obj.isvalid()) {
            req.setAttribute("errors", obj.getErrors());
            req.getRequestDispatcher("/admin/category/form.jsp").forward(req, resp);
            return;
        }
        model.save(obj);

        resp.sendRedirect("/category/list");
    }
}
