package com.example.security.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.security.entity.Book;
import com.example.security.entity.User;
import com.example.security.form.BookForm;
import com.example.security.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Bookのコントローラークラス
 * @author education
 *
 */
@Controller
@RequestMapping("books")
public class BookController {
	
	/**
	 * セッションオブジェクト
	 */
	@Autowired
	HttpSession session;
	
	/**
	 * サービスオブジェクト
	 */
	@Autowired
	BookService bookService;

	/**
	 * 書籍一覧ページの作成
	 * @param model テンプレートにフォワードされるモデルオブジェクト
	 * @param redirectAttributes リダイレクト先に情報を送信するオブジェクト
	 * @return 書籍一覧テンプレート
	 */
	@GetMapping
	public String index(Model model, RedirectAttributes redirectAttributes) {

		// セッションチェック
		User loginUser = (User)session.getAttribute("user");
		if (loginUser == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "セッションが切れました。再度ログインしてください。");
			return "redirect:/login";
		}

		// 書籍一覧の取得
		List<Book> books = bookService.findAll();

		// Modelに書籍一覧を追加
		model.addAttribute("books", books);

		// 書籍一覧ページの表示
		return "book/index";
	}

	/**
	 * 書籍情報ページの作成
	 * @param id 書籍ID（パスパラメータ）
	 * @param model テンプレートにフォワードされるモデルオブジェクト
	 * @param bookForm 書籍情報のフォームオブジェクト
	 * @param redirectAttributes リダイレクト先に情報を送信するオブジェクト
	 * @return 書籍情報テンプレート
	 */
	@GetMapping("{id}")
	public String show(@PathVariable int id, Model model, @ModelAttribute BookForm bookForm, RedirectAttributes redirectAttributes) {

		// セッションチェック
		User loginUser = (User)session.getAttribute("user");
		if (loginUser == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "セッションが切れました。再度ログインしてください。");
			return "redirect:/login";
		}

		// 選択された書籍情報を取得（+Modelに追加）
		Book book = bookService.findById(id);
		bookForm.setName(book.getName());
		bookForm.setPrice(book.getPrice());
		model.addAttribute("id", id);

		// 書籍情報ページの表示
		return "book/show";
	}

	/**
	 * 書籍更新処理
	 * @param id 書籍ID（パスパラメータ）
	 * @param bookForm 書籍情報のフォームオブジェクト
	 * @param redirectAttributes ダイレクト先に情報を送信するオブジェクト
	 * @return 書籍一覧へリダイレクト
	 */
	@PutMapping("{id}")
	public String update(@PathVariable int id, BookForm bookForm, RedirectAttributes redirectAttributes) {

		// セッションチェック
		User loginUser = (User)session.getAttribute("user");
		if (loginUser == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "セッションが切れました。再度ログインしてください。");
			return "redirect:/login";
		}

		// 書籍情報の更新
		Book book = new Book(id, bookForm.getName(), bookForm.getPrice());
		bookService.update(book);

		// 書籍一覧ページの表示
		return "redirect:/books";
	}
}
