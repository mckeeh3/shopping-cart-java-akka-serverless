/* This code was generated by Akka Serverless tooling.
 * As long as this file exists it will not be re-generated.
 * You are free to make changes to this file.
 */
package cartve.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.akkaserverless.javasdk.valueentity.ValueEntityContext;
import com.google.protobuf.Empty;

import cartve.ShoppingCartVeApi;
import cartve.ShoppingCartVeApi.AddLineItem;
import cartve.ShoppingCartVeApi.ChangeLineItemQuantity;
import cartve.ShoppingCartVeApi.CheckoutShoppingCart;
import cartve.ShoppingCartVeApi.RemoveLineItem;
import cartve.ShoppingCartVeApi.RemoveShoppingCart;
import cartve.domain.ShoppingCartVeDomain.Cart;

public class ShoppingCartVe extends AbstractShoppingCartVe {
  private final String entityId;

  public ShoppingCartVe(ValueEntityContext context) {
    this.entityId = context.entityId();
  }

  @Override
  public ShoppingCartVeDomain.Cart emptyState() {
    return ShoppingCartVeDomain.Cart
        .newBuilder()
        .setCartId(entityId)
        .build();
  }

  @Override
  public Effect<Empty> addItem(ShoppingCartVeDomain.Cart currentState, ShoppingCartVeApi.AddLineItem command) {
    return reject(currentState, command).orElse(handle(currentState, command));
  }

  @Override
  public Effect<Empty> changeItem(ShoppingCartVeDomain.Cart currentState, ShoppingCartVeApi.ChangeLineItemQuantity command) {
    return reject(currentState, command).orElse(handle(currentState, command));
  }

  @Override
  public Effect<Empty> removeItem(ShoppingCartVeDomain.Cart currentState, ShoppingCartVeApi.RemoveLineItem command) {
    return reject(currentState, command).orElse(handle(currentState, command));
  }

  @Override
  public Effect<Empty> checkoutCart(ShoppingCartVeDomain.Cart currentState, ShoppingCartVeApi.CheckoutShoppingCart command) {
    return reject(currentState, command).orElse(handle(currentState, command));
  }

  @Override
  public Effect<ShoppingCartVeApi.Cart> getCart(ShoppingCartVeDomain.Cart currentState, ShoppingCartVeApi.GetShoppingCart command) {
    return handle(currentState);
  }

  @Override
  public Effect<Empty> removeCart(ShoppingCartVeDomain.Cart currentState, ShoppingCartVeApi.RemoveShoppingCart command) {
    return reject(currentState, command).orElse(handle(currentState, command));
  }

  private Optional<Effect<Empty>> reject(Cart currentState, AddLineItem command) {
    var rejected = rejectCheckedOutOrDeleted(currentState);
    if (rejected.isPresent()) {
      return rejected;
    }
    if (command.getCustomerId().isBlank()) {
      return Optional.of(effects().error("Customer ID must be specified"));
    }
    if (command.getProductId().isBlank()) {
      return Optional.of(effects().error("Product ID must be specified"));
    }
    if (command.getQuantity() < 1) {
      return Optional.of(effects().error("Quantity must be greater than 0"));
    }
    return Optional.empty();
  }

  private Optional<Effect<Empty>> reject(Cart currentState, ChangeLineItemQuantity command) {
    var rejected = rejectCheckedOutOrDeleted(currentState);
    if (rejected.isPresent()) {
      return rejected;
    }
    if (command.getProductId().isEmpty()) {
      return Optional.of(effects().error("Product ID must be specified"));
    }
    if (command.getQuantity() < 1) {
      return Optional.of(effects().error("Quantity must be greater than 0"));
    }
    return Optional.empty();
  }

  private Optional<Effect<Empty>> reject(Cart currentState, RemoveLineItem command) {
    var rejected = rejectCheckedOutOrDeleted(currentState);
    if (rejected.isPresent()) {
      return rejected;
    }
    if (command.getProductId().isEmpty()) {
      return Optional.of(effects().error("Product ID must be specified"));
    }
    return Optional.empty();
  }

  private Optional<Effect<Empty>> reject(Cart currentState, CheckoutShoppingCart command) {
    if (currentState.getDeleted()) {
      return Optional.of(effects().error("Shopping cart has been deleted"));
    }
    if (currentState.getItemsList().size() < 1) {
      return Optional.of(effects().error("Cannot checkout an empty shopping cart"));
    }
    return Optional.empty();
  }

  private Optional<Effect<Empty>> reject(Cart currentState, RemoveShoppingCart command) {
    if (currentState.getCheckedOut()) {
      return Optional.of(effects().error("Shopping cart is already checked out"));
    }
    return Optional.empty();
  }

