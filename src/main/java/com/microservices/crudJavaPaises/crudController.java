package com.microservices.crudJavaPaises;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList; 
import java.util.List;
// Librerías para el JSON:
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class crudController {
	//Creación de elementos
	private List<String> items = new ArrayList<String>();
	
	//Constructor de la clase
	public crudController() {
		items.add("Colombia");
		items.add("Brasil");
		items.add("Argentina");
		items.add("Peru");
		items.add("Chile");
		items.add("Venezuela");
		items.add("Bolivia");
	}
	
    // Creación de elementos (POST)
	@PostMapping
	public String addItem(@RequestBody String newItem) {
		items.add(newItem);
		return "Item insertado con éxito!!";
	}
	
	// Consulta de elementos (GET)
    //1. Para acceder a todos los elementos guardados en lista "items":
	//1.1 Forma sin JSON:
	@GetMapping
	public List<String> getAllItems() {
        return items;
    }
	
	//1.2 Para que retorne los elementos como un JSON:
	/*@GetMapping
	public Map<String, Object> getAllItems() { 
		// mapa que va a recibir un string y un objeto (la lista)
    	Map<String, Object> response = new HashMap<>();
    	// mediante metodo put se insertan datos a estructura response:
    	response.put("items", items); // elementos guardados en la lista
    	response.put("count", items.size()); //conteo
        return response;
    }*/
    
	//2. Para acceder a solo un elemento del arreglo:
    @GetMapping("/{index}") //valor que entrega el usuario
    public String getItem(@PathVariable int index) {
    	//validar que el index enviado corresponda con la cantidad de elementos que estan en la lista:
    	if(index >= 0 && index < items.size()) {
    		return items.get(index);
    	}else {
    		return "Item no encontrado!";
    	}
    }
    
    // Actualización de elementos (PUT)
    @PutMapping("/{index}")
    // Parametros: (indice a modificar por la url, valor nuevo en el cuerpo)
    public String modifyItem(@PathVariable int index, @RequestBody String newItem) {
    	// validar que existe indice:
    	if(index >= 0 && index < items.size()) {
    		items.set(index, newItem);
    		return "Item actualizado con éxito";
    	}else {
    		return "Item no encontrado!";
    	}
    }
    
    // Eliminación de elementos (DELETE)
    @DeleteMapping("/{index}")
    public String deleteItem(@PathVariable int index) {
    	// validar que existe indice:
    	if(index >= 0 && index < items.size()) {
    		items.remove(index);
    		return "Item eliminado con éxito";
    	}else {
    		return "Item no encontrado!";
    	}
    }
}
