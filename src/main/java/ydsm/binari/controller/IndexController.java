package ydsm.binari.controller;

import ydsm.binari.config.auth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ydsm.binari.model.User;

@Controller
public class IndexController {


	@GetMapping("/user")
	public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principal) {
		System.out.println("princpalDetails: "+principal.getUser());
		return "유저 페이지입니다.";
	}

	@GetMapping("/loginForm")
	public String login() {
		return "loginForm";
	}
}
