package com.example.security.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ログアウト用コントローラー
 * @author education
 *
 */
@Controller
@RequestMapping("logout")
public class LogoutController {
	
	/**
	 * セッションオブジェクト
	 */
	@Autowired
	HttpSession session;
	
	/**
	 * ログアウト処理
	 * @return ログインへリダイレクト
	 */
	@GetMapping
	public String logout() {
		
		// セッションの破棄
		session.invalidate();
		
		// ログインページの表示
		return "redirect:login";
	}
}
