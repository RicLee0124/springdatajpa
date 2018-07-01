package cn.sm1234.dao;

import java.util.List;

import cn.sm1234.domain.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface IProductDao extends Repository<Product, Integer>{

	//第一种方式：基于方法命名规则查询
	public List<Product> findByName(String string);

	public List<Product> findByNameLike(String string);

	public List<Product> findByNameLikeAndPriceGreaterThanEqual(String string,double d);


	//第二种方式：基于@Query注解查询, 编写JPQL语句，JPA的查询语句（类似hibernate的HQL语句）
	@Query(value="from Product where name = ?")
	public List<Product> queryName(String name);

	@Query(value="from Product where name like ?")
	public List<Product> queryName2(String name);

	@Query(value="from Product where name like ? and price between ? and ?")
	public List<Product> queryProducts(String name,Double price1,Double price2);


	//修改id为4的商品，把store修改为3000
	@Query("update Product set store=? where id=?")
	@Modifying // 如果是修改操作必须加上此注解
	public void updateStore(Integer store,Integer id);
}
