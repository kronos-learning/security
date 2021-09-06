package com.example.security.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * エラー用コントローラー
 * @author education
 *
 */
@Controller
@RequestMapping("/error")
public class MyErrorController implements ErrorController {

	public String getErrorPath() {
		return "/error";
	}

	/**
	 * エラーページ
	 * @return エラーテンプレート
	 */
	@GetMapping
	public String error() {
		
		// エラーページの表示
		return "/error";
	}
}