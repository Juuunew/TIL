package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    /**
     * @Transactional
     * Test case 에 포함되어있으면 Test 가 끝난 후 바로 rollback
     */
    @Test
    @Transactional
    @Rollback(false)
    void testMember() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        /**
         * 저장한 member == 조회한 member 비교 (true)
         *      -> 같은 트랜잭션 안에서 저장하고 조회하면 영속성 컨텍스트가 동일
         *      -> 같은 영속성 컨텍스트 안에서는 Id 값이 동일하면 같은 Entity 로 인식
         */
        Assertions.assertThat(findMember).isEqualTo(member);
        System.out.println("findMember == member : " + (findMember == member));
    }

}