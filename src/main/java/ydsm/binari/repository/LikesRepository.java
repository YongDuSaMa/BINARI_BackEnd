package ydsm.binari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ydsm.binari.model.Board;
import ydsm.binari.model.Likes;
import ydsm.binari.model.User;

public interface LikesRepository extends JpaRepository<Likes, Integer>{
    boolean existsByUserAndBoard(User user, Board board);
    void deleteByUserAndBoard(User user,Board board);
    void deleteByBoard(Board board);
}

