package com.example.wdm.order;

import com.example.wdm.payment.CallActor;
import com.example.wdm.payment.PaymentActor;
import io.dapr.actors.ActorId;
import io.dapr.actors.client.ActorClient;
import io.dapr.actors.client.ActorProxyBuilder;
import io.dapr.actors.runtime.ActorRuntime;
import io.dapr.v1.DaprProtos.DeleteStateRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
public class OrderServiceController {
    @PostMapping("/orders/create/{user_id}")
    public String create_order(@PathVariable(name = "user_id") String user_id) {
        String order_id = "";
        try (ActorClient client = new ActorClient()) {
            ActorProxyBuilder<OrderActor> builder = new ActorProxyBuilder(OrderActor.class, client);
            ExecutorService threadPool = Executors.newSingleThreadExecutor();
            ActorId actorId = ActorId.createRandom();
            OrderActor actor = builder.build(actorId);
            Future<String> future =
                    threadPool.submit(new OrderCallActor(actorId.toString(), actor, 1, user_id));
            order_id = future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        String json = "{\"order_id\":" + order_id + "}";
        return json;
    }

    @DeleteMapping("/orders/remove/{order_id}")
    public String remove_order(@PathVariable(name = "order_id") String order_id) {
        ActorRuntime.getInstance().deactivate("OrderActor",order_id).block();
        String result = "";
        try (ActorClient client = new ActorClient()) {
            ActorProxyBuilder<OrderActor> builder = new ActorProxyBuilder(OrderActor.class, client);
            ExecutorService threadPool = Executors.newSingleThreadExecutor();
            ActorId actorId = new ActorId(order_id);
            OrderActor actor = builder.build(actorId);
            Future<String> future = threadPool.submit(new OrderCallActor(actorId.toString(), actor, order_id,2));
            result = future.get();
            System.out.println("Got user credit:" + result);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/orders/find/{order_id}")
    public String find_order(@PathVariable(name = "order_id") String order_id) {
        ActorRuntime.getInstance().deactivate("OrderActor",order_id).block();
        String result = "";
        String paid = "";
        String items = "";
        String user_id = "";
        String total_cost = "";
        try (ActorClient client = new ActorClient()) {
            ActorProxyBuilder<OrderActor> builder = new ActorProxyBuilder(OrderActor.class, client);
            ExecutorService threadPool = Executors.newSingleThreadExecutor();

            ActorId actorId = new ActorId(order_id);
            OrderActor actor = builder.build(actorId);
            Future<String> future =
                    threadPool.submit(new OrderCallActor(actorId.toString(), actor, 3));
            result = future.get();
            String[] arr = result.split("#");
            paid = arr[0];
            items = arr[1];
            user_id = arr[2];
            total_cost = arr[3];
            System.out.println("Got user paid: " + paid);
            System.out.println("Got order items: " + items);
            System.out.println("Got user id " + user_id);
            System.out.println("Got user total_cost" + total_cost);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        String json = "{\"order_id\":" + order_id + "," + "\"paid\":" + paid + "\"items\":"
                + items + "\"user_id\":" + user_id + "\"total_cost\":" + total_cost + "}";
        return json;
    }

    @PostMapping("/orders/addItem/{order_id}/{item_id}")
    public String addOrders(@PathVariable(name = "order_id") String order_id, @PathVariable(name = "item_id") String item_id) {
        String result = "";
        try (ActorClient client = new ActorClient()) {
            ActorProxyBuilder<OrderActor> builder = new ActorProxyBuilder(OrderActor.class, client);
//            List<Thread> threads = new ArrayList<>(NUM_ACTORS);
            ExecutorService threadPool = Executors.newSingleThreadExecutor();

            ActorId actorId = new ActorId(order_id);
            OrderActor actor = builder.build(actorId);
            Future<String> future =
                    threadPool.submit(new OrderCallActor(actorId.toString(), actor, item_id, 4, 4));
            result = future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @DeleteMapping("/orders/removeItem/{order_id}/{item_id}")
    public String remove_item(@PathVariable(name = "order_id") String order_id, @PathVariable(name = "item_id") String item_id) {
        String result = "";
        try (ActorClient client = new ActorClient()) {
            ActorProxyBuilder<OrderActor> builder = new ActorProxyBuilder(OrderActor.class, client);
//            List<Thread> threads = new ArrayList<>(NUM_ACTORS);
            ExecutorService threadPool = Executors.newSingleThreadExecutor();

            ActorId actorId = new ActorId(order_id);
            OrderActor actor = builder.build(actorId);
            Future<String> future =
                    threadPool.submit(new OrderCallActor(actorId.toString(), actor, item_id, 5, 4));
            result = future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/orders/checkout/{order_id}")
    public String index6() {
        return "/orders/checkout/{order_id}";
    }

}


