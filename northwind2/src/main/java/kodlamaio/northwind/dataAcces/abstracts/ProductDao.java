package kodlamaio.northwind.dataAcces.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

public interface ProductDao extends JpaRepository<Product, Integer> // hangi entity e gireegini ve id nin hinteger oldugunu soyledık 
{
	
	Product getByProductName(String productName); // tek tek getırıyo
	
	Product getByProductNameAndCategory_CategoryId(String productName,int categoryId); // tek tek gelıyo
	
	List<Product> getByProductNameOrCategory_CategoryId(String productName,int categoryId); //liste getırıyo

	List<Product> getByCategory_CategoryIdIn(List<Integer> categories);
	
	List<Product> getByProductNameContains(String productName); 
	
	List<Product> getByProductNameStartsWith(String productName);
	
	@Query("from Product where productName=:productName and category.categoryId=:categoryId")
	List<Product> getByNameAndCategory(String productName,int categoryId);
	
	
	@Query("Select new kodlamaio.northwind.entities.dtos.ProductWithCategoryDto"
			+ " (p.id,p.productName,c.categoryName)"
			+ " From Category c Inner Join c.products p")
	List<ProductWithCategoryDto> getProductwithCategoryDetails();

}
