package umc.spring.service;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;
import umc.spring.domain.Member;
import umc.spring.repository.MemberRepository;

@Service
@Transactional

public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository =memberRepository;
    }


    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Long join(Member member){
        memberRepository.save(member);
        return member.getId();
    }


    public Member findById(Long id){
        return memberRepository.findById(id);
    }
    public void update(Long id, String name) {
        Member member = memberRepository.findById(id);
        member.setName(name);
    }


}
