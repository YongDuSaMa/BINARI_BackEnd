package ydsm.binari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ydsm.binari.Dto.ResponseDto;
import ydsm.binari.config.auth.PrincipalDetails;
import ydsm.binari.model.Board;
import ydsm.binari.model.Reply;
import ydsm.binari.service.BoardService;

@RestController
public class BoardApiController {

    @Autowired
    BoardService boardService;

    //글 저장
    @PostMapping(value = "/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.writeBoardService(board,principalDetails.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //글 추천
    @PostMapping(value = "/api/board/like/{id}")
    public ResponseDto<Integer> up(@PathVariable int id,@AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.likeBoardService(id,principalDetails.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //글 스크랩
    @PostMapping(value = "/api/board/scrap/{id}")
    public ResponseDto<Integer> scrap(@PathVariable int id,@AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.scrapBoardService(id,principalDetails.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //글 수정
    @PutMapping(value = "/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id,@RequestBody Board board){
        boardService.boardUpdateService(id,board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //글 삭제
    @DeleteMapping(value = "/api/board/{id}")
    public ResponseDto<Integer> deleteBoard(@PathVariable int id){
        boardService.boardDeleteService(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping(value = "/api/board/{id}/reply")
    public ResponseDto<Integer> addReply(@RequestBody Reply reply,@PathVariable int id,@AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.replyAddService(reply,id,principalDetails);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
        boardService.replyDeleteService(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
