package by.kolbun.example.controller;

import by.kolbun.example.services.ExampleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExampleController {

	@NonNull
	private final ExampleService exampleService;

	@RequestMapping("/main")
	public ModelAndView mainPage(ModelAndView mav, @RequestParam(value = "id", required = false) Long id) {

		mav.setViewName("main");

		String exampleText = id != null ? (id + " : " + exampleService.getTextById(id)) : "no query";

		mav.addObject("text", exampleText);
		mav.addObject("comment", new Comment());

		return mav;
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String postNewEntity(@ModelAttribute("comment") Comment comment) {

		long newId = exampleService.postNewEntity(comment.getText());

		return "redirect:/api/main?id=" + newId;
	}

}
