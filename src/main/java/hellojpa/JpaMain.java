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

        EntityManager em = emf.createEntityManager();   // 엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 실무에서는 일대다 단방향보다는 다대일 양방향을 사용한다.
            /*Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            Team team = new Team();
            team.setName("teamA");
            team.getMembers().add(member);
            em.persist(team);*/
            Movie movie = new Movie();
            movie.setDirector("aa");
            movie.setActor("bb");
            movie.setName("탑건 매버릭");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();
            em.clear();

            Movie findMovie = em.find(Movie.class, movie.getId());  // item을 inner join해서 값을 가져옴
            System.out.println("findMovie: " + findMovie);

            tx.commit();    // 트랜잭션 커밋 시점에서 DB에 insert 쿼리를 날림
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
