package ydsm.binari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ydsm.binari.model.LikesReply;
import ydsm.binari.model.ReReply;
import ydsm.binari.model.Reply;
import ydsm.binari.model.User;

public interface LikesReplyRepository extends JpaRepository<LikesReply,Integer> {

    boolean existsByUserAndReply(User user, Reply reply);
    void deleteByUserAndReply(User user,Reply reply);
    void deleteByReply(Reply reply);

    boolean existsByUserAndReReply(User user, ReReply reReply);
    void deleteByUserAndReReply(User user,ReReply reReply);
    void deleteByReReply(ReReply reReply);
}
