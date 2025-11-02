package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MutterDAO;
import model.Mutter;
import model.User;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MutterDAO dao = new MutterDAO();
		List<Mutter> mutterList = dao.findAll();
		
		request.setAttribute("mutterList", mutterList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//セッションからユーザー名を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		String userName = loginUser.getName();
		
		//パラメータからつぶやき内容を取得
		String text = request.getParameter("text");
		
		
		//入力値チェックOKならつぶやきを保存
		if(text != null && text.length() != 0) {

			MutterDAO dao = new MutterDAO();
			Mutter mutter = new Mutter(userName, text);
			dao.create(mutter);
			
	        // 再読み込みのためリダイレクト（フォーム二重送信防止）
	        response.sendRedirect("/docoTsubu/Main");
	        return;
			
		}else {
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "つぶやきが入力されていません");
			
	        // 最新の投稿一覧を再取得
	        MutterDAO dao = new MutterDAO();
	        List<Mutter> mutterList = dao.findAll();
	        request.setAttribute("mutterList", mutterList);
			
		}
		
		//メイン画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
		
		
	}
}
