package org.api.api.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;

import org.api.api.model.Comment;
import org.api.api.model.Reply;
import org.api.api.repository.CommentRepository;
import org.api.api.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private ReplyRepository replyRepo;
	
	
	@Override
	public String addComment(@Valid @NotNull Comment comment) {
		
		Comment cd = commentRepo.save(comment);
		return cd.getcommentId();
	}
	@Override
	public String deleteComment(String commentId) {
		
		Optional<Comment> delComment =commentRepo.findById(commentId);
		if(delComment.isPresent())
		{
		commentRepo.delete(delComment.get());
		return delComment.get().getBlogId();
		}
		return null;
	}
	
	@Override
	public ArrayList<Comment>getComments(ArrayList<String> commentIds) {
		
		ArrayList<Comment> commentSet =new ArrayList<>();
		
		for(int i=0;i<commentIds.size();i++) {
			Optional<Comment> comments =commentRepo.findById(commentIds.get(i));
			if(comments.isPresent()) 
				commentSet.add(comments.get());		
			}
		 commentSet.sort(Comparator.comparingInt(Comment::getUpvotes));
		return commentSet;
	}
	@Override
	public HashMap<Comment, ArrayList<Reply>> getCommentAndReply(ArrayList<String> commentIds) {
		// TODO Auto-generated method stub
		HashMap<Comment, ArrayList<Reply>> fullComment = new HashMap<>();
		for(int i=0;i<commentIds.size();i++) {
			Optional<Comment> comments =commentRepo.findById(commentIds.get(i));
			if(comments.isPresent())
			{
				Comment temp =comments.get();
				ArrayList<Reply> replies =new ArrayList<>();
				
				for(String replyIds:temp.getReplyIDs()) {
					Reply existingReply =replyRepo.findById(replyIds).orElse(null);
			        if (existingReply != null)
			        	replies.add(existingReply);	
				}	
				fullComment.put(temp, replies);
			}
		}
		return fullComment;
	}
	
	@Override
	public void addUpvote(String commentId) {
		
		Optional<Comment> comments =commentRepo.findById(commentId);
		if(comments.isPresent()) {
			Comment temp =comments.get();
			int tempUpvotes = temp.getUpvotes();
			temp.setUpvotes(tempUpvotes+1);
			System.out.println(tempUpvotes);
			commentRepo.save(temp);
		}
		
	}
	@Override
	public void removeUpvote(String commentId) {
		
		Optional<Comment> comments =commentRepo.findById(commentId);
		if(comments.isPresent()) {
			Comment temp =comments.get();
			int tempUpvotes = temp.getUpvotes();
			temp.setUpvotes(tempUpvotes-1);
			System.out.println(tempUpvotes);
			commentRepo.save(temp);
		}
		
	}
	@Override
	public void saveReply(Reply reply) {
		
		Reply savedReply= replyRepo.save(reply);
		
		Optional<Comment> comments =commentRepo.findById(reply.getCommentId());
		if(comments.isPresent()) {
			comments.get().addReplyIds(savedReply.getReplyId());
			commentRepo.save(comments.get());

		}
	}
	
	@Override
	public void deleteReply(String replyId) {
		// TODO Auto-generated method stub
		Optional<Reply> delReply =replyRepo.findById(replyId);
		if(delReply.isPresent())
		{
		 Optional<Comment> comments =commentRepo.findById(delReply.get().getCommentId());
			if(comments.isPresent()) {
				comments.get().deleteReplyIds(replyId);
			}
		 
		replyRepo.delete(delReply.get());
		
		}
		
		
	}
	
	
	
	
	
	
	
}
