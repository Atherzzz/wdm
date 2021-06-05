package com.example.wdm.payment;

import io.dapr.actors.client.ActorClient;
import org.springframework.web.bind.annotation.*;

import io.dapr.actors.ActorId;
import io.dapr.actors.client.ActorClient;
import io.dapr.actors.client.ActorProxyBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.UUID;

@RestController
public class PaymentServiceController {

//    private static final int NUM_ACTORS = 3;

    @PostMapping("/payment/pay/{user_id}/{order_id}/{amount}")
    public String postPayment(@PathVariable(name="user_id") String user_id, @PathVariable(name="amount") Integer amount) {
        String credit = "";
        try (ActorClient client = new ActorClient()) {
            ActorProxyBuilder<PaymentActor> builder = new ActorProxyBuilder(PaymentActor.class, client);
//            List<Thread> threads = new ArrayList<>(NUM_ACTORS);
            ExecutorService threadPool = Executors.newSingleThreadExecutor();

            ActorId actorId = new ActorId(user_id);
            PaymentActor actor = builder.build(actorId);
            Future<String> future =
                    threadPool.submit(new CallActor(actorId.toString(), actor, 3, amount));

            credit = future.get();

            System.out.println("Got user credit: "+credit);

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        String json =  "{\"user_id\":"+user_id+","+"\"credit\":"+credit+"}";
        return json;
    }

    @PostMapping("/payment/cancel/{user_id}/{order_id}")
    public String cancelPayment() {


        return "'ok': (true/false)";
    }

    @GetMapping("/payment/status/{order_id}")
    public String getPaymentStatus() {

        return "'paid': (true/false)";
    }

    @PostMapping("/payment/add_funds/{user_id}/{amount}")
    public String addFunds(@PathVariable(name="user_id") String user_id, @PathVariable(name="amount") Integer amount) {
        String credit = "";
        try (ActorClient client = new ActorClient()) {
            ActorProxyBuilder<PaymentActor> builder = new ActorProxyBuilder(PaymentActor.class, client);
//            List<Thread> threads = new ArrayList<>(NUM_ACTORS);
            ExecutorService threadPool = Executors.newSingleThreadExecutor();

            ActorId actorId = new ActorId(user_id);
            PaymentActor actor = builder.build(actorId);
            Future<String> future =
                    threadPool.submit(new CallActor(actorId.toString(), actor, 4, amount));

            credit = future.get();

            System.out.println("Got user credit: "+credit);

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        String json =  "{\"user_id\":"+user_id+","+"\"credit\":"+credit+"}";
        return json;
    }
    @PostMapping("/payment/create_user")
    public String createUser() {
        String user_id = "";
        try (ActorClient client = new ActorClient()) {
            ActorProxyBuilder<PaymentActor> builder = new ActorProxyBuilder(PaymentActor.class, client);
//            List<Thread> threads = new ArrayList<>(NUM_ACTORS);
            ExecutorService threadPool = Executors.newSingleThreadExecutor();
//            UUID uuid = UUID.randomUUID();
//            ActorId actorId = new ActorId(uuid.toString());
            ActorId actorId = ActorId.createRandom();
            PaymentActor actor = builder.build(actorId);
            Future<String> future =
                    threadPool.submit(new CallActor(actorId.toString(), actor, 1));

            user_id = future.get();

            System.out.println("Got user id:"+user_id);

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        String json =  "{\"user_id\":"+user_id+"}";
        return json;
    }

    @GetMapping("/payment/find_user/{user_id}")
    public String findUser(@PathVariable(name="user_id") String user_id) {
        String credit = "";
        try (ActorClient client = new ActorClient()) {
            ActorProxyBuilder<PaymentActor> builder = new ActorProxyBuilder(PaymentActor.class, client);
//            List<Thread> threads = new ArrayList<>(NUM_ACTORS);
            ExecutorService threadPool = Executors.newSingleThreadExecutor();

            ActorId actorId = new ActorId(user_id);
            PaymentActor actor = builder.build(actorId);
            Future<String> future =
                    threadPool.submit(new CallActor(actorId.toString(), actor, 2));

            credit = future.get();

            System.out.println("Got user credit:"+credit);

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        String json =  "{\"user_id\":"+user_id+","+"\"credit\":"+credit+"}";
        return json;
    }
}
