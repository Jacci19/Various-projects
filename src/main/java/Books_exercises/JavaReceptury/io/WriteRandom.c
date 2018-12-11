/* BEGIN main */
/* Program napisany w języku C tworzący plik, który jest używany
 * w przykładzie demonstrującym wykorzystanie klasy RandomAccessFile.
 * Ian F. Darwin, http://www.darwinsys.com/
 */

#include <stdio.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <machine/endian.h>

const off_t OFFSET = 1234;    // off_t został zdefiniowany przy użyciu
                        // "typedef", zazwyczaj odpowiada on long integer
const char* FILENAME = "random.dat";
const int MODE = 0644;
const char* MESSAGE = "Zobaczyłeś i znalazłeś!\r\n";

int
main(int argc, char **argv) {
    int fd;
    int java_offset;

    if ((fd = creat(FILENAME, MODE)) < 0) {
        perror(FILENAME);
        return 1;
    }

    /* Klasy DataStreams itp. w Javie zostały zdefiniowane tak,
     * że operują na danych, których bajty są zapisywane w takiej
     * samej kolejności jak w sieci. */
    java_offset = htonl(OFFSET);

    if (write(fd, &java_offset, sizeof java_offset) < 0) {
        perror("zapis");
        return 1;
    }

    if (lseek(fd, OFFSET, SEEK_SET) < 0) {
        perror("przesuwanie");
        return 1;
    }

    if (write(fd, MESSAGE, strlen(MESSAGE)) != strlen(MESSAGE)) {
        perror("zapis 2");
        return 1;
    }

    if (close(fd) < 0) {
        perror("zamykamy!?");
        return 1;
    }

    return 0;
}
/* END main */
