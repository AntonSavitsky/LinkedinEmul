package org.savitsky.linkedin.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.savitsky.linkedin.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

/**
 * Created by LenovoUser on 02.06.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context-config.xml"})
//@EnableTransactionManagement
//@Transactional
public class DaoTest {

    @Autowired
    private Dao dao;

    @Test
    public void testMemberById(){
        System.out.println("memberById(2): "+ dao.memberById(1));
    }

    @Test
    public void testSaveMember(){
        Member member=new Member();
        member.setFirstName("Newwerw");
        member.setLastName("Guywerwe");
        member.setGender(Member.Gender.MALE);
        dao.saveMember(member);
    }

    @Test
    public void testConnect(){
        Member sav=dao.memberById(1);
        Member bor=dao.memberById(2);
        dao.connect(sav, bor);
    }

    @Test
    public void testAllConnections(){
        Member sav=dao.memberById(2);
        Collection<Member> allConnections=dao.allConnections(sav);

        for(Member m: allConnections)
            System.out.println(m.getFirstName());
    }

    @Test
    public void testDeleteConnection(){
        Member sav=dao.memberById(1);
        Member bor=dao.memberById(2);

        dao.deleteConnection(sav, bor);
    }
}
