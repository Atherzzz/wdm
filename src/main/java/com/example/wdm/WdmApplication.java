package com.example.wdm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Method;


import java.util.Arrays;

@SpringBootApplication
public class WdmApplication {
//	public static void main(String[] args) throws Exception {
//		Options options = new Options();
//		options.addRequiredOption("p", "port", true, "Port the will listen to.");
//
//		CommandLineParser parser = new DefaultParser();
//		CommandLine cmd = parser.parse(options, args);
//
//		// If port string is not valid, it will throw an exception.
//		final int port = Integer.parseInt(cmd.getOptionValue("port"));
//
//		// Idle timeout until actor instance is deactivated.
//		ActorRuntime.getInstance().getConfig().setActorIdleTimeout(Duration.ofSeconds(30));
//		// How often actor instances are scanned for deactivation and balance.
//		ActorRuntime.getInstance().getConfig().setActorScanInterval(Duration.ofSeconds(10));
//		// How long to wait until for draining an ongoing API call for an actor instance.
//		ActorRuntime.getInstance().getConfig().setDrainOngoingCallTimeout(Duration.ofSeconds(10));
//		// Determines whether to drain API calls for actors instances being balanced.
//		ActorRuntime.getInstance().getConfig().setDrainBalancedActors(true);
//
//		// Register the Actor class.
//		ActorRuntime.getInstance().registerActor(DemoActorImpl.class);
//
//		// Start Dapr's callback endpoint.
//		DaprApplication.start(port);
//	}


//	public static void main(String[] args) throws Exception {
//		Options options = new Options();
//		options.addRequiredOption("p", "port", true, "Port to listen to.");
//
//		CommandLineParser parser = new DefaultParser();
//		CommandLine cmd = parser.parse(options, args);
//
//		// If port string is not valid, it will throw an exception.
//		int port = Integer.parseInt(cmd.getOptionValue("port"));
//
//		DaprApplication.start(port);
//	}
//	public static void main(String[] args) {
//		SpringApplication.run(WdmApplication.class, args);
//	}
    /**
     * 调用actor的办法
     */
    public static void main(String[] args) throws Exception {
        String[] arguments;
        if (args.length < 1) {
            SpringApplication.run(WdmApplication.class, args);
            System.out.println("SpringApplication run");
//            throw new IllegalArgumentException("Requires at least one argument - name of the main class");
        } else {

            arguments = Arrays.copyOfRange(args, 1, args.length);
            Class mainClass = Class.forName(args[0]);
            Method mainMethod = mainClass.getDeclaredMethod("main", String[].class);
            Object[] methodArgs = new Object[1];
            methodArgs[0] = arguments;
            mainMethod.invoke(mainClass, methodArgs);
        }
    }

}