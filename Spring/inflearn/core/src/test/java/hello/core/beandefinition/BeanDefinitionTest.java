package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

class BeanDefinitionTest {

    // Spring Bean을 만드는 두 가지 방법
    // 1. 직접등록
    // 2. factory bean을 통해 등록

    // java config -> factory method를 이용한 빈 등록
    // factory bean, factory method -> 외부에서 method를 호출해서 생성
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    // xml로 설정하면 bean class가 드러남
    GenericXmlApplicationContext gac = new GenericXmlApplicationContext("appConfig.xml");

    // 스프링이 다양한 형태의 설정 정보를 BeanDefinition으로 추상화해서 사용함
    @Test
    @DisplayName("Bean 설정 메타정보 확인")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                        " beanDefinition = " + beanDefinition);
            }
        }
    }

    @Test
    @DisplayName("Bean 설정 메타정보 확인")
    void findApplicationBean2() {
        String[] beanDefinitionNames = gac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = gac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                        " beanDefinition = " + beanDefinition);
            }
        }
    }
}
