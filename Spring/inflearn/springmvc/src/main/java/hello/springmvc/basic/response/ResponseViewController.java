package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {

        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mav;
    }

    /**
     * String 을 반환하는 경우 - View or HTTP 메시지
     *
     * @ResponseBody
     *  있으면 -> view resolver 를 실행하지 않고, HTTP 메시지 바디에 직접 입력
     *  없으면 -> 입력값으로 view resolver 가 실행되어서 view를 찾고 렌더링
     */
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {

        model.addAttribute("data", "hello!");

        return "response/hello";
    }

    /**
     * Void를 반환하는 경우우     *
     * @Controller 를 사용하고 HttpServletResponse, OutputStream 같은 HTTP 메시지 바디를 처리하는 파라미터가 없으면
     * 요청 URL 을 참고해서 논리 뷰 이름으로 사용
     *
     * 명시성이 떨어지고, 딱 맞는 경우도 많이 없어서 권장하지 않음.
     */
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {

        model.addAttribute("data", "hello!");

    }
}
