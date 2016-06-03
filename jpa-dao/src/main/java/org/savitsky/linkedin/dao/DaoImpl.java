package org.savitsky.linkedin.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.savitsky.linkedin.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by LenovoUser on 02.06.2016.
 */
@EnableTransactionManagement
@Transactional
public class DaoImpl implements Dao {
    @Autowired
    private SessionFactory sessionFactory;

    public Member memberById(int id) {
        Session session=sessionFactory.getCurrentSession();
        Member member= (Member) session.get(Member.class, id);
        return member;
    }

    public int saveMember(Member member) {
        Session session=sessionFactory.getCurrentSession();
            Integer id = (Integer) session.save(member);
        return id;
    }

    public void connect(Member member1, Member member2) {
        Session session=sessionFactory.getCurrentSession();
        Member freshMember1= (Member) session.get(Member.class, member1.getMemberId());
            freshMember1.getConnections().add(member2);
            Member freshMember2= (Member) session.get(Member.class, member2.getMemberId());
            freshMember2.getConnections().add(freshMember1);
    }

    public Collection<Member> allConnections(Member member) {
        Session session=sessionFactory.getCurrentSession();
            Member member1= (Member) session.get(Member.class, member.getMemberId());
            Collection<Member> allConnections=member1.getConnections();
        return allConnections;
    }

    public void deleteConnection(Member member1, Member member2) {
        Session session=sessionFactory.getCurrentSession();
                Member persistentMember1= (Member) session.get(Member.class, member1.getMemberId());
                Member persistentMember2= (Member) session.get(Member.class, member2.getMemberId());
                persistentMember1.getConnections().remove(persistentMember2);
                persistentMember2.getConnections().remove(persistentMember1);
    }
}
