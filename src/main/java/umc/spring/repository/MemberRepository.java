package umc.spring.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Member;


@Repository
public class MemberRepository {
    private final EntityManager entityManager;

    public MemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Member save(Member member){
        entityManager.persist(member);
        return member;
    }

    public Member findById(Long id){
        // find(Type, Primary Key)
        return entityManager.find(Member.class, id);
    }

    public Member findByName(String name){
        return entityManager.createQuery("select m from Member m where  m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findAny()
                .get();
    }

    public List<Member> findAll(){
        return entityManager.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
