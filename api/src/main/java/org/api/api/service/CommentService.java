package org.api.api.service;

import java.util.ArrayList;

import org.api.api.model.Comment;
import org.api.api.model.Reply;

public interface CommentService {
	
	
	public String addComment(Comment c);
	public void deleteComment(String commentId);
	
	public ArrayList<Comment> showComments(ArrayList<String> commentIds);
	//list of commentIDs
	
	public void addUpvote(String commentId);
	public void removeUpvote(String commentId);
	
	public void saveReply(Reply r);
	public void deleteReply(String replyId);
	
	

}
