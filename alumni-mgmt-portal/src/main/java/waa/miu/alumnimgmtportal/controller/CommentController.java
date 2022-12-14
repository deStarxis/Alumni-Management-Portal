package waa.miu.alumnimgmtportal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import waa.miu.alumnimgmtportal.entity.dto.CommentDto;
import waa.miu.alumnimgmtportal.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
@CrossOrigin
public class CommentController {
    private final CommentService commentService;
    @GetMapping
    public List<CommentDto> getAllComments(){
        var x = commentService.findAll();
        return x;
    }
    @GetMapping("/students/{studentId}")
    public List<CommentDto> findCommentByStudent_Id(@PathVariable int studentId){
        return commentService.findCommentByStudent_Id(studentId);
    }

    @GetMapping("/{id}")
    public CommentDto getComment(@PathVariable int id){
        return commentService.findById(id);
    }
    @PostMapping
    public CommentDto addComment(@RequestBody CommentDto comment){
        return commentService.addComment(comment);
    }
    @PutMapping("/{id}")
    public CommentDto updateComment(@PathVariable int id, @RequestBody CommentDto comment){
        return commentService.updateComment(id,comment);
    }
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable int id){
        commentService.deleteComment(id);
    }
}
