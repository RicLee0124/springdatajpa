package cn.sm1234.test;

import java.util.List;

import javax.annotation.Resource;

import cn.sm1234.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.sm1234.dao.IProductDao;

/**
 * 演示Repository接口的使用
 * @author lenovo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Demo1 {

	@Resource
	private IProductDao productDao;

	/**
	 * 第一种功能：基于方法命名规则查询
	 *   规则： findBy+规则
	 */
	@Test
	public void test1(){
		//查询产品名称为"小米手机"的产品
		List<Product> list = productDao.findByName("Mi phone");
		for (Product product : list) {
			System.out.println(product);
		}
	}

	@Test
	public void test2(){
		//查询产品名称包含"手机"的产品
		List<Product> list = productDao.findByNameLike("%手机%");
		for (Product product : list) {
			System.out.println(product);
		}
	}

	@Test
	public void test3(){
		//查询产品名称包含"手机",且价格大于等于3000的产品
		List<Product> list = productDao.findByNameLikeAndPriceGreaterThanEqual("%手机%",3000D);
		for (Product product : list) {
			System.out.println(product);
		}
	}

	/**
	 * 第二种功能：基于@Query注解查询
	 */


	@Test
	public void test4(){
		List<Product> list = productDao.queryName("小米手机");
		for (Product product : list) {
			System.out.println(product);
		}
	}

	@Test
	public void test5(){
		List<Product> list = productDao.queryName2("%手机%");
		for (Product product : list) {
			System.out.println(product);
		}
	}

	@Test
	public void test6(){
		List<Product> list = productDao.queryProducts("%手机%", 3000D, 5000D);
		for (Product product : list) {
			System.out.println(product);
		}
	}

	@Test
	@Transactional
	@Rollback(false)
	public void test7(){
		productDao.updateStore(3000, 4);
	}
}
