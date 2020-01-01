package com.github.byrage.jpashop.controller.member;

import com.github.byrage.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", MemberForm.empty());
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public String create(@Valid MemberForm form, BindingResult result) {
        log.info("[POST]create. form={}", form);
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        memberService.create(form.convertToEntity());
        return "redirect:/";
    }

    @GetMapping
    public String list(Model model) {
        List<MemberView> memberViews = memberService.findAll()
                .stream()
                .map(MemberView::from)
                .collect(Collectors.toList());
        model.addAttribute("members", memberViews);
        return "/members/memberList";
    }

}
