package org.savitsky.linkedin.domain;

import javax.persistence.*;

/**
 * Created by LenovoUser on 05.06.2016.
 */
@Entity
public class GroupFollowingRequest {
    @Id
    @GeneratedValue
    private int requestId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member memberWillingToFollow;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Member getMemberWillingToFollow() {
        return memberWillingToFollow;
    }

    public void setMemberWillingToFollow(Member memberWillingToFollow) {
        this.memberWillingToFollow = memberWillingToFollow;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
