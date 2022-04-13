package com.example.fastfood.controller.category;

import com.example.fastfood.entity.Category;
import com.example.fastfood.model.CategoryModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;

public class CategoryDetailServlet extends HttpServlet {
    private CategoryModel model = new CategoryModel();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        Category obj = model.findById(id);
        if (obj==null){
            resp.setStatus(404);
            resp.getWriter().println("Not found");
        }else {
            HttpSession session = req.getSession();
            HashSet<Category> recentView = (HashSet<Category>) session.getAttribute("recentView");
            if (recentView==null){
                recentView = new HashSet<>();
            }
            recentView.add(obj);
            session.setAttribute("recentView",recentView);
            req.setAttribute("obj",obj);
            req.getRequestDispatcher("/admin/category/detail.jsp").forward(req,resp);
        }
    }
}
