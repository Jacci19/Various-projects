/*
 * Przykłady kod potrzebny do nawiązania połączenia sieciowego w
 * programie-kliencie napisanym w języku C.
 */

/* BEGIN main */
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>

int
main(int argc, char *argv[])
{
    char* server_name = "localhost";
    struct hostent *host_info;
    int sock;
    struct sockaddr_in server;

    /* Sprawdzamy adres IP zdalnego komputera */
    host_info = gethostbyname(server_name);
    if (host_info == NULL) {
        fprintf(stderr, "%s: nieznany host: %s\n", argv[0], server_name);
        exit(1);
    }

    /* Tworzymy gniazdo */
    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
        perror("tworzenie gniazda klienta");
        exit(2);
    }

    /* konfiguracja adresu gniazda na serwerze */
    server.sin_family = AF_INET;
    memcpy((char *)&server.sin_addr, host_info->h_addr,
                     host_info->h_length);
    server.sin_port = htons(80);

    /* nawiązanie połączenia z serwerem */
    if (connect(sock,(struct sockaddr *)&server,sizeof server) < 0) {
        perror("nawiązujemy połączenie z serwerem");
        exit(4);
    }

    /* teraz, w końcu, można odczytywać i zapisywać dane, używając gniazda */
    /* ... */

    (void) close(sock);
}
/* END main */
