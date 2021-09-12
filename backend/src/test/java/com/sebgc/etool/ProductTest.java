package com.sebgc.etool;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebgc.etool.product.AgeRange;
import com.sebgc.etool.product.IncomeRangeConstants;
import com.sebgc.etool.product.Product;
import com.sebgc.etool.product.ProductController;
import com.sebgc.etool.product.ProductEnum;
import com.sebgc.etool.product.ProductRepository;
import com.sebgc.etool.product.ProductService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductTest {

    @MockBean
    ProductService productService;

    @MockBean
    ProductRepository productRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void it_should_return_eight () throws Exception {
//
//        List<Product> products = new ArrayList<>();
//        when(productService.findProducts()).thenReturn(products);
//
//        mockMvc.perform(get("/products/all"))
//                .andExpect(status().isOk())
//                .andExpect(8).value();
//    }

    @Test
    public void findProductsQuantity() {
        List<Product> products = productService.findProducts();
        int expectedQuantity = 8;

        assertEquals(expectedQuantity, products.size());
    }

    @Test
    public void findProductByTitle() {
        String expectedTitle = ProductEnum.SENIOR.getTitle();
        Product product = productRepository.findProductByTitle(expectedTitle);

        assertEquals(expectedTitle, product.getTitle());
    }


    @Test
    public void findRecommendedProductsForStudent() {
        String ageRange = AgeRange.MIDDLE.getDescription();
        boolean isStudent = true;
        String incomeRange = IncomeRangeConstants.MEDIUM;
        String expectedTitle = ProductEnum.STUDENT.getTitle();

        List<Product> products = productService.findRecommendedProductByRanges(ageRange, isStudent, incomeRange);
        assertEquals(expectedTitle, products.get(0).getTitle());
    }

    @Test
    public void findGoldCredit() {
        String ageRange = AgeRange.MIDDLE.getDescription();
        boolean isStudent = false;
        String incomeRange = IncomeRangeConstants.LARGE;
        String expectedTitle = ProductEnum.GOLD.getTitle();

        List<Product> products = productService.findRecommendedProductByRanges(ageRange, isStudent, incomeRange);
        assertEquals(expectedTitle, products.get(0).getTitle());
    }
}
