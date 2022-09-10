package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain02 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();   // 엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member member = em.find(Member.class, 1L);
//            printMember(member);
//            printmemberAndTeam(member);
            Member member = new Member();
            member.setUsername("hello");

            em.persist(member);

            em.flush();
            em.clear();

//            Member findMember = em.find(Member.class, member.getId());
            Member findMember = em.getReference(Member.class, member.getId()); // getReference 호출 시점에서는 쿼리가 안날라감
            System.out.println("before findMember: " + findMember.getClass());     // hibernate가 만든 가상 class
            System.out.println("findMember.id: " + findMember.getId());
            System.out.println("after findMember: " + findMember.getClass());

            tx.commit();    // 트랜잭션 커밋 시점에서 DB에 insert 쿼리를 날림
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void printMember(Member member) {
        System.out.println("member: " + member.getUsername());
    }

    private static void printmemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username= " + username);

        Team team = new Team();
        System.out.println("team = " + team.getName());
    }
}
