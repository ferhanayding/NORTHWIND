package kodlamaio.northwind.api.apiController;

import java.util.HashMap;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.ErrorDataResult;



@RestController
@RequestMapping(value="/api/users")
public class UsersController {

	private UserService service;
	
	
	public UsersController(UserService service) {
		super();
		this.service = service;
	}


	@PostMapping(value="/add")
	public 	ResponseEntity<?> add(@Valid @RequestBody User user){
		
		return ResponseEntity.ok(this.service.add(user));
		
	}
//	herhangi bi yerd hata alırsak mesela email icin veya password gibi entity katmanında notnull un yanına bi parantes icine message acıp yazabılırız
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	bu sistemde bu turde bi hata olursa bu metodu devreye sok
	public ErrorDataResult<Object> handlerValidationException(MethodArgumentNotValidException exceptions){

		//map yapısını kullanıcaz sol string yazılan yer anahtar yani hangi varlıgımızı kulanıcaz mesela email
//		olsun sol tarafta onun hatası ıcın ne gondereecegımız string olarak mesaj gonderecegız
		
		Map<String,String> validationErrors = new HashMap<String, String>();
		
		for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			
			validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
		}
		
		ErrorDataResult<Object> error = new ErrorDataResult<Object>(validationErrors,"dogrulama hataları"); 
       return error;	
	}
	
	
}
