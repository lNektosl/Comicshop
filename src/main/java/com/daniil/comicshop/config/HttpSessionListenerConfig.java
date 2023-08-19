package com.daniil.comicshop.config;

import com.daniil.comicshop.entity.Client;
import com.daniil.comicshop.entity.Order;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;


@WebListener
public class HttpSessionListenerConfig implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        if(se.getSession().getAttribute("order") == null){
            se.getSession().setAttribute("order",new Order());
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        if (session.getAttribute("order") != null && session.getAttribute("user")!=null){
            Order order = (Order) session.getAttribute("order");
            order.setClient((Client) session.getAttribute("user"));
        }
    }
}
