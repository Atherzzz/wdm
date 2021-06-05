/*
 * Copyright (c) Microsoft Corporation and Dapr Contributors.
 * Licensed under the MIT License.
 */

package com.example.wdm.payment;

import io.dapr.actors.ActorMethod;
import io.dapr.actors.ActorType;
import reactor.core.publisher.Mono;

/**
 * Example of implementation of an Actor.
 */
@ActorType(name = "DemoActor")
public interface PaymentActor {

  void registerReminder();

  @ActorMethod(returns = String.class)
  Mono<String> createUser();

  @ActorMethod(returns = String.class)
  Mono<String> findUser();

  @ActorMethod(returns = String.class)
  Mono<String> postPayment(int amount);

  @ActorMethod(returns = String.class)
  Mono<String> addFunds(int amount);

  @ActorMethod(returns = String.class)
  Mono<String> cancelPayment(String user_id, String order_id);

  @ActorMethod(returns = String.class)
  Mono<String> getPaymentStatus(String order_id);


}
