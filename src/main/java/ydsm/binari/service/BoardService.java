package ydsm.binari.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ydsm.binari.config.auth.PrincipalDetails;
import ydsm.binari.model.*;
import ydsm.binari.repository.*;

import java.util.List;


@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    LikesRepository likesRepository;

    @Autowired
    ScrapRepository scrapRepository;

    @Autowired
    ReplyRepository replyRepository;


    @Transactional
    public void writeBoardService(Board board, User user) {
        board.setUser(user);
        board.setBoardType(board.getBoardType());
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public List<Board> boardListQnAService() {
        return boardRepository.findByBoardType(BoardType.QnA);
    }

    @Transactional(readOnly = true)
    public List<Board> boardListInformService() {
        return boardRepository.findByBoardType(BoardType.information);
    }

    @Transactional(readOnly = true)
    public Board boardDetailService(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("아이디를 찾을 수 없습니다.");
                });
    }

    @Transactional
    public void countUpService(int id) {
        Board findBoard = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("아이디를 찾을 수 없습니다.");
                });
        findBoard.setViewCount(findBoard.getViewCount() + 1);
    }

    @Transactional
    public void boardUpdateService(int id,Board after) {
        Board before = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("아이디를 찾을 수 없습니다.");
                });
        before.setTitle(after.getTitle());
        before.setContent(after.getContent());
    }

    @Transactional
    public void boardDeleteService(int id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("아이디를 찾을 수 없습니다.");
                });
        boardRepository.deleteById(id);
        likesRepository.deleteByBoard(board);
        scrapRepository.deleteByBoard(board);
    }

    @Transactional
    public void replyAddService(Reply reply, int id, PrincipalDetails principalDetails) {
        Board board=boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("아이디를 찾을 수 없습니다.");
                });
        Reply newReply=Reply.builder()
                .user(principalDetails.getUser())
                .board(board)
                .content(reply.getContent())
                .build();
        replyRepository.save(newReply);
    }

    @Transactional
    public void replyDeleteService(int id){
        replyRepository.deleteById(id);
    }

    @Transactional
    public void likeBoardService(int id, User user) {

        Board findBoard = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("아이디를 찾을 수 없습니다.");
                });
        System.out.println(findBoard.getLikeCount());
        if(!likesRepository.existsByUserAndBoard(user,findBoard)){
            findBoard.setLikeCount(findBoard.getLikeCount() + 1);
            Likes likes=Likes.builder()
                    .user(user)
                    .board(findBoard)
                    .build();
            likesRepository.save(likes);
        }
        else{
            findBoard.setLikeCount(findBoard.getLikeCount() - 1);
            likesRepository.deleteByUserAndBoard(user,findBoard);
        }
        System.out.println(findBoard.getLikeCount());
        boardRepository.save(findBoard);
    }

    @Transactional
    public void scrapBoardService(int id, User user) {

        Board findBoard = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("아이디를 찾을 수 없습니다.");
                });
        System.out.println(findBoard.getLikeCount());
        if(!scrapRepository.existsByUserAndBoard(user,findBoard)){
            Scrap scrap= Scrap.builder()
                    .user(user)
                    .board(findBoard)
                    .build();
            scrapRepository.save(scrap);
        }
        else{
            scrapRepository.deleteByUserAndBoard(user,findBoard);
        }
        System.out.println(findBoard.getLikeCount());
    }
}
