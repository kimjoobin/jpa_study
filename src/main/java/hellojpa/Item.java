package hellojpa;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // join 전략
@DiscriminatorColumn
public abstract class Item {

    /* JPA 기본전략 자체가 single table 방식
    * TABLE_PER_CLASS는 웬만하면 지양해야한다. -> 여러 테이블을 함께 조회할때 UNION SQL로 인해 성능 저하때문에 */
    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
