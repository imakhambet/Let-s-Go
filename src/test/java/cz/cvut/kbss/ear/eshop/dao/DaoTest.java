package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.EShopApplication;
import cz.cvut.kbss.ear.eshop.model.Cart;
import cz.cvut.kbss.ear.eshop.model.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
// DataJpaTest does not load all the application beans, it starts only persistence-related stuff
@DataJpaTest
// Exclude SystemInitializer from the startup, we don't want the admin account here
@ComponentScan(basePackageClasses = EShopApplication.class)
public class DaoTest {

    @Autowired
    protected CartDao cartDao;

    @Autowired
    protected CategoryDao categoryDao;

    @Autowired
    protected ItemDao itemDao;

    @Autowired
    protected OrderDao orderDao;

    @Autowired
    protected ProductDao productDao;

    @Autowired
    protected UserDao userDao;

    /**
     * This test checks if the spring application contexts contains all the DAO beans from the cz.cvut.kbss.ear.eshop.dao  package
     */
    @Test
    public void testRepositoriesInApplicationContext(){
        Assert.assertNotNull(cartDao);
        Assert.assertNotNull(categoryDao);
        Assert.assertNotNull(itemDao);
        Assert.assertNotNull(orderDao);
        Assert.assertNotNull(productDao);
        Assert.assertNotNull(userDao);
    }
}
