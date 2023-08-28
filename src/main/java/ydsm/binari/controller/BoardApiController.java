package ydsm.binari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ydsm.binari.Dto.ResponseDto;
import ydsm.binari.config.auth.PrincipalDetails;
import ydsm.binari.model.Board;
import ydsm.binari.model.ReReply;
import ydsm.binari.model.Reply;
import ydsm.binari.service.BoardService;

@RestController
public class BoardApiController {

    @Autowired
    BoardService boardService;

    //글 저장 (게시판)
    @PostMapping(value = "/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.writeBoardService(board,principalDetails.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //글 저장(병원 추천 게시판)
    @PostMapping(value = "/api/hospitalBoard/{hospitalId}")
    public ResponseDto<Integer> save2(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetails principalDetails,@PathVariable int hospitalId){
        boardService.writeHospitalBoardService(board,principalDetails.getUser(),hospitalId);
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

    //댓글 달기
    @PostMapping(value = "/api/board/{boardId}/reply")
    public ResponseDto<Integer> addReply(@RequestBody Reply reply,@PathVariable int boardId,@AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.replyAddService(reply,boardId,principalDetails);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //대댓글 달기
    @PostMapping(value = "/api/board/{boardId}/reply/{replyId}/reReply")
    public ResponseDto<Integer> addReReply(@RequestBody ReReply reReply, @PathVariable int replyId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.reReplyAddService(reReply,replyId,principalDetails);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //댓글 삭제
    @DeleteMapping("/api/board/{boardId}/reply/{replyId}/del")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
        boardService.replyDeleteService(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //대댓글 삭제
    @DeleteMapping("/api/board/{boardId}/reply/{replyId}/reReply/{reReplyId}/del")
    public ResponseDto<Integer> reReplyDelete(@PathVariable int reReplyId) {
        boardService.reReplyDeleteService(reReplyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //댓글 추천
    @PostMapping(value = "/api/board/{boardId}/reply/{replyId}/like")
    public ResponseDto<Integer> likeReply(@PathVariable int replyId,@AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.likeReplyService(replyId,principalDetails.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //대댓글 추천
    @PostMapping(value = "/api/board/{boardId}/reply/{replyId}/reReply/{reReplyId}/like")
    public ResponseDto<Integer> likeReReply(@PathVariable int reReplyId,@AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.likeReReplyService(reReplyId,principalDetails.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //댓글 수정
    @PutMapping(value = "/api/board/{boardId}/reply/{replyId}/edit")
    public ResponseDto<Integer> updateReply(@PathVariable int replyId,@RequestBody Reply reply){
        boardService.replyUpdateService(replyId,reply);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //대댓글 수정
    @PutMapping(value = "/api/board/{boardId}/reply/{replyId}/reReply/{reReplyId}/edit")
    public ResponseDto<Integer> updateReReply(@PathVariable int reReplyId,@RequestBody ReReply reReply){
        boardService.reReplyUpdateService(reReplyId,reReply);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


}
