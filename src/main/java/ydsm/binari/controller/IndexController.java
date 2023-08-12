package ydsm.binari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ydsm.binari.config.auth.PrincipalDetails;
import ydsm.binari.service.BoardService;

@Controller
public class IndexController {

	@Autowired
	BoardService boardService;

	@GetMapping({"/",""})
	public String index(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
		model.addAttribute("boards",boardService.boardListQnAService());
		model.addAttribute("principal",principalDetails);
		return "index";
	}

	@GetMapping("/loginForm")
	public String login(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		model.addAttribute("principal",principalDetails);
		return "/user/loginForm";
	}

	@GetMapping("/boardFormQnA")
	public String boardQnA(Model model,@AuthenticationPrincipal PrincipalDetails principalDetails){
		model.addAttribute("boards",boardService.boardListQnAService());
		model.addAttribute("principal",principalDetails);
		return "/board/boardFormQnA";
	}

	@GetMapping("/boardFormInform")
	public String boardInform(Model model,@AuthenticationPrincipal PrincipalDetails principalDetails){
		model.addAttribute("boards",boardService.boardListInformService());
		model.addAttribute("principal",principalDetails);
		return "/board/boardFormInform";
	}

	@GetMapping("/board/saveForm")
	public String saveForm(Model model,@AuthenticationPrincipal PrincipalDetails principalDetails){
		model.addAttribute("principal",principalDetails);
		return "board/saveForm";
	}

	@GetMapping("/board/{id}")
	public String boardDetail(Model model, @PathVariable int id,@AuthenticationPrincipal PrincipalDetails principalDetails){
		model.addAttribute("board",boardService.boardDetailService(id));
		model.addAttribute("principal",principalDetails);
		boardService.countUpService(id);
		return "/board/detail";
	}
	// "/board/detail" -  글 목록에서 해당 글 눌렀을 때 글 내용, 댓글 나오는 form 만들어주세요

	@GetMapping("/board/{id}/updateForm")
	public String boardUpdate(Model model,@PathVariable int id,@AuthenticationPrincipal PrincipalDetails principalDetails){
		model.addAttribute("board",boardService.boardDetailService(id));
		model.addAttribute("principal",principalDetails);
		return "/board/updateForm";
	}

}
