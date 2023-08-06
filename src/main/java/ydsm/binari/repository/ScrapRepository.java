package ydsm.binari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ydsm.binari.model.Board;
import ydsm.binari.model.Scrap;
import ydsm.binari.model.User;

public interface ScrapRepository extends JpaRepository<Scrap, Integer>{
    boolean existsByUserAndBoard(User user, Board board);
    void deleteByUserAndBoard(User user,Board board);
    void deleteByBoard(Board board);
}

