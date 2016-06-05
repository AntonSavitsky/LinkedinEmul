package org.savitsky.linkedin.dao;

import org.savitsky.linkedin.domain.Group;
import org.savitsky.linkedin.domain.GroupFollowingRequest;
import org.savitsky.linkedin.domain.Member;

import java.util.Collection;

/**
 * Created by LenovoUser on 04.06.2016.
 */
public interface Dao {
    void deleteConnection(Member member1, Member member2);
    Collection<Member> firstLevelConnections(Member member);
    Collection<Member> secondLevelConnections(Member member);
    boolean areConnected(Member member1, Member member2);
    int requestConnection(Member member1, Member member2);
    void acceptConnection(Member member1, Member member2);
    void denyConnectionRequest(Member member1, Member member2);


    int saveGroup(Group group);
    void addToGroup(Member member, Group group);
    void requestFollowingGroup(Group group, Member member);
    Group groupById(int id);
    void updateGroup(Group group);
    void deleteGroup(Group group);
    void getAllIncomingRequests(Group group);


    Member memberById(int id);
    int saveMember(Member member);
    void deleteMember(Member member);
    void updateMember(Member member);
    Collection<GroupFollowingRequest> getAllGroupFollowingRequests(Member member);

}
