///*
// * Copyright (c) Microsoft Corporation and Dapr Contributors.
// * Licensed under the MIT License.
// */
//
//package com.example.wdm.demo;
//
//import io.dapr.actors.ActorMethod;
//import io.dapr.actors.ActorType;
//import reactor.core.publisher.Mono;
//
///**
// * Example of implementation of an Actor.
// */
//@ActorType(name = "DemoActor")
//public interface DemoActor {
//
//  void registerReminder();
//
//  @ActorMethod(returns = Object.class)
//  Mono<String> createUser();
//
//}

/*
 * Copyright (c) Microsoft Corporation and Dapr Contributors.
 * Licensed under the MIT License.
 */

package com.example.wdm.demo;

import io.dapr.actors.ActorMethod;
import io.dapr.actors.ActorType;
import reactor.core.publisher.Mono;

/**
 * Example of implementation of an Actor.
 */
@ActorType(name = "DemoActor")
public interface DemoActor {

  void registerReminder();

  @ActorMethod(name = "echo_message")
  String say(String something);

  void clock(String message);

  @ActorMethod(returns = Integer.class)
  Mono<Integer> incrementAndGet(int delta);

  @ActorMethod(returns = Object.class)
  Mono<String> createUser();
}
