package com.ws.factory;

import com.ws.action.Action;
import com.ws.action.AddCommentAction;
import com.ws.action.EditPostAction;
import com.ws.action.DeleteCommentAction;
import com.ws.action.DeletePostAction;
import com.ws.action.CheckOverlapAction;
import com.ws.action.GetCommentAction;
import com.ws.action.GetMainAction;
import com.ws.action.GetPostAction;
import com.ws.action.GoodAction;
import com.ws.action.LoadPage;
import com.ws.action.LoginAction;
import com.ws.action.RegistPostAction;
import com.ws.action.SearchPostAction;
import com.ws.action.SignupAction;
import com.ws.dao.CommentDAO;
import com.ws.dao.MemberDAO;
import com.ws.dao.PostDAO;

public class CmdFactory{
	private MemberDAO mdao = null;
	private PostDAO pdao = null;
	private CommentDAO cdao = null;

	public CmdFactory(MemberDAO mdao, PostDAO pdao, CommentDAO cdao) {
		this.mdao = mdao;
		this.pdao = pdao;
		this.cdao = cdao;
	}

	public Action cmdActionFactory(String cmd) {
		Action a = null;
		switch (cmd) {
		case "loginAction":
			a = new LoginAction(mdao);
			break;
		case "loadPage":
			a = new LoadPage();
			break;
		case "getMainAction":
			a = new GetMainAction(pdao);
			break;
		case "signupAction":
			a = new SignupAction(mdao);
			break;
		case "registPostAction":
			a = new RegistPostAction(pdao);
			break;
		case "getPostAction":
			a = new GetPostAction(pdao);
			break;
		case "searchPostAction":
			a = new SearchPostAction(pdao);
			break;
		case "editPostAction":
			a = new EditPostAction(pdao);
			break;
		case "addCommentAction":
			a = new AddCommentAction(cdao);
			break;
		case "getCommentAction":
			a = new GetCommentAction(cdao);
			break;
		case "deleteComment":
			a = new DeleteCommentAction(cdao);
			break;
		case "deletePostAction":
			a = new DeletePostAction(pdao);
			break;
		case "checkOverlapAction":
			a = new CheckOverlapAction(mdao,pdao);
			break;
		case "goodAction":
			a = new GoodAction(pdao);
			break;
		case "postBoard":
		default:
			a = new LoadPage();
			break;
		}
		return a;
	}
}
