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
    @PostMapping(value = "/api/board/like/{boardId}")
    public ResponseDto<Integer> likeBoard(@PathVariable int boardId,@AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.likeBoardService(boardId,principalDetails.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //글 스크랩
    @PostMapping(value = "/api/board/scrap/{boardId}")
    public ResponseDto<Integer> scrap(@PathVariable int boardId,@AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.scrapBoardService(boardId,principalDetails.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //글 수정
    @PutMapping(value = "/api/board/{boardId}")
    public ResponseDto<Integer> update(@PathVariable int boardId,@RequestBody Board board){
        boardService.boardUpdateService(boardId,board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //글 삭제
    @DeleteMapping(value = "/api/board/{boardId}")
    public ResponseDto<Integer> deleteBoard(@PathVariable int boardId){
        boardService.boardDeleteService(boardId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //댓글달기
    @PostMapping(value = "/api/board/{boardId}/reply")
    public ResponseDto<Integer> addReply(@RequestBody Reply reply,@PathVariable int boardId,@AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.replyAddService(reply,boardId,principalDetails);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //댓글삭제
    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
        boardService.replyDeleteService(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //댓글 추천
    @PostMapping(value = "/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> likeReply(@PathVariable int replyId,@AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.likeReplyService(replyId,principalDetails.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
