/*
 * Copyright (c) Microsoft Corporation and Dapr Contributors.
 * Licensed under the MIT License.
 */

package com.example.wdm.payment;

import io.dapr.actors.ActorId;
import io.dapr.actors.client.ActorClient;
import io.dapr.actors.client.ActorProxyBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * Client for Actor runtime to invoke actor methods.
 * 1. Build and install jars:
 * mvn clean install
 * 2. cd to [repo-root]/examples
 * 3. Run the client:
 * dapr run --components-path ./components/actors --app-id demoactorclient -- java -jar \
 * target/dapr-java-sdk-examples-exec.jar io.dapr.examples.actors.DemoActorClient
 */

public class PaymentActorClient {

  private static final int NUM_ACTORS = 0;

  /**
   * The main method.
   * @param args Input arguments (unused).
   * @throws InterruptedException If program has been interrupted.
   */
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    try (ActorClient client = new ActorClient()) {
      ActorProxyBuilder<PaymentActor> builder = new ActorProxyBuilder(PaymentActor.class, client);
      List<Thread> threads = new ArrayList<>(NUM_ACTORS);
      ExecutorService threadPool = Executors.newFixedThreadPool(10);

//      ActorId actorId = ActorId.createRandom();
      ActorId actorId = new ActorId("d18c14af-91e8-4048-b07d-0cb9cd127a4e");
      PaymentActor actor = builder.build(actorId);
      //create user
//      Future<String> future1 = threadPool.submit(new CallActor(actorId.toString(), actor, 1));
//      String user_id = future1.get();
//      System.out.println("Got user id: "+user_id);
      //find user
//      Future<String> future2 = threadPool.submit(new CallActor(actorId.toString(), actor, 2));
//      String credit = future2.get();
//      System.out.println("Got user credit: "+credit);
      //add credit
      Future<String> future3 = threadPool.submit(new CallActor(actorId.toString(), actor, 4, 1));
      String credit1 = future3.get();
      System.out.println("credit after adding: "+credit1);
//      //decrease credit
      Future<String> future4 = threadPool.submit(new CallActor(actorId.toString(), actor, 3, 1));
      String credit2 = future4.get();
      System.out.println("credit after subtrscting 1: "+credit2);
    }

    System.out.println("Done.");
  }

}
