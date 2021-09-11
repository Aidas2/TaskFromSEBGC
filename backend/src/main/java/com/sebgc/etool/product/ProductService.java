package com.sebgc.etool.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sebgc.etool.product.IncomeRangeConstants.*;

@Service("SurveyService")
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> findProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    // Solution_1 (manipulating ranges as strings):
    public Product findRecommendedProductByRanges(String ageRange, boolean isStudent, String incomeRange) {
        // restore plus sign
        if (ageRange.contains(" ")) {
            ageRange = ageRange.replace(" ", "+");
        };
        if (incomeRange.contains(" ")) {
            incomeRange = incomeRange.replace(" ", "+");
        };

        return this.compute(ageRange, isStudent, incomeRange);
    }

    public Product compute(String ageRange, Boolean isStudent, String incomeRange) {
        Product recommendedProduct = null;
        if (ageRange.equals(AgeRange.YOUNG.description)) {
            recommendedProduct = productRepository.findProductByTitle(ProductEnum.JUNIOR.title);
        }
        if (ageRange.equals(AgeRange.SENIOR.description)) {
            recommendedProduct = productRepository.findProductByTitle(ProductEnum.SENIOR.title);
        }
        if (ageRange.equals(AgeRange.MIDDLE.description) && isStudent == true) {
            recommendedProduct = productRepository.findProductByTitle(ProductEnum.STUDENT.title);
        };
        if (ageRange.equals(AgeRange.MIDDLE.description)) {
            // this looks like semi-hardcoded ...
            switch (incomeRange) {
                case ZERO:
                    recommendedProduct = productRepository.findProductByTitle(ProductEnum.CURRENT.title);
                    break;
                case SMALL:
                    recommendedProduct = productRepository.findProductByTitle(ProductEnum.DEBIT.title);
                    break;
                case MEDIUM:
                    recommendedProduct = productRepository.findProductByTitle(ProductEnum.CREDIT.title);
                    break;
                case LARGE:
                    recommendedProduct = productRepository.findProductByTitle(ProductEnum.GOLD.title);
                    break;
                default:
                    recommendedProduct = null;
            }
        };

        return recommendedProduct;
    }

    // Solution_2 (manipulating numbers as integers):
    public List<Product> findRecommendedProducts(Integer minAge, Integer maxAge, Integer minIncome, Integer maxIncome, boolean isStudent) {
        List<Product> products = productRepository.findRecommendedProducts(minAge, maxAge, minIncome, maxIncome, isStudent);
        return products;
    }
}
