package hellojpa;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    /* nullable = false: null 허용 안됨
       updatable = false로 하면 이 컬럼은 변경되지 않음 */
    @Column(name = "USERNAME", nullable = false)
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    /* Member.team이 연관관계의 주인 */
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)  // 읽기전용 필드를 사용해서 양방향처럼 사용하는 방법
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

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

//    public Team getTeam() {
//        return team;
//    }
//
//    public void setTeam(Team team) {
//        this.team = team;
//    }
//
//    public void changeTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this);
//    }

    /* EnumType은 무조건 STRING으로 선언, ORDINAL로 하면 enum 순서의 문제가 있음 */
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
//    private LocalDate testLocalDate;
//    private LocalDateTime testLocalDateTime;
//
//    @Lob    // String이면 clob으로 생성됨
//    private String description;


}
