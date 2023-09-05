package main;

public class Constants {

    public static class PlayerConstants {
        public static final int     DIE = 0;
        public static final int     WIN = 1;
        public static final int     WALKINGDOWN = 2;
        public static final int     WALKINGUP = 3;
        public static final int     WALKINGLEFT = 4;
        public static final int     WALKINGRIGHT = 5;
        public static final int     IDLEDOWN = 6;
        public static final int     IDLEUP = 7;
        public static final int     IDLELEFT = 8;
        public static final int     IDLERiGHT = 9;

        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
                case WALKINGDOWN:
                case WALKINGUP:
                case WALKINGLEFT:
                case WALKINGRIGHT:
                    return 8;
                case IDLEDOWN:
                case IDLEUP:
                case IDLELEFT:
                case IDLERiGHT:
                    return 4;
                case WIN:
                    return 6;
                case DIE:
                    return 15;
                default:
                    return 1;
            }
        }
    }

}