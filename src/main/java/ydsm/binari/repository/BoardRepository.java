package ydsm.binari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ydsm.binari.model.Board;
import ydsm.binari.model.BoardType;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer>{
    List<Board> findByBoardType(BoardType boardType);
}

