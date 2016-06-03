package org.savitsky.linkedin.dao;


import org.savitsky.linkedin.domain.Member;

import java.util.Collection;

/**
 * Created by LenovoUser on 02.06.2016.
 */
public interface Dao {
    Member memberById(int id);
    int saveMember(Member member);
    void connect(Member member1, Member member2);
    Collection<Member> allConnections(Member member);
    void deleteConnection(Member member1, Member member2);
}
