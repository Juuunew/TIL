package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller -> view 가 return
//@Slf4j
@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {

        String name = "Spring";

        System.out.println("name = " + name);

        /**
         * 로그출력 포멧
         * 시간, 로그 레벨, 프로세스 ID, 쓰레드 명, 클래스 명, 로그 메시지
         *
         * 로그 레벨
         * TRACE > DEBUG > INFO > WARN > ERROR
         *
         * 개발 서버 -> DEBUG 출력
         * 운영 서버 -> INFO 출력
         *
         * 잘못된 사용법
         * log.trace("trace log = " + log);
         *      -> 로그 출력 레벨을 info로 사용해도 해당 코드에 있는 "trace log=" + log가 실제 실행됨
         *      -> 문자더하기 연산이 발생하면서 쓸모없는 resource를 사용하게 됨
         */
        log.trace("trace log= {}", name);
        log.debug("debug log= {}", name);
        log.info("info log = {}", name);
        log.warn("warn log= {}", name);
        log.error("error log= {}", name);

        return "ok";
    }
}
