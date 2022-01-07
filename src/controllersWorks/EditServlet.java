package controllersWorks;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.WorkTask;
import utils.DBUtil;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/workEdit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        // 該当のIDのタスク1件のみをデータベースから取得
        WorkTask w = em.find(WorkTask.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        // タスク情報とセッションIDをリクエストスコープに登録
        request.setAttribute("workTask", w);
        request.setAttribute("_token", request.getSession().getId());

        // タスクデータが存在しているときのみ
        // タスクIDをセッションスコープに登録
        if(w != null) {
            request.getSession().setAttribute("task_id", w.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/workTasks/edit.jsp");
        rd.forward(request, response);
    }

}
