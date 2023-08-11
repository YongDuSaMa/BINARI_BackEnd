package ydsm.binari.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

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

  /*  @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Reply parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Reply> children = new ArrayList<>();
*/
    @CreationTimestamp
    private Timestamp createDate;
}
