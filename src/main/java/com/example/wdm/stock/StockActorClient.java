/*
 * Copyright (c) Microsoft Corporation and Dapr Contributors.
 * Licensed under the MIT License.
 */

package com.example.wdm.stock;

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

public class StockActorClient {

  private static final int NUM_ACTORS = 0;

  /**
   * The main method.
   * @param args Input arguments (unused).
   * @throws InterruptedException If program has been interrupted.
   */
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    String stock = "";
    String price = "";
    String result = "";
    try (ActorClient client = new ActorClient()) {
      ActorProxyBuilder<StockActor> builder = new ActorProxyBuilder(StockActor.class, client);
      List<Thread> threads = new ArrayList<>(NUM_ACTORS);
      ExecutorService threadPool = Executors.newFixedThreadPool(10);

      System.out.println("start");
      //ActorId actorId = ActorId.createRandom();
      ActorId actorId = new ActorId("c42870ec-cb10-41cc-8e25-922a4455b54e");
      StockActor actor = builder.build(actorId);
      //create item
//      Future<String> future1 = threadPool.submit(new CallActor(actorId.toString(), actor, 1, 100.0));
//      result = future1.get();
//      System.out.println("Got item id: "+result);
      //find item
//      Future<String> future2 = threadPool.submit(new CallActor(actorId.toString(), actor, 2));
//      result = future2.get();
//      String [] arr = result.split("#");
//      stock = arr[1];
//      price = arr[0];
//      System.out.println("stock: "+stock+"\n price:"+price);
      //add stock
//      Future<String> future3 = threadPool.submit(new CallActor(actorId.toString(), actor, 4, 18));
//      String res = future3.get();
//      System.out.println("new stock: "+res);
      //decrease credit
      Future<String> future4 = threadPool.submit(new CallActor(actorId.toString(), actor, 3, 3));
      String res = future4.get();
      System.out.println("new stock: "+res);
    }

    System.out.println("Done.");
  }

}
