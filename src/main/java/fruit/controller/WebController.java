
package fruit.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fruit.beans.Fruit;
import fruit.repository.FruitRepository;

@Controller
public class WebController {
	
	@Autowired
	FruitRepository repo;
	
	@GetMapping("/viewAll")
	public String viewAllFruits(Model model) {
		model.addAttribute("fruits", repo.findAll());
		return "results";
	}
	
	
	@GetMapping("/inputFruit")
	public String addNewFruit(Model model) {
	    Fruit c = new Fruit();
	    model.addAttribute("newFruit", c);
	    return "input";
	}
	
	@PostMapping("/inputFruit")
	public String addNewFruit(@ModelAttribute Fruit c, Model model) {
		repo.save(c);
		model.addAttribute("fruits", repo.findAll());
		return "results";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
	    Fruit c = repo.findById((long) id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    model.addAttribute("fruit", c);
	    return "update";
	}

	
	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid Fruit c, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        c.setId(id);
	        return "update";
	    }
	         
	    repo.save(c);
	    model.addAttribute("fruits", repo.findAll());
		return "results";
	}
	     
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
	    Fruit c = repo.findById((long) id).orElseThrow(() 
	    		-> new IllegalArgumentException("Invalid user Id:" + id));
	    repo.delete(c);
	    model.addAttribute("fruits", repo.findAll());
		return "results";
	}

}