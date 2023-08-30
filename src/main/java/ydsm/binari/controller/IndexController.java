package ydsm.binari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ydsm.binari.config.auth.PrincipalDetails;
import ydsm.binari.model.Board;
import ydsm.binari.model.BoardType;
import ydsm.binari.repository.HospitalDataRepository;
import ydsm.binari.service.BoardService;

@Controller
public class IndexController {

	@Autowired
	BoardService boardService;

	@Autowired
	HospitalDataRepository hospitalDataRepository;

	@GetMapping({"/",""})
	public String index(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
		model.addAttribute("boardsQnA",boardService.boardListQnAService());
		model.addAttribute("boardsHospital",boardService.boardListHospitalService());
		model.addAttribute("boardsBlog",boardService.boardListBlogService());
		model.addAttribute("boardsInform",boardService.boardListInformService());
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

	@GetMapping("/boardFormHospital")
	public String boardHospital(Model model,@AuthenticationPrincipal PrincipalDetails principalDetails){
		model.addAttribute("boards",boardService.boardListHospitalService());
		model.addAttribute("principal",principalDetails);
		return "/board/boardFormHospital";
	}

	@GetMapping("/boardFormBlog")
	public String boardBlog(Model model,@AuthenticationPrincipal PrincipalDetails principalDetails){
		model.addAttribute("boards",boardService.boardListBlogService());
		model.addAttribute("principal",principalDetails);
		return "/board/boardFormBlog";
	}

	//투병일지 내 글 모아보기
	@GetMapping("/boardFormBlog/my")
	public String boardBlogMy(Model model,@AuthenticationPrincipal PrincipalDetails principalDetails){
		model.addAttribute("principal",principalDetails);
		model.addAttribute("boards",boardService.boardListBlogService());
		return "/board/boardFormBlogMy";
	}

	//글 작성 폼
	@GetMapping("/board/QnASaveForm")
	public String QnAsaveForm(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
		model.addAttribute("principal",principalDetails);
		return "board/QnASaveForm";
	}
	@GetMapping("/board/informSaveForm")
	public String informSaveForm(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
		model.addAttribute("principal",principalDetails);
		return "board/informSaveForm";
	}
	@GetMapping("/board/hospitalSaveForm")
	public String hospitalSaveForm(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
		model.addAttribute("principal",principalDetails);
		//병원 db 넘겨주기
		model.addAttribute("hospital", hospitalDataRepository.findAll());
		return "board/hospitalSaveForm";
	}
	@GetMapping("/board/blogSaveForm")
	public String blogSaveForm(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
		model.addAttribute("principal",principalDetails);
		return "board/blogSaveForm";
	}

	//글 상세보기
	@GetMapping("/board/{id}")
	public String boardDetail(Model model, @PathVariable int id,@AuthenticationPrincipal PrincipalDetails principalDetails){
		Board board = boardService.boardDetailService(id);
		model.addAttribute("principal",principalDetails);
		model.addAttribute("boards", board);
		boardService.countUpService(id);

		if(board.getBoardType()== BoardType.blog) {
			return "/board/blogDetail";
		}
		else if(board.getBoardType()== BoardType.QnA) {
			return "/board/QnADetail";
		}
		else if(board.getBoardType()== BoardType.information){
			return "/board/informDetail";
		}
		else{
			return "/board/hospitalDetail";
		}
	}

	@GetMapping("/board/{id}/updateForm")
	public String boardUpdate(Model model,@PathVariable int id,@AuthenticationPrincipal PrincipalDetails principalDetails){
		Board board = boardService.boardDetailService(id);
		model.addAttribute("boards", board);
		model.addAttribute("principal",principalDetails);
		if(board.getBoardType()== BoardType.blog) {
			return "/board/blogUpdateForm";
		}
		else if(board.getBoardType()== BoardType.QnA) {
			return "/board/QnAUpdateForm";
		}
		else if(board.getBoardType()== BoardType.information){
			return "/board/informUpdateForm";
		}
		else{
			return "/board/hospitalUpdateForm";
		}
	}

	@GetMapping("/mypage/{id}")
	public String mypage(Model model,@PathVariable int id,@AuthenticationPrincipal PrincipalDetails principalDetails){
		model.addAttribute("Scraps",boardService.scrapDetailService(id));
		model.addAttribute("principal",principalDetails);
		return "/mypage";
	}



}
