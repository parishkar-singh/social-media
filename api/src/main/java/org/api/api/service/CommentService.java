package org.api.api.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.api.api.model.Comment;
import org.api.api.model.Reply;

public interface CommentService {
	
	
	public String addComment(Comment c);
	public String deleteComment(String commentId);
	public HashMap<Comment, ArrayList<Reply>> getCommentAndReply(ArrayList<String> commentIds);

	public ArrayList<Comment> getComments(ArrayList<String> commentIds);
	//list of commentIDs
	
	public void addUpvote(String commentId);
	public void removeUpvote(String commentId);
	
	public void saveReply(Reply r);
	public void deleteReply(String replyId);
	
	

}
