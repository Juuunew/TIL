package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 로그 포멧
     * 시간, 로그 레벨, 프로세스 ID, 쓰레드 명, 클래스명, 로그 메시지
     *
     * LEVEL
     * TRACE > DEBUG > INFO > WARN > ERROR
     *
     * 개발 서버 -> debug 출력
     * 운영 서버 -> info 출력
     *
     * 잘못된 로그 사용법
     * log.debug("data =" + data)
     * 로그 출력 레벨을 한 단계 낮은 info로 설정해도 해당 코드에 있는 "data =" + data 연산이 실행됨
     *      -> 쓸모없는 resource 사용
     *      -> 문자 더하기 연산이 발생
     */

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);
        //로그를 사용하지 않아도 a+b 계산 로직이 먼저 실행됨, 이런 방식으로 사용하면 X
        log.debug("String concat log=" + name);
        return "ok";
    }
}
