package stepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class ApiStepDefinitions {
    Response response;

    static String name = "altor";
    static String key = "e52239ba83e8f6b978cf49e7e00842ac";
    static String token = "b0507839d792332969acc003ad1e5c5c3efe04b69f4ca363b42b9be560401f3f";

    static String idboard = "";
    static String idList = "";
    static List<String> cardİd = new ArrayList<>();
    JsonPath jsonPath;
    static int exceptedStatusCode=200;

    @Given("Trello üzerinde bir board oluşturunuz.")
    public void trello_üzerinde_bir_board_oluşturunuz() {


        String boardEndPoint = "https://api.trello.com/1/boards/?name=" + name + "&key="+key+"&token="+token+"";

        response = given().contentType(ContentType.JSON).when().post(boardEndPoint);
        response.then().statusCode(exceptedStatusCode);
        jsonPath = response.jsonPath();
        idboard = jsonPath.get("id");

        response.prettyPrint();

    }

    @When("Bir list oluşturunuz")
    public void bir_list_oluşturunuz() {


        String ListEndPoint = "https://api.trello.com/1/lists?name=" + name + "&idBoard=" + idboard + "&key="+key+"&token="+token+"";

        response = given().contentType(ContentType.JSON).when().post(ListEndPoint);
        response.then().statusCode(exceptedStatusCode);
        jsonPath = response.jsonPath();
        idList = jsonPath.get("id");

        response.prettyPrint();

    }


    @When("Oluşturduğunuz board’ a ilk kartı  oluşturunuz.")
    public void oluşturduğunuz_board_a_iki_tane_kart_oluşturunuz() {


        String cardEndPoint = "https://api.trello.com/1/cards?idList=" + idList + "&key="+key+"&token="+token+"";
        response = given().headers("Accept", ContentType.JSON).contentType(ContentType.JSON).when().post(cardEndPoint);
        response.then().statusCode(exceptedStatusCode);
        jsonPath = response.jsonPath();
        cardİd.add(jsonPath.get("id"));

        response.prettyPrint();

    }

    @When("Oluşturduğunuz board’ a ikinci kartı oluşturunuz.")
    public void oluşturduğunuz_board_a_ikinci_kartı_oluşturunuz() {


        String cardEndPoint2 = "https://api.trello.com/1/cards?idList=" + idList + "&key="+key+"&token="+token+"";
        response = given().headers("Accept", ContentType.JSON).contentType(ContentType.JSON).when().post(cardEndPoint2);
        response.then().statusCode(exceptedStatusCode);
        jsonPath = response.jsonPath();
        cardİd.add(jsonPath.get("id"));

        response.prettyPrint();


    }


    @When("Oluştrduğunuz bu iki karttan random olacak sekilde bir tanesini güncelleyiniz.")
    public void oluştrduğunuz_bu_iki_karttan_random_olacak_sekilde_bir_tanesini_güncelleyiniz() {


        Random rnd = new Random();

        String id = cardİd.get(rnd.nextInt(cardİd.size()));

        JSONObject cardPutBody = new JSONObject();
        cardPutBody.put("email", "semih22@gmail.com");

        String PutCardPoint = "https://api.trello.com/1/cards/" + id + "?key="+key+"&token="+token+"";

        response = given().contentType(ContentType.JSON).when().
                body(cardPutBody.toString()).
                put(PutCardPoint);

        Assert.assertTrue(response.statusCode() == 200);
    }

    @When("Oluşturduğunuz kartları siliniz.")
    public void oluşturduğunuz_kartları_siliniz() {

        for (int i = 0; i < cardİd.size(); i++) {

            String deleteCardEndpoint = "https://api.trello.com/1/cards/" + cardİd.get(i) + "?key="+key+"&token="+token+"";

            response = given().contentType(ContentType.JSON).when().delete(deleteCardEndpoint);

            Assert.assertTrue(response.statusCode() == 200);
        }


    }

    @When("Oluşturduğunuz board’ u siliniz")
    public void oluşturduğunuz_board_u_siliniz() {

        String deleteboardEndpoint = "https://api.trello.com/1/boards/" + idboard + "?key="+key+"&token="+token+"";

        response = given().contentType(ContentType.JSON).when().delete(deleteboardEndpoint);

        Assert.assertTrue(response.statusCode() == 200);


    }
}
