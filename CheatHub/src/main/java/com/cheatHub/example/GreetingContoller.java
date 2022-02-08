package com.cheatHub.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingContoller {
	
	private Object filtro;

	@RequestMapping("/search")
	public String greeting(Model model, @RequestParam String userName,String filtro) {
		/*Los atributos de esta función corresponden a:
		 1: "name": que corresponde al nombre de la plantilla del fichero html. ->index.html->{{name}}
		 2: "wordl": Lo que sustituimos en dicha palabra
		 */
		model.addAttribute("name",userName);
		if(filtro!=null) {
			model.addAttribute("fill",filtro);
		}
		else {
			model.addAttribute("fill","ninguno");
		}
		
		/*Devolvemos como cadena de texto el nombre del archivo html en el que se encuentra la plantilla
		que se debe de encontrar en la carpeta de templates.
		*/
		return "principal";
	}
	
	@RequestMapping("/category")
	public String greetingCategory(Model model, @RequestParam String boton) {
		/*Los atributos de esta función corresponden a:
		 1: "name": que corresponde al nombre de la plantilla del fichero html. ->index.html->{{name}}
		 2: "wordl": Lo que sustituimos en dicha palabra
		 */
			model.addAttribute("fill",boton);
		
		/*Devolvemos como cadena de texto el nombre del archivo html en el que se encuentra la plantilla
		que se debe de encontrar en la carpeta de templates.
		*/
		return "category";
	}

	@GetMapping("/")
	public String greetingCond(Model model) {
		
		return "index";
	}
}
