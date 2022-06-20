import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class APITests {
    public JSONObject request = new JSONObject();
    public JSONObject requestCategory = new JSONObject();
    public JSONObject requestTags = new JSONObject();

    @BeforeMethod
    public void requestData(){

        //baseURI = "https://petstore.swagger.io";
        //basePath = "/v2/pet";
        //RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        //requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();

        //Category Name
        requestCategory.put("name", DataTest.getCategoryName());
        request.put("category", requestCategory);

        //Pet Name
        request.put("name", DataTest.getPetName());

        //Tags Name
        requestTags.put("name", DataTest.getTagsName());
        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
        jsonObjects.add(requestTags);
        request.put("tags", jsonObjects);

        //Status
        request.put("status", "available");
    }

    @Test(priority=1)
    public void testPost(ITestContext context) {

        long idPet =
        given().
                log().all().
                baseUri("https://petstore.swagger.io").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
                post("/v2/pet").
        then().
                log().all().
                assertThat().statusCode(200).
                body("name", containsString("Blacky")).
                extract().jsonPath().get("id");

        context.setAttribute("id", idPet);

        System.out.println("ID PET CREATED: " + idPet);
    }

    @Test(priority=2)
    public void testGet(ITestContext context){

        long idPet = (long) context.getAttribute("id");

        given().
                log().all().
                baseUri("https://petstore.swagger.io").
        when().
                get("/v2/pet/" + idPet).
        then().
                log().all().
                assertThat().statusCode(200).
                body("name", containsString("Blacky"));
    }

    @Test(priority=3)
    public void testPut(){

        given().
                log().all().
                baseUri("https://petstore.swagger.io").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
                put("/v2/pet/").
        then().
                log().all().
                assertThat().statusCode(200).
                body("name", containsString("Blacky"));
    }

    @Test(priority=4)
    public void testDelete(ITestContext context){

        long idPet = (long) context.getAttribute("id");
        String messageResponse = Long.toString(idPet);

        given().
                log().all().
                baseUri("https://petstore.swagger.io").
        when().
                delete("/v2/pet/" + idPet).
        then().
                log().all().
                assertThat().statusCode(200).
                body("message", equalTo(messageResponse));
    }

}
