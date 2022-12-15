package waa.miu.alumnimgmtportal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import waa.miu.alumnimgmtportal.entity.Comment;
import waa.miu.alumnimgmtportal.entity.Faculty;
import waa.miu.alumnimgmtportal.entity.Student;
import waa.miu.alumnimgmtportal.entity.dto.CommentDto;
import waa.miu.alumnimgmtportal.repository.CommentRepo;
import waa.miu.alumnimgmtportal.security.MyUserDetails;
import waa.miu.alumnimgmtportal.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;
    private final ModelMapper modelMapper;
    @Override
    public List<CommentDto> findAll() {
        var cmts = commentRepo.findAll();
        List<CommentDto> comments = cmts.stream().filter(comment -> !comment.isDeleted()).map(comment -> modelMapper.map(comment, CommentDto.class)).toList();
        return comments;
    }

    @Override
    public CommentDto addComment(CommentDto comment) {
        int facultyId = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        Faculty faculty = new Faculty();
        faculty.setId(facultyId);
        Comment comment1 = modelMapper.map(comment, Comment.class);
        comment1.setFaculty(faculty);
        commentRepo.save(comment1);
        return modelMapper.map(comment1, CommentDto.class);
    }

    @Override
    public CommentDto updateComment(int id, CommentDto comment) {
        Comment comment1 = commentRepo.findById(id).orElseThrow(()->new RuntimeException("Comment Not found"));
        comment1 = modelMapper.map(comment, Comment.class);
        comment1.setId(id);
        commentRepo.save(comment1);
        return modelMapper.map(comment1, CommentDto.class);
    }

    @Override
    public void deleteComment(int id) {
        Comment comment = commentRepo.findById(id).orElseThrow(()->new RuntimeException("Comment Not Found"));
        comment.setDeleted(true);
        commentRepo.save(comment);
    }

    @Override
    public CommentDto findById(int id) {
        Comment comment = commentRepo.findById(id).filter(comment1 -> !comment1.isDeleted()).orElseThrow(()->new RuntimeException("Comment Not found"));
        return modelMapper.map(comment, CommentDto.class);
    }


    @Override
    public List<CommentDto> findCommentByStudent_Id(int id) {
        List<CommentDto> comments = commentRepo.findCommentByStudent_Id(id).stream().filter(comment -> !comment.isDeleted()).map(comment -> modelMapper.map(comment, CommentDto.class)).toList();
        return comments;
    }
}

