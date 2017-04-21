package kr.ac.jejunu;

/**
 * Created by super on 2017-04-21.
 */
public class ProductDaoFactory {

    public ProductDao productDao() {
         return new ProductDao(getConnectionMaker());
    }

    public JejuConnectionMaker getConnectionMaker() {
        return new JejuConnectionMaker();
    }
}
