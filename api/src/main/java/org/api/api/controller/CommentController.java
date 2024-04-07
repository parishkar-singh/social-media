
package org.api.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.api.api.model.Comment;
import org.api.api.model.Reply;
import org.api.api.service.BlogService;
import org.api.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {

    @Autowired
    private CommentService commentService; 
    @Autowired
    private BlogService blogService;
    
    @PostMapping("/api/comment")
    public void createComment(@RequestBody @Valid @NotNull Comment comment) {
        try {
        	String createdCommentId =commentService.addComment(comment);
        	
        	blogService.addComment(comment.getBlogId(),createdCommentId);
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/api/comments")
    public List<Comment> showComments(@RequestBody @Valid @NotNull ArrayList<String> commentIds) {
        try {
        	
            return commentService.getComments(commentIds);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping("/api/comment/{commentId}")
    public void deleteComment(@PathVariable("commentId") String commentId){
        try {
        	String blogId=commentService.deleteComment(commentId);
        	blogService.deleteComment(commentId,blogId);
        } catch (Exception e) {
            throw e;
        }
        
    }
    
    @PutMapping("/api/comment/addUpvote/{commentId}")
    public void addUpvotes(@PathVariable("commentId") String commentId){
        try {
        	commentService.addUpvote(commentId);
        } catch (Exception e) {
            throw e;
        }
        
    }
    @PutMapping("/api/comment/removeUpvote/{commentId}")
    public void removeUpvotes(@PathVariable("commentId") String commentId){
        try {
        	commentService.removeUpvote(commentId);
        } catch (Exception e) {
            throw e;
        }
        
    }
   
    @PostMapping("/api/comment/reply")
    public void createReply(@RequestBody @Valid @NotNull Reply reply) {
        try {
              commentService.saveReply(reply);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping("/api/comment/reply/{replyId}")
    public void deleteReply(@PathVariable("replyId") String replyId){
        try {
        	commentService.deleteReply(replyId);
        } catch (Exception e) {
            throw e;
        }
        
    }
    


}