package org.somthing.yellow.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangshizhe on 2018/8/23
 */
public class RpcProvider {
    private static Executor executor = new ThreadPoolExecutor(2, 5, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>());

    public static void provider() {

        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(4080));

            while (true) {
                executor.execute(new Task(serverSocket.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private static class Task implements Runnable {

        Socket socket = null;

        public Task(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            ObjectInputStream inputStream = null;
            ObjectOutputStream outputStream = null;
            try {
                inputStream = new ObjectInputStream(socket.getInputStream());
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                String interfaceName = inputStream.readUTF();
                String methodName = inputStream.readUTF();

                Class service = Class.forName(interfaceName);
                Class[] paramTypes = (Class[]) inputStream.readObject();
                Object[] params = (Object[]) inputStream.readObject();

                Method method = service.getMethod(methodName, paramTypes);
                Object result = method.invoke(service.newInstance(), params);

                outputStream.writeObject(result);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
