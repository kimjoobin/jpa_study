package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    /* mappedBy를 가지고 있는 엔티티는 읽기전용임 -> team.getMember 조회만 가능
    * insert, update를 할 수 없음 */
    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<>();

    public List<Member> getMembers() {
        return members;
    }

//    public void addMembers(Member member) {
//        member.setTeam(this);
//        members.add(member);
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
