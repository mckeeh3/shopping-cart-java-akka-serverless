/* This code was generated by Akka Serverless tooling.
 * As long as this file exists it will not be re-generated.
 * You are free to make changes to this file.
 */
package cartve.entity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.akkaserverless.javasdk.valueentity.ValueEntityContext;
import com.google.protobuf.Empty;

import cartve.api.ShoppingCartVeApi;

public class ShoppingCartVe extends AbstractShoppingCartVe {
  private final String entityId;

  public ShoppingCartVe(ValueEntityContext context) {
    this.entityId = context.entityId();
  }

  @Override
  public ShoppingCartVeEntity.Cart emptyState() {
    return ShoppingCartVeEntity.Cart
        .newBuilder()
        .setCartId(entityId)
        .build();
  }

  @Override
  public Effect<Empty> addItem(ShoppingCartVeEntity.Cart currentState, ShoppingCartVeApi.AddLineItem command) {
    return reject(currentState, command).orElseGet(() -> handle(currentState, command));
  }

  @Override
  public Effect<Empty> changeItem(ShoppingCartVeEntity.Cart currentState, ShoppingCartVeApi.ChangeLineItemQuantity command) {
    return reject(currentState, command).orElseGet(() -> handle(currentState, command));
  }

  @Override
  public Effect<Empty> removeItem(ShoppingCartVeEntity.Cart currentState, ShoppingCartVeApi.RemoveLineItem command) {
    return reject(currentState, command).orElseGet(() -> handle(currentState, command));
  }

  @Override
  public Effect<Empty> checkoutCart(ShoppingCartVeEntity.Cart currentState, ShoppingCartVeApi.CheckoutShoppingCart command) {
    return reject(currentState, command).orElseGet(() -> handle(currentState, command));
  }

  @Override
  public Effect<ShoppingCartVeApi.Cart> getCart(ShoppingCartVeEntity.Cart currentState, ShoppingCartVeApi.GetShoppingCart command) {
    return handle(currentState);
  }

  @Override
  public Effect<Empty> removeCart(ShoppingCartVeEntity.Cart currentState, ShoppingCartVeApi.RemoveShoppingCart command) {
    return reject(currentState, command).orElseGet(() -> handle(currentState, command));
  }

