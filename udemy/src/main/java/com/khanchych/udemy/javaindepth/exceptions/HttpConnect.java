package com.khanchych.udemy.javaindepth.exceptions;

import java.io.IOException;

public class HttpConnect {
    /**
     *
     * @param destination
     * @param data
     * @param partner
     * @return
     * @throws IOException
     */
    public static String send(int destination, String data, String partner) throws IOException {
        System.out.println("\nInside send ... ");
        String response = "success";

        if (destination < 0 || destination > 1){
            throw new IllegalArgumentException();
        }
        if (destination == 0){
            throw new IllegalArgumentException();
        }
        else if (destination == 1){
            response = "<result><code>success</code></result>";
        }

        System.out.println("\nEnd of send ... ");
        return response;
    }
}
