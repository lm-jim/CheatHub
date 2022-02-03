package com.cheatHub.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingContoller {
	
	@GetMapping("/")
	public String greeting(Model model) {
		/*Los atributos de esta función corresponden a:
		 1: "name": que corresponde al nombre de la plantilla del fichero html. ->index.html->{{name}}
		 2: "wordl": Lo que sustituimos en dicha palabra
		 */
		model.addAttribute("name","world");
		/*Devolvemos como cadena de texto el nombre del archivo html en el que se encuentra la plantilla
		que se debe de encontrar en la carpeta de templates.
		*/
		return "index";
	}
}