  private Optional<Effect<Empty>> reject(ShoppingCartVeEntity.Cart currentState, ShoppingCartVeApi.AddLineItem command) {
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

  private Optional<Effect<Empty>> reject(ShoppingCartVeEntity.Cart currentState, ShoppingCartVeApi.ChangeLineItemQuantity command) {
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

  private Optional<Effect<Empty>> reject(ShoppingCartVeEntity.Cart currentState, ShoppingCartVeApi.RemoveLineItem command) {
    var rejected = rejectCheckedOutOrDeleted(currentState);
    if (rejected.isPresent()) {
      return rejected;
    }
    if (command.getProductId().isEmpty()) {
      return Optional.of(effects().error("Product ID must be specified"));
    }
    return Optional.empty();
  }

  private Optional<Effect<Empty>> reject(ShoppingCartVeEntity.Cart currentState, ShoppingCartVeApi.CheckoutShoppingCart command) {
    if (currentState.getDeleted()) {
      return Optional.of(effects().error("Shopping cart has been deleted"));
    }
    if (currentState.getItemsList().size() < 1) {
      return Optional.of(effects().error("Cannot checkout an empty shopping cart"));
    }
    return Optional.empty();
  }

  private Optional<Effect<Empty>> reject(ShoppingCartVeEntity.Cart currentState, ShoppingCartVeApi.RemoveShoppingCart command) {
    if (currentState.getCheckedOut()) {
      return Optional.of(effects().error("Shopping cart is already checked out"));
    }
    return Optional.empty();
  }

  private Optional<Effect<Empty>> rejectCheckedOutOrDeleted(ShoppingCartVeEntity.Cart cart) {
    if (cart.getCheckedOut()) {
      return Optional.of(effects().error("Cannot modify a checked out cart"));
    }
    if (cart.getDeleted()) {
      return Optional.of(effects().error("Cannot modify a deleted cart"));
    }
    return Optional.empty();
  }

  private Effect<Empty> handle(ShoppingCartVeEntity.Cart currentState, ShoppingCartVeApi.AddLineItem command) {
    return effects()
        .updateState(
            ShoppingCart
                .fromState(currentState)
                .handle(command)
                .toState())
        .thenReply(Empty.getDefaultInstance());
  }

  private Effect<Empty> handle(ShoppingCartVeEntity.Cart currentState, ShoppingCartVeApi.ChangeLineItemQuantity command) {
    return effects()
        .updateState(
            ShoppingCart
                .fromState(currentState)
                .handle(command)
                .toState())
        .thenReply(Empty.getDefaultInstance());
  }

  private Effect<Empty> handle(ShoppingCartVeEntity.Cart currentState, ShoppingCartVeApi.RemoveLineItem command) {
    return effects()
        .updateState(
            ShoppingCart
                .fromState(currentState)
                .handle(command)
                .toState())
        .thenReply(Empty.getDefaultInstance());
  }

  private Effect<Empty> handle(ShoppingCartVeEntity.Cart currentState, ShoppingCartVeApi.CheckoutShoppingCart command) {
    return effects()
        .updateState(
            ShoppingCart
                .fromState(currentState)
                .handle(command)
                .toState())
        .thenReply(Empty.getDefaultInstance());
  }

  private Effect<ShoppingCartVeApi.Cart> handle(ShoppingCartVeEntity.Cart currentState) {
    return effects().reply(toApi(currentState));
  }

  private Effect<Empty> handle(ShoppingCartVeEntity.Cart currentState, ShoppingCartVeApi.RemoveShoppingCart command) {
    return effects()
        .updateState(
            ShoppingCart
                .fromState(currentState)
                .handle(command)
                .toState())
        .thenReply(Empty.getDefaultInstance());
  }

  static ShoppingCartVeApi.Cart toApi(ShoppingCartVeEntity.Cart state) {
    var lineItems = state.getItemsList().stream().map(item -> ShoppingCartVeApi.LineItem
        .newBuilder()
        .setProductId(item.getProductId())
        .setName(item.getName())
        .setQuantity(item.getQuantity())
        .build())
        .collect(Collectors.toList());
    return ShoppingCartVeApi.Cart
        .newBuilder()
        .setCustomerId(state.getCustomerId())
        .setCheckedOut(state.getCheckedOut())
        .setDeleted(state.getDeleted())
        .addAllItems(lineItems)
        .build();
  }

  static class ShoppingCart {
    ShoppingCartVeEntity.Cart state;
    Map<String, ShoppingCartVeEntity.LineItem> lineItems = new LinkedHashMap<>();

    ShoppingCart(ShoppingCartVeEntity.Cart state) {
      this.state = state;
      state.getItemsList().forEach(item -> lineItems.put(item.getProductId(), item));
    }

    ShoppingCart handle(ShoppingCartVeApi.AddLineItem command) {
      lineItems.computeIfPresent(command.getProductId(),
          (productId, lineItem) -> lineItem
              .toBuilder()
              .setQuantity(lineItem.getQuantity() + command.getQuantity())
              .build());
      lineItems.computeIfAbsent(command.getProductId(),
          productId -> ShoppingCartVeEntity.LineItem
              .newBuilder()
              .setProductId(command.getProductId())
              .setName(command.getName())
              .setQuantity(command.getQuantity())
              .build());
      var lineItem = ShoppingCartVeEntity.LineItem.newBuilder()
          .setProductId(command.getProductId())
          .setName(command.getName())
          .setQuantity(command.getQuantity())
          .build();
      lineItems.put(command.getProductId(), lineItem);
      state.toBuilder()
          .setCartId(command.getCartId())
          .setCustomerId(command.getCustomerId())
          .clearItems()
          .addAllItems(lineItems.values())
          .build();
      return this;
    }

    ShoppingCart handle(ShoppingCartVeApi.ChangeLineItemQuantity command) {
      lineItems.computeIfPresent(command.getProductId(), (productId, item) -> item
          .toBuilder()
          .setQuantity(command.getQuantity())
          .build());
      state = state.toBuilder()
          .clearItems()
          .addAllItems(lineItems.values())
          .build();
      return this;
    }

    ShoppingCart handle(ShoppingCartVeApi.RemoveLineItem command) {
      lineItems.remove(command.getProductId());
      state = state.toBuilder()
          .clearItems()
          .addAllItems(lineItems.values())
          .build();
      return this;
    }

    ShoppingCart handle(ShoppingCartVeApi.CheckoutShoppingCart command) {
      state = state.toBuilder()
          .setCheckedOut(true)
          .build();
      return this;
    }

    ShoppingCart handle(ShoppingCartVeApi.RemoveShoppingCart command) {
      state = state.toBuilder()
          .setDeleted(true)
          .build();
      return this;
    }

    static ShoppingCart fromState(ShoppingCartVeEntity.Cart state) {
      return new ShoppingCart(state);
    }

    ShoppingCartVeEntity.Cart toState() {
      return state
          .toBuilder()
          .clearItems()
          .addAllItems(lineItems.values())
          .build();
    }
  }
}
