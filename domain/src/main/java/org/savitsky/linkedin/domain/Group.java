package org.savitsky.linkedin.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by LenovoUser on 03.06.2016.
 */
@Entity
@Table(name = "groups")
public class Group {

    @Id @GeneratedValue
    private int groupId;

    private String groupName;

    @Lob
    private String details;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Member manager;


    @ManyToMany
    @JoinTable(name = "groups_followers",
            uniqueConstraints = {@UniqueConstraint(columnNames = {"follower_id", "group_id"})},
            joinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "groupId", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "follower_id", referencedColumnName = "memberId",nullable = false)})
    private Collection<Member> followers=new ArrayList<Member>();


    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Member getManager() {
        return manager;
    }

    public void setManager(Member manager) {
        this.manager = manager;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Collection<Member> getFollowers() {
        return followers;
    }

    public void setFollowers(Collection<Member> followers) {
        this.followers = followers;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
