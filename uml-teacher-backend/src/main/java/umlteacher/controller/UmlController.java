package umlteacher.controller;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/uml")
public class UmlController {
	@Autowired
	private FakeStorage fakeStorage;
	
	@GetMapping("/{key}")
	public String get(@PathVariable String key) {
		return fakeStorage.get(key);
	}
	
	@PostMapping("/{key}")
	public void put(@PathVariable String key, @RequestBody String body) {
		fakeStorage.put(key, body);
	}
	
	
	@Component
	public class FakeStorage {
		private Map<String, String> storage = new HashMap<String, String>();
		
		public String get(String key) {
			return storage.getOrDefault(key, null);
		}
		
		public void put(String key, String value) {
			if (storage.containsKey(key)) {
				storage.remove(key);
			}
			storage.put(key, value);
		}
	}
}
