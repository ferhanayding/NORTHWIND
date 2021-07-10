package kodlamaio.northwind.api.apiController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

@RestController // sen bi restcontrollersin  
@RequestMapping("/api/products") // react vs bunlara entegre etmek ıcın
public class ProductsController { 

	private ProductService  productService;
	
	@Autowired
	public ProductsController(ProductService productService) {
	super();
	this.productService = productService;
}

	@GetMapping("/getall")
	public DataResult<List<Product>> geetAll(){ // burda controller icinde reactla vs uyumlu olası icin get all ın ne is yapacagını soyluyoruz
		
		return this.productService.getAll();
	
}
	@PostMapping("/add")
	public Result add(@RequestBody Product product) { //requestbody ekleme yaptıgımızda bir yer olusturur...
		return this.productService.add(product);	
	}
//http://localhost:8080/swagger-ui.html	

	@GetMapping("/getByProductName")
	public DataResult<Product> getByProductName(@RequestParam String productName) {
		
		return this.productService.getByProductName(productName);
	
	}
	@GetMapping("/getByProductNameAndCategory")
	public DataResult<Product> getByProductNameAndCategory(@RequestParam("productName") String productName,
			@RequestParam("categoryId") int categoryId){
		
		return this.productService.getByProductNameAndCategory(productName, categoryId);
	}
	@GetMapping("/getByProductNameOrCategory")
	public DataResult<List<Product>>  getByProductNameOrCategoryId(@RequestParam String productName,@RequestParam int categoryId){
		
		return this.productService.getByProductNameOrCategory(productName, categoryId);
	
	}
	@GetMapping("/getByCategory_CategoryIdIn")
	public DataResult<List<Product>>  getByCategory_CategoryIdIn( List<Integer> categories){
		
		return this.productService.getByCategory_CategoryIdIn(categories);
		
	}
	
	
	@GetMapping("/getByProductNameContains")
	public DataResult<List<Product>>  getByProductNameContains(@RequestParam String productName){
		
		return this.productService.getByProductNameContains(productName);
	
	}
	@GetMapping("/getByProductNameStartsWith")
	public DataResult<List<Product>>  getByProductNameStartsWith(@RequestParam String productName){
		
		return this.productService.getByProductNameStartsWith(productName);
		
	}
	
	@GetMapping("/getByNameAndCategory")
	public DataResult<Product>  getByNameAndCategory(@RequestParam String productName,@RequestParam int categoryId){
		
		return this.productService.getByProductNameAndCategory(productName, categoryId);
	}
	
	@GetMapping("/getAllByPage")
	DataResult<List<Product>> getAll(int pageNo,int pageSize){
		
		return this.productService.getAll(pageNo,pageSize);
		
	}
	@GetMapping("/getAllSorted")
	public DataResult<List<Product>> getAllSorted(){
		
		return this.productService.getAllSorted();	
	}
	
	@GetMapping("/getProductwithCategoryDetails")
	public DataResult<List<ProductWithCategoryDto>> getProductwithCategoryDetails(){
		
		return this.productService.getProductwithCategoryDetails();
		
	}
}



