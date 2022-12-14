package waa.miu.alumnimgmtportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import waa.miu.alumnimgmtportal.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findCommentByStudent_Id(int id);
}
