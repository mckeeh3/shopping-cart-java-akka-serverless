/* This code was generated by Akka Serverless tooling.
 * As long as this file exists it will not be re-generated.
 * You are free to make changes to this file.
 */

package cartve.action;

import com.akkaserverless.javasdk.ServiceCallRef;
import com.akkaserverless.javasdk.action.ActionCreationContext;
import com.google.protobuf.Empty;

import cartve.api.ShoppingCartVeApi;
import cartve.api.ShoppingCartVeApi.AddLineItem;
import cartve.api.ShoppingCartVeApi.ChangeLineItemQuantity;
import cartve.api.ShoppingCartVeApi.CheckoutShoppingCart;
import cartve.api.ShoppingCartVeApi.RemoveLineItem;
import cartve.api.ShoppingCartVeApi.RemoveShoppingCart;

public class ShoppingCartVeToTopicAction extends AbstractShoppingCartVeToTopicAction {
  private final ActionCreationContext creationContext;

  public ShoppingCartVeToTopicAction(ActionCreationContext creationContext) {
    this.creationContext = creationContext;
  }

  @Override
  public Effect<Empty> addItem(ShoppingCartVeActionTopicIn.AddLineItem addLineItem) {
    return effects().forward(addItemRef().createCall(toApi(addLineItem)));
  }

  @Override
  public Effect<Empty> changeItem(ShoppingCartVeActionTopicIn.ChangeLineItemQuantity changeLineItemQuantity) {
    return effects().forward(changeItemRef().createCall(toApi(changeLineItemQuantity)));
  }

  @Override
  public Effect<Empty> removeItem(ShoppingCartVeActionTopicIn.RemoveLineItem removeLineItem) {
    return effects().forward(removeItemRef().createCall(toApi(removeLineItem)));
  }

  @Override
  public Effect<Empty> checkoutCart(ShoppingCartVeActionTopicIn.CheckoutShoppingCart checkoutShoppingCart) {
    return effects().forward(checkoutCartRef().createCall(toApi(checkoutShoppingCart)));
  }

  @Override
  public Effect<Empty> removeCart(ShoppingCartVeActionTopicIn.RemoveShoppingCart removeShoppingCart) {
    return effects().forward(removeCartRef().createCall(toApi(removeShoppingCart)));
  }

  private static AddLineItem toApi(ShoppingCartVeActionTopicIn.AddLineItem addLineItem) {
    return ShoppingCartVeApi.AddLineItem
        .newBuilder()
        .setCustomerId(addLineItem.getCustomerId())
        .setCartId(addLineItem.getCartId())
        .setProductId(addLineItem.getProductId())
        .setName(addLineItem.getName())
        .setQuantity(addLineItem.getQuantity())
        .build();
  }

  private static ChangeLineItemQuantity toApi(ShoppingCartVeActionTopicIn.ChangeLineItemQuantity changeLineItemQuantity) {
    return ShoppingCartVeApi.ChangeLineItemQuantity
        .newBuilder()
        .setCartId(changeLineItemQuantity.getCartId())
        .setProductId(changeLineItemQuantity.getProductId())
        .setQuantity(changeLineItemQuantity.getQuantity())
        .build();
  }

  private static RemoveLineItem toApi(ShoppingCartVeActionTopicIn.RemoveLineItem removeLineItem) {
    return ShoppingCartVeApi.RemoveLineItem
        .newBuilder()
        .setCartId(removeLineItem.getCartId())
        .setProductId(removeLineItem.getProductId())
        .build();
  }

  private static CheckoutShoppingCart toApi(ShoppingCartVeActionTopicIn.CheckoutShoppingCart checkoutShoppingCart) {
    return ShoppingCartVeApi.CheckoutShoppingCart
        .newBuilder()
        .setCartId(checkoutShoppingCart.getCartId())
        .build();
  }

  private static RemoveShoppingCart toApi(ShoppingCartVeActionTopicIn.RemoveShoppingCart removeShoppingCart) {
    return ShoppingCartVeApi.RemoveShoppingCart
        .newBuilder()
        .setCartId(removeShoppingCart.getCartId())
        .build();
  }

  private ServiceCallRef<ShoppingCartVeApi.AddLineItem> addItemRef() {
    return creationContext.serviceCallFactory()
        .lookup("cartve.api.CartService", "AddItem", ShoppingCartVeApi.AddLineItem.class);
  }

  private ServiceCallRef<ShoppingCartVeApi.ChangeLineItemQuantity> changeItemRef() {
    return creationContext.serviceCallFactory()
        .lookup("cartve.api.CartService", "ChangeItem", ShoppingCartVeApi.ChangeLineItemQuantity.class);
  }

  private ServiceCallRef<ShoppingCartVeApi.RemoveLineItem> removeItemRef() {
    return creationContext.serviceCallFactory()
        .lookup("cartve.api.CartService", "RemoveItem", ShoppingCartVeApi.RemoveLineItem.class);
  }

  private ServiceCallRef<ShoppingCartVeApi.CheckoutShoppingCart> checkoutCartRef() {
    return creationContext.serviceCallFactory()
        .lookup("cartve.api.CartService", "CheckoutCart", ShoppingCartVeApi.CheckoutShoppingCart.class);
  }

  private ServiceCallRef<ShoppingCartVeApi.RemoveShoppingCart> removeCartRef() {
    return creationContext.serviceCallFactory()
        .lookup("cartve.api.CartService", "RemoveCart", ShoppingCartVeApi.RemoveShoppingCart.class);
  }
}