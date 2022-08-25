package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    /* Member 엔티티 입장에서 N */
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    private Team team;

    /* 일대일 매핑은 다대일(@ManyToOne) 단방향 매핑과 유사하다.
    * 외래 키가 있는곳이 연관관계의 주인 */
    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT")     // 연결 테이블 생성
    private List<Product> products = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
