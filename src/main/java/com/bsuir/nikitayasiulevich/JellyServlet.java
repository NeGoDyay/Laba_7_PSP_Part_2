package com.bsuir.nikitayasiulevich;



import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JellyServlet extends HttpServlet {
    private JellyDao jellyDao;

    @Override
    public void init() throws ServletException {
        super.init();
        jellyDao = new JellyDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listJellies(request, response);
                break;
            case "show":
                showJelly(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteJelly(request, response);
                break;
            default:
                listJellies(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "add":
                addJelly(request, response);
                break;
            case "edit":
                updateJelly(request, response);
                break;
            default:
                listJellies(request, response);
        }
    }

    private void listJellies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Jelly> jellies = jellyDao.getAllJellies();

        request.setAttribute("jellies", jellies);
        request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
    }

    private void showJelly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Jelly jelly = jellyDao.getJellyById(id);

        request.setAttribute("jelly", jelly);
        request.getRequestDispatcher("/WEB-INF/jsp/show.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Jelly jelly = jellyDao.getJellyById(id);

        request.setAttribute("jelly", jelly);
        request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(request, response);
    }

    private void addJelly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String flavor = request.getParameter("flavor");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Jelly jelly = new Jelly(id, flavor, quantity);
        jellyDao.addJelly(jelly);

        response.sendRedirect(request.getContextPath() + "/jelly?action=list");
    }

    private void updateJelly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String flavor = request.getParameter("flavor");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Jelly jelly = new Jelly(id, flavor, quantity);
        jellyDao.updateJelly(jelly);

        response.sendRedirect(request.getContextPath() + "/jelly?action=list");
    }

    private void deleteJelly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        jellyDao.deleteJelly(id);

        response.sendRedirect(request.getContextPath() + "/jelly?action=list");
    }
}