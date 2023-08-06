package ydsm.binari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ydsm.binari.service.BoardService;

@Controller
public class IndexController {

	@GetMapping({"/",""})
	public String index(){
		return "index";
	}


	@Autowired
	BoardService boardService;

	@GetMapping("/loginForm")
	public String login() {
		return "/user/loginForm";
	}

	@GetMapping("/boardForm")
	public String board(Model model, @PageableDefault(size = 3,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
		model.addAttribute("boards",boardService.boardListService(pageable));
		return "/board/boardForm";
	}

	@GetMapping("/board/saveForm")
	public String saveForm(){
		return "board/saveForm";
	}

	@GetMapping("/board/{id}")
	public String boardDetail(Model model, @PathVariable int id){
		model.addAttribute("board",boardService.boardDetailService(id));
		boardService.countUpService(id);
		return "/board/detail";
	}
	// "/board/detail" - 해당 글 내용과 댓글 나오는 form 만들어주세요

	@GetMapping("/board/{id}/updateForm")
	public String boardUpdate(Model model,@PathVariable int id){
		model.addAttribute("board",boardService.boardDetailService(id));
		return "/board/updateForm";
	}

}