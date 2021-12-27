package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    /**
     * HttpMethod -> HTTP 메서드를 조회
     * Locale -> Locale 정보를 조회
     *
     * @RequestHeader MultiValueMap<String, String> headerMap
     * 모든 HTTP 헤더를 MultiValueMap 형식으로 조회
     *
     * @RequestHeader("host") String host
     * 특정 HTTP 헤더를 조회한다.
     * 속성
     * 필수 값 여부: required
     * 기본 값 속성: defaultValue
     *
     * @CookieValue(value = "myCookie", required = false) String cookie
     * 특정 쿠키를 조회한다.
     * 속성
     * 필수 값 여부: required
     * 기본 값: defaultValue
     */

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request, HttpServletResponse response,
                          HttpMethod httpMethod, Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie) {

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";
    }
}
