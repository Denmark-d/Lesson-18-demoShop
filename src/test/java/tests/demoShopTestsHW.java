package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class demoShopTestsHW {
    @Test
    void addToCartWithoutHDD(){
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_16_5_4=14&" +
                        "product_attribute_16_6_5=15" +
                        "&product_attribute_16_4_7=44" +
                        "&product_attribute_16_8_8=22&" +
                        "addtocart_16.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/16/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(false))
                //.body("message", hasItemInArray("Please select HDD"))
                .body("message", hasItem("Please select HDD"));
    }

    @Test
    void addToCartWithHDD(){
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_16_5_4=14&" +
                        "product_attribute_16_6_5=15&" +
                        "product_attribute_16_3_6=18&" +
                        "product_attribute_16_4_7=44" +
                        "&product_attribute_16_8_8=22&" +
                        "addtocart_16.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/16/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your " +
                        "<a href=\"/cart\">shopping cart</a>"));
    }

    @Test
    void subscribeNewsLetter() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("email=")
                .when()
                .post("http://demowebshop.tricentis.com/subscribenewsletter")
                .then()
                .log().all()
                .statusCode(200)
                .body("Success", is(false))
                .body("Result", is("Enter valid email"));
    }

    @Test
    void addToWishes() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_5_7_1=1&addtocart_5.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/5/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/wishlist\">wishlist</a>"))
                .body("updatetopwishlistsectionhtml", is("(1)"));
    }

    //"success": true,
    //    "message": "The product has been added to your <a href=\"/wishlist\">wishlist</a>",
    //    "updatetopwishlistsectionhtml": "(1)"
}

