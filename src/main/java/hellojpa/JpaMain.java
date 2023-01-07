package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // emf는 애플리케이션 로딩 시점에 하나만 만들어야함
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();     // JPA의 모든 데이터변경은 트랜잭션 안에서 실행해야함
        tx.begin();

        try {
            /* chapter03 */
            // 비영속 상태
            /*Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");*/
//
//            // 엔티티를 persist 하는 시점부터 영속상태가 됨 -> 엔티티를 1차캐시에 저장
            // 이 시점에서 엔티티의 id가 생성됨
//            em.persist(member);
//            Member member = em.find(Member.class, 150L);

//            /* 영속 엔티티 데이터 수정 - 더티체킹(Dirty checking)
//            *  JPA는 엔티티값을 바꾸면 트랜잭션 커밋시점에 반영이 됨(update 쿼리가 날라감) */
//            member.setName("DDDD");

//            Member member = new Member(200L, "member200");
//            em.persist(member);
//            em.flush(); // 영속성 컨텍스트의 변경내용을 바로 DB에 반영이 됨 -> 영속성 컨텍스트를 비우는게 아님
            // member.setName("AAAA");

            // 영속성 컨텍스트에서 엔티티를 관리하지 않음, 영속 -> 준영속 상태가 됨
            // em.detach(member);
            // em.clear(); // 영속성 컨텍스트를 모두 초기화
            //Member member = new Member();
            //member.setUsername("C");
            //em.persist(member);

            //============================ Chapter5. ====================================================
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            // member.setTeam(team);
            //member.setTeamId(team.getId()); // 객체지향스럽지 않은 코드
            em.persist(member);

            // flush and clear를 해줘야 insert 후 조회 쿼리가 날라감
            em.flush();
            em.clear();

            // 연관관계를 설정하지 않으면 아래와 같이 계속해서 JPA를 통해 조회를 해야한다. -> 전혀 객체지향스럽지 않음
            /*Member findMember = em.find(Member.class, member.getId());
            Long findTeamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class, findTeamId);*/

            Member findMember = em.find(Member.class, member.getId());
            //List<Member> members = findMember.getTeam().getMembers();

            /*for (Member m : members) {
                System.out.println("m: " + m.getUsername());
            }*/
            tx.commit();    // 트랜잭션 커밋하는 순간 DB에 insert 쿼리를 날림
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
