package org.example;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class MessengerServer {
    // Attribut privé pour stocker le serveur gRPC
    private Server server;
/**
   * Démarre le serveur gRPC et l'écoute sur le port spécifié.
   * 
   * @throws IOException Si une erreur d'entrée/sortie se produit lors du démarrage du serveur.
   */
    
    private void start() throws IOException {
        /* The port on which the server should run */
        int port = 50051;
         // Construction du serveur gRPC
        server = ServerBuilder.forPort(port)
             // Ajoute le service implémenté par MessengerServiceImpl
                .addService(new MessengerServiceImpl())
                .build()
                .start();
        System.out.println("Server started, listening on " + port);
    }
     /**
   * Bloque le thread principal jusqu'à l'arrêt du serveur.
   * 
   * @throws InterruptedException Si le thread est interrompu pendant l'attente.
   */
    
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
 /**
   * Point d'entrée de l'application. Crée une instance du serveur et le démarre.
   * 
   * @param args Arguments de la ligne de commande (ignorés).
   * @throws IOException Si une erreur d'entrée/sortie se produit lors du démarrage du serveur.
   * @throws InterruptedException Si le thread principal est interrompu pendant l'attente de l'arrêt du serveur.
   */
    public static void main(String[] args) throws IOException, InterruptedException {
        final MessengerServer server = new MessengerServer();
        server.start();
        server.blockUntilShutdown();
    }
}
