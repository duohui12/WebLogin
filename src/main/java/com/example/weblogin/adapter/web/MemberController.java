package com.example.weblogin.adapter.web;

import com.example.weblogin.application.usecase.JoinUseCase;
import com.example.weblogin.domain.Member;
import com.example.weblogin.util.DtoToDomain;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final JoinUseCase joinUseCase;

    @GetMapping("/add")
    public String addForm(@ModelAttribute JoinForm joinForm){
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute JoinForm joinForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }

        joinUseCase.join(DtoToDomain.JoinFormToDomain(joinForm));
        return "redirect:/";
    }

}

