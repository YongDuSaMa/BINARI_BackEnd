package ydsm.binari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ydsm.binari.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}

