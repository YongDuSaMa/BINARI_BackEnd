package ydsm.binari.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(nullable = false,length = 200)
    private String content;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    private int likeCount=0;

    @OneToMany(mappedBy = "reply",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("reply")
    @OrderBy("id asc")
    private List<ReReply> reReplies;

    @CreationTimestamp
    private Timestamp createDate;
}
