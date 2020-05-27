import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Сервер запущен! Слушаю 80-ый порт!");
            try {
                ServerSocket ss = new ServerSocket(80);
                while (true) {
                    Socket soc = ss.accept();
                    InputStream input = soc.getInputStream();
                    OutputStream output = soc.getOutputStream();
                        byte[] BytesArray = new byte[255];
                        input.read(BytesArray);
                        String DataString = new String(BytesArray);
                        System.out.println("Приняты новые данные:" + DataString);
                    
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
    }
}
