package com.example.fastfood.controller.fastfood;

import com.example.fastfood.entity.fastfood;
import com.example.fastfood.model.FastFoodModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;

public class foodDetailServlet extends HttpServlet {
    private FastFoodModel model = new FastFoodModel();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        fastfood obj = model.findById(id);
        if (obj==null){
            resp.setStatus(404);
            resp.getWriter().println("Not found");
        }else {
            HttpSession session = req.getSession();
            HashSet<fastfood> recentView = (HashSet<fastfood>) session.getAttribute("recentView");
            if (recentView==null){
                recentView = new HashSet<>();
            }
            recentView.add(obj);
            session.setAttribute("recentView",recentView);
            req.setAttribute("obj",obj);
            req.getRequestDispatcher("/admin/food/detail.jsp").forward(req,resp);
        }
    }
}
