package org.savitsky.linkedin.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.savitsky.linkedin.domain.ConnectionRequest;
import org.savitsky.linkedin.domain.Group;
import org.savitsky.linkedin.domain.GroupFollowingRequest;
import org.savitsky.linkedin.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by LenovoUser on 02.06.2016.
 */
@EnableTransactionManagement
@Transactional
public class DaoImpl implements Dao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Member memberById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Member member = (Member) session.get(Member.class, id);
        return member;
    }

    @Override
    public int saveMember(Member member) {
        Session session = sessionFactory.getCurrentSession();
        Integer id = (Integer) session.save(member);
        return id;
    }

    @Override
    public void deleteMember(Member member) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(member);
    }

    @Override
    public void updateMember(Member member){
        Session session = sessionFactory.getCurrentSession();
        session.update(member);
    }

    @Override
    public Collection<GroupFollowingRequest> getAllGroupFollowingRequests(Member member) {
        //TODO
        return null;
    }

    @Override
    public void acceptConnection(Member member1, Member member2) {
        Session session = sessionFactory.getCurrentSession();
        Member persistedMember1 = (Member) session.get(Member.class, member1.getMemberId());
        persistedMember1.getConnections().add(member2);
        Member persistedMember2 = (Member) session.get(Member.class, member2.getMemberId());
        persistedMember2.getConnections().add(persistedMember1);

        String hql = "delete from ConnectionRequest where requestFrom.memberId=? and requestTo.memberId=?";
        Query query = session.createQuery(hql);
        query.setInteger(0, member1.getMemberId());
        query.setInteger(1, member2.getMemberId());
        query.executeUpdate();
    }

    @Override
    public void denyConnectionRequest(Member member1, Member member2) {
        Session session = sessionFactory.getCurrentSession();

        String hql = "delete from ConnectionRequest where requestFrom.memberId=? and requestTo.memberId=?";
        Query query = session.createQuery(hql);
        query.setInteger(0, member1.getMemberId());
        query.setInteger(1, member2.getMemberId());
        query.executeUpdate();
    }


    @Override
    public void deleteConnection(Member member1, Member member2) {
        Session session = sessionFactory.getCurrentSession();
        Member persistentMember1 = (Member) session.get(Member.class, member1.getMemberId());
        Member persistentMember2 = (Member) session.get(Member.class, member2.getMemberId());
        persistentMember1.getConnections().remove(persistentMember2);
        persistentMember2.getConnections().remove(persistentMember1);
    }

    @Override
    public int saveGroup(Group group) {
        Session session = sessionFactory.getCurrentSession();
        Integer id = (Integer) session.save(group);
        return id;
    }


    @Override
    public void addToGroup(Member member, Group group) {
        Session session = sessionFactory.getCurrentSession();
        Group group1 = (Group) session.get(Group.class, group.getGroupId());
        group1.getFollowers().add(member);
    }

    @Override
    public void requestFollowingGroup(Group group, Member member) {
        Session session = sessionFactory.getCurrentSession();

        GroupFollowingRequest groupFollowingRequest=new GroupFollowingRequest();
        groupFollowingRequest.setGroup(group);
        groupFollowingRequest.setMemberWillingToFollow(member);
        session.save(groupFollowingRequest);
    }

    @Override
    public Group groupById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Group) session.get(Group.class, id);
    }

    @Override
    public void updateGroup(Group group) {
        Session session = sessionFactory.getCurrentSession();
        session.update(group);
    }

    @Override
    public void deleteGroup(Group group) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(group);
    }

    @Override
    public void getAllIncomingRequests(Group group) {
        //TODO
    }

    @Override
    public Collection<Member> firstLevelConnections(Member member) {
        Session session = sessionFactory.getCurrentSession();
        Member member1 = (Member) session.get(Member.class, member.getMemberId());
        Collection<Member> allConnections = member1.getConnections();
        return allConnections;
    }

    @Override
    public Collection<Member> secondLevelConnections(Member member) {
        Collection<Member> allFirstLevelConnections = firstLevelConnections(member);
        Collection<Member> allSecondLevelConnections = new ArrayList<>();
        for (Member mem : allFirstLevelConnections) {
            for(Member mem2: mem.getConnections()) {
                if(mem2.getMemberId()!=member.getMemberId()&&!allFirstLevelConnections.contains(mem2))
                    allSecondLevelConnections.add(mem2);
            }
        }
        return allSecondLevelConnections;
    }

    @Override
    public boolean areConnected(Member member1, Member member2) {
        Collection<Member> allFirstLevelConnections=firstLevelConnections(member1);
        return allFirstLevelConnections.contains(member2);
    }

    @Override
    public int requestConnection(Member member1, Member member2) {
        Session session = sessionFactory.getCurrentSession();

        ConnectionRequest member1Request=new ConnectionRequest();
        member1Request.setRequestFrom(member1);
        member1Request.setRequestTo(member2);

        Integer requestId= (Integer) session.save(member1Request);

        Member persistedMember2= (Member) session.get(Member.class, member2.getMemberId());
        persistedMember2.getInRequests().add(member1Request);

        return requestId;
    }


}