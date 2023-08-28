package ydsm.binari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ydsm.binari.model.Board;
import ydsm.binari.model.BoardType;
import ydsm.binari.service.BoardService;

@Controller
public class IndexController {

	@Autowired
	BoardService boardService;

	@GetMapping({"/",""})
	public String index(Model model){
		model.addAttribute("boardsQnA",boardService.boardListQnAService());
		model.addAttribute("boardsHospital",boardService.boardListHospitalService());
		model.addAttribute("boardsBlog",boardService.boardListBlogService());
		model.addAttribute("boardsInform",boardService.boardListInformService());
		return "index";
	}

	@GetMapping("/loginForm")
	public String login() {
		return "/user/loginForm";
	}

	@GetMapping("/boardFormQnA")
	public String boardQnA(Model model){
		model.addAttribute("boards",boardService.boardListQnAService());
		return "/board/boardFormQnA";
	}

	@GetMapping("/boardFormInform")
	public String boardInform(Model model){
		model.addAttribute("boards",boardService.boardListInformService());
		return "/board/boardFormInform";
	}

	@GetMapping("/boardFormHospital")
	public String boardHospital(Model model){
		model.addAttribute("boards",boardService.boardListHospitalService());
		return "/board/boardFormHospital";
	}

	@GetMapping("/boardFormBlog")
	public String boardBlog(Model model){
		model.addAttribute("boards",boardService.boardListBlogService());
		return "/board/boardFormBlog";
	}

	//투병일지 내 글 모아보기
	@GetMapping("/boardFormBlog/my")
	public String boardBlogMy(Model model){
		model.addAttribute("boards",boardService.boardListBlogService());
		return "/board/boardFormBlogMy";
	}

	@GetMapping("/board/saveForm")
	public String saveForm(){
		return "board/saveForm";
	}

	@GetMapping("/board/{id}")
	public String boardDetail(Model model, @PathVariable int id){
		Board board = boardService.boardDetailService(id);
		model.addAttribute("board", board);
		boardService.countUpService(id);
		if (board.getHospitalData()==null) {
			if(board.getBoardType()== BoardType.blog) {
				return "/board/blogDetail";
			}
			else {
				return "/board/detail";
			}
		}
		else{
			return "/board/hospitalDetail";
		}
	}
	// "/board/detail" -  글 목록에서 해당 글 눌렀을 때 글 내용, 댓글 나오는 form 만들어주세요
	@GetMapping("/board/{id}/updateForm")
	public String boardUpdate(Model model,@PathVariable int id){
		model.addAttribute("board",boardService.boardDetailService(id));
		return "/board/updateForm";
	}

}
