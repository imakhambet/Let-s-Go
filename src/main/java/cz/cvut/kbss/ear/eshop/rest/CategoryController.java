package cz.cvut.kbss.ear.eshop.rest;

import cz.cvut.kbss.ear.eshop.exception.NotFoundException;
import cz.cvut.kbss.ear.eshop.model.Category;
import cz.cvut.kbss.ear.eshop.model.Product;
import cz.cvut.kbss.ear.eshop.rest.util.RestUtils;
import cz.cvut.kbss.ear.eshop.service.CategoryService;
import cz.cvut.kbss.ear.eshop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/categories")
public class CategoryController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService service;

    private final ProductService productService;

    @Autowired
    public CategoryController(CategoryService service, ProductService productService) {
        this.service = service;
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> getCategories() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCategory(@RequestBody Category category) {
        service.persist(category);
        LOG.debug("Created category {}.", category);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", category.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Category getById(@PathVariable("id") Integer id) {
        final Category category = service.find(id);
        if (category == null) {
            throw NotFoundException.create("Category", id);
        }
        return category;
    }

    @RequestMapping(value = "/{id}/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProductsByCategory(@PathVariable("id") Integer id) {
        return productService.findAll(getById(id));
    }

    @RequestMapping(value = "/{id}/products", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addProductToCategory(@PathVariable("id") Integer id, @RequestBody Product product) {
        final Category category = getById(id);
        service.addProduct(category, product);
        LOG.debug("Product {} added into category {}.", product, category);
    }

    @RequestMapping(value = "/{categoryId}/products/{productId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeProductFromCategory(@PathVariable("categoryId") Integer categoryId,
                                          @PathVariable("productId") Integer productId) {
        final Category category = getById(categoryId);
        final Product toRemove = productService.find(productId);
        if (toRemove == null) {
            throw NotFoundException.create("Product", productId);
        }
        service.removeProduct(category, toRemove);
        LOG.debug("Product {} removed from category {}.", toRemove, category);
    }





}
