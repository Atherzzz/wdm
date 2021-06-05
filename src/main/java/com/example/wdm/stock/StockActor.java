/*
 * Copyright (c) Microsoft Corporation and Dapr Contributors.
 * Licensed under the MIT License.
 */

package com.example.wdm.stock;

import io.dapr.actors.ActorMethod;
import io.dapr.actors.ActorType;
import reactor.core.publisher.Mono;

/**
 * Example of implementation of an Actor.
 */
@ActorType(name = "DemoActor")
public interface StockActor {
  void registerReminder();


  @ActorMethod(returns = String.class)
  Mono<String> createItem(Double price);

  @ActorMethod(returns = String.class)
  Mono<String> findItem();

  @ActorMethod(returns = String.class)
  Mono<String> subtractStock(Integer number);

  @ActorMethod(returns = String.class)
  Mono<String> addStock(Integer number);


}
