package JavaFX.Almas_progs.Tetris;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static JavaFX.Almas_progs.Tetris.TetrisApp.TILE_SIZE;

/** *   https://www.youtube.com/watch?v=mi8nIed9460&index=4&list=PL4h6ypqTi3RTIoPa_Qz3haEo3OXJQqOwc
 *      https://github.com/AlmasB/FXTutorials/tree/master/src/com/almasb/tetris                      */

public class Tetromino {

    public int x, y;

    public Color color;

    public List<Piece> pieces;

    public Tetromino(Color color, Piece... pieces) {
        this.color = color;
        this.pieces = new ArrayList<>(Arrays.asList(pieces));

        for (Piece piece : this.pieces)
            piece.setParent(this);
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;

        pieces.forEach(p -> {
            p.x += dx;
            p.y += dy;
        });
    }

    public void move(Direction direction) {
        move(direction.x, direction.y);
    }

    public void draw(GraphicsContext g) {
        g.setFill(color);

        pieces.forEach(p -> g.fillRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE, TILE_SIZE));
    }

    public void rotateBack() {
        pieces.forEach(p -> p.setDirection(p.direction.prev()));
    }

    public void rotate() {
        pieces.forEach(p -> p.setDirection(p.direction.next()));
    }

    public void detach(int x, int y) {
        pieces.removeIf(p -> p.x == x && p.y == y);
    }

    public Tetromino copy() {
        return new Tetromino(color, pieces.stream()
                .map(Piece::copy)
                .collect(Collectors.toList())
                .toArray(new Piece[0]));
    }
}
