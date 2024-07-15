//package ch17.akka;
//
//import ch17.TempInfo;
//import ch17.TempSubscriber;
//import ch17.TempSubscription;
//import java.util.concurrent.Flow.Publisher;
//
//public class Main {
//
//  public static void main(String[] args) {
//    ActorSystem system = ActorSystem.create("temp-info");
//    Materializer materializer = ActorMaterializer.create(system);
//
//    Publisher<TempInfo> publisher =
//        Source.fromPublisher(getTemperatures("New York"))
//            .runWith(Sink.asPublisher(AsPublisher.WITH_FANOUT), materializer);
//    publisher.subscribe(new TempSubscriber());
//
//    try {
//      Thread.sleep(10000L);
//    }
//    catch (InterruptedException e) {
//      throw new RuntimeException(e);
//    }
//  }
//
//  private static Publisher<TempInfo> getTemperatures(String town) {
//    return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
//  }
//
//}
