package hellojpa;

import org.hibernate.Hibernate;

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
            Member member1 = new Member();
            member1.setName("member1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member refMember = em.find(Member.class, member1.getId());

            System.out.println("refMember: " + refMember.getClass());   // Proxy
            /* 영속성 컨텍스트를 사용할 수 없는 상태에서 프록시를 초기화하면 에러 발생 */
//            em.detach(refMember);

//            refMember.getUsername();    // Proxy 객체 초기화

//            Member findMember = em.getReference(Member.class, member.getId()); // getReference 호출 시점에서는 쿼리가 안날라감
//            System.out.println("before findMember: " + findMember.getClass());     // hibernate가 만든 가상 class
//            System.out.println("findMember.id: " + findMember.getId());
//            System.out.println("after findMember: " + findMember.getClass());

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
