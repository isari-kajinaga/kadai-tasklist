package controllersWorks;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.WorkTask;
import utils.DBUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/workSort")
public class SortServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SortServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        // 開くページ数を取得（デフォルトは1ページ目）
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) {}

        // 最大件数と開始位置を指定してメッセージを取得
        List<WorkTask> workTasks = em.createNamedQuery("getAllWorkTasksSort", WorkTask.class)
                                   .setFirstResult(15 * (page - 1))
                                   .setMaxResults(15)
                                   .getResultList();

        // 全件数を取得
        long workTasks_count = (long)em.createNamedQuery("getWorkTasksCount", Long.class)
                                      .getSingleResult();

        // 期限３日以内のタスクを取得
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        date = calendar.getTime();
        List<WorkTask> nearingWorkTasks = em.createQuery("SELECT w FROM WorkTask AS w WHERE w.deadline BETWEEN CURRENT_DATE AND :date ORDER BY w.deadline ASC, w.deadline_time ASC", WorkTask.class)
                .setParameter("date", date, TemporalType.DATE)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();


        em.close();

        request.setAttribute("workTasks", workTasks);
        request.setAttribute("nearingWorkTasks", nearingWorkTasks);
        request.setAttribute("workTasks_count", workTasks_count);     // 全件数
        request.setAttribute("page", page);                         // ページ数

        // フラッシュメッセージがセッションスコープにセットされていたら
        // リクエストスコープに保存する（セッションスコープからは削除）
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/workTasks/sort.jsp");
        rd.forward(request, response);
    }

}
