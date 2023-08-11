package ydsm.binari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ydsm.binari.model.Board;
import ydsm.binari.model.LikesBoard;
import ydsm.binari.model.User;

public interface LikesBoardRepository extends JpaRepository<LikesBoard, Integer>{
    boolean existsByUserAndBoard(User user, Board board);
    void deleteByUserAndBoard(User user,Board board);
    void deleteByBoard(Board board);
}

