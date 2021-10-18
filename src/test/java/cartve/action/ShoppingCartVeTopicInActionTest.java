package cartve.action;

import akka.stream.javadsl.Source;
import cartve.action.ShoppingCartVeActionTopicIn;
import cartve.action.ShoppingCartVeTopicInAction;
import cartve.action.ShoppingCartVeTopicInActionTestKit;
import com.akkaserverless.javasdk.testkit.ActionResult;
import com.google.protobuf.Empty;
import org.junit.Test;
import static org.junit.Assert.*;

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public class ShoppingCartVeTopicInActionTest {

  @Test
  public void exampleTest() {
    ShoppingCartVeTopicInActionTestKit testKit = ShoppingCartVeTopicInActionTestKit.of(ShoppingCartVeTopicInAction::new);
    // use the testkit to execute a command
    // ActionResult<SomeResponse> result = testKit.someOperation(SomeRequest);
    // verify the response
    // SomeResponse actualResponse = result.getReply();
    // assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void addItemTest() {
    ShoppingCartVeTopicInActionTestKit testKit = ShoppingCartVeTopicInActionTestKit.of(ShoppingCartVeTopicInAction::new);
    // ActionResult<Empty> result = testKit.addItem(ShoppingCartVeActionTopicIn.AddLineItem.newBuilder()...build());
  }

  @Test
  public void changeItemTest() {
    ShoppingCartVeTopicInActionTestKit testKit = ShoppingCartVeTopicInActionTestKit.of(ShoppingCartVeTopicInAction::new);
    // ActionResult<Empty> result = testKit.changeItem(ShoppingCartVeActionTopicIn.ChangeLineItemQuantity.newBuilder()...build());
  }

  @Test
  public void removeItemTest() {
    ShoppingCartVeTopicInActionTestKit testKit = ShoppingCartVeTopicInActionTestKit.of(ShoppingCartVeTopicInAction::new);
    // ActionResult<Empty> result = testKit.removeItem(ShoppingCartVeActionTopicIn.RemoveLineItem.newBuilder()...build());
  }

  @Test
  public void checkoutCartTest() {
    ShoppingCartVeTopicInActionTestKit testKit = ShoppingCartVeTopicInActionTestKit.of(ShoppingCartVeTopicInAction::new);
    // ActionResult<Empty> result = testKit.checkoutCart(ShoppingCartVeActionTopicIn.CheckoutShoppingCart.newBuilder()...build());
  }

  @Test
  public void removeCartTest() {
    ShoppingCartVeTopicInActionTestKit testKit = ShoppingCartVeTopicInActionTestKit.of(ShoppingCartVeTopicInAction::new);
    // ActionResult<Empty> result = testKit.removeCart(ShoppingCartVeActionTopicIn.RemoveShoppingCart.newBuilder()...build());
  }

}
