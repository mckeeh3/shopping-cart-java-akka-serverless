package cartese.action;

import com.akkaserverless.javasdk.action.ActionCreationContext;
import com.google.protobuf.Empty;

import cartese.api.ShoppingCartEseApi;

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

/** An action. */
public class ShoppingCartEseTopicInAction extends AbstractShoppingCartEseTopicInAction {

  public ShoppingCartEseTopicInAction(ActionCreationContext creationContext) {
  }

  @Override
  public Effect<Empty> addItem(ShoppingCartEseActionTopicIn.AddLineItem addLineItem) {
    return effects().forward(components().shoppingCartEse().addItem(toApi(addLineItem)));
  }

  @Override
  public Effect<Empty> changeItem(ShoppingCartEseActionTopicIn.ChangeLineItemQuantity changeLineItemQuantity) {
    return effects().forward(components().shoppingCartEse().changeItem(toApi(changeLineItemQuantity)));
  }

  @Override
  public Effect<Empty> removeItem(ShoppingCartEseActionTopicIn.RemoveLineItem removeLineItem) {
    return effects().forward(components().shoppingCartEse().removeItem(toApi(removeLineItem)));
  }

  @Override
  public Effect<Empty> checkoutCart(ShoppingCartEseActionTopicIn.CheckoutShoppingCart checkoutShoppingCart) {
    return effects().forward(components().shoppingCartEse().checkoutCart(toApi(checkoutShoppingCart)));
  }

  @Override
  public Effect<Empty> removeCart(ShoppingCartEseActionTopicIn.RemoveShoppingCart removeShoppingCart) {
    return effects().forward(components().shoppingCartEse().removeCart(toApi(removeShoppingCart)));
  }

  private ShoppingCartEseApi.AddLineItem toApi(ShoppingCartEseActionTopicIn.AddLineItem addLineItem) {
    return ShoppingCartEseApi.AddLineItem.newBuilder()
        .setCartId(addLineItem.getCartId())
        .setCustomerId(addLineItem.getCustomerId())
        .setProductId(addLineItem.getProductId())
        .setName(addLineItem.getName())
        .setQuantity(addLineItem.getQuantity())
        .build();
  }

  private ShoppingCartEseApi.ChangeLineItemQuantity toApi(ShoppingCartEseActionTopicIn.ChangeLineItemQuantity changeLineItemQuantity) {
    return ShoppingCartEseApi.ChangeLineItemQuantity
        .newBuilder()
        .setCartId(changeLineItemQuantity.getCartId())
        .setProductId(changeLineItemQuantity.getProductId())
        .setQuantity(changeLineItemQuantity.getQuantity())
        .build();
  }

  private ShoppingCartEseApi.RemoveLineItem toApi(ShoppingCartEseActionTopicIn.RemoveLineItem removeLineItem) {
    return ShoppingCartEseApi.RemoveLineItem
        .newBuilder()
        .setCartId(removeLineItem.getCartId())
        .setProductId(removeLineItem.getProductId())
        .build();
  }

  private ShoppingCartEseApi.CheckoutShoppingCart toApi(ShoppingCartEseActionTopicIn.CheckoutShoppingCart checkoutShoppingCart) {
    return ShoppingCartEseApi.CheckoutShoppingCart
        .newBuilder()
        .setCartId(checkoutShoppingCart.getCartId())
        .build();
  }

  private ShoppingCartEseApi.RemoveShoppingCart toApi(ShoppingCartEseActionTopicIn.RemoveShoppingCart removeShoppingCart) {
    return ShoppingCartEseApi.RemoveShoppingCart
        .newBuilder()
        .setCartId(removeShoppingCart.getCartId())
        .build();
  }
}
