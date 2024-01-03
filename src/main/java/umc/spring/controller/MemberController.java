package umc.spring.controller;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.domain.Member;
import umc.spring.dto.JoinMemberRequest;
import umc.spring.dto.JoinMemberResponse;
import umc.spring.dto.MemberDto;
import umc.spring.dto.UpdateMemberRequest;
import umc.spring.dto.UpdateMemberResponse;
import umc.spring.service.MemberService;


@RestController
@RequiredArgsConstructor
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public Result members(){
        List<Member> foundMembers = memberService.findMembers();
        List<MemberDto> memberDtos = foundMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .toList();
        return new Result(memberDtos);
    }

    @PostMapping("/members")
    public JoinMemberResponse saveMember(@RequestBody @Validated JoinMemberRequest request){
        Member newMember = new Member();
        newMember.setName(request.getName());
        memberService.join(newMember);

        return new JoinMemberResponse(newMember.getId());
    }

    @PutMapping("members/{id}")
    public UpdateMemberResponse updateMemberName(
            @PathVariable("id") Long id,
            @RequestBody @Validated UpdateMemberRequest request){
        memberService.update(id, request.getName());

        Member foundMember = memberService.findById(id);
        return new UpdateMemberResponse(id, foundMember.getName());
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }


}
