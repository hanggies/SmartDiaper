package kr.ac.hanggies.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hanggies.model.Product;

@Repository
public class ProductDao {
	private JdbcTemplate jdbcTemplate;

	// DataSource를 JdbcTemplate에 Dependency Injection
	@Autowired
	public void setDataSource(DataSource datasource) {
		jdbcTemplate = new JdbcTemplate(datasource);
	}

	public List<Product> getProducts() {

		String sqlStatement = "select * from product"; // record -> object

		return jdbcTemplate.query(sqlStatement, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

				Product product = new Product();

				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getInt("price"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setUnitInStock(rs.getInt("unitInStock"));
				product.setDescription(rs.getString("description"));

				return product;
			}

		});
	}

	public boolean addProduct(Product product) {

		String name = product.getName();
		String category = product.getCategory();
		int price = product.getPrice();
		String manufacturer = product.getManufacturer();
		int unitInStock = product.getUnitInStock();
		String description = product.getDescription();

		String sqlStatement = "insert into estore.product (name, category, price"
				+ ", manufacturer, unitInStock, description) values (?, ?, ?, ?, ?, ?)";

		return (jdbcTemplate.update(sqlStatement,
				new Object[] { name, category, price, manufacturer, unitInStock, description }) == 1);
	}

	public boolean deleteProduct(int id) {

		String sqlStatement = "delete from product where id = ?";

		return (jdbcTemplate.update(sqlStatement, new Object[] { id }) == 1);
	}

	public Product getProductById(int id) {
		String sqlStatement = "select * from product where id = ?"; // record -> object

		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { id }, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

				Product product = new Product();

				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getInt("price"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setUnitInStock(rs.getInt("unitInStock"));
				product.setDescription(rs.getString("description"));

				return product;
			}

		});
	}

	public boolean updateProduct(Product product) {
		int id = product.getId();
		String name = product.getName();
		String category = product.getCategory();
		int price = product.getPrice();
		String manufacturer = product.getManufacturer();
		int unitInStock = product.getUnitInStock();
		String description = product.getDescription();

		String sqlStatement = "update product set name=?, category=?, price=?"
				+ ", manufacturer=?, unitInStock=?, description=? where id=?";

		return (jdbcTemplate.update(sqlStatement,
				new Object[] { name, category, price, manufacturer, unitInStock, description, id}) == 1);

	}

}