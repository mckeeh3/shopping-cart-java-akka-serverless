package cartese.action;

import akka.stream.javadsl.Source;
import cartese.action.ShoppingCartEseActionTopicIn;
import cartese.action.ShoppingCartEseTopicInAction;
import cartese.action.ShoppingCartEseTopicInActionTestKit;
import com.akkaserverless.javasdk.testkit.ActionResult;
import com.google.protobuf.Empty;
import org.junit.Test;
import static org.junit.Assert.*;

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public class ShoppingCartEseTopicInActionTest {

  @Test
  public void exampleTest() {
    ShoppingCartEseTopicInActionTestKit testKit = ShoppingCartEseTopicInActionTestKit.of(ShoppingCartEseTopicInAction::new);
    // use the testkit to execute a command
    // ActionResult<SomeResponse> result = testKit.someOperation(SomeRequest);
    // verify the response
    // SomeResponse actualResponse = result.getReply();
    // assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void addItemTest() {
    ShoppingCartEseTopicInActionTestKit testKit = ShoppingCartEseTopicInActionTestKit.of(ShoppingCartEseTopicInAction::new);
    // ActionResult<Empty> result = testKit.addItem(ShoppingCartEseActionTopicIn.AddLineItem.newBuilder()...build());
  }

  @Test
  public void changeItemTest() {
    ShoppingCartEseTopicInActionTestKit testKit = ShoppingCartEseTopicInActionTestKit.of(ShoppingCartEseTopicInAction::new);
    // ActionResult<Empty> result = testKit.changeItem(ShoppingCartEseActionTopicIn.ChangeLineItemQuantity.newBuilder()...build());
  }

  @Test
  public void removeItemTest() {
    ShoppingCartEseTopicInActionTestKit testKit = ShoppingCartEseTopicInActionTestKit.of(ShoppingCartEseTopicInAction::new);
    // ActionResult<Empty> result = testKit.removeItem(ShoppingCartEseActionTopicIn.RemoveLineItem.newBuilder()...build());
  }

  @Test
  public void checkoutCartTest() {
    ShoppingCartEseTopicInActionTestKit testKit = ShoppingCartEseTopicInActionTestKit.of(ShoppingCartEseTopicInAction::new);
    // ActionResult<Empty> result = testKit.checkoutCart(ShoppingCartEseActionTopicIn.CheckoutShoppingCart.newBuilder()...build());
  }

  @Test
  public void removeCartTest() {
    ShoppingCartEseTopicInActionTestKit testKit = ShoppingCartEseTopicInActionTestKit.of(ShoppingCartEseTopicInAction::new);
    // ActionResult<Empty> result = testKit.removeCart(ShoppingCartEseActionTopicIn.RemoveShoppingCart.newBuilder()...build());
  }

}
