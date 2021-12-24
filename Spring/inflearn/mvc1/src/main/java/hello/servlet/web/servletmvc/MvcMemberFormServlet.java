package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Controller
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String viewPath = "/WEB-INF/views/new-form.jsp";
        // controller -> view 넘어갈 때 사용
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        // dispatcher.forward() -> servlet에서 jsp호출
        //  서버 내부에서 다시 호출이 발생
        dispatcher.forward(request, response);
    }

    /**
     * redirect vs forward
     *
     * redirect는 클라이언트에 응답이 나갔다가 클라이언트가 redirect경로로 다시 요청한다.
     * 따라서 클라이언트가 인지할 수 있고, URL 경로도 실제로 변경된다.
     *
     * forward는 서버 내부에서 일어나는 호출이기 때문에 클라이언트가 인지하지 못한다.
    */
}
