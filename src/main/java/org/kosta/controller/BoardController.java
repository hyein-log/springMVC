package org.kosta.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kosta.model.dto.BoardVO;
import com.kosta.myapp.model.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	Logger logger = LoggerFactory.getLogger(DeptController.class);

	@Autowired
	BoardService bservice;

	@RequestMapping(value = "/boardList.do", method = RequestMethod.GET)
	public void boardList(Model model, HttpServletRequest request) {

		logger.info("��� ��ȸ�մϴ�.");
		List<BoardVO> blist = bservice.select();
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		String resultMessage = null;
		if (flashMap != null) {
			flashMap.get("message");
			resultMessage = (String) flashMap.get("resultMessage");
		}
		model.addAttribute("resultMessage", resultMessage);
		model.addAttribute("boardLists", blist);
	}

	@RequestMapping(value = "/boardInsert.do", method = RequestMethod.GET)
	public String boardInsert() {
		logger.info("�Է��������� �����ݴϴ�.");
		return "board/boardInsert";
	}

	@RequestMapping(value = "/boardInsert.do", method = RequestMethod.POST)
	public String boardInsert(BoardVO board, RedirectAttributes redirectAttr) {
		logger.info("�Է� : " + board.toString());
		int result = bservice.insert(board);
		redirectAttr.addFlashAttribute("resultMessage", result + "�� �Է�");
		return "redirect:/board/boardList.do";
	}

	@RequestMapping(value = "/boardDetail.do", method = RequestMethod.GET)
	public String boardDetail(int boardid, Model model) {
		
		BoardVO board = bservice.selectByBno(boardid);
		model.addAttribute("board", board);
		logger.info("�󼼺��� �Դϴ�. "+ board);
		return "board/boardDetail";
	}

	@RequestMapping(value = "/boardUpdate.do", method = RequestMethod.POST)
	public String boardUpdate(BoardVO board, int bno, RedirectAttributes redirectAttr) {
		logger.info("���� : " + board.toString());
		int result = bservice.update(board, bno);
		redirectAttr.addFlashAttribute("resultMessage", result + "�� ����");
		return "redirect:/board/boardList.do";
	}

	@RequestMapping(value = "/boardDelete.do", method = RequestMethod.GET)
	public String boardDelete(int bno, Model model, RedirectAttributes redirectAttr) {
		int result = bservice.delete(bno);
		logger.info(bno + "�� " + result + "�� �����մϴ�.");
		redirectAttr.addFlashAttribute("resultMessage", result + "�� ����");
		return "redirect:/board/boardList.do";
	}
}
