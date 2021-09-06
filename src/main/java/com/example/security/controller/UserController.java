package com.example.security.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.security.entity.User;
import com.example.security.form.UserForm;
import com.example.security.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("users")
public class UserController {

	/**
	 * セッションオブジェクト
	 */
	@Autowired
	HttpSession session;

	/**
	 * サービスオブジェクト
	 */
	@Autowired
	UserService userService;

	/**
	 * ユーザー一覧ページの作成
	 * @param model テンプレートにフォワードされるモデルオブジェクト
	 * @param redirectAttributes リダイレクト先に情報を送信するオブジェクト
	 * @return ユーザー一覧テンプレート
	 */
	@GetMapping
	public String index(Model model, RedirectAttributes redirectAttributes) {
		
		// セッションチェック
		User loginUser = (User)session.getAttribute("user");
		if (loginUser == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "セッションが切れました。再度ログインしてください。");
			return "redirect:/login";
		}
		// ユーザー一覧の取得
		List<User> users = userService.findAll();

		// Modelにユーザー一覧を追加
		model.addAttribute("users", users);

		// ユーザー一覧ページの表示
		return "user/index";
	}

	/**
	 * ユーザー情報ページの作成
	 * @param id ユーザーID（パスパラメータ）
	 * @param model テンプレートにフォワードされるモデルオブジェクト
	 * @param userForm ユーザー情報のフォームオブジェクト
	 * @param redirectAttributes リダイレクト先に情報を送信するオブジェクト
	 * @return ユーザー情報テンプレート
	 */
	@GetMapping("{id}")
	public String show(@PathVariable int id, Model model, @ModelAttribute UserForm userForm, RedirectAttributes redirectAttributes) {

		// セッションチェック
		User loginUser = (User)session.getAttribute("user");
		if (loginUser == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "セッションが切れました。再度ログインしてください。");
			return "redirect:/login";
		}

		// 選択されたユーザー情報の取得（＋Modelに追加）
		User user = userService.findById(id);
		userForm.setLoginId(user.getLoginId());
		userForm.setAdmin(user.getAdmin());
		model.addAttribute("id", id);

		// ユーザー情報ページの表示
		return "user/show";
	}

	/**
	 * ユーザー情報更新処理
	 * @param id ユーザーID(パスパラメータ）
	 * @param userForm ユーザー情報のフォームオブジェクト
	 * @param redirectAttributes リダイレクト先に情報を送信するオブジェクト
	 * @return ユーザー一覧へリダイレクト
	 */
	@PutMapping("{id}")
	private String update(@PathVariable int id, UserForm userForm, RedirectAttributes redirectAttributes) {

		// セッションチェック
		User loginUser = (User)session.getAttribute("user");
		if (loginUser == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "セッションが切れました。再度ログインしてください。");
			return "redirect:/login";
		}
		
		// ユーザーの更新
		User user = new User(id, userForm.getLoginId(), userForm.getAdmin());
		userService.update(user);

		// ユーザー一覧ページの表示
		return "redirect:/users";
	}
}
