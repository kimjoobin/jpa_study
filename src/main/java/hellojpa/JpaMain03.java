package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain03 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();   // 엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /* 일대다 단방향 예시
            * -> 객체지향적으로 손해를 보더라도 일대다 단방향보다는 다대일 양방향을 사용하는것을 권장 */
            Member member = new Member();
            member.setUsername("KIM");
            em.persist(member);

            Team team = new Team();
            team.setName("teamA");

            team.getMembers().add(member);
            em.persist(team);

            tx.commit();    // 트랜잭션 커밋 시점에서 DB에 insert 쿼리를 날림
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
