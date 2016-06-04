package org.savitsky.linkedin.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by LenovoUser on 02.06.2016.
 */
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue
    private int memberId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    //validator to be implemented
    private String email;

    @Lob
    private String otherDetails;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "connections",
            uniqueConstraints = {@UniqueConstraint(columnNames = {"member1", "member2"})},
            joinColumns = {@JoinColumn(name = "member1", referencedColumnName = "memberId",nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "member2", referencedColumnName = "memberId", nullable = false)})
    private Collection<Member> connections=new ArrayList<Member>();

    @OneToMany(mappedBy = "manager")
    private Collection<Group> groupsManaged=new ArrayList<Group>();

    @ManyToMany(mappedBy = "followers")
    private Collection<Group> inGroups=new ArrayList<Group>();

    public enum Gender {
        MALE, FEMALE
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public Collection<Member> getConnections() {
        return connections;
    }

    public void setConnections(Collection<Member> connections) {
        this.connections = connections;
    }

    public Collection<Group> getGroupsManaged() {
        return groupsManaged;
    }

    public void setGroupsManaged(Collection<Group> groupsManaged) {
        this.groupsManaged = groupsManaged;
    }

    public Collection<Group> getInGroups() {
        return inGroups;
    }

    public void setInGroups(Collection<Group> inGroups) {
        this.inGroups = inGroups;
    }
}
