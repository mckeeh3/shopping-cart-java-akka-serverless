/* This code was generated by Akka Serverless tooling.
 * As long as this file exists it will not be re-generated.
 * You are free to make changes to this file.
 */

package cartese.action;

import com.akkaserverless.javasdk.ServiceCallRef;
import com.akkaserverless.javasdk.action.ActionCreationContext;
import com.google.protobuf.Empty;

import cartese.api.ShoppingCartEseApi;
import cartese.entity.ShoppingCartEse;

public class ShoppingCartEseTopicInAction extends AbstractShoppingCartEseTopicInAction {
  private final ActionCreationContext creationContext;
  private final ServiceCallRef<ShoppingCartEseApi.AddLineItem> addItemRef;
  private final ServiceCallRef<ShoppingCartEseApi.ChangeLineItemQuantity> changeItemRef;
  private final ServiceCallRef<ShoppingCartEseApi.RemoveLineItem> removeItemRef;
  private final ServiceCallRef<ShoppingCartEseApi.CheckoutShoppingCart> checkoutCartRef;
  private final ServiceCallRef<ShoppingCartEseApi.RemoveShoppingCart> removeCartRef;

  public ShoppingCartEseTopicInAction(ActionCreationContext creationContext) {
    this.creationContext = creationContext;
    addItemRef = addItemRef();
    changeItemRef = changeItemRef();
    removeItemRef = removeItemRef();
    checkoutCartRef = checkoutCartRef();
    removeCartRef = removeCartRef();
  }

  @Override
  public Effect<Empty> addItem(ShoppingCartEseActionTopicIn.AddLineItem addLineItem) {
    var arg = ShoppingCartEseApi.AddLineItem
        .newBuilder()
        .setCustomerId(addLineItem.getCustomerId())
        .setCartId(addLineItem.getCartId())
        .setProductId(addLineItem.getProductId())
        .setName(addLineItem.getName())
        .setQuantity(addLineItem.getQuantity())
        .build();
    return effects().forward(addItemRef.createCall(arg));
  }

  @Override
  public Effect<Empty> changeItem(ShoppingCartEseActionTopicIn.ChangeLineItemQuantity changeLineItemQuantity) {
    var arg = ShoppingCartEseApi.ChangeLineItemQuantity
        .newBuilder()
        .setCartId(changeLineItemQuantity.getCartId())
        .setProductId(changeLineItemQuantity.getProductId())
        .setQuantity(changeLineItemQuantity.getQuantity())
        .build();
    return effects().forward(changeItemRef.createCall(arg));
  }

  @Override
  public Effect<Empty> removeItem(ShoppingCartEseActionTopicIn.RemoveLineItem removeLineItem) {
    var arg = ShoppingCartEseApi.RemoveLineItem
        .newBuilder()
        .setCartId(removeLineItem.getCartId())
        .setProductId(removeLineItem.getProductId())
        .build();
    return effects().forward(removeItemRef.createCall(arg));
  }

  @Override
  public Effect<Empty> checkoutCart(ShoppingCartEseActionTopicIn.CheckoutShoppingCart checkoutShoppingCart) {
    var arg = ShoppingCartEseApi.CheckoutShoppingCart
        .newBuilder()
        .setCartId(checkoutShoppingCart.getCartId())
        .build();
    return effects().forward(checkoutCartRef.createCall(arg));
  }

  @Override
  public Effect<Empty> removeCart(ShoppingCartEseActionTopicIn.RemoveShoppingCart removeShoppingCart) {
    var arg = ShoppingCartEseApi.RemoveShoppingCart
        .newBuilder()
        .setCartId(removeShoppingCart.getCartId())
        .build();
    return effects().forward(removeCartRef.createCall(arg));
  }

  private ServiceCallRef<ShoppingCartEseApi.AddLineItem> addItemRef() {
    return creationContext.serviceCallFactory()
        .lookup(ShoppingCartEse.class.getName(), "addItem", ShoppingCartEseApi.AddLineItem.class);
  }

  private ServiceCallRef<ShoppingCartEseApi.ChangeLineItemQuantity> changeItemRef() {
    return creationContext.serviceCallFactory()
        .lookup(ShoppingCartEse.class.getName(), "changeItem", ShoppingCartEseApi.ChangeLineItemQuantity.class);
  }

  private ServiceCallRef<ShoppingCartEseApi.RemoveLineItem> removeItemRef() {
    return creationContext.serviceCallFactory()
        .lookup(ShoppingCartEse.class.getName(), "removeItem", ShoppingCartEseApi.RemoveLineItem.class);
  }

  private ServiceCallRef<ShoppingCartEseApi.CheckoutShoppingCart> checkoutCartRef() {
    return creationContext.serviceCallFactory()
        .lookup(ShoppingCartEse.class.getName(), "checkoutCart", ShoppingCartEseApi.CheckoutShoppingCart.class);
  }

  private ServiceCallRef<ShoppingCartEseApi.RemoveShoppingCart> removeCartRef() {
    return creationContext.serviceCallFactory()
        .lookup(ShoppingCartEse.class.getName(), "removeCart", ShoppingCartEseApi.RemoveShoppingCart.class);
  }
}
