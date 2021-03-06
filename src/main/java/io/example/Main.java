/* This code was generated by Akka Serverless tooling.
 * As long as this file exists it will not be re-generated.
 * You are free to make changes to this file.
 */

package io.example;

import com.akkaserverless.javasdk.AkkaServerless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cartese.action.ShoppingCartEseTopicInAction;
import cartese.entity.ShoppingCartEse;
import cartese.view.OrdersByCustomerEseViewImpl;
import cartve.action.ShoppingCartVeTopicInAction;
import cartve.entity.ShoppingCartVe;
import cartve.view.OrdersByCustomerVeViewImpl;

public final class Main {

  private static final Logger LOG = LoggerFactory.getLogger(Main.class);

  public static AkkaServerless createAkkaServerless() {
    // The AkkaServerlessFactory automatically registers any generated Actions, Views or Entities,
    // and is kept up-to-date with any changes in your protobuf definitions.
    // If you prefer, you may remove this and manually register these components in a
    // `new AkkaServerless()` instance.
    return AkkaServerlessFactory.withComponents(
      ShoppingCartEse::new,
      ShoppingCartVe::new,
      OrdersByCustomerEseViewImpl::new,
      OrdersByCustomerVeViewImpl::new,
      ShoppingCartEseTopicInAction::new,
      ShoppingCartVeTopicInAction::new);
  }

  public static void main(String[] args) throws Exception {
    LOG.info("starting the Akka Serverless service");
    createAkkaServerless().start();
  }
}
