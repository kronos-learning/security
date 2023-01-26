package com.example.security.controller;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import com.example.security.entity.User;
import com.example.security.form.LoginForm;
import com.example.security.service.UserService;
import com.example.security.util.Csrf;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * ログイン用コントローラー
 * @author education
 *
 */
@Controller
@RequestMapping("login")
public class LoginController {
	
	/**
	 * ユーザーサービスオブジェクト
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * セッションオブジェクト
	 */
	@Autowired
	private HttpSession session;
	
	/**
	 * ログインページの作成
	 * @param loginForm ログイン情報のフォームオブジェクト
	 * @return ログインテンプレート
	 */
	@GetMapping
	public String show(@ModelAttribute("loginForm")LoginForm loginForm, HttpServletResponse response) {

		// CSRFトークンの設定
		// String csrf = Csrf.createToken();
		// session.setAttribute("csrf", csrf);
		// loginForm.setCsrf(csrf);
		
		// すべてのページで埋め込みを不許可
		// response.addHeader("X-FRAME-OPTIONS", "DENY");

		// Content－Typeから判断する
		// response.addHeader("X-CONTENT-TYPE-OPTIONS", "NOSNIFF");

		// ログインページの表示
		return "login";
	}
	
	/**
	 * ログイン処理
	 * @param loginForm ログイン情報のフォームオブジェクト
	 * @param redirectAttributes リダイレクト先に情報を送信するオブジェクト
	 * @param model テンプレートにフォワードされるモデルオブジェクト
	 * @return ログイン成功：書籍一覧へリダイレクト、ログイン失敗：ログインへリダイレクト
	 */
	@PostMapping
	public String login(LoginForm loginForm, 
						RedirectAttributes redirectAttributes,
						Model model) {
		
		// CSRFチェック
    // String csrf = (String)session.getAttribute("csrf");
    // session.removeAttribute("csrf");
    // if (csrf == null || !csrf.equals(loginForm.getCsrf())) {
    //   return "redirect:logout";
    // }

		// ログイン情報の取得
		String loginId = loginForm.getLoginId();
		String password = loginForm.getPassword();

		// 入力されたログイン情報をもとにユーザー情報の取得
		User user = userService.findByIdAndPassword(loginId, password);
		
		// ユーザー情報が取得できなければエラーとしてログインページにリダイレクト
		if (user == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "IDかパスワードが間違っています。");
			return "redirect:login";
		}
		
		// ユーザー情報が取得できれば、ユーザー情報をセッションに格納
		session.setAttribute("user", user);
		
		// 書籍一覧ページの表示
		return "redirect:books";
	}
}
