package Design_Patterns.Observer.YTChannel;

/**     https://www.youtube.com/watch?v=RVZ-wsfpcaw     */

public class Client{

    public static void main(String[] args) {

        YTChannel ytChannel = new YTChannel();

        YTUser ytUser = new YTUser("Matt");
        ytChannel.register(ytUser);

        /**
         *
         *
         *
         *
         */

        ytChannel.publishNewVideo();
        System.out.println("------------------");

        YTUser ytUser2 = new YTUser("Patt");
        ytChannel.register(ytUser2);
        ytChannel.publishNewVideo();
        System.out.println("------------------");

        ytChannel.unregister(ytUser);
        ytChannel.publishNewVideo();
        System.out.println("------------------");
    }

}