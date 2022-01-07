package controllersWorks;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.WorkTask;
import models.validatores.WorkTaskValidatore;
import utils.DBUtil;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/workCreate")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();
            em.getTransaction().begin();

            WorkTask w = new WorkTask();

            String content = request.getParameter("content");
            w.setContent(content);


            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date deadline = dateFormat.parse(request.getParameter("deadline"));
                w.setDeadline(deadline);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            LocalTime deadline_time = LocalTime.parse(request.getParameter("deadline_time"));
            w.setDeadline_time(deadline_time);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            w.setCreated_at(currentTime);
            w.setUpdated_at(currentTime);

            // バリデーションを実行してエラーがあったら新規登録のフォームに戻る
            List<String> errors = WorkTaskValidatore.validate(w);
            if(errors.size() > 0) {
                em.close();

                // フォームに初期値を設定、さらにエラーメッセージを送る
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("task", w);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/WorkTasks/new.jsp");
                rd.forward(request, response);
            } else {


            em.persist(w);
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "登録が完了しました。");
            em.close();

            response.sendRedirect(request.getContextPath() + "/workIndex");
            }
        }
    }

}
