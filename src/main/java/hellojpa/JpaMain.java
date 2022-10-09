package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");
//
//            // 엔티티를 persist 하는 시점부터 영속상태가 됨 -> 엔티티를 1차캐시에 저장
//            em.persist(member);
//            Member member = em.find(Member.class, 150L);

//            /* 영속 엔티티 데이터 수정 - 더티체킹(Dirty checking)
//            *  JPA는 엔티티값을 바꾸면 트랜잭션 커밋시점에 반영이 됨(update 쿼리가 날라감) */
//            member.setName("DDDD");

//            Member member = new Member(200L, "member200");
//            em.persist(member);
//            em.flush(); // 영속성 컨텍스트의 변경내용을 바로 DB에 반영이 됨 -> 영속성 컨텍스트를 비우는게 아님
            Member member = new Member();
            member.setId(151L);
            member.setName("tester");
            em.persist(member);

            Member findMember = em.find(Member.class, 152L);

            System.out.println("findMember:::: " + findMember.getName());
            // member.setName("AAAA");

            // 영속성 컨텍스트에서 엔티티를 관리하지 않음, 영속 -> 준영속 상태가 됨
            // em.detach(member);
            // em.clear(); // 영속성 컨텍스트를 모두 초기화

            System.out.println("====================");

            tx.commit();    // 트랜잭션 커밋하는 순간 DB에 insert 쿼리를 날림
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
