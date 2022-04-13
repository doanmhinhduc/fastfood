package com.example.fastfood.controller.fastfood;

import com.example.fastfood.entity.fastfood;
import com.example.fastfood.model.CategoryModel;
import com.example.fastfood.model.FastFoodModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetListFoodServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryModel categoryModel = new CategoryModel();
        FastFoodModel model = new FastFoodModel();
        List<fastfood> listObj = model.findAll();
        req.setAttribute("listObj", listObj);
        req.setAttribute("listCate", categoryModel.findAll());
        req.getRequestDispatcher("/admin/food/list.jsp").forward(req, resp);


    }
}