  private Optional<Effect<Empty>> rejectCheckedOutOrDeleted(Cart cart) {
    if (cart.getCheckedOut()) {
      return Optional.of(effects().error("Cannot modify a checked out cart"));
    }
    if (cart.getDeleted()) {
      return Optional.of(effects().error("Cannot modify a deleted cart"));
    }
    return Optional.empty();
  }

  private Effect<Empty> handle(Cart currentState, AddLineItem command) {
    var shoppingCart = ShoppingCart
        .toShoppingCart(currentState)
        .handle(command);

    return effects()
        .updateState(shoppingCart.toState())
        .thenReply(Empty.getDefaultInstance());
  }

  private Effect<Empty> handle(Cart currentState, ChangeLineItemQuantity command) {
    var shoppingCart = ShoppingCart
        .toShoppingCart(currentState)
        .handle(command);

    return effects()
        .updateState(shoppingCart.toState())
        .thenReply(Empty.getDefaultInstance());
  }

  private Effect<Empty> handle(Cart currentState, RemoveLineItem command) {
    var shoppingCart = ShoppingCart
        .toShoppingCart(currentState)
        .handle(command);

    return effects()
        .updateState(shoppingCart.toState())
        .thenReply(Empty.getDefaultInstance());
  }

  private Effect<Empty> handle(Cart currentState, CheckoutShoppingCart command) {
    var shoppingCart = ShoppingCart
        .toShoppingCart(currentState)
        .handle(command);

    return effects()
        .updateState(shoppingCart.toState())
        .thenReply(Empty.getDefaultInstance());
  }

  private Effect<ShoppingCartVeApi.Cart> handle(Cart currentState) {
    return effects().reply(ShoppingCart.toApi(currentState));
  }

  private Effect<Empty> handle(Cart currentState, RemoveShoppingCart command) {
    var shoppingCart = ShoppingCart
        .toShoppingCart(currentState)
        .handle(command);

    return effects()
        .updateState(shoppingCart.toState())
        .thenReply(Empty.getDefaultInstance());
  }

  static class ShoppingCart {
    String cartId;
    String customerId;
    boolean checkedOut;
    boolean deleted;
    Map<String, LineItem> items = new LinkedHashMap<>();

    static class LineItem {
      String productId;
      String name;
      int quantity;

      LineItem(String productId, String name, int quantity) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
      }

      static LineItem toLineItem(cartve.domain.ShoppingCartVeDomain.LineItem item) {
        return new LineItem(item.getProductId(), item.getName(), item.getQuantity());
      }
    }

    ShoppingCart handle(AddLineItem command) {
      cartId = command.getCartId();
      customerId = command.getCustomerId();
      items.put(command.getProductId(), new LineItem(command.getProductId(), command.getName(), command.getQuantity()));
      return this;
    }

    ShoppingCart handle(ChangeLineItemQuantity command) {
      items.computeIfPresent(command.getProductId(), (key, value) -> new LineItem(value.productId, value.name, command.getQuantity()));
      return this;
    }

    ShoppingCart handle(RemoveLineItem command) {
      items.remove(command.getProductId());
      return this;
    }

    ShoppingCart handle(CheckoutShoppingCart command) {
      checkedOut = true;
      return this;
    }

    ShoppingCart handle(RemoveShoppingCart command) {
      deleted = true;
      return this;
    }

    static ShoppingCart toShoppingCart(Cart cart) {
      var shoppingCart = new ShoppingCart();
      shoppingCart.cartId = cart.getCartId();
      shoppingCart.customerId = cart.getCustomerId();
      shoppingCart.checkedOut = cart.getCheckedOut();
      shoppingCart.deleted = cart.getDeleted();
      cart.getItemsList().forEach(item -> shoppingCart.items.put(item.getProductId(), LineItem.toLineItem(item)));
      return shoppingCart;
    }

    ShoppingCartVeDomain.Cart toState() {
      var lineItems = items.values().stream().map(item -> ShoppingCartVeDomain.LineItem
          .newBuilder()
          .setProductId(item.productId)
          .setName(item.name)
          .setQuantity(item.quantity)
          .build())
          .collect(Collectors.toList());
      return ShoppingCartVeDomain.Cart
          .newBuilder()
          .setCartId(cartId)
          .setCustomerId(customerId)
          .setCheckedOut(checkedOut)
          .setDeleted(deleted)
          .addAllItems(lineItems)
          .build();
    }

    static ShoppingCartVeApi.Cart toApi(Cart cart) {
      var lineItems = cart.getItemsList().stream().map(item -> ShoppingCartVeApi.LineItem
          .newBuilder()
          .setProductId(item.getProductId())
          .setName(item.getName())
          .setQuantity(item.getQuantity())
          .build())
          .collect(Collectors.toList());
      return ShoppingCartVeApi.Cart
          .newBuilder()
          .setCustomerId(cart.getCustomerId())
          .setCheckedOut(cart.getCheckedOut())
          .setDeleted(cart.getDeleted())
          .addAllItems(lineItems)
          .build();
    }
  }
}
