package org.savitsky.linkedin.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.savitsky.linkedin.domain.Group;
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
        member.setFirstName("Garik");
        member.setLastName("Badyuk");
        member.setGender(Member.Gender.MALE);
        dao.saveMember(member);
    }

    @Test
    public void testConnect(){
        Member sav=dao.memberById(3);
        Member bor=dao.memberById(6);
        dao.connect(sav, bor);
    }

    @Test
    public void testFirstLevelConnections(){
        Member sav=dao.memberById(1);
        Collection<Member> allConnections=dao.firstLevelConnections(sav);

        for(Member m: allConnections)
            System.out.println(m.getFirstName());
    }

    @Test
    public void testDeleteConnection(){
        Member sav=dao.memberById(1);
        Member bor=dao.memberById(3);

        dao.deleteConnection(sav, bor);
    }

    @Test
    public void testSaveGroup(){
        Member sav=dao.memberById(1);

        Group group=new Group();
        group.setGroupName("Brest Community");
        group.setManager(sav);
        Integer id=dao.saveGroup(group);
        System.out.println("saveGroup() id: "+id);
    }

    @Test
    public void testAddToGroup(){
        Member sav=dao.memberById(1);
        Group group=dao.groupById(2);
            dao.addToGroup(sav, group);
    }

    @Test
    public void testSecondLevelConnections(){
        Member sav=dao.memberById(1);
        Collection<Member> allSecondLevelConnections=dao.secondLevelConnections(sav);
        for(Member m: allSecondLevelConnections)
            System.out.println(m.getFirstName());
    }
}
