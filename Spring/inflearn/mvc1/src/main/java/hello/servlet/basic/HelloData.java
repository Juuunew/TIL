package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

// lombok 활용
@Getter
@Setter
public class HelloData {

    private String username;
    private int age;

    // 자바 property 접근법
    // 자바 bean 접근법
    /*public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }*/
}
