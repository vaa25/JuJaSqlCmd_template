package ua.com.juja.sqlcmd.servlet;

import com.google.gson.Gson;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.model.JDBCDatabaseManager;
import ua.com.juja.sqlcmd.service.Service;
import ua.com.juja.sqlcmd.service.ServiceImpl;
import ua.com.juja.sqlcmd.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by vaa25 on 29.11.2015.
 */
public class SqlCmdServlet extends HttpServlet {

    private Service service;
    private DatabaseManager manager;

    @Override
    public void init() throws ServletException {
        manager = new JDBCDatabaseManager();
        service = new ServiceImpl(manager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);

        if ("/".equals(action) || "/menu".equals(action)) {
            req.setAttribute("commands", service.commandList());
            req.getRequestDispatcher("menu.jsp").forward(req, resp);
        } else if ("/help".equals(action)) {
            req.getRequestDispatcher("help.jsp").forward(req, resp);
        } else if ("/connect".equals(action)) {
            req.getRequestDispatcher("connect.jsp").forward(req, resp);
        } else if ("/clear".equals(action)) {
            Util.checkConnection(manager, "clear");
            req.getRequestDispatcher("clear.jsp").forward(req, resp);
        } else if ("/tables".equals(action)) {
            Util.checkConnection(manager, "tables");
            req.setAttribute("tables", service.tables());
            req.getRequestDispatcher("tables.jsp").forward(req, resp);
        } else if ("/find".equals(action)) {
            Util.checkConnection(manager, "find");
            req.getRequestDispatcher("find.jsp").forward(req, resp);
        } else if ("/create".equals(action)) {
            Util.checkConnection(manager, "create");
            req.getRequestDispatcher("create.jsp").forward(req, resp);
        }

    }

    private String getAction(HttpServletRequest req) {
        return req.getRequestURI().substring(req.getContextPath().length());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);
        if ("/connect".equals(action)) {
            String dbname = req.getParameter("dbname");
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            service.connect(dbname, username, password);
        } else if ("/clear".equals(action)) {
            String tableName = req.getParameter("tablename");
            service.clear(tableName);
        } else if ("/find".equals(action)) {
            String tableName = req.getParameter("tablename");
            String found = service.find(tableName).replaceAll("\\r\\n", "<br>");
            req.setAttribute("found", found);
            req.getRequestDispatcher("found.jsp").forward(req, resp);
        } else if ("/columnnames".equals(action)) {
            String tableName = req.getParameter("tablename");
            Set<String> columnNames = service.columnNames(tableName);
            String json = new Gson().toJson(columnNames);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
            return;
        } else if ("/create".equals(action)) {
            String tableName = req.getParameter("tablename");
            service.create(tableName, req.getParameterMap());
        }
        resp.sendRedirect(resp.encodeRedirectURL("menu"));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            super.service(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

}
