import java.util.Random;

public class Referee {
    // 1: White wins   0: Continue to play   -1: Black wins   -100: Draw
    public static int checkWinningCondition(PieceInfo pi) {
        if (checkHorizontallyAndVertically(pi) || checkDiagonal(pi)) {
            return pi.getColor();
        }
        else if (!checkIfBlankExist()) {
            return -100;
        }
        else {
            return 0;
        }
    }

    public static boolean checkIfBlankExist() {
        for (int x = 0; x < Constants.getOrder(); x++) {
            for (int y = 0; y < Constants.getOrder(); y++) {
                if (Pieces.getInstance().getPieceValue(x, y) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkHorizontallyAndVertically(PieceInfo pi) {
        int pX = pi.getX();
        int pY = pi.getY();
        int pC = pi.getColor();

        // check horizontally
        int lowX = pX;
        int highX = pX;

        for ( ; (lowX >= 1) && (Pieces.getInstance().getPieceValue(lowX - 1, pY) == Pieces.getInstance().getPieceValue(pX, pY)); lowX--) {
        }

        for ( ; (highX < Constants.getOrder() - 1) && (Pieces.getInstance().getPieceValue(highX + 1, pY) == Pieces.getInstance().getPieceValue(pX, pY)); highX++) {
        }
        if (highX - lowX >= 4) {
            Pieces.getInstance().setWinningPieceInfo(new PieceInfo(lowX, pY, pC), new PieceInfo(highX, pY, pC));
            return true;
        }



        // check Vertically
        int lowY = pY;
        int highY = pY;

        for ( ; (lowY >= 1) && (Pieces.getInstance().getPieceValue(pX, lowY - 1) == Pieces.getInstance().getPieceValue(pX, pY)); lowY--) {
        }

        for ( ; (highY < Constants.getOrder() - 1) && (Pieces.getInstance().getPieceValue(pX, highY + 1) == Pieces.getInstance().getPieceValue(pX, pY)); highY++) {
        }
        if (highY - lowY >= 4) {
            Pieces.getInstance().setWinningPieceInfo(new PieceInfo(pX, lowY, pC), new PieceInfo(pX, highY, pC));
            return true;
        }

        // otherwise
        return false;
    }

    public static boolean checkDiagonal(PieceInfo pi) {
        int pX = pi.getX();
        int pY = pi.getY();
        int pC = pi.getColor();

        // check '\' diagonal
        int lowX = pX;
        int lowY = pY;

        int highX = pX;
        int highY = pY;

        for ( ; (lowX >= 1) && (lowY >= 1) && (Pieces.getInstance().getPieceValue(lowX - 1, lowY - 1) == Pieces.getInstance().getPieceValue(pX, pY)); lowX--, lowY--) {
        }

        for (; (highX < Constants.getOrder() - 1) && (highY < Constants.getOrder() - 1) && (Pieces.getInstance().getPieceValue(highX + 1, highY + 1) == Pieces.getInstance().getPieceValue(pX, pY)); highX++, highY++) {
        }
        if (highX - lowX >= 4) {
            Pieces.getInstance().setWinningPieceInfo(new PieceInfo(lowX, lowY, pC), new PieceInfo(highX, highY, pC));
            return true;
        }


        // check '/' diagonal
        lowX = pX;
        highY = pY;

        highX = pX;
        lowY = pY;

        for ( ; (lowX >= 1) && (highY < Constants.getOrder() - 1) && (Pieces.getInstance().getPieceValue(lowX - 1, highY + 1) == Pieces.getInstance().getPieceValue(pX, pY)); lowX--, highY++) {
        }

        for ( ; (highX < Constants.getOrder() - 1) && (lowY >= 1) && (Pieces.getInstance().getPieceValue(highX + 1, lowY - 1) == Pieces.getInstance().getPieceValue(pX, pY)); highX++, lowY--) {
        }
        if (highX - lowX >= 4) {
            Pieces.getInstance().setWinningPieceInfo(new PieceInfo(lowX, highY, pC), new PieceInfo(highX, lowY, pC));
            return true;
        }

        // otherwise
        return false;
    }
}


// TODO passable

// TODO add ban rules support